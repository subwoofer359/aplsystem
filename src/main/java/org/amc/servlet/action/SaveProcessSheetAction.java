package org.amc.servlet.action;

import java.sql.SQLException;

import org.amc.model.MouldingProcessRemote;
import org.amc.dao.MouldingProcessDAORemote;
import org.springframework.beans.factory.annotation.Autowired;

public class SaveProcessSheetAction 
{
	private MouldingProcessDAORemote mouldingProcessDAO;
	
	@Autowired
	public SaveProcessSheetAction(MouldingProcessDAORemote mouldingProcessDAO)
	{
		this.mouldingProcessDAO=mouldingProcessDAO;
	}
	/**
	 * Saves Job to the database as a new entry
	 * @param job
	 * @throws SQLException
	 */
	public void save(MouldingProcessRemote processSheet) throws SQLException
	{
		mouldingProcessDAO.addProcessSheet(processSheet);
		
		
	}
	
	/**
	 * Updates database entry
	 * @param job
	 * @throws SQLException
	 */
	public void edit(MouldingProcessRemote processSheet) throws SQLException
	{
		mouldingProcessDAO.updateProcessSheet(processSheet);
	}
}
