package org.amc.servlet

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.amc.dao.DAO;
import org.amc.model.Part;
import org.amc.servlet.action.PartActionFactory;
import org.amc.servlet.action.SearchPartAction;
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
    
    @Test
    void testAddPart() {
        def view = controller.addPart(part);
        assert view == PartsSearchController.VIEW_PART_PAGE;
    }
    
}
