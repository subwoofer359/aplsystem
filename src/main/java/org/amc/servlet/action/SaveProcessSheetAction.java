package org.amc.servlet.action;
/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
import org.amc.DAOException;

import org.amc.model.MouldingProcess;
import org.amc.dao.DAO;
import org.springframework.beans.factory.annotation.Autowired;

public class SaveProcessSheetAction 
{
	private final DAO<MouldingProcess> mouldingProcessDAO;
	
	@Autowired
	public SaveProcessSheetAction(DAO<MouldingProcess> mouldingProcessDAO)
	{
		this.mouldingProcessDAO=mouldingProcessDAO;
	}
	/**
	 * Saves Job to the database as a new entry
	 * @param job
	 * @throws DAOException
	 */
	public void save(MouldingProcess processSheet) throws DAOException
	{
		mouldingProcessDAO.addEntity(processSheet);
		
		
	}
	
	/**
	 * Updates database entry
	 * @param job
	 * @throws DAOException
	 */
	public void edit(MouldingProcess processSheet) throws DAOException
	{
		mouldingProcessDAO.updateEntity(processSheet);
	}
}
