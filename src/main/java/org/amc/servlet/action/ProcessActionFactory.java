package org.amc.servlet.action;
/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */

public interface ProcessActionFactory
{
	public  SaveProcessSheetAction getSaveProcessSheetAction();
	public  SearchProcessSheetAction getSearchProcessSheetAction();
}
