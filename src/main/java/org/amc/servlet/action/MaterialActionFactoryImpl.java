package org.amc.servlet.action;

import org.amc.dao.DAO;
import org.amc.model.Material;
import org.amc.servlet.action.search.MaterialSearch;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
public class MaterialActionFactoryImpl implements ActionFactory<Material, MaterialSearch> {
    private final DAO<Material> materialDAO;

    @Autowired
    public MaterialActionFactoryImpl(DAO<Material> materialDAO) {
        this.materialDAO = materialDAO;
    }

    @Override
    public SearchAction<Material, MaterialSearch> getSearchAction() {
        return new SearchAction<Material, MaterialSearch>(materialDAO);
    }

    public SaveAction<Material> getSaveAction() {
        return new SaveAction<Material>(materialDAO);
    }

}
