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
import javax.persistence.metamodel.Metamodel;

public abstract class AbstractWFSearchToQuery implements WebFormSearchToQuery {
    
    private CriteriaBuilder cb;
    
    public AbstractWFSearchToQuery(EntityManager entityManager) {
        cb = entityManager.getCriteriaBuilder();
    }
    
    public CriteriaBuilder getCriteriaBuilder() {
        return cb;
    }
    
    /**
     * A Builder that helps to create a Criteria Query without a {@link Metamodel}
     * 
     * @author Adrian Mclaughlin
     *
     */
    static class QueryBuilder {
        private Root<? extends WorkEntity> root;
        private List<Predicate> predicates;
        private CriteriaBuilder cb;
        
        public QueryBuilder(CriteriaBuilder cb, Root<? extends WorkEntity> root) {
            this.cb = cb;
            this.root = root;
            predicates = new ArrayList<Predicate>();
        }
        
        /**
         * Create a JPA like expression and adds it a List
         * @param value String value to use
         * @param fields String array relating to JPA model field name
         */
        public void likeStringField(String value, String... fields) {
            if(value != null) {
                predicates.add(cb.like(getExprStr(root, fields), value));
            }
        }
        
        /**
         * Create a JPA equal expression and adds it a {@link List}
         * @param value String value to use
         * @param fields String array relating to JPA model field name
         */
        public void equalStringField(String value, String... fields) {
            if(value != null) {
                predicates.add(cb.equal(getExprStr(root, fields), value));
            }
        }
        
        /**
         * Create a JPA equal expression and adds it a {@link List}
         * @param value Object value to use
         * @param clazz Class type to cast the expression to
         * @param fields String array relating to JPA model field name
         */
        public void equalField(Object value, Class<?> clazz, String... fields) {
            if(value != null) {
                predicates.add(cb.equal(getExpr(root, clazz, fields), value));
            }
        }
        
        /**
         * Create a JPA between expression for Dates and adds it a {@link List}
         * 
         * @param startDate Date lower bound
         * @param endDate Date upper bound
         * @param fields String array JPA model date field name
         */
        public void betweenDates(Date startDate, Date endDate, String... fields) {
            predicates.add(cb.between(getExprDate(root, fields), startDate, endDate));
            
        }
        
        public List<Predicate> getPredicateList() {
            return this.predicates;
        }
        
        /**
         * gets an array of Predicates
         * @return a Predicate array
         */
        public Predicate[] getArrayOfPredicates() {
            return predicates.toArray(new Predicate[predicates.size()]);
        }
        
        private Expression<String> getExprStr(Root<? extends WorkEntity> process, String... fields) {
            return getPath(process, fields).as(String.class);
        }
        
        private Expression<Date> getExprDate(Root<? extends WorkEntity> process, String... fields) {
            return getPath(process, fields).as(Date.class);
        }
        
        private Expression<?> getExpr(Root<? extends WorkEntity> process, Class<?> clazz, String... fields) {
            return getPath(process, fields).as(clazz);
        }
        
        private Path<Object> getPath(Root<? extends WorkEntity> process, String... fields) {
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
