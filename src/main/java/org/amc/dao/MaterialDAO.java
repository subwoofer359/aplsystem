package org.amc.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.amc.DAOException;
import org.amc.model.Material;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
public class MaterialDAO extends DAO<Material> implements Serializable
{
	private static final long serialVersionUID = -4397260307883862647L;
	private static Logger logger=Logger.getLogger(MaterialDAO.class);
	
	public MaterialDAO()
	{
		super(Material.class);
	}


	public Map<Integer, Material> findMaterials(String col, String value) throws DAOException
	{
		EntityManager em=getEntityManager();
		Map<Integer, Material> list;
		try
		{
			Query q=em.createQuery("Select x from Material x where x."+col+"='"+value+"'");
			list = new HashMap<Integer, Material>();
			List<Material> resultList=(List<Material>)q.getResultList();
			for(Material m:resultList)
			{
				list.put(m.getId(),m);
			}
		}
		catch(PersistenceException pe)
		{
			em.getTransaction().rollback();
			logger.error("DAO<"+getEntityClass().getSimpleName()+">:Error has occurred when trying to find entities");
			throw new DAOException(pe);
		}
		return list;
	}

	public Map<Integer, Material> findMaterials() throws DAOException
	{
		EntityManager em=getEntityManager();
		Map<Integer, Material> list;
		try
		{
			Query q=em.createQuery("Select x from Material x ORDER BY x.id");
			list = new TreeMap<Integer, Material>();
			List<Material> resultList=(List<Material>)q.getResultList();
			for(Material m:resultList)
			{
				list.put(m.getId(),m);
			}
		}
		catch(PersistenceException pe)
		{
			em.getTransaction().rollback();
			logger.error("DAO<"+getEntityClass().getSimpleName()+">:Error has occurred when trying to find entities");
			throw new DAOException(pe);
		}
		return list;
	}

}


	
