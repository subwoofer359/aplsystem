package org.amc.servlet.validator;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
public abstract class WebPageFormValidator 
{
	private final List<String> errors=new ArrayList<String>();
	
	//public abstract List<String> validate(WebForm form);
	
	public List<String> getErrors()
	{
		return errors;
	}
	
	public boolean hasErrors()
	{
		return !errors.isEmpty();
	}
}
