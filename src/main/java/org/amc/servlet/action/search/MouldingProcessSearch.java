package org.amc.servlet.action.search;

import java.util.Date;

public class MouldingProcessSearch  extends WebFormSearch
{
	public enum ProcessSearchFields implements SearchFields
	{
		PART_NAME("partId"),
		MACHINE_NO("machineNo"),
		MATERIAL("material"),
		MASTERBATCH("masterbatchNo"),
		START_DATE("dateOfIssue"),
		END_DATE("dateOfIssue"),
		SIGNED_OFF_BY("signOffBy");
		
		private final String name;
		private ProcessSearchFields(String name)
		{
			this.name=name;
		}
		
		@Override
		public String toString()
		{
			return name;
		}
		
		
	}
	public MouldingProcessSearch()
	{
		super();
	}
	
	public void setPartId(int partId)
	{
		getFieldMap().put(ProcessSearchFields.PART_NAME,partId);
	}
	
	public void setMachineNo(String machineNo)
	{
		getFieldMap().put(ProcessSearchFields.MACHINE_NO,machineNo);
	}
	
	public void setMaterial(int materialId)
	{
		getFieldMap().put(ProcessSearchFields.MATERIAL,materialId);
	}
	
	public void setMasterBatchNo(int masterbatchNo)
	{
		getFieldMap().put(ProcessSearchFields.MASTERBATCH,masterbatchNo);
	}
	
	public void setSignOffBy(String signee)
	{
		getFieldMap().put(ProcessSearchFields.SIGNED_OFF_BY,signee);
	}
	
	public void setStartDate(Date startDate)
	{
		getFieldMap().put(ProcessSearchFields.START_DATE,startDate);
	}
	
	public void setEndDate(Date endDate)
	{
		getFieldMap().put(ProcessSearchFields.END_DATE,endDate);
	}
	
	public Integer getPartId()
	{
		return (Integer)getFieldMap().get(ProcessSearchFields.PART_NAME);
	}
	
	public String getMachineNo()
	{
		return String.valueOf(getFieldMap().get(ProcessSearchFields.MACHINE_NO));
	}
	
	public Integer getMaterial()
	{
		return (Integer)getFieldMap().get(ProcessSearchFields.MATERIAL);
	}
	
	public Integer getMasterBatchNo()
	{
		return (Integer)getFieldMap().get(ProcessSearchFields.MASTERBATCH);
	}
	
	public String getSignOffBy()
	{
		return String.valueOf(getFieldMap().get(ProcessSearchFields.SIGNED_OFF_BY));
	}
	
	public Date getStartDate()
	{
		return (Date)getFieldMap().get(ProcessSearchFields.START_DATE);
	}
	
	public Date getEndDate()
	{
		return (Date)getFieldMap().get(ProcessSearchFields.END_DATE);
	}
	
}
