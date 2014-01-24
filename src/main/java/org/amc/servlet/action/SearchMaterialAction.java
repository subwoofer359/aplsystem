package org.amc.servlet.action;
import java.sql.SQLException;
import java.util.List;

import org.amc.servlet.dao.MaterialDAO;
import org.amc.servlet.model.Material;
import org.springframework.beans.factory.annotation.Autowired;

public class SearchMaterialAction
{

	private MaterialDAO materialDAO;
	
	@Autowired
	public SearchMaterialAction(MaterialDAO materialDAO)
	{
		this.materialDAO=materialDAO;
	}
	
	
	public List<Material> search() throws SQLException
	{
		return materialDAO.findMaterials();
		
	}
	
	public List<Material> search(String item,String value) throws SQLException
	{
		return materialDAO.findMaterials(item,value);
		
	}
	
	public Material getPart(String id) throws SQLException
	{
		return materialDAO.getMaterial(id);
	}

}
