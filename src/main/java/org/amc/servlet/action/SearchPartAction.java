package org.amc.servlet.action;

import java.sql.SQLException;
import java.util.List;

import org.amc.dao.PartDAO;
import org.amc.model.Part;
import org.springframework.beans.factory.annotation.Autowired;

public class SearchPartAction 
{
	private PartDAO partDAO;
	
	@Autowired
	public SearchPartAction(PartDAO jobTemplateDAO)
	{
		this.partDAO=jobTemplateDAO;
	}
	
	public List<Part> search() throws SQLException
	{
		return partDAO.findParts();
		
	}
	
	public List<Part> search(String item,String value) throws SQLException
	{
		return partDAO.findParts(item,value);
		
	}
	
	public Part getPart(String id) throws SQLException
	{
		return partDAO.getPart(id);
	}

}
