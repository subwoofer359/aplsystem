package org.amc.servlet.dao;

public class DAOFactory 
{

	public static JobTemplateDAO getJobTemplateDAO()
	{
		return new JobTemplateDAOImpl();
	}
}
