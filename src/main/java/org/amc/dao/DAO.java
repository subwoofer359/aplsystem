package org.amc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
public class DAO<T extends WorkEntity>
{
	private static Logger logger=Logger.getLogger(DAO.class);
	private Class<? extends WorkEntity> entityClass;
	
	public DAO(Class<? extends WorkEntity> entityClass)
	{
		this.entityClass=entityClass;	
	}
	
	/**
	 * 
	 * @return EntityManager for subclass to use
	 */
	public synchronized EntityManager getEntityManager()
	{
		
		return EntityManagerThreadLocal.getEntityManager();
	}
	
	public void addEntity(T entity) throws DAOException
	{
		EntityManager em=getEntityManager();
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
	}

	public void updateEntity(T entity) throws DAOException
	{
		EntityManager em=getEntityManager();
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
		
	}

	public void deleteEntity(T entity) throws DAOException
	{
		EntityManager em=getEntityManager();
		em.getTransaction().begin();
		T u=em.merge(entity);
		em.remove(u);
		em.getTransaction().commit();

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
			
		}		
		return mp;

	}

	public List<T> findEntities(String col, Object value) throws DAOException
	{
		
		List<T> resultList;
		Query q=getEntityManager().createQuery("Select x from "+entityClass.getSimpleName()+" x where x."+col+" = ?1");
		q.setParameter(1, value);
		logger.debug(q.toString());
		resultList=(List<T>)q.getResultList();
			
		
		return resultList;
	}

	public List<T> findEntities() throws DAOException
	{
		List<T> resultList;
		Query q=getEntityManager().createQuery("Select x from "+entityClass.getSimpleName()+" x");
		resultList=(List<T>)q.getResultList();
		return resultList;
	}
	

	
}
