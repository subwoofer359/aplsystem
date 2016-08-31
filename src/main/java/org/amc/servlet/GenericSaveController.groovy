package org.amc.servlet

import org.amc.DAOException;
import org.amc.model.Material
import org.amc.servlet.action.SaveMaterialAction
import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView

import javax.servlet.ServletException
import javax.validation.Valid;

abstract class GenericSaveController<T> {

    private static final Logger logger = Logger.getLogger(GenericSaveController);
    
    ModelAndView save(@Valid @ModelAttribute T item, BindingResult errors) {
        ModelAndView mav = new ModelAndView();
        
        logger.debug("Errors: ${errors}");
        
        if(errors.hasErrors()) {
            mav.model.form = item;
            mav.model[ERRORS] = errors;
            mav.setViewName(MATERIAL_ADD_EDIT_VIEW);
        } else {
            try {
                SaveMaterialAction saveMaterialAction = materialActionFactory.getSaveMaterialAction();
                saveMaterialAction.save(item);
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
                SaveMaterialAction saveMaterialAction = materialActionFactory.getSaveMaterialAction();
                saveMaterialAction.edit(item);
                mav.setViewName(REDIRECT_MATERIAL_SEARCH);
            } catch(DAOException de) {
                throw new ServletException(ERROR_DAO).initCause(de);
            }
        }

        return mav;
    }
}
