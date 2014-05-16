package org.amc.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.sql.DataSource;

public class BasicDAOBean implements DAOBeanRemote, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2637719986763223271L;
	
	@Resource(name="MyDataSource")
	private DataSource dataSource;

	
	public BasicDAOBean()
	{
		
	}

	public Connection getConnection() throws SQLException
	{
		return dataSource.getConnection();		
	}
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
