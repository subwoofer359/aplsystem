package org.amc.servlet.action;

import java.sql.SQLException;

import org.amc.model.MaterialRemote;
import org.amc.dao.MaterialDAORemote;
import org.springframework.beans.factory.annotation.Autowired;

public class SaveMaterialAction 
{
	private MaterialDAORemote materialDAO;
	
	@Autowired
	public SaveMaterialAction(MaterialDAORemote materialDAO)
	{
		this.materialDAO=materialDAO;
	}
	/**
	 * Saves Job to the database as a new entry
	 * @param job
	 * @throws SQLException
	 */
	public void save(MaterialRemote material) throws SQLException
	{
		this.materialDAO.addMaterial(material);
		
		
	}
	
	/**
	 * Updates database entry
	 * @param job
	 * @throws SQLException
	 */
	public void edit(MaterialRemote material) throws SQLException
	{
		this.materialDAO.updateMaterial(material);
	}
}
