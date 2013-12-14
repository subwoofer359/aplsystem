package org.amc.servlet.action;

import org.amc.servlet.dao.JobTemplateDAO;
import org.springframework.beans.factory.annotation.Autowired;

public class JobActionFactoryImpl implements JobActionFactory
{

	private JobTemplateDAO jobDAO;
	@Autowired
	public JobActionFactoryImpl(JobTemplateDAO jobDAO)
	{
		this.jobDAO=jobDAO;
	}
//	public SaveJobTemplateAction getSaveJobTemplateAction()
//	{
//		return new SaveJobTemplateAction(jobDAO);
//	}
	@Override
	public SaveJobTemplateAction getSaveJobTemplateAction()
	{
		return new SaveJobTemplateAction(this.jobDAO);
	}
	@Override
	public SearchJobTemplateAction getSearchJobTemplateAction()
	{ 
		return new SearchJobTemplateAction(this.jobDAO);
	}
}
