package org.amc.dao.parsers

import static org.junit.Assert.*

import org.amc.model.MouldingProcess
import org.amc.model.Part
import org.amc.myservlet.test.spc.DatabaseFixture;
import org.amc.servlet.action.search.MouldingProcessSearch
import org.junit.After;
import org.junit.Before;
import org.junit.Test

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;;

class MouldingProcessSearchParserTest {
    MouldingProcessSearchParser parser;
    MouldingProcessSearch mps;
    DatabaseFixture fixture = new DatabaseFixture();
    static Calendar calendar = Calendar.getInstance(); 

    @Before
    public void setUp() throws Exception {
        fixture.setUp();
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
    
    @Test 
    public void parsePartId() {
        EntityManager em = fixture.getNewEntityManager();
        Part p = new Part(name: 'New Part');
        mps.partId = p.name;
        
        CriteriaQuery<MouldingProcess> query = parser.createCriteriaQuery(em, mps);
        assert query != null;
        println(query.toString());
        assert query.toString() == "SELECT m FROM MouldingProcess m WHERE m.basicInfo.partId.name LIKE 'New Part'";
    }
    
    @Test
    public void parsePartIdAndMachineNo() {
        EntityManager em = fixture.getNewEntityManager();
        Part p = new Part(name: 'New Part');
        mps.partId = p.name;
        mps.machineNo = 2;
        
        CriteriaQuery<MouldingProcess> query = parser.createCriteriaQuery(em, mps);
        assert query != null;
        println(query.toString());
        assert query.toString() == "SELECT m FROM MouldingProcess m WHERE (m.basicInfo.partId.name LIKE 'New Part' AND m.basicInfo.machineNo = '2')";
    }
    
    @Test
    public void parseStartDate() {
        EntityManager em = fixture.getNewEntityManager();
        Part p = new Part(name: 'New Part');
        Date date = calendar.getTime();
        
        mps.partId = p.name;
        mps.startDate = date;
        
        CriteriaQuery<MouldingProcess> query = parser.createCriteriaQuery(em, mps);
        assert query != null;
        println(query.toString());
        assert query.toString() == "SELECT m FROM MouldingProcess m WHERE (m.basicInfo.partId.name LIKE 'New Part' AND m.basicInfo.dateOfIssue = ${date})";
    }
    
    @Test
    public void parseEndDate() {
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
        assert query.toString() == "SELECT m FROM MouldingProcess m WHERE (m.basicInfo.partId.name LIKE 'New Part' AND m.basicInfo.dateOfIssue BETWEEN ${startDate} AND ${endDate})";
    }

}
