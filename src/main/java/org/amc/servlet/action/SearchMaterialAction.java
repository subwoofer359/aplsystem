package org.amc.servlet.action;

import org.amc.DAOException;
import org.amc.dao.MaterialDAO;
import org.amc.model.Material;
import org.amc.servlet.action.search.MaterialSearch;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
public class SearchMaterialAction {

    private final MaterialDAO materialDAO;

    @Autowired
    public SearchMaterialAction(MaterialDAO materialDAO) {
        this.materialDAO = materialDAO;
    }

    public Map<Integer, Material> search() throws DAOException {
        return materialDAO.findMaterials();

    }

    public Map<Integer, Material> search(String item, String value) throws DAOException {
        return materialDAO.findMaterials(item, value);

    }

    public Map<Integer, Material> search(MaterialSearch materialSearch) throws DAOException {
        return materialDAO.findMaterials(materialSearch);

    }

    public Material getMaterial(String id) throws DAOException {
        return materialDAO.getEntity(Integer.parseInt(id));
    }

}
