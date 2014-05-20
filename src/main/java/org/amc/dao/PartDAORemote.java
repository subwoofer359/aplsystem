package org.amc.dao;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import org.amc.model.PartRemote;

@Remote
public interface PartDAORemote {

	public abstract void addPart(PartRemote job) throws SQLException;

	public abstract void updatePart(PartRemote job) throws SQLException;

	public abstract void deletePart(PartRemote job) throws SQLException;

	public abstract PartRemote getPart(String jobTemplateId) throws SQLException;

	public abstract List<PartRemote> findParts(String col, String value) throws SQLException;
	
	public abstract List<PartRemote> findParts() throws SQLException;

}