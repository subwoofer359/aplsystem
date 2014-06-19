package org.amc.dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.amc.model.Part;
import org.amc.model.WorkEntity;
import org.amc.model.spc.SPCMeasurement;
import org.apache.log4j.Logger;

public class SPCMeasurementDAO extends DAO<SPCMeasurement>
{
	private static Logger logger=Logger.getLogger(SPCMeasurement.class);
	
	public SPCMeasurementDAO()
	{
		super(SPCMeasurement.class);
	}

	
	private String getUniqueTableName(SPCMeasurement entity)
	{
		long uuid=UUID.randomUUID().getMostSignificantBits();
		String tableName="table_"+entity.getDimension()+entity.getNominal()+uuid;
		tableName=tableName.replace('.', '_').replace(' ', '_').replace('-', '_');
		return tableName;
	}
	
	@Override
	public void addEntity(SPCMeasurement entity)
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
		synchronized (em)
		{
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
				logger.error("SPCMeasuremenDAO:"+pe.getMessage());
			}
			finally
			{
					
			}

		}
	}


	@Override
	public void updateEntity(SPCMeasurement entity)
	{
		if(!(entity.getTableId()==null || entity.getTableId().trim().equals("")))
		{
			super.updateEntity(entity);
		}
		
	}
	
	
	
	
}
