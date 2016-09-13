package org.amc.servlet

import org.amc.model.MouldingProcess;
import org.amc.servlet.action.ActionFactory
import org.amc.servlet.action.SaveAction;
import org.amc.servlet.action.search.MouldingProcessSearch;
import org.junit.Before;
import org.junit.BeforeClass
import org.junit.Test;
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.validation.BindingResult
import org.springframework.web.servlet.ModelAndView;

import static org.mockito.Mockito.*;

class MouldProcessSaveControllerTest {
    MouldingProcessSaveController controller;
    
    @Mock
    ActionFactory<MouldingProcess, MouldingProcessSearch> actionFactory;
    
    @Mock
    SaveAction<MouldingProcess> saveAction;
    
    @Mock
    BindingResult errors;
    
    MouldingProcess process;
    
    @Before
    void setup() {
        MockitoAnnotations.initMocks(this);
        controller = new MouldingProcessSaveController();
        controller.processActionFactory = actionFactory;
        controller.init();
        
        when(actionFactory.getSaveAction()).thenReturn(saveAction);
        
        process = new MouldingProcess();
    }
    
    
    @Test
    void saveTest() {
        when(errors.hasErrors()).thenReturn(false);
        ModelAndView mav = controller.save(process, errors);
        ModelAndViewAssert.assertViewName(mav, MouldingProcessSaveController.REDIRECT_SEARCH);
    }

}
