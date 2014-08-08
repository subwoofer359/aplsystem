package org.amc.myservlet.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.amc.DAOException;
import org.amc.EntityManagerThreadLocal;
import org.amc.dao.DAO;
import org.amc.dao.MaterialDAO;
import org.amc.dao.UserRolesDAO;
import org.amc.model.Material;
import org.amc.model.MouldingProcess;
import org.amc.model.Part;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
public class TestPartandMouldingProcessDAO
{

	private EntityManager em;
	private EntityManagerFactory factory;
	
	//Material Constants
	private final String NAME="Moplen550";
	private final String COMPANY="TOSARA";
	private final String TYPE="ABS";
	
	private static Logger logger=Logger.getLogger(TestPartandMouldingProcessDAO.class);
	
	@Before
	public void setUp()
	{
		factory=Persistence.createEntityManagerFactory("myDataSource");
		EntityManagerThreadLocal.setEntityManagerFactory(factory);
		em=EntityManagerThreadLocal.getEntityManager();
		
		//Clear the table
		Query q=em.createNativeQuery("DELETE FROM processSheets");
		Query q1=em.createNativeQuery("DELETE FROM material");
		Query q2=em.createNativeQuery("DELETE FROM jobtemplate");
		em.getTransaction().begin();
		q.executeUpdate();
		q1.executeUpdate();
		q2.executeUpdate();
		em.getTransaction().commit();
	}
	
	@After
	public void tearDown()
	{
		EntityManagerThreadLocal.closeEntityManager();
		if(factory.isOpen())
		{
			factory.close();
		}
	}
	
	@Test
	public void testAdd()  throws DAOException
	{
		Material m=new Material();
		m.setCompany(COMPANY);
		m.setName(NAME);
		m.setType(TYPE);
		MaterialDAO daoMaterial=new MaterialDAO();
		//daoMaterial.setEm(em);
		daoMaterial.addEntity(m);
		
		//Create a process sheet and part and add them to the database
		String testSheetName="Test Product 123";
		String testPartName="Blocks";
		MouldingProcess mp=new MouldingProcess();
		mp.setPartId(testSheetName);
		mp.setDateOfIssue(new Date(System.currentTimeMillis()));
		mp.setSignOffBy("John");
		mp.setMachineNo("San 2");
		mp.setMachineSize(320);
		mp.setMaterial(m.getId());
	
		DAO<MouldingProcess> d=new DAO<MouldingProcess>(MouldingProcess.class);
		//d.setEm(em);
		d.addEntity(mp);
		
		Part p=getPart(testPartName);
		
		DAO<Part> pd=new DAO<Part>(Part.class);
		//pd.setEm(em);
		pd.addEntity(p);
		
		//Check to see if they are in the database
		
		List<MouldingProcess> mlist=d.findEntities("partId", testSheetName);
		assertTrue(mlist.size()>= 1);
		
		List<Part> plist=pd.findEntities("name", testPartName);
		assertTrue(plist.size()>=1);
	}
	@Test
	public void testConcurrency()  throws DAOException
	{
		int NO_OF_THREADS=12;
		CountDownLatch latch=new CountDownLatch(NO_OF_THREADS);
		List<UpdateThread> threads=new ArrayList<TestPartandMouldingProcessDAO.UpdateThread>();
		DAO<Part> pd=new DAO<Part>(Part.class);
		//Add Parts to database
		for(int i=0;i<NO_OF_THREADS;i++)
		{
			pd.addEntity(getPart("Part:"+i));
			threads.add(new UpdateThread(latch,new DAO<Part>(Part.class)));
		}
		
		for(UpdateThread thread:threads)
		{
				thread.start();
		}
		
		try
		{
			latch.await();
		} 
		catch (InterruptedException e)
		{
		
		} 
		
		
		
	}


	public static Part getPart(String testPartName) 
	{
		Part p=new Part();
		p.setCompany("Tosara");
		p.setName(testPartName);
		p.setExternal(true);
		p.setColour("Red");
		p.setQss_no("Td 22");
		p.setPart_id("TS23");
		p.setVersion("1");
		return p;
	}
	
	private static class UpdateThread extends Thread
	{
		private DAO<Part> dao;
		private CountDownLatch latch;
		
		public UpdateThread(CountDownLatch latch,DAO<Part> dao)
		{
			
			this.dao=dao;
			this.latch=latch;
		}
		
		public void run()
		{
			try
			{
				List<Part> list=dao.findEntities();
				for(Part p:list)
				{
					p.setName("Updated by "+this.getName());
					dao.updateEntity(p);
				}
				latch.countDown();
			} catch (DAOException e)
			{
				logger.error(e.getMessage());
			}
			
		}
	}
}
