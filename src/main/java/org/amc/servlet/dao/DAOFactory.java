package org.amc.servlet.dao;

public class DAOFactory 
{

	public static PartDAO getJobTemplateDAO()
	{
		return new PartDAOImpl();
	}
	
	public static MouldingProcessDAO getMouldingProcessDAO()
	{
		return new MouldingProcessDAOImpl();
	}
	
}
