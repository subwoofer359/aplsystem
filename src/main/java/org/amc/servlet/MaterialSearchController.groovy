package org.amc.servlet

import org.amc.DAOException;
import org.amc.model.Material
import org.amc.servlet.action.ActionFactory
import org.amc.servlet.action.SearchAction;
import org.amc.servlet.action.search.MaterialSearch
import org.amc.servlet.action.search.SearchFields;
import org.apache.log4j.Logger
import org.apache.openjpa.kernel.exps.This;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid
import javax.validation.constraints.NotNull;

@Controller
@SessionAttributes('MATERIALSEARCH')
class MaterialSearchController extends GenericSearchController<Material, MaterialSearch> {
    
    private static final Logger logger = Logger.getLogger(MaterialSearchController.class);
    
    @PostConstruct
    void init() {
        this.searchPage = 'MaterialSearchPage';
        this.itemsName = 'materials';
        this.sessionSearchName = 'MATERIALSEARCH';
        this.itemView = 'Material';
        this.errorEditFailMessage = 'Can\'t edit material';
    }
    
    @RequestMapping(value = ['/MaterialServlet', '/Material_search'], method = RequestMethod.GET)
    ModelAndView getMaterialServlet(HttpSession session) {
        if(session.getAttribute(this.sessionSearchName) != null) {
            return searchForMaterial(session, new MaterialSearch(), [hasErrors: {return false}] as BindingResult)
        } else {
            ModelAndView mav = new ModelAndView();
            mav.viewName = this.searchPage;
            return mav;
        }
    }
        
    @RequestMapping(value = '/Material_search', method = RequestMethod.POST, params = 'mode=search')
    ModelAndView searchForMaterial(HttpSession session, @Valid @ModelAttribute MaterialSearch materialSearch,
            BindingResult errors) {
            
        return super.searchForItem(session, materialSearch, errors);
    }
            
    @RequestMapping(method = RequestMethod.POST, value = '/Material_search', params='mode=add')
    String addMaterial() {
        return this.itemView;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = '/Material_search', params='mode=edit')
    ModelAndView editMaterial(@NotNull @RequestParam('edit') String idValue) {
       return super.editPage(idValue);
    }
    
    @Resource(name = 'materialActionFactory')
    void setMaterialActionFactory(ActionFactory<Material, MaterialSearch> materialActionFactory){
        this.actionFactory = materialActionFactory;
    }
}
