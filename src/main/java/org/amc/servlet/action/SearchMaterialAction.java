package org.amc.servlet.action;
import java.sql.SQLException;
import java.util.Map;

import org.amc.model.MaterialBeanRemote;
import org.amc.dao.MaterialDAOBeanRemote;
import org.springframework.beans.factory.annotation.Autowired;

public class SearchMaterialAction
{

	private MaterialDAOBeanRemote materialDAO;
	
	@Autowired
	public SearchMaterialAction(MaterialDAOBeanRemote materialDAO)
	{
		this.materialDAO=materialDAO;
	}
	
	
	public Map<Integer,MaterialBeanRemote> search() throws SQLException
	{
		return materialDAO.findMaterials();
		
	}
	
	public Map<Integer,MaterialBeanRemote> search(String item,String value) throws SQLException
	{
		return materialDAO.findMaterials(item,value);
		
	}
	
	public MaterialBeanRemote getMaterial(String id) throws SQLException
	{
		return materialDAO.getMaterial(id);
	}

}
