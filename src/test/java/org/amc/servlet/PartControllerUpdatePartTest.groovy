package org.amc.servlet

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.amc.DAOException;
import org.amc.dao.DAO;
import org.amc.model.Part;
import org.amc.servlet.action.PartActionFactory;
import org.amc.servlet.action.SavePartAction;
import org.amc.servlet.action.SearchPartAction;
import org.amc.servlet.validator.PartSearchFormValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

class PartControllerUpdatePartTest {
    PartsController controller;
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
    PartActionFactory partActionFactory;
    
    @Mock
    DAO<Part> jobDAO;
    
    @Mock
    SavePartAction savePartAction;
    
    @Mock
    PartSearchFormValidator searchFormValidator;
    
    
    @Mock
    BindingResult errors;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        
        when(partActionFactory.getSaveJobTemplateAction()).thenReturn(savePartAction);
        
        controller = new PartsController();
        controller.partActionFactory = partActionFactory;
        controller.searchFormValidator = searchFormValidator;
    }
    
    @Test
    void partUpdateTest() {
        ModelAndView mav = controller.updatePart(part, errors);
        verify(savePartAction, times(1)).edit(part);
        ModelAndViewAssert.assertViewName(mav, PartsController.VIEW_SEARCH_PAGE);
    }
    
    @Test
    void partInvalidUpdateTest() {
        part.company = null;
        when(errors.hasErrors()).thenReturn(true);
        ModelAndView mav = controller.updatePart(part, errors);
        
        
        verify(savePartAction, never()).edit(part);
        ModelAndViewAssert.assertViewName(mav, PartsController.VIEW_PART_PAGE);
        ModelAndViewAssert.assertModelAttributeAvailable(mav, PartsController.ERRORS);
        ModelAndViewAssert.assertModelAttributeAvailable(mav, 'form');
        ModelAndViewAssert.assertModelAttributeValue(mav, 'mode', PartsController.EDIT_MODE);
    }
    
    @Test(expected = ServletException)
    void partUpdateThrowsServletExceptionTest() {
        when(errors.hasErrors()).thenReturn(false);
        when(savePartAction.edit(part)).thenThrow(new DAOException("Test exception"));
        
        ModelAndView mav = controller.updatePart(part, errors);
        
        verify(savePartAction, times(1)).edit(part);
        ModelAndViewAssert.assertViewName(mav, PartsController.VIEW_SEARCH_PAGE);
    }
}
