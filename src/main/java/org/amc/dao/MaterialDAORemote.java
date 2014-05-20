package org.amc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.ejb.Remote;

import org.amc.model.MaterialRemote;


@Remote
public interface MaterialDAORemote
{
	public abstract void addMaterial(MaterialRemote material) throws SQLException;

	public abstract void updateMaterial(MaterialRemote material) throws SQLException;
	
	public abstract MaterialRemote getMaterial(String materialId) throws SQLException;

	public abstract Map<Integer,MaterialRemote> findMaterials(String col, String value) throws SQLException;
	
	public abstract Map<Integer,MaterialRemote> findMaterials() throws SQLException;
	
	public abstract Connection getConnection() throws SQLException;
}
