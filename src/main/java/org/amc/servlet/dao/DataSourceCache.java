package org.amc.servlet.dao;


import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;
import javax.sql.DataSource;


public class DataSourceCache 
{
	private static DataSourceCache instance;
	private DataSource dataSource;
	
	static
	{
		instance=new DataSourceCache();
	}
	
	private DataSourceCache()
	{
		Context context=null;
		
		try
		{
			context=new InitialContext();
			
			Context envCtx = (Context) context.lookup("java:comp/env");
			
			dataSource=(DataSource)envCtx.lookup("jdbc/APLSystem");
			System.err.println("Datasource:"+dataSource);
			
		}
		catch(NamingException ne)
		{
			
			ne.printStackTrace();
		}
		catch(ClassCastException cce)
		{
			cce.printStackTrace();
		}
	}
	

	
	public static DataSourceCache getInstance()
	{
		return instance;
	}
	
	public DataSource getDataSource()
	{
		
		return this.dataSource;
	}
}
