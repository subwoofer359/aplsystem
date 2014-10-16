package org.amc.dao;

import org.amc.DAOException;
import org.amc.EntityManagerThreadLocal;
import org.amc.dao.parsers.NoSuchWebFormParserException;
import org.amc.dao.parsers.WebFormSearchParserFactory;
import org.amc.dao.parsers.WebFormSearchToJPQLParser;
import org.amc.model.WorkEntity;
import org.amc.servlet.action.search.SearchFields;
import org.amc.servlet.action.search.WebFormSearch;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 * Fetches and holds a reference to the Persistence EntityManager
 * 
 * @author Adrian Mclaughlin
 * @version 1
 * @param <T>  WorkEntity
 */
public class DAO<T extends WorkEntity> implements Serializable {
    /**
     * Serializable
     */
    private static final long serialVersionUID = 854157422459241714L;

    /**
     * Logger used by the object
     */
    private static final Logger LOG = Logger.getLogger(DAO.class);

    /**
     * The class this DAO is handling
     */
    private final Class<? extends WorkEntity> entityClass;

    public DAO(Class<? extends WorkEntity> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Adds the Entity to be added to the Persistence context
     * 
     * @param entity to be added to the Persistence context
     * @throws DAOException
     */
    public void addEntity(T entity) throws DAOException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (PersistenceException pe) {
            em.getTransaction().rollback();
            em.close();
            LOG.error("DAO<" + entityClass.getSimpleName()
                            + ">:Error has occurred when trying to persist entity");
            throw new DAOException(pe);
        }
    }

    /**
     * Deletes the entity from the Persistence context
     * 
     * @param entity
     * @throws DAOException
     */
    public void deleteEntity(T entity) throws DAOException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            T entityToBeRemoved = em.merge(entity);
            em.remove(entityToBeRemoved);
            em.getTransaction().commit();
        } catch (PersistenceException pe) {
            em.close();
            LOG.error("DAO<" + entityClass.getSimpleName()
                            + ">:Error has occurred when trying to delete entity");
            throw new DAOException(pe);
        }
    }

    /**
     * 
     * @return List of WorkEntities
     * @throws DAOException
     */
    @SuppressWarnings("unchecked")
    public List<T> findEntities() throws DAOException {
        try {
            Query query = getEntityManager().createQuery(
                            "Select x from " + entityClass.getSimpleName() + " x");
            List<T> resultList = query.getResultList();
            return resultList;
        } catch (PersistenceException pe) {
            LOG.error("DAO<" + entityClass.getSimpleName()
                            + ">:Error has occurred when trying to find entities");
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
    public List<T> findEntities(String col, Object value) throws DAOException {

        try {
            Query query = getEntityManager().createQuery(
                            "Select x from " + entityClass.getSimpleName() + " x where x." + col
                                            + " = ?1");
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
     * 
     * @param search
     *            Fields for the search
     * @return a List of Entities from the Database or an empty List if the
     *         WebFormSearch has no variables set.
     * @throws DAOException
     *             if the database raises an error
     */
    public List<T> findEntities(WebFormSearch search) throws DAOException {
        try {
            return getResultListUsingWebSearchForm(search);
        } catch (PersistenceException pe) {
            LOG.error("DAO<" + entityClass.getSimpleName()
                            + ">:Error has occurred when trying to find entities");
            throw new DAOException(pe);
        } catch (NoSuchWebFormParserException nswe) {
            LOG.error(nswe.getMessage());
            throw new DAOException(nswe);
        }
    }

    private List<T> getResultListUsingWebSearchForm(WebFormSearch search)
                    throws NoSuchWebFormParserException, PersistenceException {
        String textQuery = parseWebFormSearch(search);
        if (textQuery.length() == 0) {
            return new ArrayList<T>();
        }
        
        LOG.debug("FindEntities(Search) query is :" + textQuery);

        Query query = generateQueryFromWebForm(search, textQuery);
        @SuppressWarnings("unchecked")
        List<T> resultList = query.getResultList();
        return resultList;
    }

    /**
     * Creates a Query in String form
     * 
     * @param search WebSearchForm to be parsed
     * @return String query
     * @throws NoSuchWebFormParserException if an appropriate WebFormParser is not found
     * @see {@link org.amc.servlet.action.search.WebFormSearch.WebFormSearchToJPQLParser WebFormSearchToJPQLParser}
     */
    private String parseWebFormSearch(WebFormSearch search) throws NoSuchWebFormParserException {
        WebFormSearchToJPQLParser parser = WebFormSearchParserFactory
                        .getWebFormSearchParser(search);
        return parser.parse(this.entityClass, search);
    }

    /**
     * Generates a JPA Query from a WebFormSearch Object and Query String
     * 
     * @param search a WebFormSearch containing the fields required to generate the fields
     * @param textQuery A String query to be passed to the Query object
     * @return A JPA Query
     */
    private Query generateQueryFromWebForm(WebFormSearch search,String textQuery){
        Query query = getEntityManager().createQuery(textQuery);
        for (Iterator<SearchFields> i = search.getFields().iterator(); i.hasNext();) {
            SearchFields currentField = i.next();
            Object value = search.getField(currentField);
            query.setParameter(currentField.name(), value);
            LOG.debug("Setting field:" + currentField.name() + " to " + value.toString());
        }
        return query;
    }
    /**
     * @param workEntityId
     * @return MouldingProcess or null if not found
     * @throws DAOException
     */
    @SuppressWarnings("unchecked")
    public T getEntity(String workEntityId) throws DAOException {

        Query query = getEntityManager().createQuery(
                        "Select x from " + entityClass.getSimpleName() + " x where x.id="
                                        + workEntityId + "");
        try {
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
     * 
     * @return Class which this DAO is handing
     */
    protected Class<? extends WorkEntity> getEntityClass() {
        return this.entityClass;
    }

    /**
     * 
     * @return EntityManager for subclass to use
     */
    public EntityManager getEntityManager() {
        return EntityManagerThreadLocal.getEntityManager();
    }

    /**
     * Overrides Object toString()
     * 
     * @return String representation
     */
    @Override
    public String toString() {
        return "DAO<" + getEntityClass().getSimpleName() + ">";
    }

    /**
     * @param entity
     * @throws DAOException
     */
    public void updateEntity(T entity) throws DAOException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } catch (PersistenceException pe) {
            em.getTransaction().rollback();
            em.close();
            LOG.error("DAO<"
                            + entityClass.getSimpleName()
                            + ">:Error has occurred when trying to merge entity into the persistence context");
            throw new DAOException(pe);
        }

    }

}
