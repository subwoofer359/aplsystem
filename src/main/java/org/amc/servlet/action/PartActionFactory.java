package org.amc.servlet.action;

import java.io.Serializable;

/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
public interface PartActionFactory extends Serializable
{
	public SavePartAction getSaveJobTemplateAction();
	
	public SearchPartAction getSearchJobTemplateAction();
}
