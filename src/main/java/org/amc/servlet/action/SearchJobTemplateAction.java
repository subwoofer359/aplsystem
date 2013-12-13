package org.amc.servlet.action;

import java.sql.SQLException;
import java.util.List;

import org.amc.servlet.dao.DAOFactory;
import org.amc.servlet.dao.JobTemplateDAO;
import org.amc.servlet.model.JobTemplate;

public class SearchJobTemplateAction 
{
	public void search(JobTemplate job) throws SQLException
	{
		JobTemplateDAO jobTemplateDAO=DAOFactory.getJobTemplateDAO();
		jobTemplateDAO.addJobTemplate(job);
		
	}
	
	public List<JobTemplate> search() throws SQLException
	{
		JobTemplateDAO jobTemplateDAO=DAOFactory.getJobTemplateDAO();
		return jobTemplateDAO.findJobTemplates();
		
	}
	
	public List<JobTemplate> search(String item,String value) throws SQLException
	{
		JobTemplateDAO jobTemplateDAO=DAOFactory.getJobTemplateDAO();
		return jobTemplateDAO.findJobTemplates(item,value);
		
	}
	
	public JobTemplate getJobTemplate(String id) throws SQLException
	{
		JobTemplateDAO jobTemplateDAO=DAOFactory.getJobTemplateDAO();
		return jobTemplateDAO.getJobTemplate(id);
	}

}
