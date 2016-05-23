package org.amc.servlet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.amc.dao.DAO;
import org.amc.model.Part;
import org.amc.servlet.action.PartActionFactory;
import org.amc.servlet.action.SearchPartAction;
import org.amc.servlet.action.search.PartSearch;
import org.amc.servlet.validator.PartSearchFormValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


class PartsControllerTest {
    PartsController controller;
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
        controller = new PartsController();
        controller.partActionFactory = partActionFactory;
        controller.searchFormValidator = searchFormValidator;
        
        when(httpRequest.getParameter('company')).thenReturn(company);
        when(httpRequest.getParameter('partName')).thenReturn(partName);
        when(httpRequest.getParameter('QSSNumber')).thenReturn(qssNumber);
        
        when(searchFormValidator.hasErrors()).thenReturn(false);
        
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetAPLSystemServlet() {
        String view = controller.getAPLSystemServlet();
        assert view == "Main";
    }
    
    @Test
    public void testLogOut() {
        def redirect = controller.logout(session, httpRequest);
        verify(session, times(1)).invalidate();
        verify(httpRequest, times(1)).logout();
        
        assert redirect == "redirect:/";
        
    }
    
    @Test
    public void testLogOutNoSession() {
        controller.logout(null, httpRequest);
        verify(session, times(0)).invalidate();
    }
    
    @Test
    public void testDisplayPart() {
        String view = controller.displayPart();
        assert view == "Part";   
    }
    
    @Test
    public void testSearchForPartSearchMode() {       
        def parts = [new Part(partName, '3432', company, '3', '2', 'BLACK', true, qssNumber)];
        when(searchPartAction.search(any())).thenReturn(parts);
        
        ModelAndView mav = controller.searchForPart(httpRequest, null, mode, edit);
        ModelAndViewAssert.assertViewName(mav, "PartsSearchPage");
        ModelAndViewAssert.assertModelAttributeAvailable(mav, "parts");
        ModelAndViewAssert.assertModelAttributeValue(mav, "parts", parts);
        
        
    }
    
    @Test
    public void testSearchForPartBlankSearchNoSessionAttribute() {
        def parts = [new Part(partName, '3432', company, '3', '2', 'BLACK', true, qssNumber)];
        when(searchPartAction.search(any())).thenReturn(parts);
        reset(httpRequest);
        
        ModelAndView mav = controller.searchForPart(httpRequest, null, mode, edit);
        ModelAndViewAssert.assertViewName(mav, "PartsSearchPage");
        ModelAndViewAssert.assertModelAttributeAvailable(mav, "parts");
        ModelAndViewAssert.assertModelAttributeValue(mav, "parts", []);  
    }
    
    @Test
    public void testSearchForPartBlankSearchWithSessionAttribute() {
        def parts = [new Part(partName, '3432', company, '3', '2', 'BLACK', true, qssNumber)];
        when(searchPartAction.search(any())).thenReturn(parts);
        reset(httpRequest);
        
        PartSearch lastSearch = new PartSearch();
        lastSearch.company = company;
        lastSearch.partName = partName;
        lastSearch.QSSNumber = qssNumber;
        
        ModelAndView mav = controller.searchForPart(httpRequest, lastSearch, mode, edit);
        ModelAndViewAssert.assertViewName(mav, "PartsSearchPage");
        ModelAndViewAssert.assertModelAttributeAvailable(mav, "parts");
        ModelAndViewAssert.assertModelAttributeValue(mav, "parts", parts);
    }
    
    @Test
    public void testNotValidSubmission() {
        def parts = [];
        when(searchPartAction.search(any())).thenReturn(parts);
        reset(searchFormValidator);
        when(searchFormValidator.hasErrors()).thenReturn(true);
        when(searchFormValidator.getErrors()).thenReturn([]);
        
        ModelAndView mav = controller.searchForPart(httpRequest, null, mode, edit);
        ModelAndViewAssert.assertViewName(mav, "PartsSearchPage");
        ModelAndViewAssert.assertModelAttributeAvailable(mav, "parts");
        ModelAndViewAssert.assertModelAttributeValue(mav, "parts", parts);
        ModelAndViewAssert.assertAndReturnModelAttributeOfType(mav, ControllerConstants.MESSAGE, List); 
    }
    
    @Test
    public void testSearchParseException() {
        def parts = [];
        when(searchPartAction.search(any())).thenReturn(parts);
        def newParser = { throw new ParseException('Test Parse Error', 1)};
        controller.partFormParser = newParser;
        
        ModelAndView mav = controller.searchForPart(httpRequest, null, mode, edit);
        ModelAndViewAssert.assertViewName(mav, "PartsSearchPage");
        ModelAndViewAssert.assertModelAttributeAvailable(mav, "parts");
        ModelAndViewAssert.assertModelAttributeValue(mav, "parts", parts);
        ModelAndViewAssert.assertModelAttributeValue(mav, ControllerConstants.MESSAGE, PartsController.SEARCH_PARSE_ERROR_MSG);
    }

}
