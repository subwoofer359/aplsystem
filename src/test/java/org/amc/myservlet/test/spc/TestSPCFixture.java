package org.amc.myservlet.test.spc;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.amc.DAOException;
import org.amc.EntityManagerThreadLocal;
import org.amc.dao.DAO;
import org.amc.dao.SPCMeasurementDAO;
import org.amc.model.Part;
import org.amc.model.User;
import org.amc.model.spc.SPCMeasurement;

/**
 * 
 * @author Adrian McLaughlin
 * 
 * Create Entities required to test the different elements of the spc package
 *
 */
public class TestSPCFixture
{
	private EntityManager em;
	private EntityManagerFactory factory;
	
	
	public TestSPCFixture()
	{
		
	}
	
	public void setUp()
	{
		//factory=Persistence.createEntityManagerFactory("myDataSource");
		em=EntityManagerThreadLocal.getEntityManager();	
	}


	public void setupPartTable() throws DAOException
	{
		//Clear table users
		if(tableExists("jobtemplate"))
		{
			Query q1=em.createNativeQuery("delete from jobtemplate");
			em.getTransaction().begin();
			q1.executeUpdate();
			em.getTransaction().commit();
		}
		//Get DAO object
		DAO<Part> partDAO=new DAO<Part>(Part.class);
		String[] colours={"red","blue","green","yellow"};
		String[] companies={"HMV","Granada","Apple","Apple"};
		String[] names={"CD","Car","IPOD","IPHONE"};
		String[] part_ids={"393939","99w2933","ap3003","ap2202"};
		String[] qss_nos={"CD 001","GA 002","A 001","A 002"};
		
		Part part=null;
		for(int i=0;i<colours.length;i++)
		{
			part=new Part();
			part.setName(names[i]);
			part.setColour(colours[i]);
			part.setCompany(companies[i]);
			part.setExternal(true);
			part.setPart_id(part_ids[i]);
			part.setQss_no(qss_nos[i]);
			part.setRevision("0.2"+i);
			part.setVersion(String.valueOf(i));
			partDAO.addEntity(part);
		}
		//Set it to be garbage collected
		partDAO=null;
	}
	public void setUpUserTable() throws DAOException
	{
		//Clear table users
		if(tableExists("users"))
		{
			Query q1=em.createNativeQuery("delete from users");
			em.getTransaction().begin();
			q1.executeUpdate();
			em.getTransaction().commit();
		}
		//Get DAO object
		DAO<User> userDAO=new DAO<User>(User.class);
		
		//Create User Entities and add them to the database
		String[] fullnames={"Adrian McLaughlin","Stephen Nolan","Chris Dalton"};
		String[] userNames={"adrian","stephen","chris"};
		String[] emailAddresses={"adrian@adrianmclaughlin.ie","stephen@gmail.com","chris@gmail.com"};
		String[] passwords={"wewew","3i3i3i3i","fpefpefle"};
		User user=null;
		for(int i=0;i<fullnames.length;i++)
		{
			user=new User();
			user.setActive(true);
			user.setEmailAddress(emailAddresses[i]);
			user.setFullName(fullnames[i]);
			user.setUserName(userNames[i]);
			user.setPassword(passwords[i]);
			userDAO.addEntity(user);
		}
		//Set it to be garbage collected
		userDAO=null;
	}
	
	public void setUpSPCMeasurementTable() throws DAOException
	{
		//Clear SPCDimension Table
		if(tableExists("SPCMeasurements"))
		{
			Query q1=em.createNativeQuery("delete from SPCMeasurements");
			em.getTransaction().begin();
			q1.executeUpdate();
			em.getTransaction().commit();
		}
		
		DAO<Part> partDAO=new DAO<Part>(Part.class);
		SPCMeasurementDAO measurementDAO=new SPCMeasurementDAO();
	
		//Presuming there are entries in the part database table;
		Part p=partDAO.findEntities().get(0);
		
		
		String[] dimensions={"length","height","width","radius"};
		float[] lowerLimits={0.3f,0.2f,0f,3f,1f};
		float[] upperLimits={0.3f,0.2f,0f,3f,1f};
		float[] nominals={123.34f,221.2f,11.22f,21.23f,0.33f};
		int[] noOfMeasurments={3,2,5,5,10};
		//String[] tableIds={"tableId0020202020","tableId03","tableId0020ffrrf0","tableId022ff202020","tableId0020330"};
		
		for(int i=0;i<dimensions.length;i++)
		{
			SPCMeasurement measurement=new SPCMeasurement();
			measurement.setActive(true);
			measurement.setDimension(dimensions[i]);
			measurement.setLowerLimit(lowerLimits[i]);
			measurement.setUpperLimit(upperLimits[i]);
			measurement.setNominal(nominals[i]);
			measurement.setNoOfMeasurements(noOfMeasurments[i]);
			//measurement.setTableId(tableIds[i]);
			measurement.setPart(p);
			measurementDAO.addEntity(measurement);
		}
		
		
		
	}
	
	public boolean tableExists(String tableName)
	{
		Query tableExists=em.createNativeQuery("SHOW TABLES");
		em.getTransaction().begin();
		tableExists.executeUpdate();
		List<String> tables=tableExists.getResultList();
		boolean result=false;
		if(tables.contains(tableName))
		{
			result=true;
		}
		em.getTransaction().commit();
		
		return result;
	}
	
	//Todo Create method setUpSPCMeasurementTable
}
