package org.amc.servlet.action.search;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Class that store information of a User's search parameters for a org.amc.model.Part
 * @author Adrian McLaughlin
 *
 */
public class PartSearch implements Search
{
	public enum PartSearchFields implements SearchParameters
	{
		PART_NAME("part"),
		QSS_NUMBER("qss_no"),
		COMPANY("company");
		
		/**
		 * Database column name
		 */
		private String name;
		private PartSearchFields(String name)
		{
			this.name=name;
		}
		
		@Override
		public String toString()
		{
			return name;
		}
	}
	
	private Map<SearchParameters, Object> values;
	
	public PartSearch()
	{
		values=new HashMap<SearchParameters, Object>();
	}
	/**
	 * @return the part
	 */
	public String getPartName()
	{
		return String.valueOf(values.get(PartSearchFields.PART_NAME));
	}
	/**
	 * @return the qSSNumber
	 */
	public String getQSSNumber()
	{
		return String.valueOf(values.get(PartSearchFields.QSS_NUMBER));
	}
	/**
	 * @return the company
	 */
	public String getCompany()
	{
		return String.valueOf(values.get(PartSearchFields.COMPANY));
	}
	/**
	 * @param part the part to set
	 */
	public void setPartName(String partName)
	{
		values.put(PartSearchFields.PART_NAME,partName);
	}
	/**
	 * @param qSSNumber the qSSNumber to set
	 */
	public void setQSSNumber(String qSSNumber)
	{
		values.put(PartSearchFields.QSS_NUMBER, qSSNumber);
	}
	/**
	 * @param company the company to set
	 */
	public void setCompany(String company)
	{
		values.put(PartSearchFields.COMPANY, company);
	}
	
	public Set<SearchParameters> getFields()
	{
		return values.keySet();
	}
	
	public Object getField(SearchParameters field)
	{
		return values.get(field);
	}
	
}
