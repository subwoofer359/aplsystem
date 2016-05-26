package org.amc.servlet

import org.amc.DAOException;
import org.amc.model.Part;
import org.amc.servlet.action.SavePartAction;
import org.amc.servlet.validator.PartValidator;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.validation.Valid;

@Controller
class PartsModifyController extends PartsController {
    private static final Logger logger = Logger.getLogger(PartsModifyController);
    
    @Resource(name = 'partActionFactory')
    def partActionFactory;
    
    @InitBinder("part")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new PartValidator());
    }
    
    @RequestMapping(method = RequestMethod.POST, value = '/Part_save', params='mode=Edit')
    ModelAndView updatePart(@Valid @ModelAttribute Part part, BindingResult errors) {
        ModelAndView mav = new ModelAndView();
        mav.viewName = VIEW_SEARCH_PAGE;
        if(errors.hasErrors()) {
            mav.model.form = part;
            mav.model[ERRORS] = errors;
            mav.model.mode = EDIT_MODE
            mav.viewName = VIEW_PART_PAGE;
        } else {
            try {
                SavePartAction spa = partActionFactory.getSaveJobTemplateAction();
                spa.edit(part);
            } catch(DAOException de) {
                throw (ServletException) new ServletException(ERROR_DAO).initCause(de);
            }
        }
        return mav;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = '/Part_save', params='mode=Enter')
    ModelAndView savePart(@Valid @ModelAttribute Part part, BindingResult errors) {
        ModelAndView mav = new ModelAndView();
        mav.viewName = VIEW_PART_PAGE;
        SavePartAction spa = partActionFactory.getSaveJobTemplateAction();
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
                throw (ServletException) new ServletException(ERROR_DAO).initCause(de);
            } catch(NumberFormatException ne) {
                throw new ServletException(ne);
            }
        }
        return mav;
    }
}
