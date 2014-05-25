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
/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
public class TestMaterialDAO
{

	private EntityManager em;
	private EntityManagerFactory factory;
	private final String NAME="Moplen550";
	private final String COMPANY="TOSARA";
	private final String TYPE="ABS";
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
	public void testAddMaterial()
	{
		Material m=new Material();
		
		m.setCompany(COMPANY);
		m.setName(NAME);
		m.setType(TYPE);
		MaterialDAO d=new MaterialDAO();
		d.setEm(em);
		d.addMaterial(m);
	}

	@Test
	public void testUpdateMaterial()
	{
		//Create material
		Material m=new Material();
		m.setCompany(COMPANY);
		m.setName(NAME);
		m.setType(TYPE);
		//Create Material DAO
		MaterialDAO d=new MaterialDAO();
		d.setEm(em);
		//Add Material Database
		d.addMaterial(m);
		
		//Check Material has been added and retrived
		Map<Integer,Material> list=d.findMaterials("name", NAME);
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
		Map<Integer,Material> mp=d.findMaterials("name",NAME);
		assertNotSame(mp.size(),0);
	}

	@Test
	public void testFindMaterials()
	{
		MaterialDAO d=new MaterialDAO();
		d.setEm(em);
		Map<Integer,Material> mp=d.findMaterials();
		assertNotSame(mp.size(),0);
	}

}
