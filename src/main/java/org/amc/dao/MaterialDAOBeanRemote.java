package org.amc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.ejb.Remote;

import org.amc.model.MaterialBeanRemote;


@Remote
public interface MaterialDAOBeanRemote
{
	public abstract void addMaterial(MaterialBeanRemote material) throws SQLException;

	public abstract void updateMaterial(MaterialBeanRemote material) throws SQLException;
	
	public abstract MaterialBeanRemote getMaterial(String materialId) throws SQLException;

	public abstract Map<Integer,MaterialBeanRemote> findMaterials(String col, String value) throws SQLException;
	
	public abstract Map<Integer,MaterialBeanRemote> findMaterials() throws SQLException;
	
	public abstract Connection getConnection() throws SQLException;
}
