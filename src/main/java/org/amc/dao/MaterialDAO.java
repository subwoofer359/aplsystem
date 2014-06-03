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
public class MaterialDAO extends DAO<Material> implements Serializable
{
	private static final long serialVersionUID = -4397260307883862647L;

	public MaterialDAO(EntityManagerFactory emf)
	{
		super(emf,Material.class);
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

}


	
