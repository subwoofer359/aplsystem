package org.amc.servlet.action;

import java.sql.SQLException;
import java.util.List;

import org.amc.dao.PartDAOBeanRemote;
import org.amc.model.PartBeanRemote;
import org.springframework.beans.factory.annotation.Autowired;

public class SearchPartAction 
{
	private PartDAOBeanRemote partDAO;
	
	@Autowired
	public SearchPartAction(PartDAOBeanRemote jobTemplateDAO)
	{
		this.partDAO=jobTemplateDAO;
	}
	
	public List<PartBeanRemote> search() throws SQLException
	{
		return partDAO.findParts();
		
	}
	
	public List<PartBeanRemote> search(String item,String value) throws SQLException
	{
		return partDAO.findParts(item,value);
		
	}
	
	public PartBeanRemote getPart(String id) throws SQLException
	{
		return partDAO.getPart(id);
	}

}
