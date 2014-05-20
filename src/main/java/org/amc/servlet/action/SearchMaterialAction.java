package org.amc.servlet.action;
import java.sql.SQLException;
import java.util.Map;

import org.amc.model.MaterialRemote;
import org.amc.dao.MaterialDAORemote;
import org.springframework.beans.factory.annotation.Autowired;

public class SearchMaterialAction
{

	private MaterialDAORemote materialDAO;
	
	@Autowired
	public SearchMaterialAction(MaterialDAORemote materialDAO)
	{
		this.materialDAO=materialDAO;
	}
	
	
	public Map<Integer,MaterialRemote> search() throws SQLException
	{
		return materialDAO.findMaterials();
		
	}
	
	public Map<Integer,MaterialRemote> search(String item,String value) throws SQLException
	{
		return materialDAO.findMaterials(item,value);
		
	}
	
	public MaterialRemote getMaterial(String id) throws SQLException
	{
		return materialDAO.getMaterial(id);
	}

}
