package org.amc.servlet.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.amc.servlet.model.Material;


public interface MaterialDAO
{
	public abstract Material getMaterial(String materialId) throws SQLException;

	public abstract Map<Integer,Material> findMaterials(String col, String value) throws SQLException;
	
	public abstract Map<Integer,Material> findMaterials() throws SQLException;
	
	public abstract Connection getConnection() throws SQLException;
}
