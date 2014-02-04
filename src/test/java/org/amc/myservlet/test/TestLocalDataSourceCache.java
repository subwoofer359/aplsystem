package org.amc.myservlet.test;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;



public class TestLocalDataSourceCache
{
	private static boolean FIRST_TIME=true;
	private static final String DATABASE="test_myservlet";
	private static TestLocalDataSourceCache instance;
	static
	{
		instance=new TestLocalDataSourceCache();
	}
	private DataSource dataSource;

	private TestLocalDataSourceCache()
	{
		try
		{
			ComboPooledDataSource dataSource = new ComboPooledDataSource();
			dataSource.setDriverClass("com.mysql.jdbc.Driver");
			dataSource.setUser("adrian");
			dataSource.setPassword("cr2032ux");
			dataSource.setJdbcUrl("jdbc:mysql://192.168.1.105/");
			dataSource.setMinPoolSize(5);                                     
			dataSource.setAcquireIncrement(5);
			dataSource.setMaxPoolSize(20);
			this.dataSource=dataSource;
			
		} catch (PropertyVetoException e)
		{
			e.printStackTrace();
		}
	}
	
	public static TestLocalDataSourceCache getInstance()
	{
		return instance;
	}
	
	/**
	 * On First Call No Database set
	 * On Second Call Database set
	 * On Third and more Calls no need to set Database again
	 * @return
	 */
	public DataSource getDataSource()
	{
		if(FIRST_TIME)
		{
			FIRST_TIME=false;
			return this.dataSource;
		}
		else
		{
			((ComboPooledDataSource)this.dataSource).setJdbcUrl("jdbc:mysql://192.168.1.105/"+DATABASE);
			return this.dataSource;
		}
			
		
	}
}
