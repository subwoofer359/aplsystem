package org.amc.myservlet.test.spc;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.amc.DAOException;
import org.amc.dao.DAO;
import org.amc.model.Part;
import org.amc.model.spc.SPCMeasurement;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestSPCMeasurmentIT {
    private TestSPCFixture fixture;
    private static final DatabaseFixture dbFixture = new DatabaseFixture();
    private DAO<Part> partDAO;
    private DAO<SPCMeasurement> dao;

    @Test
    public void testSPCMeasurement() throws DAOException {
        
        
        
        SPCMeasurement measurement = new SPCMeasurement();
        measurement.setActive(true);
        measurement.setDimension("length");
        measurement.setLowerLimit(0.3f);
        measurement.setUpperLimit(0.3f);
        measurement.setNominal(152f);
        measurement.setNoOfMeasurements(5);

        // Retrieve Part entity from the database

        List<Part> parts = partDAO.findEntities();
        Part part = null;
        if (parts.size() > 0) {
            part = parts.get(0);
        }
        measurement.setPart(part);
        // Save SPCMeasurement entity to database

        dao.addEntity(measurement);

        // Retrieve SPCMeasurement entity from database
        SPCMeasurement spc = dao.getEntity(measurement.getId());

        // Check some properties
        assertEquals(spc.getDimension(), measurement.getDimension());
        assertEquals(spc.getNominal(), measurement.getNominal(), 0.1);
        assertEquals(spc.getUpperLimit(), measurement.getUpperLimit(), 0.1);
    }
    
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
        
        EntityManager enManager = dbFixture.getNewEntityManager(); 
        dao = new DAO<SPCMeasurement>(SPCMeasurement.class);
        dao.setEntityManager(enManager);
        
        partDAO = new DAO<Part>(Part.class);
        partDAO.setEntityManager(enManager);
                       
    }
}
