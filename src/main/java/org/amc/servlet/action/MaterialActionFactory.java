package org.amc.servlet.action;
/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
public interface MaterialActionFactory
{
	public SearchMaterialAction getSearchMaterialAction();
	
	public SaveMaterialAction getSaveMaterialAction();
}
