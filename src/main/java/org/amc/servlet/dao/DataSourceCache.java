package org.amc.servlet.dao;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class DataSourceCache
{
	private static DataSourceCache instance;
	private DataSource dataSource;

	private DataSourceCache(String url)
	{
		Context context=null;
		
		try
		{
			context=new InitialContext();
			Context envCtx = (Context) context.lookup("java:comp/env");	
			dataSource=(DataSource)envCtx.lookup(url); //Local testing only
			
		}
		catch(NamingException ne)
		{
			
			ne.printStackTrace();
		}
		catch(ClassCastException cce)
		{
			cce.printStackTrace();
		}
		finally
		{
			System.err.println("Datasource:"+dataSource);
		}
	}
	

	
	public static DataSourceCache getInstance(String url)
	{
		if(instance==null)
		{
			instance=new DataSourceCache(url);
		}
		return instance;
	}
	
	public DataSource getDataSource()
	{
		
		return this.dataSource;
	}
}
