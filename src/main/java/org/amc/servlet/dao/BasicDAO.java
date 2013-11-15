package org.amc.servlet.dao;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.sql.DataSource;

public class BasicDAO implements DAO 
{
	public Connection getConnection() throws SQLException
	{  
		DataSource dataSource=DataSourceCache.getInstance().getDataSource(); 
		return dataSource.getConnection();
	
//		DriverManager method for creating a database connection
//		try {
//			Class.forName("com.mysql.jdbc.Driver").newInstance();
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Properties prop=new Properties();
//		prop.put("user","adrian");
//		prop.put("password", "cr2032ux");
//		Connection connection=DriverManager.getConnection("jdbc:mysql://192.168.1.105/aplsystem",prop);
		//return connection;
		
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
