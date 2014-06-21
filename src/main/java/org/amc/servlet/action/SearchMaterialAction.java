package org.amc.servlet.action;
import org.amc.DAOException;
import java.util.Map;

import org.amc.model.Material;
import org.amc.dao.MaterialDAO;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
public class SearchMaterialAction
{

	private MaterialDAO materialDAO;
	
	@Autowired
	public SearchMaterialAction(MaterialDAO materialDAO)
	{
		this.materialDAO=materialDAO;
	}
	
	
	public Map<Integer,Material> search() throws DAOException
	{
		return materialDAO.findMaterials();
		
	}
	
	public Map<Integer,Material> search(String item,String value) throws DAOException
	{
		return materialDAO.findMaterials(item,value);
		
	}
	
	public Material getMaterial(String id) throws DAOException
	{
		return materialDAO.getEntity(id);
	}

}
