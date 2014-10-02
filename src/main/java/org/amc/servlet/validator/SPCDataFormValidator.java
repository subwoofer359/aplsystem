/**
 * @author Adrian McLaughlin
 * @date Jul 25, 2014
 */
package org.amc.servlet.validator;

import org.amc.model.spc.SPCMeasurement;
import org.amc.servlet.APLSpcDataController.SPCDataForm;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;

/**
 * Validates SPCDataForm objects
 * @author Adrian McLaughlin 
 *
 */
public class SPCDataFormValidator implements Validator
{	
	@Override
	public boolean supports(Class<?> arg0)
	{
		return SPCDataForm.class.equals(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors)
	{
		SPCDataForm form=(SPCDataForm)obj;
		if(form.getMeasurement()==null)
		{
			errors.rejectValue("measurement","SPCDataForm.measurement.format","Measurements not numbers ");
		}
		else
		{
			if(form.getMeasurement().contains(null))
			{
				errors.rejectValue("measurement","SPCDataForm.measurement.missing","Missing values");
			}
		}
		if(form.getMeasurementNumber()==null)
		{
			errors.rejectValue("measurementNumber", "SPCDataForm.measurementNumber.format","Neasurement numbers(n) not integers");
		}
		else
		{
			if(form.getMeasurementNumber().contains(null))
			{
				errors.rejectValue("measurementNumber", "SPCDataForm.measurementNumber.missing","Missing measurement numbers(n)");
			}
		}
		
		SPCMeasurement spcMeasurement=form.getSpcMeasurement();
		if(spcMeasurement==null)
		{
			errors.rejectValue("spcMeasurement", "SPCDataForm.spcmeasurement.missing","Missing SPCMeasurement object");
		}
		else
		{
			int requiredSize=spcMeasurement.getNoOfMeasurements();
			
			if(form.getMeasurement()!=null && form.getMeasurement().size()!=requiredSize)
			{
				errors.rejectValue("measurement","SPCDataForm.measurement.size","Too few/Many values");
			}
			if(form.getMeasurementNumber()!=null && form.getMeasurementNumber().size()!=requiredSize)
			{
				errors.rejectValue("measurementNumber","SPCDataForm.measurementNumber.size","Too few/Many values");
			}
		}
		Date currentDate =new Date();
		if(form.getDate().after(currentDate))
		{
			errors.rejectValue("date","SPCDataForm.date.inTheFuture","The date is in the future");
		}	
	}
}
