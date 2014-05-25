package org.amc.servlet.action;
/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
import java.sql.SQLException;
import java.util.List;

import org.amc.model.MouldingProcess;
import org.amc.dao.MouldingProcessDAO;;

public class SearchProcessSheetAction 
{
	
	private MouldingProcessDAO mouldingProcessDAO;
	
	public SearchProcessSheetAction(MouldingProcessDAO mouldingProcessDAO)
	{
		this.mouldingProcessDAO=mouldingProcessDAO;
	}
	
	public List<MouldingProcess> search() throws SQLException
	{	
		return mouldingProcessDAO.findProcessSheets();
		
	}
	
	public List<MouldingProcess> search(String item,String value) throws SQLException
	{	
		return mouldingProcessDAO.findProcessSheets(item, value);
		
	}
	
	public MouldingProcess getMouldingProcess(String id) throws SQLException
	{
		return mouldingProcessDAO.getProcessSheet(id);
	}

}
