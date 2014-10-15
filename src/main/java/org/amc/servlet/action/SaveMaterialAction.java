package org.amc.servlet.action;

/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
import org.amc.DAOException;
import org.amc.dao.MaterialDAO;
import org.amc.model.Material;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

public class SaveMaterialAction {
    private final MaterialDAO materialDAO;

    @Autowired
    public SaveMaterialAction(MaterialDAO materialDAO) {
        this.materialDAO = materialDAO;
    }

    /**
     * Saves Job to the database as a new entry
     * 
     * @param job
     * @throws SQLException
     */
    public void save(Material material) throws DAOException {
        this.materialDAO.addEntity(material);

    }

    /**
     * Updates database entry
     * 
     * @param job
     * @throws SQLException
     */
    public void edit(Material material) throws DAOException {
        this.materialDAO.updateEntity(material);
    }
}
