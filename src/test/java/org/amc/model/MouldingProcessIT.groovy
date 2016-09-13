package org.amc.model

import static org.junit.Assert.*;

import org.amc.dao.DAO;
import org.amc.myservlet.test.spc.DatabaseFixture;
import org.junit.After
import org.junit.AfterClass;
import org.junit.Before
import org.junit.BeforeClass;
import org.junit.Test;

class MouldingProcessIT {
    static DatabaseFixture fixture = new DatabaseFixture();
    DAO<MouldingProcess> dao;
    DAO<Part> partDAO;
    DAO<Material> materialDAO;
    
    MouldingProcess process;
    Part part;
    Material material;
    
    @BeforeClass
    static void setupBeforeClass() {
        fixture.setUp();
    }
    
    @Before
    void setup() {
        fixture.clearTables();
        def manager = fixture.newEntityManager;
        
        addPartToDatabase(manager);
        addMaterialToDatabase(manager);
        addMouldingProcessToDatabase(manager);

    }
    
    private void addPartToDatabase(def entityManager) {
        partDAO = new DAO<Part>(Part);
        partDAO.entityManager = entityManager;
        
        part = new Part();
        part.colour = 'Red';
        part.company = 'Ferrari';
        part.external = true;
        part.name = 'Car fender';
        part.part_id = 'eofpefepo';
        part.qss_no = 'QSS FE0012';
        part.version = '1';
        part.revision = 'revision 2.2';
        
        partDAO.addEntity(part);
        
    }
    
    private void addMaterialToDatabase(def entityManager) {
        materialDAO = new DAO<Material>(Material);
        materialDAO.entityManager = entityManager;
        
        material = new Material();
        
        material.company = 'Exxon mobil';
        material.density = 23.3f;
        material.name = 'Poly Ethanol';
        material.type = 'PP';
        
        materialDAO.addEntity(material);
    }
    
    private void addMouldingProcessToDatabase(def entityManager) {
        dao = new DAO<MouldingProcess>(MouldingProcess);
        dao.entityManager = entityManager;
        process = new MouldingProcess();
        process.basicInfo.machineNo = 1;
        process.basicInfo.machineSize = 10;
        process.basicInfo.masterbatchNo = 'Rs 03032';
        process.basicInfo.signOffBy = 'AMCL';
        process.basicInfo.partId = part;
        process.basicInfo.material = material;
        process.basicInfo.dateOfIssue = new Date();
        process.basicInfo.processNotes = 'Not working';
    }
    
    @AfterClass
    static void tearDown() {
        fixture.tearDown();
    }

    @Test
    void addEntityToDatabaseTest() {
        dao.addEntity(process);
        
        dao.entityManager = fixture.newEntityManager;
        def processSheets  = dao.findEntities();
        
        assert !processSheets.isEmpty();
        
        def returnedSheet = processSheets[0];
        
        assert returnedSheet.basicInfo.material == material;
        assert returnedSheet.basicInfo.partId == part;
    }

}
