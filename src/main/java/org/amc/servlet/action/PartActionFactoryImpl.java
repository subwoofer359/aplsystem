package org.amc.servlet.action;

import org.amc.dao.PartDAOBeanRemote;
import org.springframework.beans.factory.annotation.Autowired;

public class PartActionFactoryImpl implements PartActionFactory
{

	private PartDAOBeanRemote jobDAO;
	@Autowired
	public PartActionFactoryImpl(PartDAOBeanRemote jobDAO)
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
