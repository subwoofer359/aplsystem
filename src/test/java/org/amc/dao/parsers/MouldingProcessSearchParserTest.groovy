package org.amc.dao.parsers

import static org.junit.Assert.*;

import org.amc.dao.DAO;
import org.amc.model.MouldingProcess
import org.amc.model.Part
import org.amc.myservlet.test.spc.DatabaseFixture;
import org.amc.servlet.action.search.MouldingProcessSearch
import org.junit.After
import org.junit.AfterClass;
import org.junit.Before
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;;

class MouldingProcessSearchParserTest {
    static DatabaseFixture fixture = new DatabaseFixture();
    static MouldProcessFixture mouldFixture;
    static Calendar calendar = Calendar.getInstance();
    
    DAO<MouldingProcess> mouldingDAO;
    
    MouldingProcessSearchParser parser;
    MouldingProcessSearch mps;
    
    @BeforeClass
    static void setUpClass() {
        fixture.setUp();
        mouldFixture = new MouldProcessFixture(fixture.getNewEntityManager());
        mouldFixture.setup();
    } 

    @Before
    void setUp() {
        mouldingDAO  = new DAO<MouldingProcess>(MouldingProcess);
        mouldingDAO.entityManager = fixture.entityManager;
        
        parser = new MouldingProcessSearchParser(fixture.getNewEntityManager());
        mps = new MouldingProcessSearch();
    }

    @AfterClass
    static void tearDownAfterClass() {
        fixture.tearDown();
    }
    
    @Ignore
    @Test
    void emptyCriteriaQueryTest() {
        CriteriaQuery<MouldingProcess> query = parser.createCriteriaQuery(mps);
        println(query.toString());
        assert query.toString() == '';
    }
    
    @Test 
    void parsePartIdTest() {
        Part p = new Part(name: 'New Part');
        mps.partId = p.name;
        
        CriteriaQuery<MouldingProcess> query = parser.createCriteriaQuery(mps);
        assert query != null;
        println(query.toString());
        assert query.toString() == "SELECT m FROM MouldingProcess m WHERE m.basicInfo.partId.name = 'New Part'";
    }
    
    @Test
    void parsePartIdAndMachineNoTest() {
        Part p = new Part(name: 'New Part');
        mps.partId = p.name;
        mps.machineNo = 2;
        
        CriteriaQuery<MouldingProcess> query = parser.createCriteriaQuery(mps);
        assert query != null;
        println(query.toString());
        assert query.toString() == "SELECT m FROM MouldingProcess m WHERE (m.basicInfo.partId.name = 'New Part' AND m.basicInfo.machineNo = '2')";
    }
    
    @Test
    void parseStartDateTest() {
        Part p = new Part(name: 'New Part');
        Date date = calendar.getTime();
        
        mps.partId = p.name;
        mps.startDate = date;
        
        CriteriaQuery<MouldingProcess> query = parser.createCriteriaQuery(mps);
        assert query != null;
        println(query.toString());
        assert query.toString() == "SELECT m FROM MouldingProcess m WHERE (m.basicInfo.partId.name = 'New Part' AND m.basicInfo.dateOfIssue = ${date})";
    }
    
    @Test
    void parseEndDateTest() {
        Part p = new Part(name: 'New Part');
        Date startDate = calendar.getTime();
        calendar.add(Calendar.HOUR, 10);
        Date endDate = calendar.getTime();
        
        mps.partId = p.name;
        mps.startDate = startDate;
        mps.endDate = endDate;
        
        CriteriaQuery<MouldingProcess> query = parser.createCriteriaQuery(mps);
        assert query != null;
        println(query.toString());
        assert query.toString() == "SELECT m FROM MouldingProcess m WHERE (m.basicInfo.partId.name = 'New Part' AND m.basicInfo.dateOfIssue BETWEEN ${startDate} AND ${endDate})";
    }
    
    @Test
    void parseMasterBatchTest() {
        Part p = new Part(name: 'New Part');
        
        def masterbatch = 'masterbatch 001';
        
        mps.partId = p.name;
        mps.masterBatchNo = masterbatch;
         
        CriteriaQuery<MouldingProcess> query = parser.createCriteriaQuery(mps);
        assert query != null;
        println(query.toString());
        assert query.toString() == "SELECT m FROM MouldingProcess m WHERE (m.basicInfo.partId.name = 'New Part' AND m.basicInfo.masterbatchNo LIKE '${masterbatch}')";
    }
    
    @Test
    void parseSignOffByTest() {
        Part p = new Part(name: 'New Part');
        
        def signedOffBy = 'Terry';
        
        mps.partId = p.name;
        mps.signedOffBy = signedOffBy;

        CriteriaQuery<MouldingProcess> query = parser.createCriteriaQuery(mps);
        assert query != null;
        println(query.toString());
        assert query.toString() == "SELECT m FROM MouldingProcess m WHERE (m.basicInfo.partId.name = 'New Part' AND m.basicInfo.signOffBy LIKE '${signedOffBy}')";
    }
    
    @Test
    void parseMaterialTest() {
        Part p = new Part(name: 'New Part');
        
        def materialId = 3;
        
        mps.partId = p.name;
        mps.material = materialId;

        CriteriaQuery<MouldingProcess> query = parser.createCriteriaQuery(mps);
        assert query != null;
        println(query.toString());
        assert query.toString() == "SELECT m FROM MouldingProcess m WHERE (m.basicInfo.partId.name = 'New Part' AND m.basicInfo.material.id = ${materialId})";
    }
    
    @Test
    void queryByNameTest() {
        mps.partId = 'Pot';
        List results = mouldingDAO.findEntities(mps);
        assert results.size() == 3;
    }
    
    @Test
    void queryByMachineNoTest() {
        mps.machineNo = 'San 12';
        List results = mouldingDAO.findEntities(mps);
        assert results.size() == 1;
    }
    
    @Test
    void queryByMasterBatchNoTest() {
        mps.masterBatchNo = 'ro3993';
        List results = mouldingDAO.findEntities(mps);
        assert results.size() == 2;
    }

    @Test
    void queryBySignedByTest() {
        mps.signedOffBy = 'Terry Nolan';
        List results = mouldingDAO.findEntities(mps);
        assert results.size() == 1;
    }
    
    @Test
    void queryByMaterialTest() {
        mps.material = 1;
        List results = mouldingDAO.findEntities(mps);
        assert results.size() == 3;
    }
    
    @Test
    void queryByDateTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, mouldFixture.daysInPast);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        
        mps.startDate = calendar.getTime();
        List results = mouldingDAO.findEntities(mps);
        assert results.size() == 1;
    }
    
    @Test
    void queryByDateRangeTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, mouldFixture.daysInPast);

        mps.endDate = calendar.getTime();
        calendar.add(Calendar.DATE, mouldFixture.daysInPast * 2);
        
        mps.startDate = calendar.getTime();
        
        List results = mouldingDAO.findEntities(mps);
        assert results.size() == 2;
    }
}
