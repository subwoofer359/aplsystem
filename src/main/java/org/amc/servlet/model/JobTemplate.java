package org.amc.servlet.model;


import java.util.HashMap;
import java.util.Map;

import static org.amc.servlet.model.Fields.*;
/**
 * <p>
 * Project: APL_Problem_Database
 * </p>
 * <p>
 * file: $URL: file:///home/adrian/Documents/SVNRepository/Java/APL_Problem_Database/trunk/APL_Problem_Database/src/apl_problem_database/JobTemplate.java $
 * <p>
 * <p>
 * Created on May 4, 2006
 * </p>
 * 
 * @author adrian
 * @version $Revision: 888 $
 * Represents a job running in the factory
 */

public class JobTemplate
{
	private int id;
	private String colour;
	private String company;
	private boolean external;
	private String name;
	private String part_id;
	private String qss_no;
	private String revision;
	private String version;
	 
	
	
	/**
	 * 
	 * Constructor for JobTemplate.java
	 */
	public JobTemplate()
	{
		
	
	}
	
	/**
	 * 
	 * Constructor for JobTemplate.java
	 * @param name
	 * @param part_id
	 * @param company
	 * @param version
	 * @param revision
	 * @param colour
	 * @param external
	 */
	public JobTemplate(String name,
						String part_id,
						String company,
						String version,
						String revision,
						String colour,
						boolean external,
						String qss_no
					)
	{
			
			this.name=name;
			this.part_id=part_id;
			this.company=company;
			this.version=version;
			this.revision=revision;
			this.colour=colour;
			this.external=external;
			this.qss_no=qss_no;
			
			
	}

	@Override
	public String toString()
	{
		String value= String.valueOf(getName())+" ";
		value=value+String.valueOf(getVersion())+" ";
		value=value+String.valueOf(getRevision())+" ";
		value=value+String.valueOf(getColour());
		return value;
	}

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
	public boolean getExternal() {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
