package org.amc.servlet

import org.amc.DAOException;
import org.amc.model.Part
import org.amc.servlet.action.ActionFactory
import org.amc.servlet.action.PartActionFactoryImpl;
import org.amc.servlet.action.SearchAction;
import org.amc.servlet.action.search.PartSearch;
import org.amc.servlet.model.PartSearchForm;
import org.amc.servlet.validator.PartSearchFormValidator;
import org.amc.servlet.validator.PartValidator;
import org.amc.servlet.validator.Part_Validator;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.apache.log4j.Logger;

import java.text.ParseException

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Spring Controller for handling Part operations and returning a view
 * @author Adrian Mclaughlin
 * @version 2
 */
@SessionAttributes("PARTSEARCH")
@Controller
class PartsSearchController extends GenericSearchController<Part, PartSearch> {
 
    private static final Logger logger = Logger.getLogger(PartsSearchController);
    static final String SESSION_PARTSEARCH = 'PARTSEARCH';
    static final String MODEL_ATTR_PARTS = 'parts';
    static final String VIEW_PART_PAGE = 'Part';
    static final String VIEW_SEARCH_PAGE = 'PartsSearchPage';
    static final String ERROR_PAGE_EDIT = 'Can\'t edit Part';
    
    @PostConstruct
    void init() {
        this.sessionSearchName = SESSION_PARTSEARCH;
        this.itemsName = MODEL_ATTR_PARTS;
        this.searchPage = VIEW_SEARCH_PAGE;
        this.itemView = VIEW_PART_PAGE;
        this.errorEditFailMessage = ERROR_PAGE_EDIT;
    }
    
    @InitBinder('partSearch') 
    void initBinder(WebDataBinder binder) {
        binder.addValidators(new PartSearchFormValidator());
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
    ModelAndView searchForPart(HttpSession session, @Valid @ModelAttribute PartSearch partSearch, BindingResult errors) {
        return super.searchForItem(session, partSearch, errors);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = '/Part_search', params="mode=edit Part")
    ModelAndView editPart(@NotNull @RequestParam('edit') String idValue) {
        return super.editPage(idValue);
    }
        
    @RequestMapping(method = RequestMethod.POST, value = '/Part_search', params='mode=add Part')
    String addPart(Part part) {
        return VIEW_PART_PAGE;
    }
    
    @Resource(name = 'partActionFactory')
    void setPartActionFactory(ActionFactory<Part, PartSearch> partActionFactory) {
        this.actionFactory = partActionFactory;
    }
}
