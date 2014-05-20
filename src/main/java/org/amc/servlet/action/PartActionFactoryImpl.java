package org.amc.servlet.action;

import org.amc.dao.PartDAORemote;
import org.springframework.beans.factory.annotation.Autowired;

public class PartActionFactoryImpl implements PartActionFactory
{

	private PartDAORemote jobDAO;
	@Autowired
	public PartActionFactoryImpl(PartDAORemote jobDAO)
	{
		this.jobDAO=jobDAO;
	}
//	public SaveJobTemplateAction getSaveJobTemplateAction()
//	{
//		return new SaveJobTemplateAction(jobDAO);
//	}
	@Override
	public SavePartAction getSaveJobTemplateAction()
	{
		return new SavePartAction(this.jobDAO);
	}
	@Override
	public SearchPartAction getSearchJobTemplateAction()
	{ 
		return new SearchPartAction(this.jobDAO);
	}
}
