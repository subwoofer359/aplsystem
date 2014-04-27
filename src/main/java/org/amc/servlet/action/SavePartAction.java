package org.amc.servlet.action;

import java.sql.SQLException;

import org.amc.dao.PartDAOBeanRemote;
import org.amc.model.PartBeanRemote;

public class SavePartAction 
{
	private PartDAOBeanRemote partDAO;
	
	public SavePartAction(PartDAOBeanRemote jobTemplateDAO)
	{
		this.partDAO=jobTemplateDAO;
	}
	/**
	 * Saves Job to the database as a new entry
	 * @param job
	 * @throws SQLException
	 */
	public void save(PartBeanRemote job) throws SQLException
	{
		partDAO.addPart(job);
		
		
	}
	
	/**
	 * Updates database entry
	 * @param job
	 * @throws SQLException
	 */
	public void edit(PartBeanRemote job) throws SQLException
	{
		partDAO.updatePart(job);
	}
}
