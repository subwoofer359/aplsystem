package org.amc.servlet.action.search;

import java.util.Date;

import org.amc.model.Part;

/**
 * Class that store information of a User's search parameters for a org.amc.model.Part
 * @author Adrian McLaughlin
 *
 */
public class PartSearch implements Search
{
	private Part part;
	private Date startDate;
	private Date endDate;
	private String qSSNumber;
	private String company;
	/**
	 * @return the part
	 */
	public Part getPart()
	{
		return part;
	}
	/**
	 * @return the startDate
	 */
	public Date getStartDate()
	{
		return startDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate()
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
	public void setPart(Part part)
	{
		this.part = part;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate)
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
