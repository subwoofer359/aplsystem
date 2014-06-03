package org.amc.servlet.action;
/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
import java.sql.SQLException;
import java.util.List;

import org.amc.model.MouldingProcess;
import org.amc.dao.DAO;

public class SearchProcessSheetAction 
{
	
	private DAO<MouldingProcess> mouldingProcessDAO;
	
	public SearchProcessSheetAction(DAO<MouldingProcess> mouldingProcessDAO)
	{
		this.mouldingProcessDAO=mouldingProcessDAO;
	}
	
	public List<MouldingProcess> search() throws SQLException
	{	
		return mouldingProcessDAO.findEntities();
		
	}
	
	public List<MouldingProcess> search(String item,String value) throws SQLException
	{	
		return mouldingProcessDAO.findEntities(item, value);
		
	}
	
	public MouldingProcess getMouldingProcess(String id) throws SQLException
	{
		return mouldingProcessDAO.getEntity(id);
	}

}
