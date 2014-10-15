package org.amc.myservlet.test.spc;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.amc.DAOException;
import org.amc.EntityManagerThreadLocal;
import org.amc.dao.DAO;
import org.amc.model.Part;
import org.amc.model.spc.SPCMeasurement;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestSPCMeasurment {
    private EntityManager em;
    private EntityManagerFactory factory;
    private TestSPCFixture fixture;

    @Test
    public void testSPCMeasurement() throws DAOException {
        DAO<SPCMeasurement> dao = new DAO<SPCMeasurement>(SPCMeasurement.class);

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
        }
        measurement.setPart(part);
        // Save SPCMeasurement entity to database

        dao.addEntity(measurement);

        // Retrieve SPCMeasurement entity from database
        SPCMeasurement spc = dao.getEntity(String.valueOf(measurement.getId()));

        // Check some properties
        assertEquals(spc.getDimension(), measurement.getDimension());
        assertEquals(spc.getNominal(), measurement.getNominal(), 0.1);
        assertEquals(spc.getUpperLimit(), measurement.getUpperLimit(), 0.1);
    }

    @BeforeClass
    public static void setTables() {
        // Deletes SPCMeasusrements table
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myDataSource");
        EntityManager em = factory.createEntityManager();
        Query tableExists = em.createNativeQuery("SHOW TABLES");
        Query q = em.createNativeQuery("Drop table SPCMeasurements");
        em.getTransaction().begin();
        tableExists.executeUpdate();
        List<String> tables = tableExists.getResultList();
        if (tables.contains("SPCMeasurements")) {
            q.executeUpdate();
        }
        em.getTransaction().commit();

        em.close();
        factory.close();

    }

    @Before
    public void setUp() throws DAOException {
        factory = Persistence.createEntityManagerFactory("myDataSource");
        EntityManagerThreadLocal.setEntityManagerFactory(factory);
        em = EntityManagerThreadLocal.getEntityManager();
        fixture = new TestSPCFixture();
        fixture.setUp();
        fixture.setupPartTable();
    }

    @After
    public void tearDown() {
        fixture = null;
        EntityManagerThreadLocal.closeEntityManager();
        factory.close();
    }
}
