package org.amc.servlet.action;

import org.amc.dao.MaterialDAO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
public class MaterialActionFactoryImpl implements MaterialActionFactory {
    private static final long serialVersionUID = 2021441937440247471L;
    private final MaterialDAO materialDAO;

    @Autowired
    public MaterialActionFactoryImpl(MaterialDAO materialDAO) {
        this.materialDAO = materialDAO;
    }

    @Override
    public SearchMaterialAction getSearchMaterialAction() {
        return new SearchMaterialAction(materialDAO);
    }

    public SaveMaterialAction getSaveMaterialAction() {
        return new SaveMaterialAction(materialDAO);
    }

}
