package org.amc.servlet.model;
/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
public class PartForm 
{
	private String id;
	
	private String colour;
	private String company;
	private boolean external;
	private String name;
	private String part_id;
	private String qss_no;
	private String revision;
	private String version;
	
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public boolean isExternal() {
		return external;
	}
	public void setExternal(boolean external) {
		this.external = external;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPart_id() {
		return part_id;
	}
	public void setPart_id(String part_id) {
		this.part_id = part_id;
	}
	public String getQss_no() {
		return qss_no;
	}
	public void setQss_no(String qss_no) {
		this.qss_no = qss_no;
	}
	public String getRevision() {
		return revision;
	}
	public void setRevision(String revision) {
		this.revision = revision;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}

