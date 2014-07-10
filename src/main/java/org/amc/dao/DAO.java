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
 * @param <T> WorkEntity
 */
public class DAO<T extends WorkEntity> implements Serializable
{
	
	/**
	 * Serializable
	 */
	private static final long serialVersionUID = 854157422459241714L;
	
	/**
	 * Logger used by the object
	 */
	private static Logger LOG=Logger.getLogger(DAO.class);
	
	/**
	 * The class this DAO is handling
	 */
	private final Class<? extends WorkEntity> entityClass;
	
	/**
	 * Constructor
	 * @param entityClass
	 */
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
	
	/**
	 * @param entity
	 * @throws DAOException
	 */
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
			LOG.error("DAO<"+entityClass.getSimpleName()+">:Error has occurred when trying to persist entity");
			throw new DAOException(pe);
		}
	}

	/**
	 * @param entity
	 * @throws DAOException
	 */
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
			LOG.error("DAO<"+entityClass.getSimpleName()+">:Error has occurred when trying to merge entity into the persistence context");
			throw new DAOException(pe);
		}
		
	}

	/**
	 * @param entity
	 * @throws DAOException
	 */
	public void deleteEntity(T entity) throws DAOException
	{
		EntityManager em=getEntityManager();
		try
		{
			em.getTransaction().begin();
			T entityToBeRemoved=em.merge(entity);
			em.remove(entityToBeRemoved);
			em.getTransaction().commit();
		}
		catch(PersistenceException pe)
		{
			em.close();
			LOG.error("DAO<"+entityClass.getSimpleName()+">:Error has occurred when trying to delete entity");
			throw new DAOException(pe);
		}
	}

	/**
	 * @param workEntityId
	 * @return MouldingProcess or null if not found
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	public T getEntity(String workEntityId) throws DAOException
	{
		
		Query query=getEntityManager().createQuery("Select x from "+entityClass.getSimpleName()+" x where x.id="+workEntityId+"");
		try
		{
			T mp = (T)query.getSingleResult();
			//getEntityManager().detach(mp);
			return mp;
		}
		catch(NoResultException nre)
		{
			LOG.error("DAO<"+entityClass.getSimpleName()+">:Error has occurred when trying to retrive entity. The entity should exist in the database but it doesn't");
			throw new DAOException(nre);
		}
		catch(PersistenceException pe)
		{
			LOG.error("DAO<"+entityClass.getSimpleName()+">:Error has occurred when trying to retrive entity");
			throw new DAOException(pe);
		}
		

	}
	
	/**
	 * @param col
	 * @param value
	 * @return List of WorkEntities
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	public List<T> findEntities(String col, Object value) throws DAOException
	{
		
		try
		{
			Query query=getEntityManager().createQuery("Select x from "+entityClass.getSimpleName()+" x where x."+col+" = ?1");
			query.setParameter(1, value);
			LOG.debug(query.toString());
			List<T> resultList=query.getResultList();
			return resultList;
		}
		catch(PersistenceException pe)
		{
			LOG.error("DAO<"+entityClass.getSimpleName()+">:Error has occurred when trying to find entities");
			throw new DAOException(pe);
		}	
		
		
	}

	/**
	 * @return List of WorkEntities
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	public List<T> findEntities() throws DAOException
	{
		try
		{
			Query query=getEntityManager().createQuery("Select x from "+entityClass.getSimpleName()+" x");
			List<T> resultList=query.getResultList();
			return resultList;
		}
		catch(PersistenceException pe)
		{
			LOG.error("DAO<"+entityClass.getSimpleName()+">:Error has occurred when trying to find entities");
			throw new DAOException(pe);
		}
		
	}
	
	/**
	 * 
	 * @return Class which this DAO is handing
	 */
	protected Class<? extends WorkEntity> getEntityClass()
	{
		return this.entityClass;
	}

	/**
 	 * Overrides Object toString()
 	 * @return String representation 
 	 */
	@Override
	public String toString()
	{
		return "DAO<"+getEntityClass().getSimpleName()+">";
	}

	
}

