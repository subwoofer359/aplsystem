package org.amc.servlet.model;


public class MaterialSearchForm implements WebPageForm
{
	private String type;
	private String name;
	private String company;
	/**
	 * @return the type
	 */
	public String getType()
	{
		return type;
	}
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * @return the company
	 */
	public String getCompany()
	{
		return company;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type)
	{
		this.type = type;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	/**
	 * @param company the company to set
	 */
	public void setCompany(String company)
	{
		this.company = company;
	}
	/* (non-Javadoc)
	 * @see org.amc.servlet.model.WebPageForm#isEmpty()
	 */
	@Override
	public boolean isEmpty()
	{
		if(company==null && name==null && type==null)
		{
			return true;
		}
		return false;
	}
	
	
}
