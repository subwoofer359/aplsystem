package org.amc.servlet.action;

import java.sql.SQLException;
import java.util.List;

import org.amc.model.MouldingProcessBeanRemote;
import org.amc.dao.MouldingProcessDAOBeanRemote;;

public class SearchProcessSheetAction 
{
	
	private MouldingProcessDAOBeanRemote mouldingProcessDAO;
	
	public SearchProcessSheetAction(MouldingProcessDAOBeanRemote mouldingProcessDAO)
	{
		this.mouldingProcessDAO=mouldingProcessDAO;
	}
	
	public List<MouldingProcessBeanRemote> search() throws SQLException
	{	
		return mouldingProcessDAO.findProcessSheets();
		
	}
	
	public List<MouldingProcessBeanRemote> search(String item,String value) throws SQLException
	{	
		return mouldingProcessDAO.findProcessSheets(item, value);
		
	}
	
	public MouldingProcessBeanRemote getMouldingProcess(String id) throws SQLException
	{
		return mouldingProcessDAO.getProcessSheet(id);
	}

}
