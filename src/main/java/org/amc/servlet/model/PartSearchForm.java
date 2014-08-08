package org.amc.servlet.model;

public class PartSearchForm implements WebPageForm
{
	private String part;
	private String qSSNumber;
	private String company;
	/**
	 * @return the part
	 */
	public String getPart()
	{
		return part;
	}
	/**
	 * @return the qSSNumber
	 */
	public String getQSSNumber()
	{
		return qSSNumber;
	}
	/**
	 * @return the company
	 */
	public String getCompany()
	{
		return company;
	}
	/**
	 * @param part the part to set
	 */
	public void setPart(String part)
	{
		this.part = part;
	}

	/**
	 * @param qSSNumber the qSSNumber to set
	 */
	public void setQSSNumber(String qSSNumber)
	{
		this.qSSNumber = qSSNumber;
	}
	/**
	 * @param company the company to set
	 */
	public void setCompany(String company)
	{
		this.company = company;
	}
}
