package org.amc.servlet.action;

public interface MaterialActionFactory
{
	public SearchMaterialAction getSearchMaterialAction();
	
	public SaveMaterialAction getSaveMaterialAction();
}
