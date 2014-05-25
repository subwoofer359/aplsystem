package org.amc.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

import org.apache.log4j.Logger;

/**
 * Fetches and holds a reference to the Persistence EntityManager
 * @author adrian
 *
 */
public abstract class DAO
{
	private static Logger logger=Logger.getLogger(DAO.class);
	private EntityManagerFactory emf;
	private EntityManager em;
	
	private final String PERSISTENCE_UNIT_NAME="myDatabase";
	
	public DAO()
	{
		emf=Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em=emf.createEntityManager();
	}
	
	@PersistenceUnit(name = PERSISTENCE_UNIT_NAME)
	public void setEm(EntityManager em)
	{
		logger.info("EntityManager set to "+em.toString());
		this.em = em;
	}
	
	/**
	 * 
	 * @return EntityManager for subclass to use
	 */
	protected EntityManager getEntityManager()
	{
		return em;
	}
}
