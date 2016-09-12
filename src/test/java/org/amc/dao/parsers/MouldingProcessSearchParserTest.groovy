package org.amc.dao.parsers

import static org.junit.Assert.*;

import org.amc.dao.DAO;
import org.amc.model.MouldingProcess
import org.amc.model.Part
import org.amc.myservlet.test.spc.DatabaseFixture;
import org.amc.servlet.action.search.MouldingProcessSearch
import org.junit.After;
import org.junit.Before
import org.junit.Ignore;
import org.junit.Test

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;;

class MouldingProcessSearchParserTest {
    DAO<MouldingProcess> mouldingDAO;
    DatabaseFixture fixture = new DatabaseFixture();
    
    MouldingProcessSearchParser parser;
    MouldingProcessSearch mps;
    
    MouldProcessFixture mouldFixture;
    
    static Calendar calendar = Calendar.getInstance(); 

    @Before
    public void setUp() throws Exception {
        fixture.setUp();
        mouldingDAO  = new DAO<MouldingProcess>(MouldingProcess);
        mouldingDAO.entityManager = fixture.entityManager;
        
        mouldFixture = new MouldProcessFixture(fixture.getNewEntityManager());
        mouldFixture.setup();
        
        parser = new MouldingProcessSearchParser();
        mps = new MouldingProcessSearch();
    }

    @After
    public void tearDown() throws Exception {
        fixture.tearDown();
    }

    @Test
    public void emptyTest() {
        assert parser.parse(MouldingProcess, mps) == ''; 
    }
    
    @Ignore
    @Test
    public void emptyCriteriaQueryTest() {
        EntityManager em = fixture.getNewEntityManager();
        CriteriaQuery<MouldingProcess> query = parser.createCriteriaQuery(em, mps);
        println(query.toString());
        assert query.toString() == '';
    }
    
    @Test 
    public void parsePartIdTest() {
        EntityManager em = fixture.getNewEntityManager();
        Part p = new Part(name: 'New Part');
        mps.partId = p.name;
        
        CriteriaQuery<MouldingProcess> query = parser.createCriteriaQuery(em, mps);
        assert query != null;
        println(query.toString());
        assert query.toString() == "SELECT m FROM MouldingProcess m WHERE m.basicInfo.partId.name = 'New Part'";
    }
    
    @Test
    public void parsePartIdAndMachineNoTest() {
        EntityManager em = fixture.getNewEntityManager();
        Part p = new Part(name: 'New Part');
        mps.partId = p.name;
        mps.machineNo = 2;
        
        CriteriaQuery<MouldingProcess> query = parser.createCriteriaQuery(em, mps);
        assert query != null;
        println(query.toString());
        assert query.toString() == "SELECT m FROM MouldingProcess m WHERE (m.basicInfo.partId.name = 'New Part' AND m.basicInfo.machineNo = '2')";
    }
    
    @Test
    public void parseStartDateTest() {
        EntityManager em = fixture.getNewEntityManager();
        Part p = new Part(name: 'New Part');
        Date date = calendar.getTime();
        
        mps.partId = p.name;
        mps.startDate = date;
        
        CriteriaQuery<MouldingProcess> query = parser.createCriteriaQuery(em, mps);
        assert query != null;
        println(query.toString());
        assert query.toString() == "SELECT m FROM MouldingProcess m WHERE (m.basicInfo.partId.name = 'New Part' AND m.basicInfo.dateOfIssue = ${date})";
    }
    
    @Test
    public void parseEndDateTest() {
        EntityManager em = fixture.getNewEntityManager();
        Part p = new Part(name: 'New Part');
        Date startDate = calendar.getTime();
        calendar.add(Calendar.HOUR, 10);
        Date endDate = calendar.getTime();
        
        mps.partId = p.name;
        mps.startDate = startDate;
        mps.endDate = endDate;
        
        CriteriaQuery<MouldingProcess> query = parser.createCriteriaQuery(em, mps);
        assert query != null;
        println(query.toString());
        assert query.toString() == "SELECT m FROM MouldingProcess m WHERE (m.basicInfo.partId.name = 'New Part' AND m.basicInfo.dateOfIssue BETWEEN ${startDate} AND ${endDate})";
    }
    
    @Test
    public void parseMasterBatchTest() {
        EntityManager em = fixture.getNewEntityManager();
        Part p = new Part(name: 'New Part');
        
        def masterbatch = 'masterbatch 001';
        
        mps.partId = p.name;
        mps.masterBatchNo = masterbatch;
         
        CriteriaQuery<MouldingProcess> query = parser.createCriteriaQuery(em, mps);
        assert query != null;
        println(query.toString());
        assert query.toString() == "SELECT m FROM MouldingProcess m WHERE (m.basicInfo.partId.name = 'New Part' AND m.basicInfo.masterbatchNo LIKE '${masterbatch}')";
    }
    
    @Test
    public void parseSignOffByTest() {
        EntityManager em = fixture.getNewEntityManager();
        Part p = new Part(name: 'New Part');
        
        def signedOffBy = 'Terry';
        
        mps.partId = p.name;
        mps.signedOffBy = signedOffBy;

        CriteriaQuery<MouldingProcess> query = parser.createCriteriaQuery(em, mps);
        assert query != null;
        println(query.toString());
        assert query.toString() == "SELECT m FROM MouldingProcess m WHERE (m.basicInfo.partId.name = 'New Part' AND m.basicInfo.signOffBy LIKE '${signedOffBy}')";
    }
    
    @Test
    public void parseMaterialTest() {
        EntityManager em = fixture.getNewEntityManager();
        Part p = new Part(name: 'New Part');
        
        def materialId = 3;
        
        mps.partId = p.name;
        mps.material = materialId;

        CriteriaQuery<MouldingProcess> query = parser.createCriteriaQuery(em, mps);
        assert query != null;
        println(query.toString());
        assert query.toString() == "SELECT m FROM MouldingProcess m WHERE (m.basicInfo.partId.name = 'New Part' AND m.basicInfo.material.id = ${materialId})";
    }
    
    @Test
    public void queryByNameTest() {
        mps.partId = 'Pot';
        List results = mouldingDAO.findEntities(mps);
        assert results.size() == 3;
    }
    
    @Test
    public void queryByMachineNoTest() {
        mps.machineNo = 'San 12';
        List results = mouldingDAO.findEntities(mps);
        assert results.size() == 1;
    }
    
    @Test
    public void queryByMasterBatchNoTest() {
        mps.masterBatchNo = 'ro3993';
        List results = mouldingDAO.findEntities(mps);
        assert results.size() == 2;
    }

    @Test
    public void queryBySignedByTest() {
        mps.signedOffBy = 'Terry Nolan';
        List results = mouldingDAO.findEntities(mps);
        assert results.size() == 1;
    }
    
    @Test
    public void queryByMaterialTest() {
        mps.material = 1;
        List results = mouldingDAO.findEntities(mps);
        assert results.size() == 3;
    }
    
    @Test
    public void queryByDateTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, mouldFixture.daysInPast);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        
        mps.startDate = calendar.getTime();
        List results = mouldingDAO.findEntities(mps);
        assert results.size() == 1;
    }
    
    @Test
    public void queryByDateRangeTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, mouldFixture.daysInPast);

        mps.endDate = calendar.getTime();
        calendar.add(Calendar.DATE, mouldFixture.daysInPast * 2);
        
        mps.startDate = calendar.getTime();
        
        List results = mouldingDAO.findEntities(mps);
        assert results.size() == 2;
    }
}
