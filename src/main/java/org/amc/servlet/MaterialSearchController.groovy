package org.amc.servlet

import org.amc.DAOException;
import org.amc.model.Material;
import org.amc.servlet.action.MaterialActionFactory;
import org.amc.servlet.action.SearchMaterialAction;
import org.amc.servlet.action.search.MaterialSearch
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.UselessFileDetector;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView

import java.util.Map

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid
import javax.validation.constraints.NotNull;

@Controller
@SessionAttributes("MATERIALSEARCH")
class MaterialSearchController extends MaterialController {
    
    private static final Logger logger = Logger.getLogger(MaterialSearchController.class);
    
    @Resource(name = 'materialActionFactory')
    MaterialActionFactory materialActionFactory;
    
    @RequestMapping(value = ['/MaterialServlet', '/Material_search'], method = RequestMethod.GET)
    String getMaterialServlet() {
        return MATERIAL_SEARCH_PAGE;
    }
        
    @RequestMapping(value = '/Material_search', method = RequestMethod.POST, params = 'mode=search')
    ModelAndView searchForMaterial(HttpSession session, @Valid @ModelAttribute MaterialSearch materialSearch,
            BindingResult errors) {
            
        ModelAndView mav = new ModelAndView();
        
        logger.debug('material search:' + materialSearch.name);
        logger.debug("Errors: ${errors}");
        
        mav.setViewName(MATERIAL_SEARCH_PAGE);
        
        Map<Integer, Material> materials = Collections.EMPTY_MAP;
       
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
            
    private Map useLastSearchParameters(HttpSession session) {
        MaterialSearch saveSearch = session.getAttribute(SESSION_MATERIALSEARCH);
        SearchMaterialAction searchMAction = materialActionFactory.getSearchMaterialAction();
        return saveSearch ? searchMAction.search(saveSearch) : Collections.EMPTY_MAP;
    }
    
    private Map doSearchForMaterial(ModelAndView mav, MaterialSearch materialSearch) {
        try {
            SearchMaterialAction searchMAction = materialActionFactory.getSearchMaterialAction();
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
            SearchMaterialAction sma = materialActionFactory.getSearchMaterialAction();
            mav.model.form = sma.getMaterial(idValue);
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
