package org.amc.servlet.action;

import org.amc.dao.MouldingProcessDAORemote;
import org.springframework.beans.factory.annotation.Autowired;

public class ProcessActionFactoryImpl implements ProcessActionFactory
{

	private MouldingProcessDAORemote mouldingProcessDAO;
	
	@Autowired
	public ProcessActionFactoryImpl(MouldingProcessDAORemote mouldingProcessDAO)
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
