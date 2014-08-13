package org.amc.servlet.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.amc.servlet.action.search.MouldingProcessSearch;
import org.amc.servlet.model.MouldingProcessSearchForm;

public class MouldingProcessSearchFormValidator extends WebPageFormValidator
{
	private static final SimpleDateFormat DATE_FORMAT=new SimpleDateFormat();
	
	public List<String> validate(MouldingProcessSearchForm form)
	{
		List<String> errors=getErrors();
		
		try
		{
			if(form.getMaterial()!=null && !"".trim().equals(form.getMaterial()))
			{
				Integer.parseInt(form.getMaterial());
			}
		}
		catch(NumberFormatException nfe)
		{
			errors.add("Value for Material:"+form.getMaterial()+" can't be parsed to a number");
		}
		
		try
		{
			if(form.getStartDate()!=null && !"".trim().equals(form.getStartDate()))
			{
				DATE_FORMAT.parse(form.getStartDate());
			}
		}
		catch(ParseException pe)
		{
			errors.add("Value for Start Date:"+form.getStartDate()+" is not a valid date");
		}
		
		try
		{
			if(form.getEndDate()!=null && !"".trim().equals(form.getEndDate()))
			{
				DATE_FORMAT.parse(form.getEndDate());
			}
		}
		catch(ParseException pe)
		{
			errors.add("Value for End Date:"+form.getEndDate()+" is not a valid date");
		}
		return errors;
	}
	
	public static class MouldingProcessSearchBinder
	{
		public static MouldingProcessSearch  getMouldingProcessSearch(MouldingProcessSearchForm form) throws ParseException
		{
			MouldingProcessSearch mpSearch=new MouldingProcessSearch();
			
			if(form.getPartId()!=null && !"".trim().equals(form.getPartId()))
			{
				mpSearch.setPartId(String.valueOf(form.getPartId()));
			}
			
			if(form.getMachineNo()!=null && !"".trim().equals(form.getMachineNo()))
			{
				mpSearch.setMachineNo(form.getMachineNo());
			}
			
			if(form.getMaterial()!=null && !"".trim().equals(form.getMaterial()))
			{
				mpSearch.setMaterial(Integer.parseInt(form.getMaterial()));
			}
			
			if(form.getMasterbatchNo()!=null && !"".trim().equals(form.getMasterbatchNo()))
			{
				mpSearch.setMasterBatchNo(form.getMasterbatchNo());
			}
			
			if(form.getSignOffBy()!=null && !"".trim().equals(form.getSignOffBy()))
			{
				mpSearch.setSignedOffBy(form.getSignOffBy());
			}
			
			if(form.getStartDate()!=null && !"".trim().equals(form.getStartDate()))
			{
				mpSearch.setStartDate(DATE_FORMAT.parse(form.getStartDate()));
			}
			
			if(form.getEndDate()!=null && !"".trim().equals(form.getEndDate()))
			{
				mpSearch.setEndDate(DATE_FORMAT.parse(form.getEndDate()));
			}
			
			return mpSearch;
		}
	}
}
