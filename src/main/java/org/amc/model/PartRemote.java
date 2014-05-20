package org.amc.model;


import javax.ejb.Remote;

@Remote
public interface PartRemote
{

	public String getColour();

	public void setColour(String colour);

	public String getCompany();

	public void setCompany(String company);

	public boolean getExternal();

	public void setExternal(boolean external);

	public String getName();

	public void setName(String name);

	public String getPart_id();

	public void setPart_id(String part_id);

	public String getQss_no();

	public void setQss_no(String qss_no);

	public String getRevision();

	public void setRevision(String revision);

	public String getVersion();

	public void setVersion(String version);

	public int getId();

	public void setId(int id);

}