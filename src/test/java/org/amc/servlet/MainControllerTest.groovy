package org.amc.servlet;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test
import org.mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

class MainControllerTest {
    MainController controller;
    
    @Mock
    HttpSession session;
    
    @Mock
    HttpServletRequest httpRequest;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new MainController();
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
}
