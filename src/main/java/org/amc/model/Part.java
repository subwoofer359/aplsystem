package org.amc.model;


import java.io.Serializable;

import javax.ejb.Local;
import javax.ejb.Stateful;



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
@Stateful
public class Part implements PartRemote, PartLocal,Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8498315270583017514L;
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
	public Part()
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
	public Part(String name,
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

	/* (non-Javadoc)
	 * @see org.amc.servlet.model.PartRemote#getColour()
	 */
	@Override
	public String getColour() {
		return colour;
	}
	/* (non-Javadoc)
	 * @see org.amc.servlet.model.PartRemote#setColour(java.lang.String)
	 */
	@Override
	public void setColour(String colour) {
		this.colour = colour;
	}
	/* (non-Javadoc)
	 * @see org.amc.servlet.model.PartRemote#getCompany()
	 */
	@Override
	public String getCompany() {
		return company;
	}
	/* (non-Javadoc)
	 * @see org.amc.servlet.model.PartRemote#setCompany(java.lang.String)
	 */
	@Override
	public void setCompany(String company) {
		this.company = company;
	}
	/* (non-Javadoc)
	 * @see org.amc.servlet.model.PartRemote#getExternal()
	 */
	@Override
	public boolean getExternal() {
		return external;
	}
	/* (non-Javadoc)
	 * @see org.amc.servlet.model.PartRemote#setExternal(boolean)
	 */
	@Override
	public void setExternal(boolean external) {
		this.external = external;
	}
	/* (non-Javadoc)
	 * @see org.amc.servlet.model.PartRemote#getName()
	 */
	@Override
	public String getName() {
		return name;
	}
	/* (non-Javadoc)
	 * @see org.amc.servlet.model.PartRemote#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}
	/* (non-Javadoc)
	 * @see org.amc.servlet.model.PartRemote#getPart_id()
	 */
	@Override
	public String getPart_id() {
		return part_id;
	}
	/* (non-Javadoc)
	 * @see org.amc.servlet.model.PartRemote#setPart_id(java.lang.String)
	 */
	@Override
	public void setPart_id(String part_id) {
		this.part_id = part_id;
	}
	/* (non-Javadoc)
	 * @see org.amc.servlet.model.PartRemote#getQss_no()
	 */
	@Override
	public String getQss_no() {
		return qss_no;
	}
	/* (non-Javadoc)
	 * @see org.amc.servlet.model.PartRemote#setQss_no(java.lang.String)
	 */
	@Override
	public void setQss_no(String qss_no) {
		this.qss_no = qss_no;
	}
	/* (non-Javadoc)
	 * @see org.amc.servlet.model.PartRemote#getRevision()
	 */
	@Override
	public String getRevision() {
		return revision;
	}
	/* (non-Javadoc)
	 * @see org.amc.servlet.model.PartRemote#setRevision(java.lang.String)
	 */
	@Override
	public void setRevision(String revision) {
		this.revision = revision;
	}
	/* (non-Javadoc)
	 * @see org.amc.servlet.model.PartRemote#getVersion()
	 */
	@Override
	public String getVersion() {
		return version;
	}
	/* (non-Javadoc)
	 * @see org.amc.servlet.model.PartRemote#setVersion(java.lang.String)
	 */
	@Override
	public void setVersion(String version) {
		this.version = version;
	}

	/* (non-Javadoc)
	 * @see org.amc.servlet.model.PartRemote#getId()
	 */
	@Override
	public int getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see org.amc.servlet.model.PartRemote#setId(int)
	 */
	@Override
	public void setId(int id) {
		this.id = id;
	}

}
