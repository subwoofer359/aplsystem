package org.amc.servlet.validator;

import org.amc.servlet.action.search.MaterialSearch;
import org.amc.servlet.model.MaterialSearchForm;

import java.text.ParseException;
/**
 * Creates a MaterialSearch from a MaterialSearchForm
 * @author Adrian McLaughlin
 * @version 1.0
 *
 *
 */
public class MaterialSearchBinder {
    
    /**
     * 
     * @param form MaterialSearchForm a submission from a HTML form
     * @return a new MaterialSearch object
     * @throws ParseException if fields of form can't be parsed
     */
    public MaterialSearch getMaterialSearch(MaterialSearchForm form)
                    throws ParseException {
        MaterialSearch materialSearch = new MaterialSearch();

        if (form.getCompany() != null && !"".equals(form.getCompany())) {
            materialSearch.setCompany(form.getCompany());
        }
        if (form.getType() != null && !"".equals(form.getType())) {
            materialSearch.setType(form.getType());
        }
        if (form.getName() != null && !"".equals(form.getName())) {
            materialSearch.setName(form.getName());
        }

        return materialSearch;
    }
}