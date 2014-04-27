package org.amc.servlet.action;

import org.amc.dao.MouldingProcessDAOBeanRemote;
import org.springframework.beans.factory.annotation.Autowired;

public class ProcessActionFactoryImpl implements ProcessActionFactory
{

	private MouldingProcessDAOBeanRemote mouldingProcessDAO;
	
	@Autowired
	public ProcessActionFactoryImpl(MouldingProcessDAOBeanRemote mouldingProcessDAO)
	{
		this.mouldingProcessDAO=mouldingProcessDAO;
	}
	
	@Override
	public SaveProcessSheetAction getSaveProcessSheetAction()
	{
		return new SaveProcessSheetAction(this.mouldingProcessDAO);
	}

	@Override
	public SearchProcessSheetAction getSearchProcessSheetAction()
	{
		return new SearchProcessSheetAction(this.mouldingProcessDAO);
	}

}
