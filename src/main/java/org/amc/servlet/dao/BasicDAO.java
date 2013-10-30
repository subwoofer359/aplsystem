package org.amc.servlet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

public class BasicDAO implements DAO 
{
	public Connection getConnection() throws SQLException
	{
		Connection connection=null;
		//DataSource dataSource=DataSourceCache.getInstance().getDataSource(); connection=dataSource.getConnection();
		Properties prop=new Properties();
		prop.put("user","adminYTspVIn");
		prop.put("password", "iDwuSCz7GaUK");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://127.12.78.130:3306/myservlet",prop);
			
		}
		catch(ClassNotFoundException ce)
		{
			ce.printStackTrace();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
			throw new SQLException(se.getMessage());
		}
		//Connection connection=DriverManager.getConnection("jdbc:mysql://192.168.1.105/aplsystem",prop);
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
