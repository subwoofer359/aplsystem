package org.amc.dao.parsers;

import java.util.Iterator;

import org.amc.servlet.action.search.MouldingProcessSearch;
import org.amc.servlet.action.search.SearchFields;
import org.amc.servlet.action.search.WebFormSearch;

public class MouldingProcessSearchParser implements WebFormSearchToJPQLParser
{

	@Override
	public String parse(Class<?> entityClass, WebFormSearch webFormSearch)
	{
		if(webFormSearch.getClass().equals(MouldingProcessSearch.class))
		{
			MouldingProcessSearch search=(MouldingProcessSearch)webFormSearch;
			
			StringBuilder textQuery=new StringBuilder();
			int queryIndex=1;
			
			for(Iterator<SearchFields> i=search.getFields().iterator();i.hasNext();)
			{
				textQuery.append("x.");
				textQuery.append(i.next());
				
				if(i.equals(MouldingProcessSearch.ProcessSearchFields.START_DATE) || i.equals(MouldingProcessSearch.ProcessSearchFields.END_DATE))
				{
					if(i.equals(MouldingProcessSearch.ProcessSearchFields.START_DATE))
					{
						textQuery.append(" > ?");
					}
					else if(i.equals(MouldingProcessSearch.ProcessSearchFields.END_DATE))
					{
						textQuery.append(" < ?");
					}
				}
				else
				{
					textQuery.append(" LIKE ?");
					
				}
				textQuery.append(queryIndex++);
				
				
				if(i.hasNext())
				{
					textQuery.append(" AND ");
				}
			}
			if(textQuery.length()!=0)
			{
				textQuery.insert(0, "Select x from "+entityClass.getSimpleName()+" x WHERE ");
			}
			return textQuery.toString();
		}
		else
		{
			return "";
		}
	}

}
