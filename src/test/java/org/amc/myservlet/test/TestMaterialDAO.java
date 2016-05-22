package org.amc.myservlet.test;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Map;

import org.amc.DAOException;
import org.amc.EntityManagerThreadLocal;
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
public class TestMaterialDAO {

    private final String NAME = "Moplen550";
    private final String COMPANY = "TOSARA";
    private final String TYPE = "ABS";

    private Material testMaterial;
    
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
    }

    /**
     * Test getMaterial method as well as addMaterial
     */
    @Test
    public void testAddMaterial() throws DAOException {
        MaterialDAO d = new MaterialDAO();
        d.addEntity(testMaterial);

        Material actual = d.getEntity(String.valueOf(testMaterial.getId()));
        assertEquals(testMaterial, actual);
    }

    @Test
    public void testUpdateMaterial() throws DAOException {
        // Create Material DAO
        MaterialDAO d = new MaterialDAO();
        // Add Material Database
        d.addEntity(testMaterial);

        closeEntityManager();
        // Check Material has been added and retrived
        Map<Integer, Material> list = d.findMaterials("name", NAME);
        Collection<Material> c = list.values();
        for (Material tm : c) {
            System.out.println(tm);
            if (tm.getName().equals(NAME)) {
                tm.setType(("TEST"));
                d.updateEntity(tm);
            }
        }
        assertNotSame(c.size(), 0);

    }

    @Test
    public void testFindMaterialsStringString() throws DAOException {
        MaterialDAO d = new MaterialDAO();
        d.addEntity(testMaterial);
        closeEntityManager();
        Map<Integer, Material> mp = d.findMaterials("name", NAME);
        assertEquals(mp.size(), 1);
    }

    @Test
    public void testFindMaterials() throws DAOException {
        MaterialDAO d = new MaterialDAO();
        d.addEntity(testMaterial);
        closeEntityManager();
        Map<Integer, Material> mp = d.findMaterials();
        assertEquals(mp.size(), 1);
    }

    private void closeEntityManager() {
        EntityManagerThreadLocal.closeEntityManager();
    }
}
