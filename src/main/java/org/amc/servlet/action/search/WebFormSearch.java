package org.amc.servlet.action.search;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Class that store information of a User's search parameters
 * 
 * @author Adrian McLaughlin
 *
 */
public abstract class WebFormSearch {
    private final Map<SearchFields, Object> values;

    public WebFormSearch() {
        values = new HashMap<SearchFields, Object>();
    }

    public Set<SearchFields> getFields() {
        return values.keySet();
    }

    public Object getField(SearchFields field) {
        return values.get(field);
    }

    protected Map<SearchFields, Object> getFieldMap() {
        return values;
    }
    
    public boolean isEmpty() {
        Map<?,?> fields = getFieldMap();
        return fields.isEmpty();
    }
}
