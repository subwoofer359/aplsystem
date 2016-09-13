package org.amc.dao.parsers;

import org.amc.model.Part;
import org.amc.servlet.action.search.PartSearch;
import org.amc.servlet.action.search.WebFormSearch;


import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class PartSearchParser extends AbstractWFSearchToQuery {
    
    private CriteriaBuilder cb;
    
    public PartSearchParser(EntityManager entityManager) {
        super(entityManager);
        cb = getCriteriaBuilder();
    }

    @Override
    public CriteriaQuery<Part> createCriteriaQuery(WebFormSearch webFormSearch) {
        PartSearch search = (PartSearch) webFormSearch;
        CriteriaQuery<Part> cQuery = cb.createQuery(Part.class);
        Root<Part> part = cQuery.from(Part.class);
        
        QueryBuilder builder = new QueryBuilder(cb, part);
        
        if(!search.isEmpty()) {
                      
            builder.likeStringField(search.getCompany(), "company");
            
            builder.likeStringField(search.getPartName(), "name");
            
            builder.likeStringField(search.getQSSNumber(), "qss_no");
            
            cQuery.where(cb.and(builder.getArrayOfPredicates()));
            cQuery.select(part);
        }
        
        return cQuery;
    }
    
}
