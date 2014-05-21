package org.amc.servlet.action;
import java.sql.SQLException;
import java.util.Map;

import org.amc.model.Material;
import org.amc.dao.MaterialDAO;
import org.springframework.beans.factory.annotation.Autowired;

public class SearchMaterialAction
{

	private MaterialDAO materialDAO;
	
	@Autowired
	public SearchMaterialAction(MaterialDAO materialDAO)
	{
		this.materialDAO=materialDAO;
	}
	
	
	public Map<Integer,Material> search() throws SQLException
	{
		return materialDAO.findMaterials();
		
	}
	
	public Map<Integer,Material> search(String item,String value) throws SQLException
	{
		return materialDAO.findMaterials(item,value);
		
	}
	
	public Material getMaterial(String id) throws SQLException
	{
		return materialDAO.getMaterial(id);
	}

}
