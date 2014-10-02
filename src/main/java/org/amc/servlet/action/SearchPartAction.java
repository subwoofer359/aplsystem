package org.amc.servlet.action;
/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
import org.amc.DAOException;
import org.amc.dao.DAO;
import org.amc.model.Part;
import org.amc.servlet.action.search.PartSearch;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SearchPartAction 
{
	private final DAO<Part> partDAO;
	
	@Autowired
	public SearchPartAction(DAO<Part> jobTemplateDAO)
	{
		this.partDAO=jobTemplateDAO;
	}
	
	public List<Part> search() throws DAOException
	{
		return partDAO.findEntities();
		
	}
	
	public List<Part> search(String item,String value) throws DAOException
	{
		return partDAO.findEntities(item,value);
		
	}
	
	public Part getPart(String id) throws DAOException
	{
		return partDAO.getEntity(id);
	}

	public List<Part> search(PartSearch partSearch) throws DAOException
	{
		return partDAO.findEntities(partSearch);
	}
}
