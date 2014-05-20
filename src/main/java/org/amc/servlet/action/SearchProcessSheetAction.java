package org.amc.servlet.action;

import java.sql.SQLException;
import java.util.List;

import org.amc.model.MouldingProcessRemote;
import org.amc.dao.MouldingProcessDAORemote;;

public class SearchProcessSheetAction 
{
	
	private MouldingProcessDAORemote mouldingProcessDAO;
	
	public SearchProcessSheetAction(MouldingProcessDAORemote mouldingProcessDAO)
	{
		this.mouldingProcessDAO=mouldingProcessDAO;
	}
	
	public List<MouldingProcessRemote> search() throws SQLException
	{	
		return mouldingProcessDAO.findProcessSheets();
		
	}
	
	public List<MouldingProcessRemote> search(String item,String value) throws SQLException
	{	
		return mouldingProcessDAO.findProcessSheets(item, value);
		
	}
	
	public MouldingProcessRemote getMouldingProcess(String id) throws SQLException
	{
		return mouldingProcessDAO.getProcessSheet(id);
	}

}
