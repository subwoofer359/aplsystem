package org.amc.servlet;

import static org.amc.servlet.ControllerConstants.ERRORS;
import static org.amc.servlet.ControllerConstants.FORM;
import static org.amc.servlet.ControllerConstants.MATERIAL;
import static org.amc.servlet.ControllerConstants.MATERIALS;
import static org.amc.servlet.ControllerConstants.MODE;
import static org.amc.servlet.ControllerConstants.MODE_ADD;
import static org.amc.servlet.ControllerConstants.MODE_EDIT;
import static org.amc.servlet.ControllerConstants.MODE_ENTER;
import static org.amc.servlet.ControllerConstants.PROCESS;
import static org.amc.servlet.ControllerConstants.SEARCH;

import org.amc.Constants.Roles;
import org.amc.DAOException;
import org.amc.model.Material;
import org.amc.servlet.action.MaterialActionFactory;
import org.amc.servlet.action.MaterialActionFactoryImpl;
import org.amc.servlet.action.SaveMaterialAction;
import org.amc.servlet.action.SearchMaterialAction;
import org.amc.servlet.action.search.MaterialSearch;
import org.amc.servlet.model.MaterialForm;
import org.amc.servlet.model.MaterialSearchForm;
import org.amc.servlet.validator.MaterialForm_Validator;
import org.amc.servlet.validator.MaterialSearchValidator;
import org.amc.servlet.validator.MaterialSearchBinder;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Material Web Controller
 * 
 * @author Adrian Mclaughlin
 * @version 2
 */

@Controller
public class APLMaterialController {

    private static final Logger LOG = Logger.getLogger(APLMaterialController.class);

    private MaterialActionFactory materialActionFactory;
    
    private static MaterialSearchBinder materialSearchBinder;

    static final String SESSION_MATERIALSEARCH = "MATERIALSEARCH";

    // Views
    static final String MATERIAL_ADD_EDIT_VIEW = "/WEB-INF/JSP/Material.jsp";
    private static final String MATERIAL_SEARCH_VIEW = "/app/material/Material_search";
    private static final String MAIN_VIEW = "/WEB-INF/JSP/Main.jsp";
    static final String MATERIAL_SEARCH_PAGE = "MaterialSearchPage";
    
    
        //"Material_save"
        //"Material_display"
        //"Material_search"
        //"MaterialServlet"
       //MaterialServlet

    private List<String> validateMaterialForm(MaterialForm jForm){
        MaterialForm_Validator validator = new MaterialForm_Validator();
        return validator.validate(jForm);
    }
    
    /**
     * Saving a new Material
     * @param request
     * @param jForm
     * @param mode
     */
    @RequestMapping("/SaveNewMaterial")
    public ModelAndView saveNewMaterial(HttpServletRequest request,
                    @ModelAttribute(MATERIAL) MaterialForm jForm)
                    throws DAOException {

        ModelAndView mav = new ModelAndView();

        List<String> errors = validateMaterialForm(jForm);

        if (!(request.isUserInRole(Roles.QC.toString()) || (request.isUserInRole(Roles.MANAGER
                        .toString())))) {
            errors.add("User has no permissions to alter Material definitions!");
        }

        if (errors.isEmpty()) {
            SaveMaterialAction action = materialActionFactory.getSaveMaterialAction();

            Material material = MaterialForm.getMaterial(jForm);
            action.save(material);
            mav.setViewName("redirect:" + MATERIAL_SEARCH_VIEW);

        } else {

            mav.getModel().put(ERRORS, errors);
            mav.getModel().put(FORM, jForm);
            mav.setViewName(MATERIAL_ADD_EDIT_VIEW);
        }
        return mav;
    }

    @RequestMapping("/SaveEditedMaterial")
    public ModelAndView saveEditedMaterial(HttpServletRequest request,
                    @ModelAttribute(MATERIAL) MaterialForm jForm)
                    throws DAOException {

        ModelAndView mav = new ModelAndView();

        List<String> errors = validateMaterialForm(jForm);

        if (!(request.isUserInRole(Roles.QC.toString()) || (request.isUserInRole(Roles.MANAGER
                        .toString())))) {
            errors.add("User has no permissions to alter Material definitions!");
        }

        if (errors.isEmpty()) {
            SaveMaterialAction action = materialActionFactory.getSaveMaterialAction();

            Material material = MaterialForm.getMaterial(jForm);
            action.edit(material);
            mav.setViewName("redirect:" + MATERIAL_SEARCH_VIEW);

        } else {

            mav.getModel().put(ERRORS, errors);
            mav.getModel().put(FORM, jForm);
            mav.setViewName(MATERIAL_ADD_EDIT_VIEW);
        }
        return mav;
    }

    @RequestMapping("/Material_search")
    public ModelAndView searchForMaterial(HttpServletRequest request,
                    @ModelAttribute MaterialSearchForm materialForm, HttpSession session)
                    throws DAOException {
        ModelAndView mav = new ModelAndView();
        Map<Integer, Material> list = null;

        List<String> errors = validateMaterialSearchForm(materialForm);

        if (errors.isEmpty()) {
            try {
                list=searchForMaterial(session, materialForm);
            } catch (ParseException pe) {
                mav.getModel().put(ControllerConstants.MESSAGE,
                                "Search Parameters couldn't be parsed");
                list = new HashMap<Integer, Material>();
            }

        } else {
            mav.getModel().put(ControllerConstants.MESSAGE, errors);
        }
        mav.getModel().put(MATERIALS, list);
        mav.setViewName(MATERIAL_SEARCH_PAGE);

        return mav;
    }
    
    private Map<Integer, Material> searchForMaterial(HttpSession session,MaterialSearchForm materialForm) 
                    throws DAOException,ParseException{
        MaterialSearch materialSearch=null;
        if (materialForm.isEmpty()) {
            return searchDatabaseWithSavedMaterialSearch(session);
        } else {
            materialSearch=createMaterialSearch(materialForm);
            synchronized (session) {
                session.setAttribute(SESSION_MATERIALSEARCH,materialSearch);
            }
            return searchDatabaseWithMaterialSearch(materialSearch);
        }
        
    }
    
    private List<String> validateMaterialSearchForm(MaterialSearchForm materialForm){
        MaterialSearchValidator validator = new MaterialSearchValidator();
        return validator.validate(materialForm);
    }
    
    private Map<Integer, Material> searchDatabaseWithSavedMaterialSearch(HttpSession session)throws DAOException,ParseException{
        
        synchronized (session) {
            if (session.getAttribute(SESSION_MATERIALSEARCH) == null) {
                return new HashMap<Integer, Material>();
            } else {
                MaterialSearch materialSearch = (MaterialSearch) session
                                .getAttribute(SESSION_MATERIALSEARCH);
                return searchDatabaseWithMaterialSearch(materialSearch);
            }
        }
    }
    
    private MaterialSearch createMaterialSearch(MaterialSearchForm materialForm) throws ParseException{
        return this.materialSearchBinder.getMaterialSearch(materialForm);
    }
    
    private Map<Integer, Material> searchDatabaseWithMaterialSearch(MaterialSearch materialSearch)
                    throws DAOException,ParseException{
        SearchMaterialAction spt = materialActionFactory.getSearchMaterialAction();
        return spt.search(materialSearch);
    }
   
    @RequestMapping("/AddMaterial")
    public ModelAndView addMaterial(HttpServletRequest request){
        ModelAndView mav=new ModelAndView();
        mav.setViewName(MATERIAL_ADD_EDIT_VIEW);
        Material material=new Material();
        mav.getModel().put(ControllerConstants.FORM, material);
        
        return mav;
    }
    
    @RequestMapping("/EditMaterial")
    public ModelAndView editMaterial(HttpServletRequest request,@RequestParam("edit") String id)throws ServletException{
        ModelAndView mav=new ModelAndView();
        mav.setViewName(MATERIAL_ADD_EDIT_VIEW);
        try{
            SearchMaterialAction spt=this.materialActionFactory.getSearchMaterialAction();
            Material material=spt.getMaterial(id);
            mav.getModel().put(ControllerConstants.FORM, material);
        }catch(DAOException de){
            de.printStackTrace();
            throw (ServletException) new ServletException("Database not available:"
                            + de.getMessage()).initCause(de);
        }
        return mav;
    }
    
    
    @Resource(name = "myMaterialActionFactory")
    public void setMaterialActionFactory(MaterialActionFactory materialActionFactory) {
        this.materialActionFactory = materialActionFactory;
    }
    
    @Resource(name="myMaterialSearchbinder")
    public void setMaterialSearchBinder(MaterialSearchBinder materialSearchBinder) {
        this.materialSearchBinder=materialSearchBinder;
    }
}
