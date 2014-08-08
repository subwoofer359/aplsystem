package org.amc.servlet.validator;
import java.util.ArrayList;
import java.util.List;
import org.amc.servlet.model.PartForm;
/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
public class Part_Validator 
{
	public List<String> validate(PartForm form)
	{
		List<String> errors=new ArrayList<String>();
		String name=form.getName();
		String colour=form.getColour();
		String company=form.getCompany();
		
		
		
		if(name==null||name.trim().isEmpty())
		{
			errors.add("Product must have a name");
		}
		
		if(colour==null||colour.trim().isEmpty())
		{
			errors.add("Product must have a colour");
		}
		
		if(company==null||company.trim().isEmpty())
		{
			errors.add("Product must have a company associated with it");
		}
		
		
		
		
		return errors;
	}
}
