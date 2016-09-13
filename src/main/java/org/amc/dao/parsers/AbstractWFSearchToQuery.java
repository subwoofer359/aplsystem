package org.amc.dao.parsers;

import org.amc.model.WorkEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public abstract class AbstractWFSearchToQuery implements WebFormSearchToQuery {
    
    private CriteriaBuilder cb;
    
    public AbstractWFSearchToQuery(EntityManager entityManager) {
        cb = entityManager.getCriteriaBuilder();
    }
    
    CriteriaBuilder getCriteriaBuilder() {
        return cb;
    }
    
    static class QueryBuilder {
        private Root<? extends WorkEntity> root;
        private List<Predicate> predicates;
        private CriteriaBuilder cb;
        
        public QueryBuilder(CriteriaBuilder cb, Root<? extends WorkEntity> root) {
            this.cb = cb;
            this.root = root;
            predicates = new ArrayList<Predicate>();
        }
        
        public void likeStringField(String value, String... fields) {
            if(value != null) {
                predicates.add(cb.like(getExprStr(root, fields), value));
            }
        }
        
        public void equalStringField(String value, String... fields) {
            if(value != null) {
                predicates.add(cb.equal(getExprStr(root, fields), value));
            }
        }
        
        public void equalField(Object value, Class<?> clazz, String... fields) {
            if(value != null) {
                predicates.add(cb.equal(getExpr(root, clazz, fields), value));
            }
        }
        
        public void betweenDates(Date startDate, Date endDate, String... fields) {
            predicates.add(cb.between(getExprDate(root, fields), startDate, endDate));
            
        }
        
        public List<Predicate> getPredicateList() {
            return this.predicates;
        }
        
        public Predicate[] getArrayOfPredicates() {
            return predicates.toArray(new Predicate[predicates.size()]);
        }
        
        Expression<String> getExprStr(Root<? extends WorkEntity> process, String... fields) {
            return getPath(process, fields).as(String.class);
        }
        
        Expression<Date> getExprDate(Root<? extends WorkEntity> process, String... fields) {
            return getPath(process, fields).as(Date.class);
        }
        
        Expression<?> getExpr(Root<? extends WorkEntity> process, Class<?> clazz, String... fields) {
            return getPath(process, fields).as(clazz);
        }
        
        Path<Object> getPath(Root<? extends WorkEntity> process, String... fields) {
            Path<Object> path = null;
            for(String field : fields) {
                if(path == null) {
                    path = process.get(field);
                } else {
                    path = path.get(field);
                }
            }
            return path;
        }
    }
}
