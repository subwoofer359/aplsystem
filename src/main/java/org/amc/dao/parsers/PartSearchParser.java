package org.amc.dao.parsers;


import java.util.Iterator;
import org.amc.servlet.action.search.SearchFields;
import org.amc.servlet.action.search.WebFormSearch;

public class PartSearchParser implements WebFormSearchToJPQLParser
{

	@Override
	public String parse(Class<?> entityClass,WebFormSearch webFormSearch)
	{
		StringBuilder textQuery=new StringBuilder();
		
		for(Iterator<SearchFields> i=webFormSearch.getFields().iterator();i.hasNext();)
		{
			SearchFields currentField=i.next();
			textQuery.append("x.");
			textQuery.append(currentField);
			textQuery.append(" LIKE :");
			textQuery.append(currentField.name());
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

}
