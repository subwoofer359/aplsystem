package org.amc.servlet.validator;

import java.text.ParseException;
import java.util.List;

import org.amc.servlet.action.search.PartSearch;
import org.amc.servlet.model.PartSearchForm;

public class PartSearchFormValidator extends WebPageFormValidator
{

	
	public List<String> validate(PartSearchForm form)
	{
		List<String> errors=getErrors();
		return errors;
	}
	
	public static class PartSearchBinder
	{
		public static PartSearch getPartSearch(PartSearchForm form) throws ParseException
		{
			PartSearch partSearch=new PartSearch();
			
			partSearch.setCompany(form.getCompany());
			partSearch.setPartName(form.getPart());
			partSearch.setQSSNumber(form.getQSSNumber());

			return partSearch;
		}
	}
}
