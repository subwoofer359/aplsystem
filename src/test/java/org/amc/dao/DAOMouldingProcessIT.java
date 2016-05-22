package org.amc.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.amc.DAOException;
import org.amc.dao.DAO;
import org.amc.model.MouldingProcess;
import org.amc.myservlet.test.spc.DatabaseFixture;
import org.amc.myservlet.test.spc.TestSPCFixture;
import org.amc.servlet.action.SearchProcessSheetAction;
import org.amc.servlet.action.search.MouldingProcessSearch;
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
public class DAOMouldingProcessIT {

    private static final DatabaseFixture dbFixture = new DatabaseFixture();
    private TestSPCFixture fixture;
    
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
        fixture = new TestSPCFixture();
        fixture.setupPartTable();
        fixture.setUpMouldingProcessTable();
    }
 

    /**
     * To check that given a PartSearch Object the correct Parts are returned
     * from the database
     */
    @Test
    public void testFindEntitiesSearch() {
        MouldingProcessSearch search = new MouldingProcessSearch();
        search.setSignedOffBy("John Malone");
        DAO<MouldingProcess> mpDAO = new DAO<MouldingProcess>(MouldingProcess.class);

        SearchProcessSheetAction action = new SearchProcessSheetAction(mpDAO);

        try {
            List<MouldingProcess> result = action.search(search);
            assertNotNull(result);
            assertEquals(result.size(), 4);
        } catch (DAOException de) {
            de.printStackTrace();
            fail("DAOException thrown");
        }

        search.setMachineNo("Fanuc 1");

        try {
            List<MouldingProcess> result = action.search(search);
            assertNotNull(result);
            assertEquals(result.size(), 1);
        } catch (DAOException de) {
            de.printStackTrace();
            fail("DAOException thrown");
        }

        // Reset Search
        search = new MouldingProcessSearch();
        search.setStartDate(java.sql.Date.valueOf("2013-05-11"));
        try {
            List<MouldingProcess> result = action.search(search);
            assertNotNull(result);
            assertEquals(result.size(), 1);
        } catch (DAOException de) {
            de.printStackTrace();
            fail("DAOException thrown");
        }

        search.setStartDate(java.sql.Date.valueOf("2000-01-01"));
        search.setEndDate(java.sql.Date.valueOf("2014-01-01"));
        try {
            List<MouldingProcess> result = action.search(search);
            assertNotNull(result);
            assertEquals(result.size(), 3);
        } catch (DAOException de) {
            de.printStackTrace();
            fail("DAOException thrown");
        }

        search.setSignedOffBy("John Malone");
        try {
            List<MouldingProcess> result = action.search(search);
            // assertNotNull(result);
            // assertEquals(result.size(), 3);
        } catch (DAOException de) {
            de.printStackTrace();
            fail("DAOException thrown");
        }

    }

    @Test
    public void testFindEntitiesSearch_EmptyPartSearch() {
        MouldingProcessSearch search = new MouldingProcessSearch();
        ;
        DAO<MouldingProcess> mpDAO = new DAO<MouldingProcess>(MouldingProcess.class);

        SearchProcessSheetAction action = new SearchProcessSheetAction(mpDAO);

        try {
            List<MouldingProcess> result = action.search(search);
            assertNotNull(result);
            assertEquals(result.size(), 0);
        } catch (DAOException de) {
            de.printStackTrace();
            fail("DAOException thrown");
        }
    }

}
