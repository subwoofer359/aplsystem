package org.amc.servlet.action;
/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
import java.sql.SQLException;

import org.amc.dao.DAO;
import org.amc.model.Part;

public class SavePartAction 
{
	private DAO<Part> partDAO;
	
	public SavePartAction(DAO<Part> jobTemplateDAO)
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
		partDAO.addEntity(job);
		
		
	}
	
	/**
	 * Updates database entry
	 * @param job
	 * @throws SQLException
	 */
	public void edit(Part job) throws SQLException
	{
		partDAO.updateEntity(job);
	}
}
