package org.amc.servlet.action;
/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
import java.sql.SQLException;

import org.amc.model.Material;
import org.amc.dao.MaterialDAO;
import org.springframework.beans.factory.annotation.Autowired;

public class SaveMaterialAction 
{
	private MaterialDAO materialDAO;
	
	@Autowired
	public SaveMaterialAction(MaterialDAO materialDAO)
	{
		this.materialDAO=materialDAO;
	}
	/**
	 * Saves Job to the database as a new entry
	 * @param job
	 * @throws SQLException
	 */
	public void save(Material material) throws SQLException
	{
		this.materialDAO.addEntity(material);
		
		
	}
	
	/**
	 * Updates database entry
	 * @param job
	 * @throws SQLException
	 */
	public void edit(Material material) throws SQLException
	{
		this.materialDAO.updateEntity(material);
	}
}
