package org.amc.dao;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import org.amc.model.PartBeanRemote;

@Remote
public interface PartDAOBeanRemote {

	public abstract void addPart(PartBeanRemote job) throws SQLException;

	public abstract void updatePart(PartBeanRemote job) throws SQLException;

	public abstract void deletePart(PartBeanRemote job) throws SQLException;

	public abstract PartBeanRemote getPart(String jobTemplateId) throws SQLException;

	public abstract List<PartBeanRemote> findParts(String col, String value) throws SQLException;
	
	public abstract List<PartBeanRemote> findParts() throws SQLException;

}