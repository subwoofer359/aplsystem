package org.amc.servlet.action;
/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */


import org.amc.DAOException;
import org.amc.dao.DAO;
import org.amc.model.Part;

public class SavePartAction 
{
	private final DAO<Part> partDAO;
	
	public SavePartAction(DAO<Part> jobTemplateDAO)
	{
		this.partDAO=jobTemplateDAO;
	}
	/**
	 * Saves Job to the database as a new entry
	 * @param job
	 * @throws DAOException
	 */
	public void save(Part job) throws DAOException
	{
		partDAO.addEntity(job);
		
		
	}
	
	/**
	 * Updates database entry
	 * @param job
	 * @throws DAOException
	 */
	public void edit(Part job) throws DAOException
	{
		partDAO.updateEntity(job);
	}
}
