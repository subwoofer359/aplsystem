package org.amc.servlet.dao;

import java.sql.SQLException;
import java.util.List;

import org.amc.servlet.model.JobTemplate;

public interface JobTemplateDAO {

	public abstract void addJobTemplate(JobTemplate job) throws SQLException;

	public abstract void updateJobTemplate(JobTemplate job) throws SQLException;

	public abstract void deleteJobTemplate(JobTemplate job) throws SQLException;

	public abstract JobTemplate getJobTemplate(String jobTemplateId) throws SQLException;

	public abstract List<JobTemplate> findJobTemplates(String col, String value) throws SQLException;
	
	public abstract List<JobTemplate> findJobTemplates() throws SQLException;

}