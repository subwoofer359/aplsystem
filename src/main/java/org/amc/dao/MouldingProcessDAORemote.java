package org.amc.dao;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import org.amc.model.MouldingProcessBeanRemote;

@Remote
public interface MouldingProcessDAOBeanRemote 
{

	public abstract void addProcessSheet(MouldingProcessBeanRemote process) throws SQLException;

	public abstract void updateProcessSheet(MouldingProcessBeanRemote process) throws SQLException;

	public abstract void deleteProcessSheet(MouldingProcessBeanRemote process) throws SQLException;

	public abstract MouldingProcessBeanRemote getProcessSheet(String processId) throws SQLException;

	public abstract List<MouldingProcessBeanRemote> findProcessSheets(String col, String value) throws SQLException;
	
	public abstract List<MouldingProcessBeanRemote> findProcessSheets() throws SQLException;
}
