package org.amc.servlet.action;

import java.sql.SQLException;

import org.amc.servlet.dao.DAOFactory;
import org.amc.servlet.dao.MouldingProcessDAO;
import org.amc.servlet.model.MouldingProcess;

public class SaveProcessSheetAction 
{
	/**
	 * Saves Job to the database as a new entry
	 * @param job
	 * @throws SQLException
	 */
	public void save(MouldingProcess processSheet) throws SQLException
	{
		MouldingProcessDAO mouldingProcessDAO=DAOFactory.getMouldingProcessDAO();
		mouldingProcessDAO.addProcessSheet(processSheet);
		
		
	}
	
	/**
	 * Updates database entry
	 * @param job
	 * @throws SQLException
	 */
	public void edit(MouldingProcess processSheet) throws SQLException
	{
		MouldingProcessDAO mouldingProcessDAO=DAOFactory.getMouldingProcessDAO();
		mouldingProcessDAO.updateProcessSheet(processSheet);
	}
}
