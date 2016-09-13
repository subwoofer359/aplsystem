package org.amc.servlet.validator;

import org.amc.servlet.action.search.PartSearch;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.text.ParseException;
import java.util.List;

public class PartSearchFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PartSearch.class.equals(clazz);
    }

    @Override
    public void validate(Object partSearch, Errors errors) {
        //No validation yet
    }
    
}
