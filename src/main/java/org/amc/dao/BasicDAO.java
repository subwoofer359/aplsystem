package org.amc.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.sql.DataSource;
/**
 * A base class for other Data Access Objects
 * @author Adrian McLaughlin
 * @version $REV$
 */
public class BasicDAOBean implements DAOBeanRemote, Serializable
{
	private static final long serialVersionUID = 2637719986763223271L;
	
	@Resource(name="MyDataSource")
	private DataSource dataSource;

	/**
	 * Non argument Constructor
	 */
	public BasicDAOBean()
	{
		;;
	}

	/**
	 * @return java.sql.Connection
	 * @throws SQLException if this data source is null
	 */
	public Connection getConnection() throws SQLException
	{
		return dataSource.getConnection();		
	}
	
	/**
	 * Closes java.sql objects after transaction is finished.
	 * Must be explicitly called by the user.
	 * @param resultSet
	 * @param statement
	 * @param connection
	 */
	protected void closeDBObjects(ResultSet resultSet,Statement statement,Connection connection)
	{
		if(resultSet!=null)
		{
			try
			{
				resultSet.close();
			}
			catch(SQLException se)
			{
			
			}
		}
		if(statement!=null)
		{
			try
			{
				statement.close();
			}
			catch(SQLException se)
			{
			
			}
		}
		if(connection!=null)
		{
			try
			{
				connection.close();
			}
			catch(SQLException se)
			{
			
			}
		}
	}
}
