package org.amc.servlet

import org.amc.DAOException;

import org.amc.model.WorkEntity;
import org.amc.servlet.action.ActionFactory;
import org.amc.servlet.action.SaveAction
import org.amc.servlet.action.search.WebFormSearch;
import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.validation.Valid;

abstract class GenericSaveController<T extends WorkEntity,S extends WebFormSearch> {

    public enum View {
        ITEM_VIEW,
        REDIRECT_SEARCH
    };

    static final String FORM = 'form';
    static final String ERRORS = 'errors';
    static final String ERROR_DAO = 'Database not available: ${de.message}';
    static final String MODE = 'mode';
    static final String MODE_EDIT = 'edit';
    
    private static final Logger logger = Logger.getLogger(GenericSaveController);
    
    ActionFactory<T, S> actionFactory;
    
    private Map<View, String> views = new HashMap<View, String>();
    
    ModelAndView save(@Valid @ModelAttribute T item, BindingResult errors) {
        ModelAndView mav = new ModelAndView();
        
        logger.debug("Errors: ${errors}");
        
        if(errors.hasErrors()) {
            mav.model[FORM] = item;
            mav.model[ERRORS] = errors;
            mav.setViewName(views[View.ITEM_VIEW]);
        } else {
            try {
                SaveAction<T> saveAction = actionFactory.getSaveAction();
                saveAction.save(item);
                mav.setViewName(views[View.REDIRECT_SEARCH]);
            } catch(DAOException de) {
                throw new ServletException(ERROR_DAO).initCause(de);
            }
        }
        return mav;
    }


    ModelAndView update(@Valid @ModelAttribute T item, BindingResult errors) {
        ModelAndView mav = new ModelAndView();
        if(errors.hasErrors()) {
            mav.model[FORM] = item;
            mav.model[ERRORS] = errors;
            mav.model[MODE] = MODE_EDIT;
            mav.setViewName(views[View.ITEM_VIEW]);
        } else {
            try {
                SaveAction<T> saveAction = actionFactory.getSaveAction();
                saveAction.edit(item);
                mav.setViewName(views[View.REDIRECT_SEARCH]);
            } catch(DAOException de) {
                throw new ServletException(ERROR_DAO).initCause(de);
            }
        }

        return mav;
    }
    
    void setView(View view, String viewName) {
        this.views.put(view, viewName);
    }
}
