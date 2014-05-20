package org.amc.dao;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import org.amc.model.MouldingProcessRemote;

@Remote
public interface MouldingProcessDAORemote 
{

	public abstract void addProcessSheet(MouldingProcessRemote process) throws SQLException;

	public abstract void updateProcessSheet(MouldingProcessRemote process) throws SQLException;

	public abstract void deleteProcessSheet(MouldingProcessRemote process) throws SQLException;

	public abstract MouldingProcessRemote getProcessSheet(String processId) throws SQLException;

	public abstract List<MouldingProcessRemote> findProcessSheets(String col, String value) throws SQLException;
	
	public abstract List<MouldingProcessRemote> findProcessSheets() throws SQLException;
}
