package org.amc.servlet.validator;

import org.amc.servlet.model.MaterialSearchForm;

import java.util.List;

public class MaterialSearchValidator extends WebPageFormValidator {
    public List<String> validate(MaterialSearchForm form) {
        List<String> errors = getErrors();
        return errors;
    }
}
