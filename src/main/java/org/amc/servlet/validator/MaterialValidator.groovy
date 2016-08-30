package org.amc.servlet.validator

import org.amc.model.Material;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator

class MaterialValidator implements Validator {

    @Override
    boolean supports(Class<?> clazz) {
        return Material.class == clazz;
    }

    @Override
    void validate(Object material, Errors errors) {
        if(material instanceof Material) {
            
        }    
    }
    
}
