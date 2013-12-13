package org.amc.servlet.action;

import java.sql.SQLException;
import java.util.List;

import org.amc.servlet.dao.JobTemplateDAO;
import org.amc.servlet.model.JobTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class SearchJobTemplateAction 
{
	private JobTemplateDAO jobTemplateDAO;
	
	@Autowired
	public SearchJobTemplateAction(JobTemplateDAO jobTemplateDAO)
	{
		this.jobTemplateDAO=jobTemplateDAO;
	}
	public void search(JobTemplate job) throws SQLException
	{
		jobTemplateDAO.addJobTemplate(job);
		
	}
	
	public List<JobTemplate> search() throws SQLException
	{
		return jobTemplateDAO.findJobTemplates();
		
	}
	
	public List<JobTemplate> search(String item,String value) throws SQLException
	{
		return jobTemplateDAO.findJobTemplates(item,value);
		
	}
	
	public JobTemplate getJobTemplate(String id) throws SQLException
	{
		return jobTemplateDAO.getJobTemplate(id);
	}

}
