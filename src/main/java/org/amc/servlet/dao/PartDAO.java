package org.amc.servlet.dao;

import java.sql.SQLException;
import java.util.List;

import org.amc.servlet.model.Part;

public interface PartDAO {

	public abstract void addPart(Part job) throws SQLException;

	public abstract void updatePart(Part job) throws SQLException;

	public abstract void deletePart(Part job) throws SQLException;

	public abstract Part getPart(String jobTemplateId) throws SQLException;

	public abstract List<Part> findParts(String col, String value) throws SQLException;
	
	public abstract List<Part> findParts() throws SQLException;

}