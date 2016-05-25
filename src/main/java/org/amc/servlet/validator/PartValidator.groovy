package org.amc.servlet.validator

import org.amc.model.Part;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator

class PartValidator implements Validator {
    
    static final def NO_PRODUCT_NAME_ERROR = 'Product must have a name';
    static final def NO_COMPANY_ERROR = 'Product must have a company associated with it';
    static final def NO_COLOUR_ERROR = 'Product must have a colour';

    @Override
    public boolean supports(Class<?> arg0) {
        return Part.class.equals(arg0);
    }

    @Override
    public void validate(Object part, Errors errors) {
        if(part instanceof Part) {
            if(part.name == null) {
                ValidationUtils.rejectIfEmpty(errors, 'name', 'name can\'t be empty', NO_PRODUCT_NAME_ERROR);
            }
            if(part.company == null) {
                ValidationUtils.rejectIfEmpty(errors, 'company', 'company can\'t be empty', NO_COMPANY_ERROR);
            }
            if(part.colour == null) {
                ValidationUtils.rejectIfEmpty(errors, 'colour', 'colour can\'t be empty', NO_COLOUR_ERROR);
            }
            
        }
    }

}
