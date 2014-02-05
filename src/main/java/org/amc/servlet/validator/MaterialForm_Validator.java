package org.amc.servlet.validator;
import java.util.ArrayList;
import java.util.List;

import org.amc.servlet.model.MaterialForm;

public class MaterialForm_Validator 
{
	public List<String> validate(MaterialForm form)
	{
		List<String> errors=new ArrayList<String>();
		String name=form.getName();
		String type=form.getType();
		String company=form.getCompany();
		
		
		
		if(name==null||name.trim().isEmpty())
		{
			errors.add("Product must have a name");
		}
		
		if(type==null||type.trim().isEmpty())
		{
			errors.add("Product must have a type");
		}
		
		if(company==null||company.trim().isEmpty())
		{
			errors.add("Product must have a company associated with it");
		}
		/*
			private float density;
			private float linear_expansion;
			private float water_absorption;
			private float material_drying;	  
			private float melting_temp_lower;
			private float melting_temp_upper;
			private float mould_shrinkage;
			private float mould_temp_low;
			private float mould_temp_upper;
			*/
		String[][] arguments=
			{
				{"density",form.getDensity()},
				{"linear expansion",form.getLinear_expansion()},
				{"water absorption",form.getWater_absorption()},
				{"material drying",form.getMaterial_drying()},
				{"Melting temp lower",form.getMelting_temp_lower()},
				{"Melting temp upper",form.getMelting_temp_upper()},
				{"Shrinkage",form.getMould_shrinkage()},
				{"Mould temp upper",form.getMould_temp_low()},
				{"Mould temp lower",form.getMould_temp_upper()}
			};
		for(String[] s:arguments)
		{
			try
			{
				Float.parseFloat(s[1]);
			}
			catch(NumberFormatException nfe)
			{
				errors.add(s[0]+" is not a floating point number");
			}
			catch(NullPointerException npe)
			{
				
			}
		}
			
		try
		{
			float mouldTempUpper=Float.parseFloat(form.getMould_temp_upper());
			float mouldTempLower=Float.parseFloat(form.getMould_temp_low());
			if(mouldTempLower>mouldTempUpper)
			{
				errors.add("Mould Temperature Lower can be higher than Mould Temperature Higher");
			}
		}
		catch(NumberFormatException nfe)
		{
			
		}
		
		
		
		return errors;
	}
}
