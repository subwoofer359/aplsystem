package org.amc.servlet.validator;

import org.amc.model.spc.SPCMeasurement;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class SPCMeasurementValidator implements Validator
{

	@Override
	public boolean supports(Class<?> arg0)
	{
		return SPCMeasurement.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors errors)
	{
		SPCMeasurement measurement=(SPCMeasurement)arg0;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dimension", "spcMeasurement.dimension.empty","No entry in Dimension");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "part", "spcMeasurement.part.empty","No related Part");
	}

}
