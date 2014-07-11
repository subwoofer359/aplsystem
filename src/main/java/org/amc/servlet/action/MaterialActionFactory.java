package org.amc.servlet.action;

import java.io.Serializable;

/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
public interface MaterialActionFactory extends Serializable
{
	public SearchMaterialAction getSearchMaterialAction();
	
	public SaveMaterialAction getSaveMaterialAction();
}
