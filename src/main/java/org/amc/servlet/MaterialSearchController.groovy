package org.amc.servlet

import org.amc.DAOException;
import org.amc.model.Material
import org.amc.servlet.action.ActionFactory
import org.amc.servlet.action.SearchAction;
import org.amc.servlet.action.search.MaterialSearch
import org.amc.servlet.action.search.SearchFields;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid
import javax.validation.constraints.NotNull;

@Controller
@SessionAttributes('MATERIALSEARCH')
class MaterialSearchController extends MaterialController {
    
    private static final Logger logger = Logger.getLogger(MaterialSearchController.class);
    
    @Resource(name = 'materialActionFactory')
    ActionFactory<Material, MaterialSearch> materialActionFactory;
    
    @RequestMapping(value = ['/MaterialServlet', '/Material_search'], method = RequestMethod.GET)
    ModelAndView getMaterialServlet(HttpSession session) {
        if(session.getAttribute(SESSION_MATERIALSEARCH) != null) {
            return searchForMaterial(session, new MaterialSearch(), [hasErrors: {return false}] as BindingResult)
        } else {
            ModelAndView mav = new ModelAndView();
            mav.viewName = MATERIAL_SEARCH_PAGE;
            return mav;
        }
    }
        
    @RequestMapping(value = '/Material_search', method = RequestMethod.POST, params = 'mode=search')
    ModelAndView searchForMaterial(HttpSession session, @Valid @ModelAttribute MaterialSearch materialSearch,
            BindingResult errors) {
            
        ModelAndView mav = new ModelAndView();
        
        logger.debug('material search:' + materialSearch.name);
        logger.debug("Errors: ${errors}");
        
        mav.setViewName(MATERIAL_SEARCH_PAGE);
        
        List<Material> materials = Collections.EMPTY_LIST;
       
        if(errors.hasErrors()) {
            mav.model[ControllerConstants.MESSAGE] = errors;  
        } else if(materialSearch.isEmpty()) {
            materials = useLastSearchParameters(session);
        } else {
            materials = doSearchForMaterial(mav, materialSearch);
        }
        
        mav.model[ControllerConstants.MATERIALS] = materials;
        logger.debug('MATERIALS retrieved  = ' + materials);
        return mav;
    }
            
    private List useLastSearchParameters(HttpSession session) {
        MaterialSearch saveSearch = session.getAttribute(SESSION_MATERIALSEARCH);
        SearchAction<Material, MaterialSearch> searchMAction = materialActionFactory.getSearchAction();
        return saveSearch ? searchMAction.search(saveSearch) : Collections.EMPTY_LIST;
    }
    
    private List doSearchForMaterial(ModelAndView mav, MaterialSearch materialSearch) {
        try {
            SearchAction<Material, MaterialSearch> searchMAction = materialActionFactory.getSearchAction();
            def materials = searchMAction.search(materialSearch);
            mav.model[SESSION_MATERIALSEARCH] = materialSearch;
            return materials;
        } catch (DAOException de) {
            throw new ServletException("Database not available:"
                + de.getMessage()).initCause(de);
        }
    }
    
    @RequestMapping(method = RequestMethod.POST, value = '/Material_search', params='mode=add')
    String addMaterial() {
        return MATERIAL_ADD_EDIT_VIEW;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = '/Material_search', params='mode=edit')
    ModelAndView editMaterial(@NotNull @RequestParam('edit') String idValue) {
        ModelAndView mav = new ModelAndView();
        if(idValue == null || ''.equals(idValue)) {
            return setErrorMsg(mav);   
        }
        return storeMaterialInModel(mav, idValue);
    }
    
    private ModelAndView storeMaterialInModel(ModelAndView mav, String idValue) {
        try {
            SearchAction<Material, MaterialSearch> sma = materialActionFactory.getSearchAction();
            mav.model.form = sma.get(idValue);
            mav.model.mode = MODE_EDIT;
            mav.viewName = MATERIAL_ADD_EDIT_VIEW;
        } catch (DAOException de) {
            setErrorMsg(mav);
        } finally {
            return mav;
        }
    }
    
    private ModelAndView setErrorMsg(ModelAndView mav) {
        mav.setViewName(MATERIAL_ADD_EDIT_VIEW);
        mav.model[ControllerConstants.MESSAGE] = ERROR_MATERIAL_EDIT;
        return mav;
    }

}
