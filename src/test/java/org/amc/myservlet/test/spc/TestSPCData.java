package org.amc.myservlet.test.spc;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.amc.dao.DAO;
import org.amc.model.Part;
import org.amc.model.User;
import org.amc.model.spc.SPCData;
import org.amc.model.spc.SPCMeasurement;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestSPCData
{

	private EntityManager em;
	private EntityManagerFactory factory;
	
	
	@BeforeClass
	public static void setTables()
	{
		//Deletes SPCMeasusrements table
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("myDataSource");
		EntityManager em=factory.createEntityManager();

		Query q=em.createNativeQuery("delete from SPCMeasurements");
		Query q1=em.createNativeQuery("delete from users");
		em.getTransaction().begin();
		q.executeUpdate();
		q1.executeUpdate();
		em.getTransaction().commit();
		
		//Create part in table to use in test
		TestSPCMeasurment.createPartFromDataBase(factory);
		
		//Create SPCMeasurement in table to use in test
		DAO<SPCMeasurement> measurementDao=new DAO<SPCMeasurement>(factory,SPCMeasurement.class);
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
		em=measurementDao.getEntityManager();
		em.getTransaction().begin();
		part=em.merge(part);
		em.getTransaction().commit();
		measurement.setPart(part);
		measurementDao.addEntity(measurement);
		
		//Create user
		User u=new User();
		u.setActive(true);
		u.setUserName("ann");
		u.setFullName("Ann Barker");
		u.setEmailAddress("ann@automaticplastics.com");
		u.setPassword("password");
		DAO<User> userDao=new DAO<User>(factory,User.class);
		userDao.addEntity(u);
		
		
		em.close();
		factory.close();
		
	}
	
	@Before
	public void setUp() throws Exception
	{
		factory=Persistence.createEntityManagerFactory("myDataSource");
		em=factory.createEntityManager();
	}

	@After
	public void tearDown() throws Exception
	{
		em.close();
		factory.close();
	}

	@Test
	public void test()
	{
		DAO<SPCMeasurement> measurementDao=new DAO<SPCMeasurement>(factory,SPCMeasurement.class);
		DAO<SPCData> spcdataDao=new DAO<SPCData>(factory,SPCData.class);
		DAO<User> userDao=new DAO<User>(factory,User.class);
		SPCMeasurement measurement=measurementDao.findEntities().get(0);
		User user=userDao.findEntities().get(0);
		
		//Force SPCMeasurement and User object into the same persistence context
		em=spcdataDao.getEntityManager();
		em.getTransaction().begin();
		measurement=em.merge(measurement);
		user=em.merge(user);
		em.getTransaction().commit();
		
		SPCData d1= new SPCData();
		d1.setDate(new java.sql.Date(System.currentTimeMillis()));
		d1.setMeasurement(123f);
		d1.setSpcMeasurement(measurement);
		d1.setUser(user);
		
		spcdataDao.addEntity(d1);
	}

}
