package org.amc.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.amc.model.spc.SPCMeasurement;

public class SPCDAO extends DAO implements Serializable
{

	private static final long serialVersionUID = -5347530850042314728L;
	
	public SPCDAO(EntityManagerFactory emf)
	{
		super(emf);
	}
	
	public void addSPCMeasurement(SPCMeasurement measurement)
	{
		EntityManager em=getEntityManager();
		em.getTransaction().begin();
		em.persist(measurement);
		em.getTransaction().commit();
		em.close();
	}

	public void updateSPCMeasurement(SPCMeasurement measurement)
	{
		EntityManager em=getEntityManager();
		em.getTransaction().begin();
		em.merge(measurement);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * 
	 * @param measurementId
	 * @return SPCMeasurement or null if no measurement with id is found
	 */
	public SPCMeasurement getSPCMeasurement(String measurementId)
	{
		EntityManager em=getEntityManager();
		SPCMeasurement measurement=null;
		Query q=em.createQuery("Select x from SPCMeasurement x where x.id="+measurementId+"");
		try
		{
			measurement = (SPCMeasurement)q.getSingleResult();
		}
		catch(NoResultException nre)
		{
			//do nothing
		}
		em.close();

		return measurement;
	}

	public List<SPCMeasurement> findSPCMeasurements(String col, String value)
	{
		EntityManager em=getEntityManager();
		Query q=em.createQuery("Select x from SPCMeasurement x where x."+col+"='"+value+"'");
		Map<Integer, SPCMeasurement> list = new HashMap<Integer, SPCMeasurement>();
		List<SPCMeasurement> resultList=(List<SPCMeasurement>)q.getResultList();
		em.close();
		return resultList;
	}

	public List<SPCMeasurement> findSPCMeasurements()
	{
		EntityManager em=getEntityManager();
		Query q=em.createQuery("Select x from SPCMeasurement x ORDER BY x.id");
		Map<Integer, SPCMeasurement> list = new TreeMap<Integer, SPCMeasurement>();
		List<SPCMeasurement> resultList=(List<SPCMeasurement>)q.getResultList();
		em.close();
		return resultList;
	}

	/** @todo needs to fail if there are any SPCData entities referencing this SPCMeasurement */
	public void deleteSPCMeasurement(SPCMeasurement measurement)
	{
		EntityManager em=getEntityManager();
		em.getTransaction().begin();
		SPCMeasurement u=em.merge(measurement);
		em.remove(u);
		em.getTransaction().commit();
		em.close();
	}
}
