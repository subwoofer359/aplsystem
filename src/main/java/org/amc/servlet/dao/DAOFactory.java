package org.amc.servlet.dao;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.amc.dao.MaterialDAO;
import org.amc.dao.MouldingProcessDAO;
import org.amc.dao.PartDAO;

public class DAOFactory 
{
	private InitialContext ctx;
	
	private static DAOFactory instance=null;
	
	private DAOFactory()
	{
		try
		{
			Properties props = new Properties();
			props.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.InitialContextFactory");
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

	public PartDAO getJobTemplateDAO()
	{
		PartDAO partDAO=null;
		
		try
		{
			partDAO=(PartDAO)ctx.lookup("PartDAO");
		} 
		catch (NamingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return partDAO;
	}
	
	public MouldingProcessDAO getMouldingProcessDAO()
	{
		MouldingProcessDAO mouldingProcessDAO=null;
		
		try
		{
			mouldingProcessDAO=(MouldingProcessDAO)ctx.lookup("MouldingProcessDAO");
		} 
		catch (NamingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mouldingProcessDAO;
	}
	
	public MaterialDAO getMaterialDAO()
	{
		MaterialDAO materialDAO=null;
		
		try
		{
			materialDAO=(MaterialDAO)ctx.lookup("MaterialDAO");
		} 
		catch (NamingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return materialDAO;
	}
	
}
