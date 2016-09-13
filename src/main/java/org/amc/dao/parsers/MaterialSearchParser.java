package org.amc.dao.parsers;

import org.amc.model.Material;
import org.amc.servlet.action.search.MaterialSearch;
import org.amc.servlet.action.search.WebFormSearch;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class MaterialSearchParser extends AbstractWFSearchToQuery {
    
    private CriteriaBuilder cb;
    
    public MaterialSearchParser(EntityManager entityManager) {
        super(entityManager);
        cb = getCriteriaBuilder();
    }

    @Override
    public CriteriaQuery<Material> createCriteriaQuery(WebFormSearch webFormSearch) {
        MaterialSearch search = (MaterialSearch)webFormSearch;
        CriteriaQuery<Material> cQuery = cb.createQuery(Material.class);
        Root<Material> material = cQuery.from(Material.class);
        
        QueryBuilder builder = new QueryBuilder(cb, material);
        
        if(!search.isEmpty()) {

            builder.likeStringField(search.getCompany(), "company");
            
            builder.likeStringField(search.getName(), "name");

            builder.likeStringField(search.getType(), "type");
            
            cQuery.where(cb.and(builder.getArrayOfPredicates()));
            cQuery.select(material);
        }
        
        return cQuery;
    }
    
    
}
