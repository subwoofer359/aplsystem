package org.amc.dao.parsers;

import org.amc.model.Part;
import org.amc.servlet.action.search.SearchFields;
import org.amc.servlet.action.search.WebFormSearch;

import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public class PartSearchParser implements WebFormSearchToJPQLParser {

    public String parse(Class<?> entityClass, WebFormSearch webFormSearch) {
        StringBuilder textQuery = new StringBuilder();

        for (Iterator<SearchFields> i = webFormSearch.getFields().iterator(); i.hasNext();) {
            SearchFields currentField = i.next();
            textQuery.append("x.");
            textQuery.append(currentField);
            textQuery.append(" LIKE :");
            textQuery.append(currentField.name());
            if (i.hasNext()) {
                textQuery.append(" AND ");
            }
        }
        if (textQuery.length() != 0) {
            textQuery.insert(0, "Select x from " + entityClass.getSimpleName() + " x WHERE ");
        }
        return textQuery.toString();
    }

    @Override
    public CriteriaQuery<Part> createCriteriaQuery(EntityManager enManager,
                    WebFormSearch webFormSearch) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
