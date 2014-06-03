package org.amc.servlet.action;
/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
import org.amc.dao.DAO;
import org.amc.model.MouldingProcess;
import org.springframework.beans.factory.annotation.Autowired;

public class ProcessActionFactoryImpl implements ProcessActionFactory
{

	private DAO<MouldingProcess> mouldingProcessDAO;
	
	@Autowired
	public ProcessActionFactoryImpl(DAO<MouldingProcess> mouldingProcessDAO)
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
