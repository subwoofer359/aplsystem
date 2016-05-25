package org.amc.servlet

import org.amc.DAOException;
import org.amc.model.Part;
import org.amc.servlet.action.SavePartAction;
import org.amc.servlet.action.SearchPartAction;
import org.amc.servlet.action.search.PartSearch;
import org.amc.servlet.model.PartSearchForm;
import org.amc.servlet.validator.PartSearchFormValidator;
import org.amc.servlet.validator.PartSearchFormValidator.PartSearchBinder;
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
import org.apache.openjpa.jdbc.kernel.exps.Param;
import org.junit.runner.Request;

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
class PartsController {
    
    static final String SEARCH = 'search';
    static final String ERRORS = 'errors';
    static final String SESSION_PARTSEARCH = 'PARTSEARCH';
    static final String COMPANY = 'company';
    static final String PARTNAME = 'partName';
    static final String QSS_NUMBER = 'qssNumber';
    static final String MODEL_ATTR_PARTS = 'parts';
    static final String VIEW_SEARCH_PAGE = 'PartsSearchPage';
    static final String VIEW_PART_PAGE = 'Part';
    static final String SEARCH_PARSE_ERROR_MSG = 'Search Parameters couldn\'t be parsed';

    @Resource(name = 'partActionFactory')
    def partActionFactory;
    
    @Resource(name = 'partSearchFormValidator')
    PartSearchFormValidator searchFormValidator;
    
    def partFormParser = PartSearchBinder.&getPartSearch;
    
    private static final Logger logger = Logger.getLogger(PartsController);
    
    @RequestMapping(method = RequestMethod.GET, value = "/APLSystemServlet")
    String getAPLSystemServlet() {
        return "Main";
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "logout")
    String logout(HttpSession session, HttpServletRequest httpServletRequest) {
        session?.invalidate();
        httpServletRequest.logout();
        
        return "redirect:/";
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "Part_display")
    String displayPart() {
        return VIEW_PART_PAGE;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/Part_search") 
    String displaySearch() {
        return VIEW_SEARCH_PAGE;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/Part_search", params="mode=search")
    ModelAndView searchForPart(HttpSession session, HttpServletRequest request) {
        
        PartSearch lastPartSearch = session.getAttribute(SESSION_PARTSEARCH);
        
        ModelAndView mav = new ModelAndView();
        mav.setViewName(VIEW_SEARCH_PAGE);
        
        SearchPartAction spa = partActionFactory.getSearchJobTemplateAction();
        List parts = Collections.EMPTY_LIST;
        
        def partSearchForm = new PartSearchForm();
        partSearchForm.setCompany(request.getParameter(COMPANY));
        partSearchForm.setPartName(request.getParameter(PARTNAME));
        partSearchForm.setQSSNumber(request.getParameter(QSS_NUMBER));
        
        searchFormValidator.validate(partSearchForm);
        
        if (searchFormValidator.hasErrors()) {
            mav.getModel().put(ControllerConstants.MESSAGE, searchFormValidator.getErrors());
        } else if (partSearchForm.isEmpty()) {
            parts = lastPartSearch ? spa.search(lastPartSearch) : Collections.EMPTY_LIST;
        } else {
           try {
               def partSearch = partFormParser(partSearchForm);
               parts = spa.search(partSearch);
               mav.getModel().put(SESSION_PARTSEARCH, partSearch);
           } catch(ParseException pe) {
               mav.getModel().put(ControllerConstants.MESSAGE, SEARCH_PARSE_ERROR_MSG);
           }
           
        }
        mav.getModel().put(MODEL_ATTR_PARTS, parts);
        return mav;
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
        mav.model[ControllerConstants.MESSAGE] = 'Can\'t edit Part';
        return mav;
    }
    
    private ModelAndView storePartInModel(ModelAndView mav, String idValue) {
        try {
            SearchPartAction spa = partActionFactory.getSearchJobTemplateAction();
            mav.model.form = spa.getPart(idValue);
            mav.model.mode = 'edit Part';
            mav.setViewName(VIEW_PART_PAGE);
        } catch(DAOException de) {
            setErrorMsg(mav);
        } finally {
            return mav;
        }
    }
    
    @RequestMapping(method = RequestMethod.GET, value = '/Part_search', params='mode=add')
    String addPart(Part part) {
        return VIEW_PART_PAGE;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = '/Part_save', params='mode=Enter')
    ModelAndView savePart(@ModelAttribute Part part, BindingResult errors) {
        ModelAndView mav = new ModelAndView();
        mav.viewName = VIEW_PART_PAGE;
        SavePartAction spa = partActionFactory.getSaveJobTemplateAction();
        Validator validator = new PartValidator();
        validator.validate(part, errors);
        if(errors.hasErrors()) {
            mav.model.form = part;
            mav.model[ERRORS] = errors;
        } else {
            try {
                spa.save(part);
                mav.model.form = part;
                mav.model.result = "${part} saved";
                mav.model.remove(ERRORS);
            } catch(DAOException de) {
                throw (ServletException) new ServletException("Database not available: ${de.message}").initCause(de);
            } catch(NumberFormatException ne) {
                throw new ServletException(ne);
            }
        }
        return mav;
    }
}
