package org.amc.servlet.action;

import org.amc.DAOException;
import org.amc.dao.DAO;
import org.amc.model.WorkEntity;

public class SaveAction<T extends WorkEntity> {
    private final DAO<T> dao;

    public SaveAction(DAO<T> dao) {
        this.dao = dao;
    }

    /**
     * Saves Job to the database as a new entry
     * 
     * @param job
     * @throws DAOException
     */
    public void save(T workEntity) throws DAOException {
        dao.addEntity(workEntity);

    }

    /**
     * Updates database entry
     * 
     * @param job
     * @throws DAOException
     */
    public void edit(T workEntity) throws DAOException {
        dao.updateEntity(workEntity);
    }
}
