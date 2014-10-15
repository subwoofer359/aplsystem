/**
 * @author Adrian McLaughlin
 * @date Aug 8, 2014
 */
package org.amc.dao.parsers;

import org.amc.servlet.action.search.WebFormSearch;

/**
 * @author Adrian McLaughlin
 *
 */
public interface WebFormSearchToJPQLParser {
    public String parse(Class<?> entityClass, WebFormSearch webFormSearch);
}
