package org.amc.servlet

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.amc.dao.DAO;
import org.amc.model.Part
import org.amc.servlet.action.ActionFactory
import org.amc.servlet.action.SearchAction;
import org.amc.servlet.action.search.PartSearch;
import org.amc.servlet.validator.PartSearchFormValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

class PartsControllerAddTest {
    PartsSearchController controller;
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
    SearchAction<Part, PartSearch> searchPartAction;
    
    @Mock
    PartSearchFormValidator searchFormValidator;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(partActionFactory.getSearchAction()).thenReturn(searchPartAction);
        controller = new PartsSearchController();
        controller.partActionFactory = partActionFactory;
        controller.searchFormValidator = searchFormValidator;
    }
    
    @Test
    void testAddPart() {
        def view = controller.addPart(part);
        assert view == PartsSearchController.VIEW_PART_PAGE;
    }
    
}
