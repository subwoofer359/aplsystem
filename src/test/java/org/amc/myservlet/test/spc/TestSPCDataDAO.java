package org.amc.myservlet.test.spc;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.amc.DAOException;
import org.amc.dao.DAO;
import org.amc.dao.SPCDataDAO;
import org.amc.dao.SPCMeasurementDAO;
import org.amc.model.User;
import org.amc.model.spc.SPCData;
import org.amc.model.spc.SPCMeasurement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestSPCDataDAO {
    private TestSPCFixture fixture;
    private static final DatabaseFixture dbFixture = new DatabaseFixture();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        dbFixture.setUp();
    }
    
    @Before
    public void setUp() throws Exception {
        dbFixture.clearTables();
        fixture = new TestSPCFixture();
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
    public void testAddEntities() throws DAOException {

        fixture.setupPartTable();
        fixture.setUpUserTable();
        fixture.setUpSPCMeasurementTable();
        SPCDataDAO dao = new SPCDataDAO();
        SPCMeasurementDAO measurementDAO = new SPCMeasurementDAO();
        DAO<User> userDAO = new DAO<User>(User.class);

        SPCMeasurement measurement = measurementDAO.findEntities().get(0);
        User user = userDAO.findEntities().get(0);

        List<SPCData> data = getSPCData(user, measurement);

        dao.addEntities(measurement, data);
        // Todo Retrieve values to check the SPCData has persisted
    }

    private List<SPCData> getSPCData(User u, SPCMeasurement spcMeasurement) {
        List<SPCData> data = new ArrayList<SPCData>();

        float[] measurements = { 123.3f, 123.2f, 123.0f, 123.1f, 123.2f };

        for (int i = 0; i < measurements.length; i++) {
            SPCData tempData = new SPCData();
            tempData.setMeasurement(measurements[i]);
            tempData.setMeasurementNumber(i);
            tempData.setDate(new Date(System.currentTimeMillis()));
            tempData.setUser(u);
            tempData.setSpcMeasurement(spcMeasurement);
            data.add(tempData);
        }

        return data;
    }

}
