package org.amc.servlet.action;

public interface JobActionFactory
{
	public SaveJobTemplateAction getSaveJobTemplateAction();
	
	public SearchJobTemplateAction getSearchJobTemplateAction();
}
