package org.amc.servlet

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.amc.DAOException;
import org.amc.dao.DAO;
import org.amc.model.Part
import org.amc.servlet.action.ActionFactory;
import org.amc.servlet.action.SaveAction
import org.amc.servlet.action.search.PartSearch;
import org.amc.servlet.validator.PartSearchFormValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

class PartsControllerSavePartTest {
    PartsModifyController controller;
    def part = new Part(
        'BMW Case',
        'bmw10202',
        'ALPS',
        'right hand',
        'v1',
        'Beige',
        true,
        'ALPS 001'
        );
    @Mock
    HttpSession session;
    
    @Mock
    HttpServletRequest httpRequest;
    
    @Mock
    ActionFactory<Part, PartSearch> partActionFactory;
    
    @Mock
    DAO<Part> jobDAO;
    
    @Mock
    SaveAction<Part> savePartAction;
    
    @Mock
    BindingResult errors;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(partActionFactory.getSaveAction()).thenReturn(savePartAction);
        controller = new PartsModifyController();
        controller.init();
        controller.partActionFactory = partActionFactory;
    }
    
    @Test
    void testSavePartEnterMode() { 
        ModelAndView mav = controller.savePart(part, errors);
        verify(savePartAction, times(1)).save(part);
        ModelAndViewAssert.assertViewName(mav, PartsModifyController.REDIRECT_SEARCH);
        assert mav.model[ControllerConstants.ERRORS] == null;
        
    }
    
    @Test(expected=ServletException)
    void testSavePartEnterModeThrowsDAOException() {
        when(savePartAction.save(any(Part))).thenThrow(new DAOException('Test Exception'));
        ModelAndView mav = controller.savePart(part, errors);
    }
    
    @Test(expected=ServletException)
    void testSavePartEnterModeThrowsNumberFormatException() {
        when(savePartAction.save(any(Part))).thenThrow(new DAOException('Test Exception'));
        ModelAndView mav = controller.savePart(part, errors);
    }
    
    @Test
    void testSavePartEnterModeNotValid() {
        when(errors.hasErrors()).thenReturn(true);
        ModelAndView mav = controller.savePart(part, errors);
        ModelAndViewAssert.assertModelAttributeAvailable(mav, PartsSearchController.ERRORS);
        ModelAndViewAssert.assertModelAttributeAvailable(mav, ControllerConstants.FORM);
        ModelAndViewAssert.assertViewName(mav, PartsSearchController.VIEW_PART_PAGE);
    }
}
