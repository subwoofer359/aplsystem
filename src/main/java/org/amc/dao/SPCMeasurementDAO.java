package org.amc.dao;

import org.amc.DAOException;
import org.amc.model.Part;
import org.amc.model.WorkEntity;
import org.amc.model.spc.SPCMeasurement;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
/**
 * DAO for SPCMeasurement entities as they need to use SQL directly
 * @author Adrian Mclaughlin
 * @version 1
 */

public class SPCMeasurementDAO extends DAO<SPCMeasurement>
{
	/**
	 * Serializable UID
	 */
	private static final long serialVersionUID = 8865055871857791936L;
	
	/**
	 * logging service
	 */
	private static final Logger LOG=Logger.getLogger(SPCMeasurement.class);
	
	/**
	 * Constructor
	 */
	public SPCMeasurementDAO()
	{
		super(SPCMeasurement.class);
	}

	
	/**
	 * @param entity
	 * @return Unique Table name
	 */
	private String getUniqueTableName(SPCMeasurement entity)
	{
		long uuid=UUID.randomUUID().getMostSignificantBits();
		String tableName=("table_"+entity.getDimension())+entity.getNominal()+uuid;
		tableName=tableName.replace('.', '_').replace(' ', '_').replace('-', '_');
		return tableName;
	}
	
	/**
	 * @see {@link #addEntity(WorkEntity)}
	 * @param entity
	 * @throws DAOException
	 */
	@Override
	public void addEntity(SPCMeasurement entity) throws DAOException
	{
		EntityManager em=getEntityManager();
		LOG.debug("SPCMeasuremenDAO:tableID="+entity.getTableId());
		
		String queryString="";
		String tableName="";
		/*The SPCMeasurement has no tableId it must be new
		 * so create a new table and save the table's name
		 * in the tableId column
		 */
		if(entity.getTableId()==null || entity.getTableId().trim().equals(""))
		{
			tableName=getUniqueTableName(entity);
			queryString=
					"create table "+tableName+" (id int NOT NULL AUTO_INCREMENT, "
					+ "date Date NOT NULL,"
					+ "user_id int NOT NULL,"
					+ "measurementNumber int NOT NULL,"
					+ "measurement_id int NOT NULL,"
					+ "measurement float NOT NULL, "
					+ "PRIMARY KEY(id))  ENGINE=InnoDB" ;
			LOG.debug(queryString);
		}
		try
		{
			em.getTransaction().begin();
			if(entity.getTableId()==null || entity.getTableId().trim().equals(""))
			{
				Query query=em.createNativeQuery(queryString);
				query.setParameter(1, tableName);
				query.executeUpdate();
				
				entity.setTableId(tableName);
			}
			Part part=em.merge(entity.getPart());
			entity.setPart(part);
			em.persist(entity);
			em.getTransaction().commit();
		}
		catch(PersistenceException pe)
		{
			em.getTransaction().rollback();
			LOG.error("DAO<"+getEntityClass().getSimpleName()+">:Error has occurred when trying to add entity");
			throw new DAOException(pe);
		}
	}


	/**
	 * @see {{@link #updateEntity(WorkEntity)}};
	 * @param entity
	 * @throws DAOException
	 */
	@Override
	public void updateEntity(SPCMeasurement entity) throws DAOException
	{
		if(!(entity.getTableId()==null || entity.getTableId().trim().equals("")))
		{
			super.updateEntity(entity);
		}
		
	}
	
	/**
	 * @see {@link #deleteEntity(WorkEntity)}
	 * @param entity
	 * @throws DAOException 
	 */
	@Override
	public void deleteEntity(SPCMeasurement entity) throws DAOException
	{
		EntityManager em=getEntityManager();
		try
		{
			if(entity.getTableId()!=null && !entity.getTableId().trim().equals(""))
			{
				if(!isTableEmpty(entity.getTableId()))
				{
					throw new DAOException("Table is not empty");
				}
				
				
				em.getTransaction().begin();
				LOG.debug("Trying to delete table:"+entity.getTableId());
				Query query=em.createNativeQuery("drop table if exists "+entity.getTableId());
				query.executeUpdate();
				em.getTransaction().commit();

				if(doesTableExists(entity.getTableId()))
				{
					LOG.debug("Table:"+entity.getTableId()+" wasn't deleted");
					em.getTransaction().rollback();//todo Check to see if this works
					em.close();
					throw new DAOException();
				}
				
			}
			super.deleteEntity(entity);
		}
		catch(PersistenceException pe)
		{
			throw new DAOException(pe);
		}
	}
	
	/**
	 * 
	 * @param tableName
	 * @return true if the table exists in the database
	 */
	private boolean doesTableExists(String tableName)
	{
		EntityManager em=getEntityManager();
		em.getTransaction().begin();
		Query tableExists=em.createNativeQuery("SHOW TABLES");
		
		tableExists.executeUpdate();
		
		@SuppressWarnings("unchecked")
		List<String> tables=tableExists.getResultList();
		boolean result=false;
		if(tables.contains(tableName))
		{
			result=true;
		}
		em.getTransaction().commit();
		
		return result;
	}

	/**
	 * 
	 * @param tableName
	 * @return true if the table has no entries
	 */
	private boolean isTableEmpty(String tableName)
	{
		//todo for the delete method
		
		return true;
	}
	
	
}
