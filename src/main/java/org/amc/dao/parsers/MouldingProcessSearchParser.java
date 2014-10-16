package org.amc.dao.parsers;

import org.amc.servlet.action.search.MouldingProcessSearch;
import org.amc.servlet.action.search.MouldingProcessSearch.ProcessSearchFields;
import org.amc.servlet.action.search.SearchFields;
import org.amc.servlet.action.search.WebFormSearch;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Parses a MouldingProcessSearchForm object
 * 
 * @author Adrian McLaughlin
 * @version 1
 *
 */
public final class MouldingProcessSearchParser implements WebFormSearchToJPQLParser {

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
                textQuery.insert(0, "Select x from " + entityClass.getSimpleName() + " x WHERE ");
            }

            copiedSet = null;

            return textQuery.toString();
        } else {
            return "";
        }
    }

}
