package org.amc.dao.parsers;

import org.amc.model.MouldingProcess;
import org.amc.servlet.action.search.MouldingProcessSearch;

import org.amc.servlet.action.search.WebFormSearch;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Parses a MouldingProcessSearchForm object
 * 
 * @author Adrian McLaughlin
 * @version 1
 *
 */
public final class MouldingProcessSearchParser implements WebFormSearchToJPQLParser {

    @Override
    public CriteriaQuery<MouldingProcess> createCriteriaQuery(EntityManager enManager, WebFormSearch webFormSearch) {
        MouldingProcessSearch search = (MouldingProcessSearch) webFormSearch;
        
        CriteriaBuilder cb = enManager.getCriteriaBuilder();
        CriteriaQuery<MouldingProcess> cQuery = cb.createQuery(MouldingProcess.class);
        Root<MouldingProcess> process = cQuery.from(MouldingProcess.class);
  
        if(!search.isEmpty()) {
           
            
            List<Predicate> predicates = new ArrayList<Predicate>();
            
            if(search.getPartId() !=null ){
                Predicate partQuery = cb.equal(process.get("basicInfo").get("partId").get("name").as(String.class),
                            search.getPartId());
                predicates.add(partQuery);
            }
            
            if(search.getMachineNo() != null) {
                Predicate machineNoQuery = cb.equal(process.get("basicInfo").get("machineNo").as(String.class), 
                            search.getMachineNo());
                predicates.add(machineNoQuery);
            }
            
            if(search.getMasterBatchNo() != null) {
                Predicate masterbatchQuery = cb.like(process.get("basicInfo").get("masterbatchNo").as(String.class), 
                                search.getMasterBatchNo());
                predicates.add(masterbatchQuery);
            }
            
            if(search.getSignedOffBy() != null) {
                Predicate signOffQuery = cb.like(process.get("basicInfo").get("signOffBy").as(String.class), 
                                search.getSignedOffBy());
                predicates.add(signOffQuery);
            }
            
            if(search.getMaterial() != null) {
                Predicate signOffQuery = cb.equal(process.get("basicInfo").get("material").get("id").as(Integer.class), 
                                search.getMaterial());
                predicates.add(signOffQuery);
            }
            
            if(search.getStartDate() != null) {
                Predicate dateQuery;
                Date startDate = search.getStartDate();
                Date endDate = search.getEndDate();
                if(endDate == null) {
                    dateQuery = cb.equal(process.get("basicInfo").get("dateOfIssue"), search.getStartDate());
                    
                } else {
                    dateQuery = cb.between(process.get("basicInfo").get("dateOfIssue").as(Date.class), startDate, endDate);
                }
                predicates.add(dateQuery);
    
            }
            
            cQuery.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
            cQuery.select(process);
        }
        
        return cQuery;
    }
    
    

}
