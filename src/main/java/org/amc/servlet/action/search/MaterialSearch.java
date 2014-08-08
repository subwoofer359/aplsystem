package org.amc.servlet.action.search;

import java.util.Set;

/**
 * 
 * @author Adrian McLaughlin
 *
 */
public class MaterialSearch implements Search
{
	private String company;
	private String name;
	private String type;
	/**
	 * @return the company
	 */
	public String getCompany()
	{
		return company;
	}
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * @return the type
	 */
	public String getType()
	{
		return type;
	}
	/**
	 * @param company the company to set
	 */
	public void setCompany(String company)
	{
		this.company = company;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type)
	{
		this.type = type;
	}
	
	public Set<SearchParameters> getFields()
	{
		return null;
	}
}
