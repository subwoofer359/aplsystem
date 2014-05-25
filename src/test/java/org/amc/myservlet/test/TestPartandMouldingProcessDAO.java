package org.amc.myservlet.test;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.List;
import java.sql.Date;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.amc.dao.MaterialDAO;
import org.amc.dao.MouldingProcessDAO;
import org.amc.dao.PartDAO;
import org.amc.model.Material;
import org.amc.model.MouldingProcess;
import org.amc.model.MouldingProcess;
import org.amc.model.Part;
import org.amc.model.Part;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestPartandMouldingProcessDAO
{

	private EntityManager em;
	private EntityManagerFactory factory;
	
	@Before
	public void setUp()
	{
		factory=Persistence.createEntityManagerFactory("myDataSource");
		em=factory.createEntityManager();
	}
	
	@After
	public void tearDown()
	{
		em.close();
		factory.close();
	}
	
	@Test
	public void testAdd()
	{
		
		//Create a process sheet and part and add them to the database
		String testSheetName="Test Product 123";
		String testPartName="Blocks";
		MouldingProcess mp=new MouldingProcess();
		mp.setPartId(testSheetName);
		mp.setDateOfIssue(new Date(System.currentTimeMillis()));
		mp.setSignOffBy("John");
		mp.setMachineNo("San 2");
		mp.setMachineSize(320);
		mp.setMaterial(1);
	
		MouldingProcessDAO d=new MouldingProcessDAO();
		d.setEm(em);
		d.addProcessSheet(mp);
		
		Part p=new Part();
		p.setCompany("Tosara");
		p.setName(testPartName);
		p.setExternal(true);
		p.setColour("Red");
		p.setQss_no("Td 22");
		p.setPart_id("TS23");
		p.setVersion("1");
		
		PartDAO pd=new PartDAO();
		pd.setEm(em);
		pd.addPart(p);
		
		//Check to see if they are in the database
		
		List<MouldingProcess> mlist=d.findProcessSheets("partId", testSheetName);
		assertTrue(mlist.size()>= 1);
		
		List<Part> plist=pd.findParts("name", testPartName);
		assertTrue(plist.size()>=1);
	}


}
