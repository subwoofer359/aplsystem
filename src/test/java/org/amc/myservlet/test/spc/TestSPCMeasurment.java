package org.amc.myservlet.test.spc;
import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.amc.dao.PartDAO;
import org.amc.dao.SPCDAO;
import org.amc.model.Part;
import org.amc.model.spc.SPCMeasurement;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestSPCMeasurment
{
	private EntityManager em;
	private EntityManagerFactory factory;
	
	@Test
	public void testSPCMeasurement()
	{
		SPCDAO dao=new SPCDAO(factory);
		
		SPCMeasurement measurement=new SPCMeasurement();
		measurement.setActive(true);
		measurement.setDimension("length");
		measurement.setLowerLimit(0.3f);
		measurement.setUpperLimit(0.3f);
		measurement.setNominal(152f);
		measurement.setNoOfMeasurements(5);
		
		//Retrieve Part entity from the database
		PartDAO partDAO=new PartDAO(factory);
		List<Part> parts=partDAO.findParts();
		Part part=null;
		if(parts.size()>0)
		{
			part=parts.get(0);
		}
		measurement.setPart(part);
		
		//Save SPCMeasurement entity to database
		dao.addSPCMeasurement(measurement);
	
		//Retrieve SPCMeasurement entity from database
		SPCMeasurement spc=dao.getSPCMeasurement(String.valueOf(measurement.getId()));
		
		//Check some properties 
		assertEquals(spc.getDimension(),measurement.getDimension());
		assertEquals(spc.getNominal(),measurement.getNominal(),0.1);
		assertEquals(spc.getUpperLimit(),measurement.getUpperLimit(),0.1);
	}
	
	/**
	 * Creates a Part entity
	 * @param factory
	 */
	private static void createPartFromDataBase(EntityManagerFactory factory)
	{
		Part part =new Part();
		part.setCompany("tosara");
		part.setExternal(true);
		part.setName("125g pot");
		part.setQss_no("TA 001");
		part.setRevision("1");
		part.setVersion("3");
		part.setPart_id("300r30");
		part.setColour("grey");
		PartDAO partDAO=new PartDAO(factory);
		partDAO.addPart(part);
	}
	
	@BeforeClass
	public static void setTables()
	{
		//Deletes SPCMeasusrements table
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("myDataSource");
		EntityManager em=factory.createEntityManager();
		Query tableExists=em.createNativeQuery("SHOW TABLES");
		Query q=em.createNativeQuery("Drop table SPCMeasurements");
		em.getTransaction().begin();
		tableExists.executeUpdate();
		List<String> tables=tableExists.getResultList();
		if(tables.contains("SPCMeasurements"))
		{
			q.executeUpdate();
		}
		em.getTransaction().commit();
		
		//Create part in table to use in test
		createPartFromDataBase(factory);
		
		em.close();
		factory.close();
		
	}
	
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
}
