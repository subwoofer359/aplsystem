package org.amc.servlet.action;

import java.sql.SQLException;
import java.util.List;

import org.amc.servlet.dao.DAOFactory;
import org.amc.servlet.dao.MouldingProcessDAO;
import org.amc.servlet.model.MouldingProcess;

public class SearchProcessSheetAction 
{
	public void search(MouldingProcess process) throws SQLException
	{
		MouldingProcessDAO mouldingProcessDAO=DAOFactory.getMouldingProcessDAO();
		mouldingProcessDAO.addProcessSheet(process);
		
	}
	
	public List<MouldingProcess> search() throws SQLException
	{
		MouldingProcessDAO mouldingProcessDAO=DAOFactory.getMouldingProcessDAO();
		return mouldingProcessDAO.findProcessSheets();
		
	}
	
	public List<MouldingProcess> search(String item,String value) throws SQLException
	{
		MouldingProcessDAO mouldingProcessDAO=DAOFactory.getMouldingProcessDAO();
		return mouldingProcessDAO.findProcessSheets(item, value);
		
	}
	
	public MouldingProcess getMouldingProcess(String id) throws SQLException
	{
		MouldingProcessDAO mouldingProcessDAO=DAOFactory.getMouldingProcessDAO();
		return mouldingProcessDAO.getProcessSheet(id);
	}

}
