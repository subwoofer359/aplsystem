package org.amc.servlet

import org.amc.model.MouldingProcess;
import org.amc.servlet.action.search.MouldingProcessSearch
import org.amc.servlet.validator.MyDateFormatter;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes
import org.springframework.web.servlet.ModelAndView

import com.gargoylesoftware.htmlunit.javascript.configuration.WebBrowser

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.Resource
import javax.servlet.http.HttpSession
import javax.validation.Valid;

import org.amc.servlet.action.ActionFactory;

@Controller
@SessionAttributes('PROCESSSEARCH')
class MouldingProcessSearchController extends GenericSearchController<MouldingProcess, MouldingProcessSearch>{
    
    static final String SESSION_PROCESSSEARCH = 'PROCESSSEARCH';
    
    static final String MODEL_ATTR_PARTS = 'processSheets';
    
    static final String VIEW_PART_PAGE = 'ProcessPage';
    
    static final String VIEW_SEARCH_PAGE = 'ProcessSheetSearchPage';
    
    static final String ERROR_PAGE_EDIT = 'Can\'t edit Process Sheet';
    
    
    @InitBinder(['mouldingProcessSearch', 'PROCESSSEARCH'])
    void initBinder(WebDataBinder binder) {
        binder.addCustomFormatter(new MyDateFormatter());
        binder.
        
    }
    
    
    @PostConstruct
    void init() {
        this.sessionSearchName = SESSION_PROCESSSEARCH;
        this.itemsName = MODEL_ATTR_PARTS;
        this.searchPage = VIEW_SEARCH_PAGE;
        this.itemView = VIEW_PART_PAGE;
        this.errorEditFailMessage = ERROR_PAGE_EDIT;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = '/ProcessSheet_search')
    String displaySearch() {
        return VIEW_SEARCH_PAGE;
    }
   
    @RequestMapping(method = RequestMethod.POST, value = '/ProcessSheet_search', params = 'mode=search')
    public ModelAndView searchForProcessSheet(HttpSession session, @Valid @ModelAttribute MouldingProcessSearch search,
                    BindingResult errors) {
        return super.searchForItem(session, search, errors);
    }

    
    @RequestMapping(method = RequestMethod.POST, value = '/ProcessSheet_search', params = 'mode=edit')
    @Override
    public ModelAndView editPage(String idValue) {
        return super.editPage(idValue);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = '/ProcessSheet_search', params = 'mode=add')
    String addProcessSheet() {
        return itemView;
    }
    
    @Resource(name = 'processActionFactory')
    void setProcessActionFactory(ActionFactory<MouldingProcess, MouldingProcessSearch> processActionFactory) {
        this.actionFactory = processActionFactory;
    }
}
