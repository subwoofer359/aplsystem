package org.amc.servlet

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.amc.dao.DAO
import org.amc.model.Material;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Before
import org.junit.Test;
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration([ '/EntityManagerFactory.groovy', '/ProcessContext.groovy', '/MaterialContext.groovy' ])
class MouldProcessSearchCtrIT {
    static final String SEARCH_URL = '/ProcessSheet_search'; 
    
    @Autowired
    WebApplicationContext wac;
    
    MockMvc mockMvc;
    
    @Before
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        DAO<Material> materialDAO = (DAO)wac.getBean('MaterialDAO');
        Material m = new Material();
        m.company = 'Exxon Mobil';
        m.name = 'PolyPropolene';
        m.density = '1';
        m.type = 'pp';
        materialDAO.addEntity(m);
    }
    
    @Test
    void searchTest() {
        MvcResult result = mockMvc.perform(post(SEARCH_URL)
            .param('mode', 'search')
            .param('partId', '%')
            .param('startDate', '2016-01-01')
            .param('endDate', '')
            //.param('material', '1')
        )
            .andExpect(model().hasNoErrors())
            .andDo(print())
            .andReturn();
            
            
    }
    
}
