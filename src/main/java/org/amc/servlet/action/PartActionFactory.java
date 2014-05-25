package org.amc.servlet.action;
/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
public interface PartActionFactory
{
	public SavePartAction getSaveJobTemplateAction();
	
	public SearchPartAction getSearchJobTemplateAction();
}
