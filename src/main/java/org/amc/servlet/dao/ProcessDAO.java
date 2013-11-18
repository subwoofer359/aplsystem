package org.amc.servlet.dao;

import java.sql.SQLException;
import java.util.List;

import org.amc.servlet.model.JobTemplate;

public interface ProcessDAO 
{

	public abstract void addProcess(Process process) throws SQLException;

	public abstract void updateProcess(Process process) throws SQLException;

	public abstract void deleteProcess(Process process) throws SQLException;

	public abstract JobTemplate getProcess(Process processId) throws SQLException;

	public abstract List<Process> findProcesses(String col, String value) throws SQLException;
	
	public abstract List<Process> findProcesses() throws SQLException;
}
