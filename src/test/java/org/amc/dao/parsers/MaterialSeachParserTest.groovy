package org.amc.dao.parsers

import org.amc.dao.DAO
import org.amc.model.Material;
import org.amc.myservlet.test.spc.DatabaseFixture
import org.amc.servlet.action.search.MaterialSearch;
import org.junit.AfterClass
import org.junit.Before;
import org.junit.BeforeClass
import org.junit.Test;


class MaterialSeachParserTest {
    static DatabaseFixture fixture = new DatabaseFixture();
    static SearchParserFixture mouldFixture;
    
    DAO<Material> materialDAO;
    
    MaterialSearch search;
    
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
        materialDAO = new DAO<Material>(Material);
        materialDAO.entityManager = fixture.entityManager;
        
        search = new MaterialSearch();
    }
    
    @Test
    void searchCompanyTest() {
        search.company = 'Sabic';
        
        List results = materialDAO.findEntities(search);
        assert results.size() == 1;
    }
    @Test
    void searchCompanyWildCardTest() {
        search.company = 'S%';
        List results = materialDAO.findEntities(search);
        assert results.size() == 1;
    }
    
    @Test
    void searchCompanyFullWildCardTest() {
        search.company = '%';
        List results = materialDAO.findEntities(search);
        assert results.size() == 2;
    }
    
    @Test
    void searchPartNameTest() {
        search.name = 'High Density PolyPropylene';
        
        List results = materialDAO.findEntities(search);
        assert results.size() == 1;
    }
    
    @Test
    void searchPartNameWildCardTest() {
        search.name = 'H%';
        List results = materialDAO.findEntities(search);
        assert results.size() == 1;
    }
    
    @Test
    void searchPartNameFullWildCardTest() {
        search.name = '%';
        List results = materialDAO.findEntities(search);
        assert results.size() == 2;
    }
    
    @Test
    void searchTypeTest() {
        search.type = 'LDPE';
        List results = materialDAO.findEntities(search);
        assert results.size() == 1;
        
    }
    
}
