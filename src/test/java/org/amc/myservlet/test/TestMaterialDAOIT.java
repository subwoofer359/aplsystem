package org.amc.myservlet.test;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Map;

import org.amc.DAOException;
import org.amc.dao.MaterialDAO;
import org.amc.model.Material;
import org.amc.myservlet.test.spc.DatabaseFixture;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
public class TestMaterialDAOIT {

    private final String NAME = "Moplen550";
    private final String COMPANY = "TOSARA";
    private final String TYPE = "ABS";

    private Material testMaterial;
    
    private MaterialDAO materialDAO;
    
    private static final DatabaseFixture dbFixture = new DatabaseFixture();

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
        // Set up test Material
        testMaterial = new Material();

        testMaterial.setCompany(COMPANY);
        testMaterial.setName(NAME);
        testMaterial.setType(TYPE);
        
        materialDAO = new MaterialDAO();
        materialDAO.setEntityManager(dbFixture.getNewEntityManager());
    }

    /**
     * Test getMaterial method as well as addMaterial
     */
    @Test
    public void testAddMaterial() throws DAOException {
        
        materialDAO.addEntity(testMaterial);

        Material actual = materialDAO.getEntity(testMaterial.getId());
        assertEquals(testMaterial, actual);
    }

    @Test
    public void testUpdateMaterial() throws DAOException {
        // Add Material Database
        materialDAO.addEntity(testMaterial);

        // Check Material has been added and retrived
        Map<Integer, Material> list = materialDAO.findMaterials("name", NAME);
        Collection<Material> c = list.values();
        for (Material tm : c) {
            System.out.println(tm);
            if (tm.getName().equals(NAME)) {
                tm.setType(("TEST"));
                materialDAO.updateEntity(tm);
            }
        }
        assertNotSame(c.size(), 0);

    }

    @Test
    public void testFindMaterialsStringString() throws DAOException {
        materialDAO.addEntity(testMaterial);
        Map<Integer, Material> mp = materialDAO.findMaterials("name", NAME);
        assertEquals(mp.size(), 1);
    }

    @Test
    public void testFindMaterials() throws DAOException {
        materialDAO.addEntity(testMaterial);
        Map<Integer, Material> mp = materialDAO.findMaterials();
        assertEquals(mp.size(), 1);
    }
}
