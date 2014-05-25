package org.amc.servlet.action;
/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
import org.amc.dao.PartDAO;
import org.springframework.beans.factory.annotation.Autowired;

public class PartActionFactoryImpl implements PartActionFactory
{

	private PartDAO jobDAO;
	@Autowired
	public PartActionFactoryImpl(PartDAO jobDAO)
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
