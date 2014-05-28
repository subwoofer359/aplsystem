package org.amc.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.amc.model.Material;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
public class MaterialDAO extends DAO implements Serializable
{
	private static final long serialVersionUID = -4397260307883862647L;

	public MaterialDAO(EntityManagerFactory emf)
	{
		super(emf);
	}

	public void addMaterial(Material material) 
	{
		EntityManager em=getEntityManager();
		em.getTransaction().begin();
		em.persist(material);
		em.getTransaction().commit();
		em.close();
	}

	public void updateMaterial(Material material)
	{
		EntityManager em=getEntityManager();
		em.getTransaction().begin();
		em.merge(material);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * 
	 * @param materialId
	 * @return Material or null if no material with id is found
	 */
	public Material getMaterial(String materialId)
	{
		EntityManager em=getEntityManager();
		Material m=null;
		Query q=em.createQuery("Select x from Material x where x.id="+materialId+"");
		try
		{
			m = (Material)q.getSingleResult();
		}
		catch(NoResultException nre)
		{
			//do nothing
		}
		em.close();

		return m;
	}

	public Map<Integer, Material> findMaterials(String col, String value)
	{
		EntityManager em=getEntityManager();
		Query q=em.createQuery("Select x from Material x where x."+col+"='"+value+"'");
		Map<Integer, Material> list = new HashMap<Integer, Material>();
		List<Material> resultList=(List<Material>)q.getResultList();
		for(Material m:resultList)
		{
			list.put(m.getId(),m);
		}
		em.close();
		return list;
	}

	public Map<Integer, Material> findMaterials()
	{
		EntityManager em=getEntityManager();
		Query q=em.createQuery("Select x from Material x ORDER BY x.id");
		Map<Integer, Material> list = new TreeMap<Integer, Material>();
		List<Material> resultList=(List<Material>)q.getResultList();
		for(Material m:resultList)
		{
			list.put(m.getId(),m);
		}
		em.close();
		return list;
	}

	public void deleteMaterial(Material m)
	{
		EntityManager em=getEntityManager();
		em.getTransaction().begin();
		Material u=em.merge(m);
		em.remove(u);
		em.getTransaction().commit();
		em.close();
	}

}


	
