package org.amc.servlet.dao;

import javax.sql.DataSource;

public class DAOFactory 
{
	private DataSource dataSource;
	
	public DAOFactory(DataSource dataSource)
	{
		this.dataSource=dataSource;
	}

	public PartDAO getJobTemplateDAO()
	{
		return new PartDAOImpl(dataSource);
	}
	
	public MouldingProcessDAO getMouldingProcessDAO()
	{
		return new MouldingProcessDAOImpl(dataSource);
	}
	
	public MaterialDAO getMaterialDAO()
	{
		return new MaterialDAOImpl(dataSource);
	}
	
}
