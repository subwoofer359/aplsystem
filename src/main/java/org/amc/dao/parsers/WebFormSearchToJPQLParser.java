/**
 * @author Adrian McLaughlin
 * @date Aug 8, 2014
 */
package org.amc.dao.parsers;

import org.amc.servlet.action.search.WebFormSearch;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

/**
 * @author Adrian McLaughlin
 *
 */
public interface WebFormSearchToJPQLParser<T> {
    public String parse(Class<?> entityClass, WebFormSearch webFormSearch);
    
    public CriteriaQuery<T> createCriteriaQuery(EntityManager enManager, WebFormSearch webFormSearch);
}
