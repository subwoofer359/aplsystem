package org.amc.servlet.action;

import java.sql.SQLException;

import org.amc.dao.PartDAORemote;
import org.amc.model.PartRemote;

public class SavePartAction 
{
	private PartDAORemote partDAO;
	
	public SavePartAction(PartDAORemote jobTemplateDAO)
	{
		this.partDAO=jobTemplateDAO;
	}
	/**
	 * Saves Job to the database as a new entry
	 * @param job
	 * @throws SQLException
	 */
	public void save(PartRemote job) throws SQLException
	{
		partDAO.addPart(job);
		
		
	}
	
	/**
	 * Updates database entry
	 * @param job
	 * @throws SQLException
	 */
	public void edit(PartRemote job) throws SQLException
	{
		partDAO.updatePart(job);
	}
}
