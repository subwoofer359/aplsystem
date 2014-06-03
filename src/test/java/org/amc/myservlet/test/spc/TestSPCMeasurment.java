package org.amc.myservlet.test.spc;
import static org.junit.Assert.*;

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
import org.junit.Test;

public class TestSPCMeasurment
{
	private EntityManager em;
	private EntityManagerFactory factory;
	
	@Test
	public void testTableCreation()
	{
		SPCDAO dao=new SPCDAO(factory);
		
		SPCMeasurement measurement=new SPCMeasurement();
		measurement.setActive(true);
		measurement.setDimension("length");
		measurement.setLowerLimit(0.3f);
		measurement.setUpperLimit(0.3f);
		measurement.setNominal(152f);
		measurement.setNoOfMeasurements(5);
		measurement.setPart(getPartFromDataBase());
		dao.addSPCMeasurement(measurement);
	
	}
	
	private Part getPartFromDataBase()
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
		//partDAO.addPart(part);
		return part;
	}
	
	@Before
	public void setUp()
	{
		factory=Persistence.createEntityManagerFactory("myDataSource");
		em=factory.createEntityManager();
		//Query q=em.createNativeQuery("DELETE FROM SPCMeasurements");
		//em.getTransaction().begin();
		//q.executeUpdate();
		//em.getTransaction().commit();	
	}
	
	@After
	public void tearDown()
	{
		em.close();
		factory.close();
	}
}
