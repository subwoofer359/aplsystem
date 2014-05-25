package org.amc.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.amc.model.Material;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
public class MaterialDAO extends DAO implements Serializable
{
	private static final long serialVersionUID = -4397260307883862647L;

	public MaterialDAO()
	{
		;;
	}

	public void addMaterial(Material material) 
	{
		EntityManager em=getEntityManager();
		em.getTransaction().begin();
		em.persist(material);
		em.getTransaction().commit();
	
	}

	public void updateMaterial(Material material)
	{
		EntityManager em=getEntityManager();
		em.getTransaction().begin();
		em.merge(material);
		em.getTransaction().commit();
	}

	public Material getMaterial(String materialId)
	{
		Query q=getEntityManager().createQuery("Select x from Material x where x.id="+materialId+"");
		Material m = (Material)q.getSingleResult();
		return m;
	}

	public Map<Integer, Material> findMaterials(String col, String value)
	{
		Query q=getEntityManager().createQuery("Select x from Material x where x."+col+"='"+value+"'");
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
		Query q=getEntityManager().createQuery("Select x from Material x");
		Map<Integer, Material> list = new HashMap<Integer, Material>();
		List<Material> resultList=(List<Material>)q.getResultList();
		for(Material m:resultList)
		{
			list.put(m.getId(),m);
		}
		return list;
	}


}


	
