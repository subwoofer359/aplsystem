package org.amc.servlet.action;

import java.sql.SQLException;

import org.amc.model.MaterialBeanRemote;
import org.amc.dao.MaterialDAOBeanRemote;
import org.springframework.beans.factory.annotation.Autowired;

public class SaveMaterialAction 
{
	private MaterialDAOBeanRemote materialDAO;
	
	@Autowired
	public SaveMaterialAction(MaterialDAOBeanRemote materialDAO)
	{
		this.materialDAO=materialDAO;
	}
	/**
	 * Saves Job to the database as a new entry
	 * @param job
	 * @throws SQLException
	 */
	public void save(MaterialBeanRemote material) throws SQLException
	{
		this.materialDAO.addMaterial(material);
		
		
	}
	
	/**
	 * Updates database entry
	 * @param job
	 * @throws SQLException
	 */
	public void edit(MaterialBeanRemote material) throws SQLException
	{
		this.materialDAO.updateMaterial(material);
	}
}
