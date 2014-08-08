package org.amc.servlet.model;

public class PartSearchForm implements WebPageForm
{
	private String partName;
	private String qSSNumber;
	private String company;
	/**
	 * @return the part
	 */
	public String getPartName()
	{
		return partName;
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
	public void setPartName(String part)
	{
		this.partName = part;
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
