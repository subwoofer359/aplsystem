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
	/* (non-Javadoc)
	 * @see org.amc.servlet.model.WebPageForm#isEmpty()
	 */
	@Override
	public boolean isEmpty()
	{
		if((this.company==null||company.trim().equals("")) && 
				(this.partName==null||partName.trim().equals("")) && 
				(this.qSSNumber==null||qSSNumber.trim().equals("")))
		{
			return true;
		}
		else
			return false;
	}
	
	@Override
	public String toString()
	{
		StringBuilder text=new StringBuilder();
		text.append("PartSearch:");		
		text.append("(Company=");text.append(getCompany());text.append("-"+(getCompany()==null));text.append(')');
		text.append("(PartName=");text.append(getPartName());text.append(')');
		text.append("(QSSNumber=");text.append(getQSSNumber());text.append(')');
		
		
		return text.toString();
	}
	
}
