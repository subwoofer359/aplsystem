package org.amc.myservlet.test;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnitUtil;

import org.amc.dao.MaterialDAO;
import org.amc.model.Material;
import org.apache.openjpa.conf.OpenJPAConfiguration;
import org.apache.openjpa.conf.OpenJPAConfigurationImpl;
import org.apache.openjpa.persistence.OpenJPAPersistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestMaterialDAO
{

	private EntityManager em;
	private EntityManagerFactory factory;
	
	@Before
	public void setUp()
	{
		Map<String,String> props=new HashMap<>();
		//props.put("openjpa.ConnectionURL", "jdbc:mysql://192.168.1.105/aplsystem");
		//props.put("openjpa.ConnectionDriverName", "com.mysql.jdbc.Driver");
		//props.put("openjpa.ConnectionUserName", "adrian");
		//props.put("openjpa.ConnectionPassword", "cr2032ux");
		//props.put("openjpa.Log","DefaultLevel=DEBUG, Tool=INFO");
		
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
	public void testAddMaterial()
	{
		Material m=new Material();
		
		m.setCompany("Tosara");
		m.setName("Moplen550");
		m.setType("PP");
		MaterialDAO d=new MaterialDAO();
		d.setEm(em);
		d.addMaterial(m);
	}

	@Test
	public void testUpdateMaterial()
	{
		//Create material
		Material m=new Material();
		m.setCompany("Tosara");
		m.setName("Moplen550");
		m.setType("PP");
		//Create Material DAO
		MaterialDAO d=new MaterialDAO();
		d.setEm(em);
		//Add Material Database
		d.addMaterial(m);
		
		//Check Material has been added and retrived
		Map<Integer,Material> list=d.findMaterials("name", "Moplen550");
		Collection<Material> c=list.values();
		for(Material tm:c)
		{
			System.out.println(tm);
		}
		assertNotSame(c.size(),0);
		
	}

	@Test
	public void testGetMaterial()
	{
		MaterialDAO d=new MaterialDAO();
		d.setEm(em);
		
		Material m=d.getMaterial("1");
		assertNotNull(m);
	}

	@Test
	public void testFindMaterialsStringString()
	{
		MaterialDAO d=new MaterialDAO();
		d.setEm(em);
		Map<Integer,Material> mp=d.findMaterials();
		assertNotSame(mp.size(),0);
	}

	@Test
	public void testFindMaterials()
	{
		fail("Not yet implemented");
	}

}
