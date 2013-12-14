package org.amc.servlet.action;

import java.sql.SQLException;

import org.amc.servlet.dao.JobTemplateDAO;
import org.amc.servlet.model.JobTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class SaveJobTemplateAction 
{
	private JobTemplateDAO jobTemplateDAO;
	
	public SaveJobTemplateAction(JobTemplateDAO jobTemplateDAO)
	{
		this.jobTemplateDAO=jobTemplateDAO;
	}
	/**
	 * Saves Job to the database as a new entry
	 * @param job
	 * @throws SQLException
	 */
	public void save(JobTemplate job) throws SQLException
	{
		jobTemplateDAO.addJobTemplate(job);
		
		
	}
	
	/**
	 * Updates database entry
	 * @param job
	 * @throws SQLException
	 */
	public void edit(JobTemplate job) throws SQLException
	{
		jobTemplateDAO.updateJobTemplate(job);
	}
}
