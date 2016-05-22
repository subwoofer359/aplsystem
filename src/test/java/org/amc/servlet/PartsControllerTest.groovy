package org.amc.servlet;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


class PartsControllerTest {
    PartsController controller;

    @Before
    public void setUp() throws Exception {
        controller = new PartsController();
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
        controller.logout();
    }

}
