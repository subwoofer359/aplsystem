package org.amc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.amc.model.WorkEntity;
import org.apache.log4j.Logger;

import static org.amc.Constants.PERSISTENCE_UNIT_NAME;
/*
 * Fetches and holds a reference to the Persistence EntityManager
 * @author Adrian Mclaughlin
 * @version 1
 *
 */
public class DAO<T extends WorkEntity>
{
	private static Logger logger=Logger.getLogger(DAO.class);
	private EntityManager em;
	private Class<? extends WorkEntity> entityClass;
	
	public DAO(EntityManager em,Class<? extends WorkEntity> entityClass)
	{
		//emf=Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		//this.emf=emf;
		this.entityClass=entityClass;
		//this.em=emf.createEntityManager();
		this.em=em;
	
	}
	
//	@PersistenceUnit(name = PERSISTENCE_UNIT_NAME)
//	public void setEm(EntityManagerFactory emf)
//	{
//		logger.info("EntityManagerFactory set to "+emf.toString());
//		this.emf = emf;
//	}
	
	/**
	 * 
	 * @return EntityManager for subclass to use
	 */
	public synchronized EntityManager getEntityManager()
	{
//		if(emf!=null)
//		{
//			if(em==null || (!em.isOpen()))
//			{
//				this.em= emf.createEntityManager();
//			}
//		}
		return em;
	}
	
	public void addEntity(T entity)
	{
		EntityManager em=getEntityManager();
		synchronized (em)
		{
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
			//em.close();
		}
	}

	public void updateEntity(T entity)
	{
		EntityManager em=getEntityManager();
		synchronized (em)
		{
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
			//em.close();
		}
	}

	public void deleteEntity(T entity)
	{
		EntityManager em=getEntityManager();
		synchronized (em)
		{
			em.getTransaction().begin();
			T u=em.merge(entity);
			em.remove(u);
			em.getTransaction().commit();
			//em.close();
		}
	}

	/**
	 * 
	 * @param processId
	 * @return MouldingProcess or null if not found
	 */
	public T getEntity(String workEntityId)
	{
		EntityManager em=getEntityManager();
		T mp=null;
		synchronized (em)
		{
			
			Query q=em.createQuery("Select x from "+entityClass.getSimpleName()+" x where x.id="+workEntityId+"");
			try
			{
				mp = (T)q.getSingleResult();
				em.detach(mp);
			}
			catch(NoResultException nre)
			{
				
			}
			//em.close();
			
		}
		return mp;

	}

	public List<T> findEntities(String col, Object value)
	{
		EntityManager em=getEntityManager();
		List<T> resultList;
		synchronized (em)
		{
			Query q=em.createQuery("Select x from "+entityClass.getSimpleName()+" x where x."+col+" = ?1");
			q.setParameter(1, value);
			logger.debug(q.toString());
			resultList=(List<T>)q.getResultList();
			//em.close();
		}
		return resultList;
	}

	public List<T> findEntities() 
	{
		EntityManager em=getEntityManager();
		List<T> resultList;
		synchronized (em)
		{
		
			Query q=getEntityManager().createQuery("Select x from "+entityClass.getSimpleName()+" x");
			resultList=(List<T>)q.getResultList();
			//em.close();
		}
		return resultList;
	}
	

	
}
