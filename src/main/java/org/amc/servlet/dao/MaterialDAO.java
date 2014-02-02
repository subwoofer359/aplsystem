package org.amc.servlet.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.amc.servlet.model.Material;


public interface MaterialDAO
{
	public abstract Material getMaterial(String materialId) throws SQLException;

	public abstract List<Material> findMaterials(String col, String value) throws SQLException;
	
	public abstract List<Material> findMaterials() throws SQLException;
	
	public abstract void setConnection(Connection connection);
	
	public abstract Connection getConnection() throws SQLException;
}
