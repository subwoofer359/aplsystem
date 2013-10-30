package org.amc.servlet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class BasicDAO implements DAO 
{
	public Connection getConnection() throws SQLException
	{
		//DataSource dataSource=DataSourceCache.getInstance().getDataSource();
		Connection connection=DriverManager.getConnection("mysql://adminYTspVIn:iDwuSCz7GaUK@127.12.78.130:3306");
		//return dataSource.getConnection();
		return connection;
		
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
