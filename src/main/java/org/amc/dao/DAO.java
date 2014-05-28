package org.amc.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.apache.log4j.Logger;

/**
 * Fetches and holds a reference to the Persistence EntityManager
 * @author Adrian Mclaughlin
 * @version 1
 *
 */
public abstract class DAO
{
	private static Logger logger=Logger.getLogger(DAO.class);
	private EntityManagerFactory emf;

	
	private final String PERSISTENCE_UNIT_NAME="myDatabase";
	
	public DAO(EntityManagerFactory emf)
	{
		//emf=Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		this.emf=emf;
	
	}
	
	@PersistenceUnit(name = PERSISTENCE_UNIT_NAME)
	public void setEm(EntityManagerFactory emf)
	{
		logger.info("EntityManagerFactory set to "+emf.toString());
		this.emf = emf;
	}
	
	/**
	 * 
	 * @return EntityManager for subclass to use
	 */
	protected synchronized EntityManager getEntityManager()
	{
		return emf.createEntityManager();
	}
	
	@Override
	public void finalize()
	{
		if(emf!=null && emf.isOpen())
			emf.close();
	}
	
}
