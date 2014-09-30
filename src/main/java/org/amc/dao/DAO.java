package org.amc.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.amc.DAOException;
import org.amc.EntityManagerThreadLocal;
import org.amc.dao.parsers.NoSuchWebFormParser;
import org.amc.dao.parsers.WebFormSearchParserFactory;
import org.amc.dao.parsers.WebFormSearchToJPQLParser;
import org.amc.model.WorkEntity;
import org.amc.servlet.action.search.WebFormSearch;
import org.amc.servlet.action.search.SearchFields;
import org.apache.log4j.Logger;


/**
 * Represents a general Data Access Object to provide the link between the business logic and database. 
 * @author Adrian Mclaughlin
 * @version 1
 * @param <T> WorkEntity
 */
public class DAO<T extends WorkEntity> implements Serializable
{
	/**
	 * Serializable UID
	 */
	private static final long serialVersionUID = 854157422459241714L;
	
	/**
	 * Logger used by this DAO object
	 */
	private static Logger LOG=Logger.getLogger(DAO.class);
	
	/**
	 * The class this DAO is working with
	 */
	private final Class<? extends WorkEntity> entityClass;
	
	/**
	 * Constructor.
	 * 
	 * @param entityClass the {@link WorkEntity} class this DAO object is working with
	 */
	public DAO(Class<? extends WorkEntity> entityClass)
	{
		this.entityClass=entityClass;	
	}
	
	/**
	 * Provides access to {@link EntityManagerThreadLocal} which contains the reference for the {@link EntityManager}
	 * 
	 * @return EntityManager for this class subclasses to use
	 * @see EntityManager
	 * @see EntityManagerThreadLocal
	 */
	public EntityManager getEntityManager()
	{	
		return EntityManagerThreadLocal.getEntityManager();
	}
	
	/**
	 * Adds the {@link WorkEntity} object to the database
	 * 
	 * @param entity the new <code>WorkEntity</code> to be stored in the database
	 * @throws DAOException if a problem occurs in the underlying database
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
	 * Updates the current {@link WorkEntity} object in the database. 
	 * 
	 * @param entity the <code>WorkEntity</code> to be updated.
	 * @throws DAOException if a problem occurs in the underlying database
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
	 * Deletes the {@link WorkEntity} object from the database
	 * 
	 * @param entity the <code>WorkEntity</code> to be deleted
	 * @throws DAOException if a problem occurs in the underlying database
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
	 * Retrieves a {@link WorkEntity} object who's database ID value is equal to <code>workEntityId</code>
	 * <p>
	 * A DAOException is thrown if there is no entry in the database with the given <code>workEntityId</code>.
	 * 
	 * @param workEntityId The String representation of the number corresponding to the ID of the <code>WorkEntity</code> in the database.
	 * @return a WorkEntity object
	 * @throws DAOException if a problem occurs in the underlying database
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
	 * Fetchs a list of objects of class {@link WorkEntity} stored in the underlying database. 
	 * The results from the database are stored in a <code>java.util.List</code>.
	 * @param col String name of the column in the database to query
	 * @param value Object to query for in the database
	 * @return List of WorkEntities
	 * @throws DAOException if a problem occurs in the underlying database
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
	 * Fetchs a list of all objects of {@link WorkEntity} stored in the underlying database. 
	 * The results from the database are stored in a <code>java.util.List</code>.
	 * <p>
	 * Caution if there are a lot of rows in the database if may return a very large <code>Collection</code> of objects.
	 * 
	 * @return List of all WorkEntities in the database 
	 * @throws DAOException if a problem occurs in the underlying database
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
	 * Given a {@link org.amc.servlet.action.search.WebFormSearch WebFormSearch} object this method fetchs a list of objects of class {@link WorkEntity}. 
	 * The search is parsed and converted into a JPQL expression which is used to carry out a
	 * Database query. The results from the query are stored in a <code>java.util.List</code>.
	 * 
	 * @param WebFormSearch object containing the search fields to construct a query
	 * @return a list of WorkEntities from the Database or an empty list if the WebFormSearch has no variables set.
	 * @throws DAOException if a problem occurs in the underlying database
	 */
	public List<T> findEntities(WebFormSearch search) throws DAOException
	{
		try
		{
			WebFormSearchToJPQLParser parser=WebFormSearchParserFactory.getWebFormSearchParser(search);
			String textQuery=parser.parse(this.entityClass,search);
					
			if(textQuery.length()==0)
			{
				return new ArrayList<T>();
			}
			
			LOG.debug("FindEntities(Search) query is :"+textQuery);
			
			Query query=getEntityManager().createQuery(textQuery);
		
			
			for(Iterator<SearchFields> i=search.getFields().iterator();i.hasNext();)
			{
				SearchFields currentField=i.next();
				Object value=search.getField(currentField);
				query.setParameter(currentField.name(), value);
				LOG.debug("Setting field:"+currentField.name()+" to "+value.toString());
			}
			
			@SuppressWarnings("unchecked")
			List<T> resultList=query.getResultList();
			return resultList;
			
		}
		catch(PersistenceException pe)
		{
			LOG.error("DAO<"+entityClass.getSimpleName()+">:Error has occurred when trying to find entities");
			throw new DAOException(pe);
		}
		catch(NoSuchWebFormParser nswe)
		{
			LOG.error(nswe.getMessage());
			throw new DAOException(nswe);
		}
	}
	
	/**
	 * Returns a <code>Class</code> object of class <code>WorkEntity</code> which this DAO object has been initialised with 
	 * @return Class object which this DAO is handling
	 * @see WorkEntity
	 */
	protected Class<? extends WorkEntity> getEntityClass()
	{
		return this.entityClass;
	}

	/**
 	 * Overrides <code>Object</code> class <code>toString</code> method
 	 * @return String representation
 	 */
	@Override
	public String toString()
	{
		return "DAO<"+getEntityClass().getSimpleName()+">";
	}

	
}

