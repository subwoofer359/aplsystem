package org.amc.dao;

import org.amc.DAOException;
import org.amc.model.Material;
import org.amc.servlet.action.search.WebFormSearch;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Material Data Acess Object
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
public class MaterialDAO extends DAO<Material> implements Serializable {
    private static final long serialVersionUID = -4397260307883862647L;

    public MaterialDAO() {
        super(Material.class);
    }

    public Map<Integer, Material> findMaterials(String col, String value) throws DAOException {
        Map<Integer, Material> list = new TreeMap<Integer, Material>();
        List<Material> resultList = super.findEntities(col, value);
        for (Material m : resultList) {
            list.put(m.getId(), m);
        }
        return list;
    }

    public Map<Integer, Material> findMaterials() throws DAOException {
        List<Material> resultList = super.findEntities();
        Map<Integer, Material> list = new TreeMap<Integer, Material>();
        for (Material m : resultList) {
            list.put(m.getId(), m);
        }
        return list;
    }

    public Map<Integer, Material> findMaterials(WebFormSearch search) throws DAOException {
        Map<Integer, Material> list = new TreeMap<Integer, Material>();
        List<Material> materials = super.findEntities(search);
        for (Material m : materials) {
            list.put(m.getId(), m);
        }
        return list;
    }

}
