/**
 * @author Adrian McLaughlin
 * @date Aug 8, 2014
 */
package org.amc.dao.parsers;

import org.amc.model.WorkEntity;
import org.amc.servlet.action.search.WebFormSearch;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

/**
 * @author Adrian McLaughlin
 *
 */
public interface WebFormSearchToJPQLParser {
    
    public CriteriaQuery<? extends WorkEntity> createCriteriaQuery(
                    EntityManager enManager, WebFormSearch webFormSearch);
}
