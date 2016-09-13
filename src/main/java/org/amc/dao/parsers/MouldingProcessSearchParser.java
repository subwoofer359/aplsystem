package org.amc.dao.parsers;

import org.amc.model.MouldingProcess;
import org.amc.servlet.action.search.MouldingProcessSearch;

import org.amc.servlet.action.search.WebFormSearch;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Parses a MouldingProcessSearchForm object
 * 
 * @author Adrian McLaughlin
 * @version 1
 *
 */
public final class MouldingProcessSearchParser extends AbstractWFSearchToQuery {
    
    private CriteriaBuilder cb;
    public MouldingProcessSearchParser(EntityManager entityManager) {
        super(entityManager);
        cb = getCriteriaBuilder();
    }
    
    @Override
    public CriteriaQuery<MouldingProcess> createCriteriaQuery(WebFormSearch webFormSearch) {
        MouldingProcessSearch search = (MouldingProcessSearch) webFormSearch;
        
        CriteriaQuery<MouldingProcess> cQuery = cb.createQuery(MouldingProcess.class);
        Root<MouldingProcess> process = cQuery.from(MouldingProcess.class);
        
        QueryBuilder builder = new QueryBuilder(cb, process);
  
        if(!search.isEmpty()) {
            
            builder.equalStringField(search.getPartId(), "basicInfo", "partId", "name");
           
            builder.equalStringField(search.getMachineNo(), "basicInfo", "machineNo");
            
            builder.likeStringField(search.getMasterBatchNo(), "basicInfo", "masterbatchNo");
            
            builder.likeStringField(search.getSignedOffBy(), "basicInfo", "signOffBy");
                        
            builder.equalField(search.getMaterial(), Integer.class, "basicInfo", "material", "id");
             
            if(search.getStartDate() != null) {
                Date startDate = search.getStartDate();
                Date endDate = search.getEndDate();
                if(endDate == null) {
                    builder.equalField(search.getStartDate(), Date.class, "basicInfo", "dateOfIssue");
                    
                } else {
                    builder.betweenDates(startDate, endDate, "basicInfo", "dateOfIssue");
                }
            }
            
            cQuery.where(cb.and(builder.getArrayOfPredicates()));
            cQuery.select(process);
        }
        
        return cQuery;
    }
}
