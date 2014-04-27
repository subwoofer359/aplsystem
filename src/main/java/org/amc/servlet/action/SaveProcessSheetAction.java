package org.amc.servlet.action;

import java.sql.SQLException;

import org.amc.model.MouldingProcessBeanRemote;
import org.amc.dao.MouldingProcessDAOBeanRemote;
import org.springframework.beans.factory.annotation.Autowired;

public class SaveProcessSheetAction 
{
	private MouldingProcessDAOBeanRemote mouldingProcessDAO;
	
	@Autowired
	public SaveProcessSheetAction(MouldingProcessDAOBeanRemote mouldingProcessDAO)
	{
		this.mouldingProcessDAO=mouldingProcessDAO;
	}
	/**
	 * Saves Job to the database as a new entry
	 * @param job
	 * @throws SQLException
	 */
	public void save(MouldingProcessBeanRemote processSheet) throws SQLException
	{
		mouldingProcessDAO.addProcessSheet(processSheet);
		
		
	}
	
	/**
	 * Updates database entry
	 * @param job
	 * @throws SQLException
	 */
	public void edit(MouldingProcessBeanRemote processSheet) throws SQLException
	{
		mouldingProcessDAO.updateProcessSheet(processSheet);
	}
}
