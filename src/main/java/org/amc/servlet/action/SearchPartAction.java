package org.amc.servlet.action;
/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
import java.sql.SQLException;
import java.util.List;

import org.amc.dao.DAO;
import org.amc.model.Part;
import org.springframework.beans.factory.annotation.Autowired;

public class SearchPartAction 
{
	private DAO<Part> partDAO;
	
	@Autowired
	public SearchPartAction(DAO<Part> jobTemplateDAO)
	{
		this.partDAO=jobTemplateDAO;
	}
	
	public List<Part> search() throws SQLException
	{
		return partDAO.findEntities();
		
	}
	
	public List<Part> search(String item,String value) throws SQLException
	{
		return partDAO.findEntities(item,value);
		
	}
	
	public Part getPart(String id) throws SQLException
	{
		return partDAO.getEntity(id);
	}

}
