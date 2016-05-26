package org.amc.servlet

import static org.amc.servlet.PartsSearchController.MODEL_ATTR_PARTS;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.amc.dao.DAO;
import org.amc.model.Part;
import org.amc.myservlet.test.spc.DatabaseFixture;
import org.amc.servlet.action.search.PartSearch;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration([ "/EntityManagerFactory.groovy", "/PartsContext.groovy" ])
class PartsControllerIT {

    static final Logger logger = Logger.getLogger(PartsControllerIT);
    static DatabaseFixture fixture = new DatabaseFixture();
    static final def COMPANY = 'ALPS';
    static final def PARTSEARCH_URL = '/Part_search';
    static final def PARTSAVE_URL = '/Part_save';
    static final def LOGOUT_URL = '/logout';
    static final def MAINAPP_PAGE = '/APLSystemServlet';
    
    @Autowired
    WebApplicationContext wac;
    
    MockMvc mockMvc;
    
    @BeforeClass
    static void setUpBeforeClass() {
        fixture.setUp();
    }
    
    @Before
    void setUp() throws Exception {
        fixture.clearTables();
        def part = new Part('Test Part', 'id 2334', 'ALPS', 'version 1', 'revision 1', 'Red', true, 'alps 01');
        DAO<Part> partDAO = new DAO<Part>(Part.class);
        partDAO.setEntityManager(fixture.newEntityManager);
        partDAO.addEntity(part);
        
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    
    @AfterClass
    static void afterClass() {
        fixture.tearDown();
    }
    
    @Test
    public void testGetAPLSystemServlet() {
        MvcResult result = mockMvc.perform(get(MAINAPP_PAGE))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name('Main'))
        .andReturn();
    }
    
    @Test
    public void testLogout() {
        MockHttpSession session = new MockHttpSession();
        assertFalse(session.isInvalid());
        
        MvcResult result = mockMvc.perform(get(LOGOUT_URL).session(session))
        .andDo(print())
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name('redirect:/'))
        .andReturn();
    
        assertTrue(session.isInvalid());
    }
    
    @Test
    public void testPartSearch() {
        MockHttpSession session = new MockHttpSession();
        
        MvcResult result = mockMvc.perform(post(PARTSEARCH_URL).session(session).param('mode', 'search')
            .param('company', COMPANY))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name(PartsSearchController.VIEW_SEARCH_PAGE))
        .andExpect(model().attributeExists(MODEL_ATTR_PARTS))
        .andReturn();
    
        ModelAndView mav = result.modelAndView;
        
        List list = mav.getModel().get(MODEL_ATTR_PARTS);
        assert list.size() == 1;
    }
    
    @Test
    public void testBlankFormPartSearch() {
        PartSearch oldPartsSearch = new PartSearch();
        oldPartsSearch.company = COMPANY;
        
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(PartsSearchController.SESSION_PARTSEARCH, oldPartsSearch);
        
        MvcResult result = mockMvc.perform(post(PARTSEARCH_URL).session(session).param('mode', 'search'))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name(PartsSearchController.VIEW_SEARCH_PAGE))
        .andExpect(model().attributeExists(MODEL_ATTR_PARTS))
        .andReturn();
    
        ModelAndView mav = result.modelAndView;
        
        List list = mav.getModel().get(MODEL_ATTR_PARTS);
        assert list.size() == 1;
    }
    
    @Test
    public void testBlankFormAndNoSessionPartSearch() { 
        MvcResult result = mockMvc.perform(post(PARTSEARCH_URL).param('mode', 'search'))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name(PartsSearchController.VIEW_SEARCH_PAGE))
        .andExpect(model().attributeExists(MODEL_ATTR_PARTS))
        .andReturn();
    
        ModelAndView mav = result.modelAndView;
        
        List list = mav.getModel().get(MODEL_ATTR_PARTS);
        assert list.isEmpty() == true;
    }
    
    /**
     * Todo No search parameters are invalid
     * This could change in the future
     */
    @Test
    public void testInvalidPartSearch() {
        
    }
    
    @Test
    public void testSaveNewPart() {
        def name = 'BMW case';
        MvcResult result = mockMvc.perform(post(PARTSAVE_URL)
            .param('mode', 'Enter')
            .param('name', name)
            .param('part_id', 'partID234')
            .param('company', COMPANY)
            .param('version', 'right hand')
            .param('revision', '2')
            .param('colour', 'beige')
            .param('qss_no', 'qss123'))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name(PartsSearchController.VIEW_PART_PAGE))
            .andExpect(model().attributeExists('result'))
            .andExpect(model().attributeExists('form'))
            .andReturn();
            
            Part p = result.modelAndView.model.form;
            assert p != null;
            assert p.name == name;
    }
    
    @Test
    public void testSaveInValidNewPart() {
        def name = 'BMW case';
        MvcResult result = mockMvc.perform(post(PARTSAVE_URL)
            .param('mode', 'Enter')
            .param('name', name)
            .param('part_id', 'partID234')
            .param('version', 'right hand')
            .param('revision', '2')
            .param('colour', 'beige')
            .param('qss_no', 'qss123'))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name(PartsSearchController.VIEW_PART_PAGE))
            .andExpect(model().attributeExists('errors'))
            .andExpect(model().attributeExists('form'))
            .andReturn();     
    }
}
