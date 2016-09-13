package org.amc.dao;

import org.amc.DAOException;
import org.amc.dao.parsers.NoSuchWebFormParserException;
import org.amc.dao.parsers.WebFormSearchParserFactory;
import org.amc.dao.parsers.WebFormSearchToQuery;
import org.amc.model.WorkEntity;
import org.amc.servlet.action.search.WebFormSearch;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 * 
 * Represents a general Data Access Object to provide the link between the
 * business logic and database. Fetches and holds a reference to the Persistence
 * EntityManager
 * 
 * @author Adrian Mclaughlin
 * @version 1
 * @param <T> WorkEntity
 */
public class DAO<T extends WorkEntity> {

    /**
     * Logger used by the object
     */
    private static final Logger LOG = Logger.getLogger(DAO.class);

    /**
     * The class this DAO is handling
     */
    private final Class<? extends WorkEntity> entityClass;
    
    private EntityManager entityManager;

    public DAO(Class<? extends WorkEntity> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Adds the {@link WorkEntity} object to the database
     * 
     * @param entity
     *            the new <code>WorkEntity</code> to be stored in the database
     * @throws DAOException
     *             if a problem occurs in the underlying database
     */
    public void addEntity(T entity) throws DAOException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (OptimisticLockException ole) {
            throw ole;
        } catch (PersistenceException pe) {
            LOG.error("DAO<" + entityClass.getSimpleName()
                            + ">:Error has occurred when trying to persist entity");
            throw new DAOException(pe);
        }
    }

    /**
     * Deletes the {@link WorkEntity} object from the database
     * 
     * @param entity
     *            the <code>WorkEntity</code> to be deleted
     * @throws DAOException
     *             if a problem occurs in the underlying database
     */
    public void deleteEntity(T entity) throws DAOException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            T entityToBeRemoved = em.merge(entity);
            em.remove(entityToBeRemoved);
            em.getTransaction().commit();
        } catch (OptimisticLockException ole) {
            throw ole;
        } catch (PersistenceException pe) {
            em.close();
            LOG.error("DAO<" + entityClass.getSimpleName()
                            + ">:Error has occurred when trying to delete entity");
            throw new DAOException(pe);
        }
    }

    /**
     * Fetchs a list of all objects of {@link WorkEntity} stored in the
     * underlying database. The results from the database are stored in a
     * <code>java.util.List</code>.
     * <p>
     * Caution if there are a lot of rows in the database if may return a very
     * large <code>Collection</code> of objects.
     * 
     * @return List of all WorkEntities in the database
     * @throws DAOException
     *             if a problem occurs in the underlying database
     * 
     */
    @SuppressWarnings("unchecked")
    public List<T> findEntities() throws DAOException {
        try {
            Query query = getEntityManager().createQuery(
                            "Select x from " + entityClass.getSimpleName() + " x", entityClass);
            List<T> resultList = query.getResultList();
            return resultList;
        } catch (PersistenceException pe) {
            LOG.error("DAO<" + entityClass.getSimpleName()
                            + ">:Error has occurred when trying to find entities");
            throw new DAOException(pe);
        }
    }

    /**
     * Fetchs a list of objects of class {@link WorkEntity} stored in the
     * underlying database. The results from the database are stored in a
     * <code>java.util.List</code>.
     * 
     * @param col
     *            String name of the column in the database to query
     * @param value
     *            Object to query for in the database
     * @return List of WorkEntities
     * @throws DAOException
     *             if a problem occurs in the underlying database
     */
    @SuppressWarnings("unchecked")
    public List<T> findEntities(String col, Object value) throws DAOException {

        try {
            Query query = getEntityManager().createQuery(
                            "Select x from " + entityClass.getSimpleName() + " x where x." + col
                                            + " = ?1", entityClass);
            query.setParameter(1, value);
            LOG.debug(query.toString());
            List<T> resultList = query.getResultList();
            return resultList;
        } catch (PersistenceException pe) {
            LOG.error("DAO<" + entityClass.getSimpleName()
                            + ">:Error has occurred when trying to find entities");
            throw new DAOException(pe);
        }

    }


    /**
     * Retrieves a {@link WorkEntity} object who's database ID value is equal to
     * <code>workEntityId</code>
     * <p>
     * A DAOException is thrown if there is no entry in the database with the
     * given <code>workEntityId</code>.
     * 
     * @param workEntityId
     *            The String representation of the number corresponding to the
     *            ID of the <code>WorkEntity</code> in the database.
     * @return a WorkEntity object
     * @throws DAOException
     *             if a problem occurs in the underlying database
     */
    @SuppressWarnings("unchecked")
    public T getEntity(int id) throws DAOException {

        Query query = getEntityManager().createQuery(
                        "Select x from " + entityClass.getSimpleName() + " x where x.id = ?1", entityClass);
        try {
            query.setParameter(1, id);
            T mp = (T) query.getSingleResult();
            return mp;
        } catch (NoResultException nre) {
            LOG.error("DAO<"
                            + entityClass.getSimpleName()
                            + ">:Error has occurred when trying to retrive entity. The entity should exist in the database but it doesn't");
            throw new DAOException(nre);
        } catch (PersistenceException pe) {
            LOG.error("DAO<" + entityClass.getSimpleName()
                            + ">:Error has occurred when trying to retrive entity");
            throw new DAOException(pe);
        }

    }

    /**
     * Returns a <code>Class</code> object of class <code>WorkEntity</code>
     * which this DAO object has been initialised with
     * 
     * @return Class object which this DAO is handling
     * @see WorkEntity
     */
    protected Class<?> getEntityClass() {
        return this.entityClass;
    }

    /**
     * Provides access to {@link EntityManagerThreadLocal} which contains the
     * reference for the {@link EntityManager}
     * 
     * @return EntityManager for this class subclasses to use
     * @see EntityManager
     * @see EntityManagerThreadLocal
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }
    
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Overrides <code>Object</code> class <code>toString</code> method
     * 
     * @return String representation
     */
    @Override
    public String toString() {
        return "DAO<" + getEntityClass().getSimpleName() + ">";
    }

    /**
     * Updates the current {@link WorkEntity} object in the database.
     * 
     * @param entity
     *            the <code>WorkEntity</code> to be updated.
     * @throws DAOException
     *             if a problem occurs in the underlying database
     */
    public T updateEntity(T entity) throws DAOException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            T mergedEntity = em.merge(entity);
            em.getTransaction().commit();
            return mergedEntity;
        } catch (OptimisticLockException ole) {
            throw ole;
        } catch (PersistenceException pe) {
            //em.close();
            LOG.error("DAO<"
                            + entityClass.getSimpleName()
                            + ">:Error has occurred when trying to merge entity into the persistence context");
            throw new DAOException(pe);
        }

    }
    
    /**
     * Detaches Entity from the Persistence context
     *
     * @param entity
     * @see EntityManager#detach(Object)
     */
    public void detachEntity(T entity) {
        EntityManager em = getEntityManager();
        try {
            em.detach(entity);
        } catch (IllegalArgumentException iae) {
            LOG.error("DAO: is not an entity");
        }
    }
    
    /**
     * Given a {@link org.amc.servlet.action.search.WebFormSearch WebFormSearch}
     * object this method fetches a list of objects of class {@link WorkEntity}.
     * The search is parsed and converted into a JPQL expression which is used
     * to carry out a Database query. The results from the query are stored in a
     * <code>java.util.List</code>.
     * 
     * @param WebFormSearch
     *            object containing the search fields to construct a query
     * @return a list of WorkEntities from the Database or an empty list if the
     *         WebFormSearch has no variables set.
     * @throws DAOException
     *             if a problem occurs in the underlying database
     */
    public List<? extends WorkEntity> findEntities(WebFormSearch search) throws DAOException {
        try {
            if(search.isEmpty()) {
                return Collections.emptyList();
            }
            
            WebFormSearchToQuery parser = 
                            WebFormSearchParserFactory.getWebFormSearchParser(getEntityManager(), search);
            CriteriaQuery<? extends WorkEntity> query = parser.createCriteriaQuery(search);
            return getEntityManager().createQuery(query).getResultList();
        } catch (PersistenceException pe) {
            LOG.error("DAO<" + entityClass.getSimpleName()
                            + ">:Error has occurred when trying to find entities");
            throw new DAOException(pe);
        } catch (NoSuchWebFormParserException nswe) {
            LOG.error(nswe.getMessage());
            throw new DAOException(nswe);
        }
    }
}
