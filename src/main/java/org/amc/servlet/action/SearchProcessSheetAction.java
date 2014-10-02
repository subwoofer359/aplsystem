package org.amc.servlet.action;
/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
import org.amc.DAOException;
import org.amc.dao.DAO;
import org.amc.model.MouldingProcess;
import org.amc.servlet.action.search.MouldingProcessSearch;

import java.util.List;

public class SearchProcessSheetAction 
{
	
	private final DAO<MouldingProcess> mouldingProcessDAO;
	
	public SearchProcessSheetAction(DAO<MouldingProcess> mouldingProcessDAO)
	{
		this.mouldingProcessDAO=mouldingProcessDAO;
	}
	
	public List<MouldingProcess> search() throws DAOException
	{	
		return mouldingProcessDAO.findEntities();
		
	}
	
	public List<MouldingProcess> search(String item,String value) throws DAOException
	{	
		return mouldingProcessDAO.findEntities(item, value);
		
	}
	
	public MouldingProcess getMouldingProcess(String id) throws DAOException
	{
		return mouldingProcessDAO.getEntity(id);
	}
	
	public List<MouldingProcess> search(MouldingProcessSearch mouldingProcessSearch) throws DAOException
	{
		return mouldingProcessDAO.findEntities(mouldingProcessSearch);
	}

}
