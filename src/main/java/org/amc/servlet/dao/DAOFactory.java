package org.amc.servlet.dao;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.amc.dao.MaterialDAORemote;
import org.amc.dao.MouldingProcessDAORemote;
import org.amc.dao.PartDAORemote;

public class DAOFactory 
{
	private InitialContext ctx;
	
	private static DAOFactory instance=null;
	
	private DAOFactory()
	{
		try
		{
			Properties props = new Properties();
			props.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.RemoteInitialContextFactory");
			props.put(Context.PROVIDER_URL,"ejbd://127.0.0.1:4201");
			ctx = new InitialContext(props);
		} catch (NamingException e)
		{
			e.printStackTrace();
		}
	}
	
	public static DAOFactory getInstance()
	{
		if(instance==null)
		{
			instance =new DAOFactory();
		}
		return instance;
	}

	public PartDAORemote getJobTemplateDAO()
	{
		PartDAORemote partDAO=null;
		
		try
		{
			partDAO=(PartDAORemote)ctx.lookup("PartDAORemote");
		} 
		catch (NamingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return partDAO;
	}
	
	public MouldingProcessDAORemote getMouldingProcessDAO()
	{
		MouldingProcessDAORemote mouldingProcessDAO=null;
		
		try
		{
			mouldingProcessDAO=(MouldingProcessDAORemote)ctx.lookup("MouldingProcessDAORemote");
		} 
		catch (NamingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mouldingProcessDAO;
	}
	
	public MaterialDAORemote getMaterialDAO()
	{
		MaterialDAORemote materialDAO=null;
		
		try
		{
			materialDAO=(MaterialDAORemote)ctx.lookup("MaterialDAORemote");
		} 
		catch (NamingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return materialDAO;
	}
	
}
