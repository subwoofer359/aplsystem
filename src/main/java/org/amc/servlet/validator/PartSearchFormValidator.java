package org.amc.servlet.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.amc.servlet.action.search.PartSearch;
import org.amc.servlet.model.PartSearchForm;

public class PartSearchFormValidator extends WebPageFormValidator
{
	private static final SimpleDateFormat SDF=(SimpleDateFormat)SimpleDateFormat.getDateInstance();
	
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
			partSearch.setPart(form.getPart());
			partSearch.setQSSNumber(form.getQSSNumber());
			
			
			partSearch.setStartDate(SDF.parse(form.getStartDate()));
			partSearch.setEndDate(SDF.parse(form.getEndDate()));
		
			return partSearch;
		}
	}
}
