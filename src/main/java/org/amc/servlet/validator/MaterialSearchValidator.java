package org.amc.servlet.validator;

import org.amc.servlet.action.search.MaterialSearch;
import org.amc.servlet.model.MaterialSearchForm;

import java.text.ParseException;
import java.util.List;

public class MaterialSearchValidator extends WebPageFormValidator
{
	public List<String> validate(MaterialSearchForm form)
	{
		List<String> errors=getErrors();
		return errors;
	}
	
	public static class MaterialSearchBinder
	{
		public static MaterialSearch getMaterialSearch(MaterialSearchForm form) throws ParseException
		{
			MaterialSearch materialSearch=new MaterialSearch();
			
			if(form.getCompany()!=null && !"".equals(form.getCompany()))
			{
				materialSearch.setCompany(form.getCompany());
			}
			if(form.getType()!=null && !"".equals(form.getType()))
			{
				materialSearch.setType(form.getType());
			}
			if(form.getName()!=null && !"".equals(form.getName()))
			{	
				materialSearch.setName(form.getName());
			}

			return materialSearch;
		}
	}
}
