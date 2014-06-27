package org.amc.dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.amc.DAOException;
import org.amc.model.Part;
import org.amc.model.spc.SPCMeasurement;
import org.apache.log4j.Logger;

public class SPCMeasurementDAO extends DAO<SPCMeasurement>
{
	private static Logger logger=Logger.getLogger(SPCMeasurement.class);
	
	public SPCMeasurementDAO()
	{
		super(SPCMeasurement.class);
	}

	
	private String getUniqueTableName(SPCMeasurement entity) throws DAOException
	{
		long uuid=UUID.randomUUID().getMostSignificantBits();
		String tableName="table_"+entity.getDimension()+entity.getNominal()+uuid;
		tableName=tableName.replace('.', '_').replace(' ', '_').replace('-', '_');
		return tableName;
	}
	
	@Override
	public void addEntity(SPCMeasurement entity) throws DAOException
	{
		EntityManager em=getEntityManager();
		logger.debug("SPCMeasuremenDAO:tableID="+entity.getTableId());
		
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
					+ "measurement_id int NOT NULL, "
					+ "PRIMARY KEY(id))  ENGINE=InnoDB" ;
			logger.debug(queryString);
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
			logger.error("DAO<"+getEntityClass().getSimpleName()+">:Error has occurred when trying to add entity");
			throw new DAOException(pe);
		}
	}


	@Override
	public void updateEntity(SPCMeasurement entity) throws DAOException
	{
		if(!(entity.getTableId()==null || entity.getTableId().trim().equals("")))
		{
			super.updateEntity(entity);
		}
		
	}


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
				logger.debug("Trying to delete table:"+entity.getTableId());
				Query q=em.createNativeQuery("drop table if exists "+entity.getTableId());
				q.executeUpdate();
				em.getTransaction().commit();

				if(tableExists(entity.getTableId()))
				{
					logger.debug("Table:"+entity.getTableId()+" wasn't deleted");
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
	public boolean tableExists(String tableName)
	{
		EntityManager em=getEntityManager();
		em.getTransaction().begin();
		Query tableExists=em.createNativeQuery("SHOW TABLES");
		
		tableExists.executeUpdate();
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
	public boolean isTableEmpty(String tableName)
	{
		//Todo
		
		return true;
	}
	
	
}
