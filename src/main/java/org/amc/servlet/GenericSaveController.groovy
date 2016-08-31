package org.amc.servlet

import org.amc.DAOException;
import org.amc.model.Material
import org.amc.model.WorkEntity;
import org.amc.servlet.action.ActionFactory;
import org.amc.servlet.action.SaveAction
import org.amc.servlet.action.search.WebFormSearch;
import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.validation.Valid;

abstract class GenericSaveController<T extends WorkEntity,S extends WebFormSearch> {

    private static final Logger logger = Logger.getLogger(GenericSaveController);
    
    private ActionFactory<T, S> actionFactory;
    
    ModelAndView save(@Valid @ModelAttribute T item, BindingResult errors) {
        ModelAndView mav = new ModelAndView();
        
        logger.debug("Errors: ${errors}");
        
        if(errors.hasErrors()) {
            mav.model.form = item;
            mav.model[ERRORS] = errors;
            mav.setViewName(MATERIAL_ADD_EDIT_VIEW);
        } else {
            try {
                SaveAction<T> saveAction = actionFactory.getSaveAction();
                saveAction.save(item);
                mav.setViewName(REDIRECT_MATERIAL_SEARCH);
            } catch(DAOException de) {
                throw new ServletException(ERROR_DAO).initCause(de);
            }
        }
        return mav;
    }


    ModelAndView update(@Valid @ModelAttribute T item, BindingResult errors) {
        ModelAndView mav = new ModelAndView();
        if(errors.hasErrors()) {
            mav.model.form = item;
            mav.model[ERRORS] = errors;
            mav.model[MODE] = MODE_EDIT;
            mav.setViewName(MATERIAL_ADD_EDIT_VIEW);
        } else {
            try {
                SaveAction<T> saveAction = actionFactory.getSaveAction();
                saveAction.edit(item);
                mav.setViewName(REDIRECT_MATERIAL_SEARCH);
            } catch(DAOException de) {
                throw new ServletException(ERROR_DAO).initCause(de);
            }
        }

        return mav;
    }
}
