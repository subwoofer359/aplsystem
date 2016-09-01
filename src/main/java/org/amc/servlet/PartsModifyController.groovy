package org.amc.servlet

import org.amc.DAOException;
import org.amc.model.Part
import org.amc.servlet.action.ActionFactory;
import org.amc.servlet.action.SaveAction
import org.amc.servlet.action.search.PartSearch;
import org.amc.servlet.validator.PartValidator;
import org.apache.log4j.Logger
import org.apache.openjpa.kernel.exps.This;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.validation.Valid;

@Controller
class PartsModifyController extends GenericSaveController<Part, PartSearch> {
    private static final Logger logger = Logger.getLogger(PartsModifyController);
    
    static final String ITEM_VIEW  = 'Part';
    static final String REDIRECT_SEARCH = 'PartsSearchPage';
     
    @InitBinder("part")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new PartValidator());
        super.initBinder(binder);
    }
    
    @PostConstruct
    void init() {
        logger.debug("PartsModifyController init method called");
        this.itemView = ITEM_VIEW;
        this.redirectSearch = REDIRECT_SEARCH;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = '/Part_save', params='mode=Edit')
    ModelAndView updatePart(@Valid @ModelAttribute Part part, BindingResult errors) {
        return super.update(part ,errors);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = '/Part_save', params='mode=Enter')
    ModelAndView savePart(@Valid @ModelAttribute Part part, BindingResult errors) {
        return super.save(part, errors);
    }
    
    @Resource(name = 'partActionFactory')
    void setPartActionFactory(ActionFactory<Part, PartSearch> partActionFactory) {
        this.actionFactory = partActionFactory;
    }
}
