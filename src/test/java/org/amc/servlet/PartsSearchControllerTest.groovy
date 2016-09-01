package org.amc.servlet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.amc.dao.DAO;
import org.amc.model.Part
import org.amc.servlet.action.ActionFactory
import org.amc.servlet.action.SearchAction;
import org.amc.servlet.action.search.PartSearch;
import org.amc.servlet.validator.PartSearchFormValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


class PartsSearchControllerTest {
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
    ActionFactory<Part, PartSearch> partActionFactory;
    
    @Mock
    DAO<Part> jobDAO;
    
    @Mock
    SearchAction<Part, PartSearch> searchPartAction;
 
    
    @Mock
    BindingResult errors;
    
    PartSearch partSearch;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(partActionFactory.getSearchAction()).thenReturn(searchPartAction);
        controller = new PartsSearchController();
        controller.init();
        controller.partActionFactory = partActionFactory;
        
        partSearch = new PartSearch();
        
        when(httpRequest.getParameter('company')).thenReturn(company);
        when(httpRequest.getParameter('partName')).thenReturn(partName);
        when(httpRequest.getParameter('QSSNumber')).thenReturn(qssNumber);  
    }
 
    @Test
    public void testDisplayPart() {
        String view = controller.displayPart();
        assert view == "Part";   
    }
    
    @Test
    public void testSearchForPartSearchMode() {       
        def parts = [new Part(partName, '3432', company, '3', '2', 'BLACK', true, qssNumber)];
        partSearch.company = company;
        when(searchPartAction.search(any())).thenReturn(parts);
        
        ModelAndView mav = controller.searchForPart(session, partSearch, errors);
        ModelAndViewAssert.assertViewName(mav, "PartsSearchPage");
        ModelAndViewAssert.assertModelAttributeAvailable(mav, "parts");
        ModelAndViewAssert.assertModelAttributeValue(mav, "parts", parts);
        
        
    }
    
    @Test
    public void testSearchForPartBlankSearchNoSessionAttribute() {
        def parts = [new Part(partName, '3432', company, '3', '2', 'BLACK', true, qssNumber)];
        when(searchPartAction.search(any())).thenReturn(parts);
        reset(httpRequest);
        
        
        ModelAndView mav = controller.searchForPart(session, partSearch, errors);
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
        
        when(session.getAttribute(PartsSearchController.SESSION_PARTSEARCH)).thenReturn(lastSearch);
        
        ModelAndView mav = controller.searchForPart(session, partSearch, errors);
        ModelAndViewAssert.assertViewName(mav, "PartsSearchPage");
        ModelAndViewAssert.assertModelAttributeAvailable(mav, "parts");
        ModelAndViewAssert.assertModelAttributeValue(mav, "parts", parts);
    }
}
