/**
 * @author Adrian McLaughlin
 * @date Aug 8, 2014
 */
package org.amc.dao.parsers;

import org.amc.model.WorkEntity;
import org.amc.servlet.action.search.WebFormSearch;

import javax.persistence.criteria.CriteriaQuery;

/**
 * @author Adrian McLaughlin
 *
 */
public interface WebFormSearchToQuery {
    
    public CriteriaQuery<? extends WorkEntity> createCriteriaQuery( WebFormSearch webFormSearch);
}
