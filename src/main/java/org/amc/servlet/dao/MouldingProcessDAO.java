package org.amc.servlet.dao;

import java.sql.SQLException;
import java.util.List;
import org.amc.servlet.model.MouldingProcess;

public interface MouldingProcessDAO 
{

	public abstract void addProcessSheet(MouldingProcess process) throws SQLException;

	public abstract void updateProcessSheet(MouldingProcess process) throws SQLException;

	public abstract void deleteProcessSheet(MouldingProcess process) throws SQLException;

	public abstract MouldingProcess getProcessSheet(String processId) throws SQLException;

	public abstract List<MouldingProcess> findProcessSheets(String col, String value) throws SQLException;
	
	public abstract List<MouldingProcess> findProcessSheets() throws SQLException;
}
