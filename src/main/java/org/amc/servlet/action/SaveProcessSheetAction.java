package org.amc.servlet.action;

import java.sql.SQLException;

import org.amc.model.MouldingProcess;
import org.amc.dao.MouldingProcessDAO;
import org.springframework.beans.factory.annotation.Autowired;

public class SaveProcessSheetAction 
{
	private MouldingProcessDAO mouldingProcessDAO;
	
	@Autowired
	public SaveProcessSheetAction(MouldingProcessDAO mouldingProcessDAO)
	{
		this.mouldingProcessDAO=mouldingProcessDAO;
	}
	/**
	 * Saves Job to the database as a new entry
	 * @param job
	 * @throws SQLException
	 */
	public void save(MouldingProcess processSheet) throws SQLException
	{
		mouldingProcessDAO.addProcessSheet(processSheet);
		
		
	}
	
	/**
	 * Updates database entry
	 * @param job
	 * @throws SQLException
	 */
	public void edit(MouldingProcess processSheet) throws SQLException
	{
		mouldingProcessDAO.updateProcessSheet(processSheet);
	}
}
