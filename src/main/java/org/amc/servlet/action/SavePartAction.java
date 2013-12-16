package org.amc.servlet.action;

import java.sql.SQLException;

import org.amc.servlet.dao.PartDAO;
import org.amc.servlet.model.Part;
import org.springframework.beans.factory.annotation.Autowired;

public class SavePartAction 
{
	private PartDAO partDAO;
	
	public SavePartAction(PartDAO jobTemplateDAO)
	{
		this.partDAO=jobTemplateDAO;
	}
	/**
	 * Saves Job to the database as a new entry
	 * @param job
	 * @throws SQLException
	 */
	public void save(Part job) throws SQLException
	{
		partDAO.addPart(job);
		
		
	}
	
	/**
	 * Updates database entry
	 * @param job
	 * @throws SQLException
	 */
	public void edit(Part job) throws SQLException
	{
		partDAO.updatePart(job);
	}
}
