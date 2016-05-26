package org.amc.servlet.validator

import org.amc.model.Part;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.Errors;
import org.springframework.validation.MapBindingResult;

import java.util.HashMap;
import java.util.Map;

class PartValidatorTest {
    PartValidator validator;
    Part part = new Part(company: '', colour: '', name: '');
    Errors errors;
    
    
    @Before
    void setUp() {
        validator = new PartValidator();
        Map<String, Object> errorMap = new HashMap<String, Object>();
        errors = new MapBindingResult(errorMap, "Part");
    }
    
    @Test
    void testValidate() {
        validator.validate(part, errors);
        assert errors.getFieldError('company') != null;
        assert errors.getFieldError('colour') != null;
        assert errors.getFieldError('name') != null;
        
        assert errors.getFieldError('name').defaultMessage == PartValidator.NO_PRODUCT_NAME_ERROR;
        assert errors.getFieldError('company').defaultMessage == PartValidator.NO_COMPANY_ERROR;
        assert errors.getFieldError('colour').defaultMessage == PartValidator.NO_COLOUR_ERROR;
    }
}
