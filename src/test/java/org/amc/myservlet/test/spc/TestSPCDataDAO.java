package org.amc.myservlet.test.spc;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.amc.DAOException;
import org.amc.EntityManagerThreadLocal;
import org.amc.dao.DAO;
import org.amc.dao.SPCDataDAO;
import org.amc.dao.SPCMeasurementDAO;
import org.amc.model.User;
import org.amc.model.spc.SPCData;
import org.amc.model.spc.SPCMeasurement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestSPCDataDAO
{
	private EntityManager em;
	private EntityManagerFactory factory;
	private TestSPCFixture fixture;
	
	@Before
	public void setUp() throws Exception
	{
		factory=Persistence.createEntityManagerFactory("myDataSource");
		EntityManagerThreadLocal.setEntityManagerFactory(factory);
		em=EntityManagerThreadLocal.getEntityManager();
		fixture=new TestSPCFixture();
		fixture.setUp();
	}

	@After
	public void tearDown() throws Exception
	{
		EntityManagerThreadLocal.closeEntityManager();
		fixture=null;
		factory.close();
	}

	@Test
	public void testAddEntities() throws DAOException
	{
	
		fixture.setupPartTable();
		fixture.setUpUserTable();
		fixture.setUpSPCMeasurementTable();
		SPCDataDAO dao=new SPCDataDAO();
		SPCMeasurementDAO measurementDAO=new SPCMeasurementDAO();
		DAO<User> userDAO=new DAO<User>(User.class);
		
		SPCMeasurement measurement=measurementDAO.findEntities().get(0);
		User user=userDAO.findEntities().get(0);
		
		List<SPCData> data=getSPCData(user, measurement);
		
		dao.addEntities(measurement,data);
		//Todo Retrieve values to check the SPCData has persisted
	}
	
	private List<SPCData> getSPCData(User u,SPCMeasurement spcMeasurement)
	{
		List<SPCData> data=new ArrayList<SPCData>();
		
		float[] measurements={123.3f,123.2f,123.0f,123.1f,123.2f};
		
		for(int i=0;i<measurements.length;i++)
		{
			SPCData tempData=new SPCData();
			tempData.setMeasurement(measurements[i]);
			tempData.setMeasurementNumber(i);
			tempData.setDate(new Date(System.currentTimeMillis()));
			tempData.setUser(u);
			tempData.setSpcMeasurement(spcMeasurement);
			data.add(tempData);
		}
		
		return data;
	}

}
