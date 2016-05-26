package org.amc.servlet

import org.amc.DAOException;
import org.amc.model.Part;
import org.amc.servlet.action.SavePartAction;
import org.amc.servlet.action.SearchPartAction;
import org.amc.servlet.action.search.PartSearch;
import org.amc.servlet.model.PartSearchForm;
import org.amc.servlet.validator.PartSearchFormValidator;
import org.amc.servlet.validator.PartValidator;
import org.amc.servlet.validator.Part_Validator;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.apache.log4j.Logger;

import java.text.NumberFormat;
import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

/**
 * Spring Controller for handling Part operations and returning a view
 * @author Adrian Mclaughlin
 * @version 2
 */
@SessionAttributes("PARTSEARCH")
@Controller
class PartsSearchController extends PartsController {
    @Resource(name = 'partActionFactory')
    def partActionFactory;
    
    @Resource(name = 'partSearchFormValidator')
    PartSearchFormValidator searchFormValidator;
    
    private static final Logger logger = Logger.getLogger(PartsSearchController);
    
    @RequestMapping(method = RequestMethod.GET, value = "/APLSystemServlet")
    String getAPLSystemServlet() {
        return VIEW_MAIN_PAGE;
    }
    
    @RequestMapping(value = "/logout")
    String logout(HttpSession session, HttpServletRequest httpServletRequest) {
        session?.invalidate();
        httpServletRequest.logout();
        
        return "redirect:/";
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/Part_display")
    String displayPart() {
        return VIEW_PART_PAGE;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/Part_search") 
    String displaySearch() {
        return VIEW_SEARCH_PAGE;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/Part_search", params="mode=search")
    ModelAndView searchForPart(HttpSession session, 
        @ModelAttribute PartSearch partSearch, BindingResult errors) {
        
        ModelAndView mav = new ModelAndView();
        
        mav.setViewName(VIEW_SEARCH_PAGE);
        
        List parts = Collections.EMPTY_LIST;
        
        if (errors.hasErrors()) {
            mav.getModel().put(ControllerConstants.MESSAGE, errors);
        } else if (partSearch.isEmpty()) {
            parts = useLastSearchParameters(session);
        } else {
            parts = doSearchForPart(mav, partSearch);
        }
        mav.getModel().put(MODEL_ATTR_PARTS, parts);
        return mav;
    }
        
    private List useLastSearchParameters(HttpSession session) {
        PartSearch lastPartSearch = session.getAttribute(SESSION_PARTSEARCH);
        SearchPartAction spa = partActionFactory.getSearchJobTemplateAction();
        return lastPartSearch ? spa.search(lastPartSearch) : Collections.EMPTY_LIST;
    }
    
    private List doSearchForPart(ModelAndView mav, PartSearch partSearch) {
        try {
            SearchPartAction spa = partActionFactory.getSearchJobTemplateAction();
            def parts = spa.search(partSearch);
            mav.getModel().put(SESSION_PARTSEARCH, partSearch);
            return parts;
        } catch(ParseException pe) {
            mav.getModel().put(ControllerConstants.MESSAGE, SEARCH_PARSE_ERROR_MSG);
        }
    }
    
    @RequestMapping(method = RequestMethod.POST, value = '/Part_search', params="mode=edit Part")
    ModelAndView editPart(@NotNull @RequestParam('edit') String idValue) {
        ModelAndView mav = new ModelAndView();
        if(idValue == null || ''.equals(idValue)) {
            return setErrorMsg(mav);
        }
        return storePartInModel(mav, idValue);
    }
    
    private ModelAndView setErrorMsg(ModelAndView mav) {
        mav.setViewName(VIEW_SEARCH_PAGE);
        mav.model[ControllerConstants.MESSAGE] = ERROR_PAGE_EDIT;
        return mav;
    }
    
    private ModelAndView storePartInModel(ModelAndView mav, String idValue) {
        try {
            SearchPartAction spa = partActionFactory.getSearchJobTemplateAction();
            mav.model.form = spa.getPart(idValue);
            mav.model.mode = EDIT_MODE;
            mav.setViewName(VIEW_PART_PAGE);
        } catch(DAOException de) {
            setErrorMsg(mav);
        } finally {
            return mav;
        }
    }
    
    @RequestMapping(method = RequestMethod.POST, value = '/Part_search', params='mode=add Part')
    String addPart(Part part) {
        return VIEW_PART_PAGE;
    }
}
