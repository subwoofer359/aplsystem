package org.amc.dao.parsers

import org.amc.dao.DAO;
import org.amc.model.Part;
import org.amc.myservlet.test.spc.DatabaseFixture
import org.amc.servlet.action.search.PartSearch;
import org.junit.After
import org.junit.AfterClass;
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test;
    
class PartSearchParserTest {
    static DatabaseFixture fixture = new DatabaseFixture();
    static SearchParserFixture mouldFixture;
    DAO<Part> partDAO;
    PartSearch search;
    
    @Test
    void searchCompanyTest() {
        search.company = 'Forest Tosara';
        
        List results = partDAO.findEntities(search);
        assert results.size() == 2;
        
        search.company = 'ALPS';
        results = partDAO.findEntities(search);
        
        assert results.isEmpty();      
    }
    
    @Test
    void searchQSSNumberTest() {
        search.QSSNumber = 'QSS: T024';
        
        List results = partDAO.findEntities(search);
        assert results.size() == 1;  
    }
    
    @Test
    void searchPartNameTest() {
        search.partName = 'Pot';
        
        List results = partDAO.findEntities(search);
        assert results.size() == 2;
        
        search.partName = 'lid';
        results = partDAO.findEntities(search);
        
        assert results.isEmpty();
    }
    
    @BeforeClass
    static void setUpBeforeClass() {
        fixture.setUp();
        mouldFixture = new SearchParserFixture(fixture.getNewEntityManager());
        mouldFixture.setup();
    }
    
    @AfterClass
    static void tearDownAfterClass() {
        fixture.tearDown();
    }
    
    @Before
    void setup() {
        partDAO = new DAO<Part>(Part);
        partDAO.entityManager = fixture.entityManager;
        
        search = new PartSearch();
    }
}
