package org.amc.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.amc.model.Material;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

public class MaterialDAO implements Serializable
{
	private static final long serialVersionUID = -4397260307883862647L;

	
	private EntityManager em;
	public MaterialDAO()
	{
		;;
	}

	public void addMaterial(Material material) 
	{

		em.getTransaction().begin();
		em.persist(material);
		em.getTransaction().commit();
	
	}

	public void updateMaterial(Material material)
	{
		em.merge(material);
	}

	public Material getMaterial(String materialId)
	{
		Query q=em.createQuery("Select x from Material x where x.id="+materialId+"");
		List<Material> l=q.getResultList();
		Material m = (Material)q.getSingleResult();
		return m;
	}

	public Map<Integer, Material> findMaterials(String col, String value)
	{
		Query q=em.createQuery("Select x from Material x where x."+col+"='"+value+"'");
		Map<Integer, Material> list = new HashMap<Integer, Material>();
		List<Material> resultList=(List<Material>)q.getResultList();
		for(Material m:resultList)
		{
			list.put(m.getId(),m);
		}
		return list;
	}

	public Map<Integer, Material> findMaterials()
	{
		Query q=em.createQuery("Select x from Material x");
		Map<Integer, Material> list = new HashMap<Integer, Material>();
		List<Material> resultList=(List<Material>)q.getResultList();
		for(Material m:resultList)
		{
			list.put(m.getId(),m);
		}
		return list;
	}

	@PersistenceUnit(name="myDatabase")
	public void setEm(EntityManager em)
	{
		this.em = em;
	}	
}


	
