package org.amc.myservlet.test.spc;
import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.amc.dao.DAO;
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
		DAO<SPCMeasurement> dao=new DAO<SPCMeasurement>(factory,SPCMeasurement.class);
		
		SPCMeasurement measurement=new SPCMeasurement();
		measurement.setActive(true);
		measurement.setDimension("length");
		measurement.setLowerLimit(0.3f);
		measurement.setUpperLimit(0.3f);
		measurement.setNominal(152f);
		measurement.setNoOfMeasurements(5);
		
		//Retrieve Part entity from the database
		DAO<Part> partDAO=new DAO<Part>(factory,Part.class);
		List<Part> parts=partDAO.findEntities();
		Part part=null;
		if(parts.size()>0)
		{
			part=parts.get(0);
		}
		
		//Forcing the EntityManager to manage Part entity
		EntityManager em=dao.getEntityManager();
		em.getTransaction().begin();
		part=em.merge(part);
		em.getTransaction().commit();
		measurement.setPart(part);
		//Save SPCMeasurement entity to database
		
		dao.addEntity(measurement);
	
		//Retrieve SPCMeasurement entity from database
		SPCMeasurement spc=(SPCMeasurement)dao.getEntity(String.valueOf(measurement.getId()));
		
		//Check some properties 
		assertEquals(spc.getDimension(),measurement.getDimension());
		assertEquals(spc.getNominal(),measurement.getNominal(),0.1);
		assertEquals(spc.getUpperLimit(),measurement.getUpperLimit(),0.1);
	}
	
	/**
	 * Creates a Part entity
	 * @param factory
	 */
	static Part createPartFromDataBase(EntityManagerFactory factory)
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
		DAO<Part> partDAO=new DAO<Part>(factory,Part.class);
		partDAO.addEntity(part);
		return part;
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
		if(em!=null && em.isOpen())
		{
			em.getTransaction().begin();
			em.flush();
			em.getTransaction().commit();
		}
		em.close();
		factory.close();
	}
}
