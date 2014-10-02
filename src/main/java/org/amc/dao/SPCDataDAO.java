package org.amc.dao;

import org.amc.DAOException;
import org.amc.model.spc.SPCData;
import org.amc.model.spc.SPCMeasurement;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

/**
 * DAO for SPCData
 * 
 * @author Adrian McLaughlin
 *
 */
public class SPCDataDAO extends DAO<SPCData>
{
	/**
	 * Serializable
	 */
	private static final long serialVersionUID = 3406544673929777389L;
	
	/**
	 * Logger used by the object
	 */
	private static Logger LOG=Logger.getLogger(SPCDataDAO.class);
	
	public SPCDataDAO()
	{
		super(SPCData.class);
	}
	
	/**
	 * 
	 * @param measurement SPCMeasurement related to the SPCData being saved. Contains reference to the database table in which to save the SPCData
	 * @param entities List of SPCData to be saved
	 * @throws DAOException if exception is thrown by the database
	 * @throws SQLException 
	 */
	public void addEntities(SPCMeasurement measurement,List<SPCData> entities) throws DAOException
	{
		EntityManager manager=getEntityManager();
		Connection connection=manager.unwrap(java.sql.Connection.class);
		PreparedStatement statement=null;
		try
		{
			connection.setAutoCommit(false);
			for(SPCData entity:entities)
			{
				statement=connection.prepareStatement("insert into "+measurement.getTableId()+" values(default,?,?,?,?,?);");
				statement.setDate(1, entity.getDate());
				statement.setInt(2, entity.getUser().getId());
				statement.setInt(3, entity.getMeasurementNumber());
				statement.setFloat(4, entity.getSpcMeasurement().getId());
				statement.setFloat(5, entity.getMeasurement());
				statement.execute();
			}
			connection.commit();
			connection.setAutoCommit(true);
		}
		catch(SQLException se)
		{
			LOG.error("SPCDataDAO:SQLException raised when adding List of Entities to database:"+se.getMessage());
			try
			{
				connection.rollback();
				LOG.debug("Database transaction rolled back");
				throw new DAOException(se);
			}
			catch(SQLException se2)
			{
				LOG.error("SPCDataDAO:SQLException raised when closing connection to database:"+se.getMessage());
				throw new DAOException(se2);
			}
		}
		finally
		{
			try
			{
				if(statement!=null)
				{
					statement.close();
				}
				connection.close();
			}
			catch(SQLException se)
			{
				throw new DAOException(se);
			}
		}
	}

	//Behaviour has not yet been defined
	@Override
	public void addEntity(SPCData entity) throws DAOException
	{throw new NoSuchMethodError();

	}
	//Behaviour has not yet been defined
	@Override
	public void updateEntity(SPCData entity) throws DAOException
	{
		throw new NoSuchMethodError();
	}
	//Behaviour has not yet been defined
	@Override
	public void deleteEntity(SPCData entity) throws DAOException
	{
		throw new NoSuchMethodError();
	}
	//Behaviour has not yet been defined
	@Override
	public SPCData getEntity(String workEntityId) throws DAOException
	{
		throw new NoSuchMethodError();
	}
	//Behaviour has not yet been defined
	@Override
	public List<SPCData> findEntities(String col, Object value) throws DAOException
	{
		throw new NoSuchMethodError();
	}
	//Behaviour has not yet been defined
	@Override
	public List<SPCData> findEntities() throws DAOException
	{
		throw new NoSuchMethodError();
	}
	
	
}
