package org.amc.dao.parsers;

import org.amc.servlet.action.search.MaterialSearch;
import org.amc.servlet.action.search.MouldingProcessSearch;
import org.amc.servlet.action.search.PartSearch;
import org.amc.servlet.action.search.WebFormSearch;

public class WebFormSearchParserFactory
{
	/**
	 * Factory Class
	 */
	private WebFormSearchParserFactory()
	{
	}
	
	public static final WebFormSearchToJPQLParser getWebFormSearchParser(WebFormSearch webFormSearch) throws NoSuchWebFormParser
	{
		//System.out.println("Class="+webFormSearch.getClass());
		if(webFormSearch.getClass().equals(PartSearch.class))
		{
			return new PartSearchParser();
		}
		else if(webFormSearch.getClass().equals(MaterialSearch.class))
		{
			return new MaterialSearchParser();
		}
		else if(webFormSearch.getClass().equals(MouldingProcessSearch.class))
		{
			return new MouldingProcessSearchParser();
		}
		else
		{
			throw new NoSuchWebFormParser("No Parser for class:"+webFormSearch.getClass());
		}
	}
}
