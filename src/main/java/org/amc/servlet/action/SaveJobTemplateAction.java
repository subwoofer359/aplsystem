package org.amc.servlet.action;

import java.sql.SQLException;

import org.amc.servlet.dao.BasicDAO;
import org.amc.servlet.dao.DAO;
import org.amc.servlet.dao.DAOFactory;
import org.amc.servlet.dao.JobTemplateDAO;
import org.amc.servlet.model.JobTemplate;

public class SaveJobTemplateAction 
{
	/**
	 * Saves Job to the database as a new entry
	 * @param job
	 * @throws SQLException
	 */
	public void save(JobTemplate job) throws SQLException
	{
		JobTemplateDAO jobTemplateDAO=DAOFactory.getJobTemplateDAO();
		jobTemplateDAO.addJobTemplate(job);
		
		
	}
	
	/**
	 * Updates database entry
	 * @param job
	 * @throws SQLException
	 */
	public void edit(JobTemplate job) throws SQLException
	{
		JobTemplateDAO jobTemplateDAO=DAOFactory.getJobTemplateDAO();
		jobTemplateDAO.updateJobTemplate(job);
	}
}
