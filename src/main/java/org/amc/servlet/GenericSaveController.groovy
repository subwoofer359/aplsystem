package org.amc.servlet

import org.amc.DAOException;

import org.amc.model.WorkEntity;
import org.amc.servlet.action.ActionFactory;
import org.amc.servlet.action.SaveAction
import org.amc.servlet.action.search.WebFormSearch;
import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.servlet.ModelAndView

import java.text.ParseException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.validation.Valid;

abstract class GenericSaveController<T extends WorkEntity,S extends WebFormSearch> {

    /**
     * Page for the Item to be displayed for edit and addition
     */
    String itemView;
    
    /**
     * Redirect to Item Search page
     */
    String redirectSearch;

    static final String FORM = 'form';
    static final String ERRORS = 'errors';
    static final String ERROR_DAO = 'Database not available: ${de.message}';
    static final String MODE = 'mode';
    static final String MODE_EDIT = 'edit';
    
    private static final Logger logger = Logger.getLogger(GenericSaveController);
    
    ActionFactory<T, S> actionFactory;
    
    
    void initBinder(WebDataBinder binder) {
        binder.addCustomFormatter(new MyIdFormatter(), 'id');
    }
    
    ModelAndView save(@Valid @ModelAttribute T item, BindingResult errors) {
        ModelAndView mav = new ModelAndView();
        
        logger.debug("Errors: ${errors}");
        
        if(errors.hasErrors()) {
            mav.model[FORM] = item;
            mav.model[ERRORS] = errors;
            mav.setViewName(itemView);
        } else {
            try {
                SaveAction<T> saveAction = actionFactory.getSaveAction();
                saveAction.save(item);
                mav.setViewName(redirectSearch);
            } catch(DAOException de) {
                throw new ServletException(ERROR_DAO).initCause(de);
            }
        }
        return mav;
    }


    ModelAndView update(@Valid @ModelAttribute T item, BindingResult errors) {
        ModelAndView mav = new ModelAndView();
        if(errors.hasErrors()) {
            mav.model[FORM] = item;
            mav.model[ERRORS] = errors;
            mav.model[MODE] = MODE_EDIT;
            mav.setViewName(itemView);
        } else {
            try {
                SaveAction<T> saveAction = actionFactory.getSaveAction();
                saveAction.edit(item);
                mav.setViewName(redirectSearch);
            } catch(DAOException de) {
                throw new ServletException(ERROR_DAO).initCause(de);
            }
        }

        return mav;
    }
    
    static class MyIdFormatter implements org.springframework.format.Formatter<Integer> {
        
                @Override
                public Integer parse(String text, Locale locale) throws ParseException {
                    if('' == text) {
                        return 0;
                    } else {
                        return Integer.parseInt(text);
                    }
                }
        
                @Override
                public String print(Integer object, Locale locale) {
                    return String.valueOf(object);
                }
                
    }
}
