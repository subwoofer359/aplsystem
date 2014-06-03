package org.amc.servlet.action;
/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
import java.sql.SQLException;

import org.amc.model.MouldingProcess;
import org.amc.dao.DAO;
import org.springframework.beans.factory.annotation.Autowired;

public class SaveProcessSheetAction 
{
	private DAO<MouldingProcess> mouldingProcessDAO;
	
	@Autowired
	public SaveProcessSheetAction(DAO<MouldingProcess> mouldingProcessDAO)
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
		mouldingProcessDAO.addEntity(processSheet);
		
		
	}
	
	/**
	 * Updates database entry
	 * @param job
	 * @throws SQLException
	 */
	public void edit(MouldingProcess processSheet) throws SQLException
	{
		mouldingProcessDAO.updateEntity(processSheet);
	}
}
