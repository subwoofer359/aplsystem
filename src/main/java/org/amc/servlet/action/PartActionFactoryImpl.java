package org.amc.servlet.action;

/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */

import org.amc.dao.DAO;
import org.amc.model.Part;
import org.amc.servlet.action.search.PartSearch;
import org.springframework.beans.factory.annotation.Autowired;

public class PartActionFactoryImpl implements ActionFactory<Part, PartSearch> {
    private DAO<Part> dao;
    
    @Autowired
    public PartActionFactoryImpl(DAO<Part> dao) {
        this.dao = dao;
    }
    @Override
    public SearchAction<Part, PartSearch> getSearchAction() {
        return new SearchAction<Part, PartSearch>(dao);
    }

    @Override
    public SaveAction<Part> getSaveAction() {
        return new SaveAction<Part>(dao);
    }
    
}
