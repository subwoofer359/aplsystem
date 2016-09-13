package org.amc.servlet

import org.amc.model.MouldingProcess;
import org.amc.servlet.action.ActionFactory;
import org.amc.servlet.action.search.MouldingProcessSearch;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
class MouldingProcessSaveController extends GenericSaveController<MouldingProcess, MouldingProcessSearch> {

    static final String ITEM_VIEW = 'ProcessPage';
    static final String REDIRECT_SEARCH = 'ProcessSheetSearchPage';
    
    @InitBinder("process")
    public void initBinder(WebDataBinder binder) {
        //binder.addValidators(new PartValidator());
        super.initBinder(binder);
    }
    
    @PostConstruct
    void init() {
        this.itemView = ITEM_VIEW;
        this.redirectSearch = REDIRECT_SEARCH;
    } 
    
    @Override
    @RequestMapping(method = RequestMethod.POST, value = '/ProcessSheet_save', params='mode=Enter')
    ModelAndView save(@Valid @ModelAttribute MouldingProcess process, BindingResult errors) {
        return super.save(process, errors);
    }
    
    @Override
    @RequestMapping(method = RequestMethod.POST, value = '/ProcessSheet_save', params='mode=Edit')
    ModelAndView update(@Valid @ModelAttribute MouldingProcess process, BindingResult errors) {
        return super.update(process, errors);
    }
    
    @Resource(name = 'processActionFactory')
    void setProcessActionFactory(ActionFactory<MouldingProcess, MouldingProcessSearch> processActionFactory) {
        this.actionFactory = processActionFactory;
    }
}
