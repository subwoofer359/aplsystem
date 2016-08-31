package org.amc.servlet

import org.amc.DAOException;
import org.amc.model.Material;
import org.amc.model.WorkEntity;
import org.amc.servlet.action.ActionFactory;
import org.amc.servlet.action.SearchAction;
import org.amc.servlet.action.search.MaterialSearch;
import org.amc.servlet.action.search.WebFormSearch;
import org.apache.log4j.Logger
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

class GenericSearchController<T extends WorkEntity, S extends WebFormSearch> {
    
    private static final Logger logger = Logger.getLogger(GenericSearchController);
    
    static final String FORM = 'form';
    static final String ERRORS = 'errors';
    static final String ERROR_DAO = 'Database not available: ${de.message}';
    static final String MODE = 'mode';
    static final String MODE_EDIT = 'edit';
    
    String sessionSearchName;
    
    String searchPage;
    
    String itemsName;
    
    String itemView;
    
    String errorEditFailMessage;
    
    ActionFactory<T, S> actionFactory;
    
    ModelAndView searchForItem(HttpSession session, @Valid @ModelAttribute S search,
        BindingResult errors) {
        
        ModelAndView mav = new ModelAndView();
    
        logger.debug('search:' + search.name);
        logger.debug("Errors: ${errors}");
    
        mav.setViewName(searchPage);
    
        List<T> items = Collections.EMPTY_LIST;
   
        if(errors.hasErrors()) {
            mav.model[ControllerConstants.MESSAGE] = errors;
        } else if(search.isEmpty()) {
            items = useLastSearchParameters(session);
        } else {
            items = doSearchForItem(mav, search);
        }
    
        mav.model[itemsName] = items;
        logger.debug('Items retrieved  = ' + items);
        return mav;
    }
        
    private List useLastSearchParameters(HttpSession session) {
        S saveSearch = session.getAttribute(sessionSearchName);
        SearchAction<T, S> searchMAction = actionFactory.getSearchAction();
        return saveSearch ? searchMAction.search(saveSearch) : Collections.EMPTY_LIST;
    }
    
    private List doSearchForItem(ModelAndView mav, S search) {
        try {
            SearchAction<T, S> searchAction = actionFactory.getSearchAction();
            def items = searchAction.search(search);
            mav.model[sessionSearchName] = search;
            return items;
        } catch (DAOException de) {
            throw new ServletException("Database not available:"
                + de.getMessage()).initCause(de);
        }
    }
    
    ModelAndView editPage(@NotNull @RequestParam('edit') String idValue) {
        ModelAndView mav = new ModelAndView();
        if(idValue == null || ''.equals(idValue)) {
            return setErrorMsg(mav);
        }
        return storeItemInModel(mav, idValue);
    }
    
    private ModelAndView storeItemInModel(ModelAndView mav, String idValue) {
        try {
            SearchAction<T, S> sma = actionFactory.getSearchAction();
            mav.model[FORM] = sma.get(idValue);
            mav.model[MODE] = MODE_EDIT;
            mav.viewName = itemView;
        } catch (DAOException de) {
            setErrorMsg(mav);
        } finally {
            return mav;
        }
    }
    
    private ModelAndView setErrorMsg(ModelAndView mav) {
        mav.setViewName(itemView);
        mav.model[ControllerConstants.MESSAGE] = errorEditFailMessage;
        return mav;
    }
}
