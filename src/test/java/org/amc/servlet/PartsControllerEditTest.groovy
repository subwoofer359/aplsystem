package org.amc.servlet

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.amc.DAOException;
import org.amc.dao.DAO;
import org.amc.model.Part;
import org.amc.servlet.action.PartActionFactory;
import org.amc.servlet.action.SearchPartAction;
import org.amc.servlet.validator.PartSearchFormValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

class PartsControllerEditTest {
    PartsSearchController controller;
    def mode = 'search';
    def edit = '3';
    def company = 'ALPS';
    def partName = '2304 LightPipe';
    def qssNumber = 'ALPS 234';
    
    @Mock
    HttpSession session;
    
    @Mock
    HttpServletRequest httpRequest;
    
    @Mock
    PartActionFactory partActionFactory;
    
    @Mock
    DAO<Part> jobDAO;
    
    @Mock
    SearchPartAction searchPartAction;
    
    @Mock
    PartSearchFormValidator searchFormValidator;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(partActionFactory.getSearchJobTemplateAction()).thenReturn(searchPartAction);
        controller = new PartsSearchController();
        controller.partActionFactory = partActionFactory;
        controller.searchFormValidator = searchFormValidator;
    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    void testEdit() {
        String idValue = '1';
        ModelAndView mav = controller.editPart(idValue);
        ModelAndViewAssert.assertViewName(mav, 'Part');
        ModelAndViewAssert.assertModelAttributeAvailable(mav, 'form');
        ModelAndViewAssert.assertModelAttributeValue(mav, 'mode', 'edit Part');
    }
    
    @Test
    void testEditIDNull() {
        String idValue = null;
        ModelAndView mav = controller.editPart(idValue);
        ModelAndViewAssert.assertViewName(mav, 'PartsSearchPage');
        ModelAndViewAssert.assertModelAttributeAvailable(mav, ControllerConstants.MESSAGE);
    }
    
    @Test
    void testEditIDEmptyString() {
        String idValue = '';
        ModelAndView mav = controller.editPart(idValue);
        ModelAndViewAssert.assertViewName(mav, 'PartsSearchPage');
        ModelAndViewAssert.assertModelAttributeAvailable(mav, ControllerConstants.MESSAGE);
    }
    
    @Test
    void testEditSearchDAOException() {
        when(searchPartAction.getPart(anyString())).thenThrow(new DAOException("Test Exception"));
        String idValue = '2';
        try {
            ModelAndView mav = controller.editPart(idValue);
            ModelAndViewAssert.assertViewName(mav, 'PartsSearchPage');
            ModelAndViewAssert.assertModelAttributeAvailable(mav, ControllerConstants.MESSAGE);
        } catch(DAOException de) {
            fail("No exception should be thrown to top level");
        }
    }
}