package org.amc.servlet.model;

public class MouldingProcessSearchForm implements WebPageForm
{
	private String partId;
	private String machineNo;
	private String material;
	private String masterbatchNo;
	private String startDate;
	private String endDate;
	private String signOffBy;
	/**
	 * @return the partId
	 */
	public String getPartId()
	{
		return partId;
	}
	/**
	 * @return the machineNo
	 */
	public String getMachineNo()
	{
		return machineNo;
	}
	/**
	 * @return the material
	 */
	public String getMaterial()
	{
		return material;
	}
	/**
	 * @return the masterbatchNo
	 */
	public String getMasterbatchNo()
	{
		return masterbatchNo;
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
	 * @return the signOffBy
	 */
	public String getSignOffBy()
	{
		return signOffBy;
	}
	/**
	 * @param partId the partId to set
	 */
	public void setPartId(String partId)
	{
		this.partId = partId;
	}
	/**
	 * @param machineNo the machineNo to set
	 */
	public void setMachineNo(String machineNo)
	{
		this.machineNo = machineNo;
	}
	/**
	 * @param material the material to set
	 */
	public void setMaterial(String material)
	{
		this.material = material;
	}
	/**
	 * @param masterbatchNo the masterbatchNo to set
	 */
	public void setMasterbatchNo(String masterbatchNo)
	{
		this.masterbatchNo = masterbatchNo;
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
	 * @param signOffBy the signOffBy to set
	 */
	public void setSignOffBy(String signOffBy)
	{
		this.signOffBy = signOffBy;
	}
	/* (non-Javadoc)
	 * @see org.amc.servlet.model.WebPageForm#isEmpty()
	 */
	@Override
	public boolean isEmpty()
	{
		if(this.endDate==null && 
				this.machineNo==null && 
				this.masterbatchNo==null && 
				this.material==null &&
				this.partId==null &&
				this.signOffBy==null &&
				this.startDate==null
				
			)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	
}
