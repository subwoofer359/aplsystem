package org.amc.servlet.model;

public class PartSearchForm implements WebPageForm
{
	private String part;
	private String startDate;
	private String endDate;
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
	 * @return the startDate
	 */
	public String getStartDate()
	{
		return startDate;
	}
	/**
	 * @return the endDate
	 */
	public String getEndDate()
	{
		return endDate;
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
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
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
