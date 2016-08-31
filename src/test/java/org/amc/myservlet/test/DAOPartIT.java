package org.amc.myservlet.test;

import static org.junit.Assert.*;

import java.util.List;

import org.amc.DAOException;
import org.amc.dao.DAO;
import org.amc.model.Part;
import org.amc.myservlet.test.spc.DatabaseFixture;
import org.amc.myservlet.test.spc.TestSPCFixture;
import org.amc.servlet.action.SearchAction;
import org.amc.servlet.action.search.PartSearch;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * To test the org.amc.dao.DAO class
 * 
 * @author Adrian McLaughlin
 *
 */
public class DAOPartIT {
    
    private static final DatabaseFixture dbFixture = new DatabaseFixture();
    private TestSPCFixture fixture;
    private DAO<Part> partDAO;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        dbFixture.setUp();
    }
    
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        dbFixture.tearDown();
    }
    
    @Before
    public void setUp() throws Exception {
        dbFixture.clearTables();
        fixture = new TestSPCFixture(dbFixture.getNewEntityManager());
        fixture.setupPartTable();
        fixture.setUpMouldingProcessTable();
        
        partDAO = new DAO<Part>(Part.class);
        partDAO.setEntityManager(dbFixture.getNewEntityManager());
    }

    /**
     * To check that given a PartSearch Object the correct Parts are returned
     * from the database
     */
    @Test
    public void testFindEntitiesSearch() {
        PartSearch search = new PartSearch();
        search.setCompany("Apple");

        SearchAction<Part, PartSearch> action = new SearchAction<Part, PartSearch>(partDAO);
        
        try {
            List<Part> result = action.search(search);
            assertNotNull(result);
            assertEquals(result.size(), 2);
        } catch (DAOException de) {
            de.printStackTrace();
            fail("DAOException thrown");
        }

        search.setQSSNumber("A 001");

        try {
            List<Part> result = action.search(search);
            assertNotNull(result);
            assertEquals(result.size(), 1);
        } catch (DAOException de) {
            de.printStackTrace();
            fail("DAOException thrown");
        }

        search.setPartName("Ted");
        try {
            List<Part> result = action.search(search);
            assertNotNull(result);
            assertEquals(result.size(), 0);
        } catch (DAOException de) {
            de.printStackTrace();
            fail("DAOException thrown");
        }

    }

    @Test
    public void testFindEntitiesSearch_EmptyPartSearch() {
        PartSearch search = new PartSearch();

        SearchAction<Part, PartSearch> action = new SearchAction<Part, PartSearch>(partDAO);

        try {
            List<Part> result = action.search(search);
            assertNotNull(result);
            assertEquals(result.size(), 0);
        } catch (DAOException de) {
            de.printStackTrace();
            fail("DAOException thrown");
        }
    }

}
