package org.amc.servlet.action;
/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
import org.amc.DAOException;
import java.util.List;

import org.amc.model.MouldingProcess;
import org.amc.dao.DAO;

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

}
