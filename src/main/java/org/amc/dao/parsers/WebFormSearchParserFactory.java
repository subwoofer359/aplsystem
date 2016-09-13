package org.amc.dao.parsers;

import org.amc.servlet.action.search.MaterialSearch;
import org.amc.servlet.action.search.MouldingProcessSearch;
import org.amc.servlet.action.search.PartSearch;
import org.amc.servlet.action.search.WebFormSearch;

import javax.persistence.EntityManager;

public class WebFormSearchParserFactory {
    /**
     * Factory Class
     */
    private WebFormSearchParserFactory() {
    }

    public static final WebFormSearchToQuery getWebFormSearchParser(EntityManager entityManager, WebFormSearch webFormSearch)
                    throws NoSuchWebFormParserException {
        // System.out.println("Class="+webFormSearch.getClass());
        if (webFormSearch.getClass().equals(PartSearch.class)) {
            return new PartSearchParser(entityManager);
        } else if (webFormSearch.getClass().equals(MaterialSearch.class)) {
            return new MaterialSearchParser(entityManager);
        } else if (webFormSearch.getClass().equals(MouldingProcessSearch.class)) {
            return new MouldingProcessSearchParser(entityManager);
        } else {
            throw new NoSuchWebFormParserException("No Parser for class:"
                            + webFormSearch.getClass());
        }
    }
}
