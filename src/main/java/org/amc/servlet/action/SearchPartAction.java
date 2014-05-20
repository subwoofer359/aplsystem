package org.amc.servlet.action;

import java.sql.SQLException;
import java.util.List;

import org.amc.dao.PartDAORemote;
import org.amc.model.PartRemote;
import org.springframework.beans.factory.annotation.Autowired;

public class SearchPartAction 
{
	private PartDAORemote partDAO;
	
	@Autowired
	public SearchPartAction(PartDAORemote jobTemplateDAO)
	{
		this.partDAO=jobTemplateDAO;
	}
	
	public List<PartRemote> search() throws SQLException
	{
		return partDAO.findParts();
		
	}
	
	public List<PartRemote> search(String item,String value) throws SQLException
	{
		return partDAO.findParts(item,value);
		
	}
	
	public PartRemote getPart(String id) throws SQLException
	{
		return partDAO.getPart(id);
	}

}
