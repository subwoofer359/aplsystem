package org.amc.servlet.validator;

import org.amc.servlet.action.search.PartSearch;
import org.amc.servlet.model.PartSearchForm;

import java.text.ParseException;
import java.util.List;

public class PartSearchFormValidator extends WebPageFormValidator {

    public List<String> validate(PartSearchForm form) {
        List<String> errors = getErrors();
        return errors;
    }

    public static class PartSearchBinder {
        public static PartSearch getPartSearch(PartSearchForm form) throws ParseException {
            PartSearch partSearch = new PartSearch();

            if (form.getCompany() != null && !"".equals(form.getCompany())) {
                partSearch.setCompany(form.getCompany());
            }
            if (form.getPartName() != null && !"".equals(form.getPartName())) {
                partSearch.setPartName(form.getPartName());
            }
            if (form.getQSSNumber() != null && !"".equals(form.getQSSNumber())) {
                partSearch.setQSSNumber(form.getQSSNumber());
            }

            return partSearch;
        }
    }
}
