package org.amc.servlet.action;

import org.amc.DAOException;
import org.amc.dao.DAO;
import org.amc.model.WorkEntity;
import org.amc.servlet.action.search.WebFormSearch;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SearchAction<T extends WorkEntity, S extends WebFormSearch> {
    
    private DAO<T> dao;
    
    @Autowired
    public SearchAction(DAO<T> dao) {
        this.dao = dao;
    }

    public List<T> search() throws DAOException {
        return dao.findEntities();

    }

    public List<T> search(String item, String value) throws DAOException {
        return dao.findEntities(item, value);

    }

    public T get(String id) throws DAOException {
        return dao.getEntity(Integer.parseInt(id));
    }

    public List<? extends WorkEntity> search(S search) throws DAOException {
        return dao.findEntities(search);
    }
}
