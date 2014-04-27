package org.amc.servlet.dao;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.amc.dao.MaterialDAOBeanRemote;
import org.amc.dao.MouldingProcessDAOBeanRemote;
import org.amc.dao.PartDAOBeanRemote;

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

	public PartDAOBeanRemote getJobTemplateDAO()
	{
		PartDAOBeanRemote partDAO=null;
		
		try
		{
			partDAO=(PartDAOBeanRemote)ctx.lookup("PartDAOBeanRemote");
		} 
		catch (NamingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return partDAO;
	}
	
	public MouldingProcessDAOBeanRemote getMouldingProcessDAO()
	{
		MouldingProcessDAOBeanRemote mouldingProcessDAO=null;
		
		try
		{
			mouldingProcessDAO=(MouldingProcessDAOBeanRemote)ctx.lookup("MouldingProcessDAOBeanRemote");
		} 
		catch (NamingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mouldingProcessDAO;
	}
	
	public MaterialDAOBeanRemote getMaterialDAO()
	{
		MaterialDAOBeanRemote materialDAO=null;
		
		try
		{
			materialDAO=(MaterialDAOBeanRemote)ctx.lookup("MaterialDAOBeanRemote");
		} 
		catch (NamingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return materialDAO;
	}
	
}
