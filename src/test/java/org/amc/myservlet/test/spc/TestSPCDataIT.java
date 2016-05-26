package org.amc.myservlet.test.spc;

import static org.junit.Assert.*;

import java.util.List;


import org.amc.DAOException;
import org.amc.dao.DAO;
import org.amc.model.Part;
import org.amc.model.User;
import org.amc.model.spc.SPCData;
import org.amc.model.spc.SPCMeasurement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestSPCDataIT {
    private static final DatabaseFixture dbFixture = new DatabaseFixture();
    private TestSPCFixture fixture;

    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        dbFixture.setUp();
    }
    
    public void setTables() throws DAOException {
        // Create part in table to use in test
        // TestSPCMeasurment.createPartFromDataBase(factory);

        // Create SPCMeasurement in table to use in test
        DAO<SPCMeasurement> measurementDao = new DAO<SPCMeasurement>(SPCMeasurement.class);
        SPCMeasurement measurement = new SPCMeasurement();
        measurement.setActive(true);
        measurement.setDimension("length");
        measurement.setLowerLimit(0.3f);
        measurement.setUpperLimit(0.3f);
        measurement.setNominal(152f);
        measurement.setNoOfMeasurements(5);

        // Retrieve Part entity from the database
        DAO<Part> partDAO = new DAO<Part>(Part.class);
        List<Part> parts = partDAO.findEntities();
        Part part = null;
        if (parts.size() > 0) {
            part = parts.get(0);
            measurement.setPart(part);
            measurementDao.addEntity(measurement);
        } else {
            throw new DAOException();
        }
    }

    @Before
    public void setUp() throws Exception {
        dbFixture.clearTables();
        fixture = new TestSPCFixture();
        fixture.setupPartTable();
        fixture.setUpUserTable();
        setTables();
    }

    @After
    public void tearDown() throws Exception {
        fixture = null;
    }
    
    @AfterClass
    public static void tearDownAfterClass() {
        dbFixture.tearDown();
    }

    @Test
    public void test() throws DAOException {
        DAO<SPCMeasurement> measurementDao = new DAO<SPCMeasurement>(SPCMeasurement.class);
        DAO<SPCData> spcdataDao = new DAO<SPCData>(SPCData.class);
        DAO<User> userDao = new DAO<User>(User.class);
        SPCMeasurement measurement = measurementDao.findEntities().get(0);
        User user = userDao.findEntities().get(0);

        SPCData d1 = new SPCData();
        d1.setDate(new java.sql.Date(System.currentTimeMillis()));
        d1.setMeasurement(123f);
        d1.setSpcMeasurement(measurement);
        d1.setUser(user);

        spcdataDao.addEntity(d1);
        assertTrue(d1.getId() != 0);
    }

}
