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
import org.amc.model.spc.SPCPartsList;
import org.amc.servlet.APLSpcController;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;


public class TestAPLSpcController
{
	private EntityManager em;
	private EntityManagerFactory factory;
	private TestSPCFixture fixture;
	DAO<SPCPartsList> partsListDao;
	DAO<Part> partsDAO;
	APLSpcController controller;
	
	@BeforeClass
	public static void setupBeforeClass()
	{
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("myDataSource");
		EntityManager em=factory.createEntityManager();
		
		
		//Clear SPCPartsList table
		Query q=em.createNativeQuery("delete from SPCPartsList");
		em.getTransaction().begin();
		q.executeUpdate();
		em.getTransaction().commit();
	}
	
	@Before
	public void setUp()
	{
		factory=Persistence.createEntityManagerFactory("myDataSource");
		em=factory.createEntityManager();
		
		fixture=new TestSPCFixture();
		fixture.setUp();
		
		partsListDao=new DAO<SPCPartsList>(factory,SPCPartsList.class);
		partsDAO=new DAO<Part>(factory,Part.class);
		controller=new APLSpcController();
		controller.setSPCListPartDAO(partsListDao);
	}
	
	@After
	public void tearDown() throws Exception
	{
		fixture.tearDown();
		fixture=null;
		em.close();
		factory.close();
	}
	
	@Test
	public void testGetSPCPartList()
	{
		fixture.setupPartTable();
		List<Part> listOfParts=partsDAO.findEntities();
		
		for(int i=0;(i<listOfParts.size() && i < 5);i++)
		{
			
			SPCPartsList spcPart=new SPCPartsList();
			Part part=null;
			
			EntityManager em=this.partsListDao.getEntityManager();
			em.getTransaction().begin();
			part=em.merge(listOfParts.get(i));
			em.getTransaction().commit();
			spcPart.setPart(part);
			partsListDao.addEntity(spcPart);
		}
		
		ModelAndView mav=controller.getSPCPartList();
		
		ModelAndViewAssert.assertAndReturnModelAttributeOfType(mav, "parts", List.class);
		
		ModelAndViewAssert.assertViewName(mav, "SPCPartList");
		
		
	}
}
