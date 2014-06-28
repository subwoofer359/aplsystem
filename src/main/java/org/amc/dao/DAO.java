package org.amc.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.amc.DAOException;
import org.amc.EntityManagerThreadLocal;
import org.amc.model.WorkEntity;
import org.apache.log4j.Logger;

/**
 * Fetches and holds a reference to the Persistence EntityManager
 * @author Adrian Mclaughlin
 * @version 1
 *
 */
public class DAO<T extends WorkEntity> implements Serializable
{
	
	private static final long serialVersionUID = 854157422459241714L;
	
	private static Logger logger=Logger.getLogger(DAO.class);
	
	private final Class<? extends WorkEntity> entityClass;
	
	public DAO(Class<? extends WorkEntity> entityClass)
	{
		this.entityClass=entityClass;	
	}
	
	/**
	 * 
	 * @return EntityManager for subclass to use
	 */
	public EntityManager getEntityManager()
	{
		
		return EntityManagerThreadLocal.getEntityManager();
	}
	
	public void addEntity(T entity) throws DAOException
	{
		EntityManager em=getEntityManager();
		try
		{
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
		}
		catch(PersistenceException pe)
		{
			em.getTransaction().rollback();
			em.close();
			logger.error("DAO<"+entityClass.getSimpleName()+">:Error has occurred when trying to persist entity");
			throw new DAOException(pe);
		}
	}

	public void updateEntity(T entity) throws DAOException
	{
		EntityManager em=getEntityManager();
		try
		{
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
		}
		catch(PersistenceException pe)
		{
			em.getTransaction().rollback();
			em.close();
			logger.error("DAO<"+entityClass.getSimpleName()+">:Error has occurred when trying to merge entity into the persistence context");
			throw new DAOException(pe);
		}
		
	}

	public void deleteEntity(T entity) throws DAOException
	{
		EntityManager em=getEntityManager();
		try
		{
			em.getTransaction().begin();
			T u=em.merge(entity);
			em.remove(u);
			em.getTransaction().commit();
		}
		catch(PersistenceException pe)
		{
			em.close();
			logger.error("DAO<"+entityClass.getSimpleName()+">:Error has occurred when trying to delete entity");
			throw new DAOException(pe);
		}
	}

	/**
	 * 
	 * @param processId
	 * @return MouldingProcess or null if not found
	 */
	public T getEntity(String workEntityId) throws DAOException
	{
		T mp=null;
		Query q=getEntityManager().createQuery("Select x from "+entityClass.getSimpleName()+" x where x.id="+workEntityId+"");
		try
		{
			mp = (T)q.getSingleResult();
			//getEntityManager().detach(mp);
		}
		catch(NoResultException nre)
		{
			logger.error("DAO<"+entityClass.getSimpleName()+">:Error has occurred when trying to retrive entity. The entity should exist in the database but it doesn't");
			//throw new DAOException(nre); todo Should this error be thrown?
		}
		catch(PersistenceException pe)
		{
			logger.error("DAO<"+entityClass.getSimpleName()+">:Error has occurred when trying to retrive entity");
			throw new DAOException(pe);
		}
		return mp;

	}

	public List<T> findEntities(String col, Object value) throws DAOException
	{
		List<T> resultList;
		try
		{
			Query q=getEntityManager().createQuery("Select x from "+entityClass.getSimpleName()+" x where x."+col+" = ?1");
			q.setParameter(1, value);
			logger.debug(q.toString());
			resultList=(List<T>)q.getResultList();
		}
		catch(PersistenceException pe)
		{
			logger.error("DAO<"+entityClass.getSimpleName()+">:Error has occurred when trying to find entities");
			throw new DAOException(pe);
		}	
		
		return resultList;
	}

	public List<T> findEntities() throws DAOException
	{
		List<T> resultList;
		try
		{
			Query q=getEntityManager().createQuery("Select x from "+entityClass.getSimpleName()+" x");
			resultList=(List<T>)q.getResultList();
		}
		catch(PersistenceException pe)
		{
			logger.error("DAO<"+entityClass.getSimpleName()+">:Error has occurred when trying to find entities");
			throw new DAOException(pe);
		}
		return resultList;
	}
	
	protected Class<? extends WorkEntity> getEntityClass()
	{
		return this.entityClass;
	}
	
}
