package org.amc.dao.parsers;

import org.amc.model.MouldingProcess;
import org.amc.model.MouldingProcess_;
import org.amc.model.Part;
import org.amc.servlet.action.search.MouldingProcessSearch;
import org.amc.servlet.action.search.MouldingProcessSearch.ProcessSearchFields;
import org.amc.servlet.validator.Part_Validator;
import org.amc.servlet.action.search.SearchFields;
import org.amc.servlet.action.search.WebFormSearch;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

/**
 * Parses a MouldingProcessSearchForm object
 * 
 * @author Adrian McLaughlin
 * @version 1
 *
 */
public final class MouldingProcessSearchParser implements WebFormSearchToJPQLParser<MouldingProcess> {

    /**
     * Parses a MouldingProcessSearchForm object. Check first if it has a Date
     * SearchFields and then processes the other SearchFields
     * 
     * @Return a string containing JPQL Query
     */
    @Override
    public final String parse(Class<?> entityClass, WebFormSearch webFormSearch) {
        if (webFormSearch.getClass().equals(MouldingProcessSearch.class)) {
            MouldingProcessSearch search = (MouldingProcessSearch) webFormSearch;

            StringBuilder textQuery = new StringBuilder();
            StringBuilder dateQuery = new StringBuilder();

            Set<SearchFields> copiedSet = new TreeSet<SearchFields>(search.getFields());

            if (search.getFields().contains(ProcessSearchFields.START_DATE)) {
                dateQuery.append("x.");
                dateQuery.append(ProcessSearchFields.START_DATE);
                copiedSet.remove(ProcessSearchFields.START_DATE);
                if (search.getFields().contains(ProcessSearchFields.END_DATE)) {
                    dateQuery.append(" BETWEEN ");
                    dateQuery.append(":START_DATE");
                    dateQuery.append(" AND :END_DATE");
                    copiedSet.remove(ProcessSearchFields.END_DATE);
                } else {
                    dateQuery.append(" = :START_DATE");
                }
            }
            
            if(search.getFields().contains(ProcessSearchFields.PART_NAME)) {
                textQuery.append("x.");
                textQuery.append(ProcessSearchFields.PART_NAME);
                textQuery.append(" = :PART_NAME");
                copiedSet.remove(ProcessSearchFields.PART_NAME);
            }
            

            for (Iterator<SearchFields> i = copiedSet.iterator(); i.hasNext();) {
                SearchFields currentField = i.next();
                textQuery.append("x.");
                textQuery.append(currentField);
                textQuery.append(" LIKE ");
                textQuery.append(":");
                textQuery.append(currentField.name());
                if (i.hasNext()) {
                    textQuery.append(" AND ");
                }
            }

            if (dateQuery.length() > 0) {
                if (textQuery.length() > 0) {
                    textQuery.append(" AND ");
                    textQuery.append(dateQuery);
                } else {
                    textQuery.append(dateQuery);
                }
            }

            if (textQuery.length() > 0) {
                textQuery.insert(0, "SELECT x FROM " + entityClass.getSimpleName() + " x WHERE ");
            }

            copiedSet = null;

            return textQuery.toString();
        } else {
            return "";
        }
    }

    @Override
    public CriteriaQuery<MouldingProcess> createCriteriaQuery(EntityManager enManager, WebFormSearch webFormSearch) {
        MouldingProcessSearch search = (MouldingProcessSearch) webFormSearch;
        
        CriteriaBuilder cb = enManager.getCriteriaBuilder();
        CriteriaQuery<MouldingProcess> cQuery = cb.createQuery(MouldingProcess.class);
        Root<MouldingProcess> process = cQuery.from(MouldingProcess.class);
        
        List<Predicate> predicates = new ArrayList<Predicate>();
        
        if(search.getPartId() !=null ){
            Predicate partQuery = cb.like(process.get("basicInfo").get("partId").get("name").as(String.class),
                        search.getPartId());
            predicates.add(partQuery);
        }
        
        if(search.getMachineNo() != null) {
            Predicate machineNoQuery = cb.equal(process.get("basicInfo").get("machineNo").as(String.class), 
                        String.valueOf(webFormSearch.getField(MouldingProcessSearch.ProcessSearchFields.MACHINE_NO)));
            predicates.add(machineNoQuery);
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
        
        return cQuery;
    }
    
    

}
