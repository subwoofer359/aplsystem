package org.amc.servlet.action;

import java.io.Serializable;

/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
public interface MaterialActionFactory extends Serializable {
    /**
     * @return a SearchMaterialAction object
     */
    public SearchMaterialAction getSearchMaterialAction();

    /**
     * @return a SaveMaterialAction object
     */
    public SaveMaterialAction getSaveMaterialAction();
}
