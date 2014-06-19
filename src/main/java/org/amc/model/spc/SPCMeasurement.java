package org.amc.model.spc;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.amc.model.Part;
import org.amc.model.WorkEntity;


/**
 * 
 * @author Adrian McLaughlin
 * @version 1
 *
 * JPA Entity to store information of what SPC Measurements should be stored
 */
@Entity
@Table(name="SPCMeasurements")
public class SPCMeasurement implements Serializable, WorkEntity
{

	private static final long serialVersionUID = 5160093236226158094L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	/**
	 * The part the SPC measurements will be taken from
	 */
	@NotNull
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="part_id",nullable=false)
	private Part part;
	
	/**
	 * The dimension of the part to be measured
	 */
	@NotNull
	@Column(nullable=false,length=100)
	private String dimension;
	
	/**
	 * The desired value of the measurement taken from the part
	 */
	@NotNull
	@Column(nullable=false)
	private float nominal;
	
	/**
	 * The upper tolerance 
	 */
	@NotNull
	@Column(nullable=false)
	private float upperLimit;
	
	/**
	 * The lower tolerance 
	 */
	@NotNull
	@Column(nullable=false)
	private float lowerLimit;
	
	/**
	 * The number of parts that have to be measured 
	 */
	@NotNull
	@Column(nullable=false)
	private int noOfMeasurements;
	
	
	/**
	 * Is the SPC data still needed to be taken?
	 */
	@NotNull
	@Column(nullable=false)
	private boolean active;

	@Column(name="table_id",nullable=true,length=70)
	private String tableId;
	/**
	 * Required by JPA for concurrency
	 */
	@Version
	private long version;
	//@Todo add reference to SPCData
	
	public int getId()
	{
		return id;
	}

	public Part getPart()
	{
		return part;
	}

	public String getDimension()
	{
		return dimension;
	}

	public float getNominal()
	{
		return nominal;
	}

	public float getUpperLimit()
	{
		return upperLimit;
	}

	public float getLowerLimit()
	{
		return lowerLimit;
	}

	public int getNoOfMeasurements()
	{
		return noOfMeasurements;
	}

	public boolean isActive()
	{
		return active;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public void setPart(Part part)
	{
		this.part = part;
	}

	public void setDimension(String dimension)
	{
		this.dimension = dimension;
	}

	public void setNominal(float nominal)
	{
		this.nominal = nominal;
	}

	public void setUpperLimit(float upperLimit)
	{
		this.upperLimit = upperLimit;
	}

	public void setLowerLimit(float lowerLimit)
	{
		this.lowerLimit = lowerLimit;
	}

	public void setNoOfMeasurements(int noOfMeasurements)
	{
		this.noOfMeasurements = noOfMeasurements;
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}

	public long getVersion()
	{
		return version;
	}

	public String getTableId()
	{
		return tableId;
	}

	public void setTableId(String tableId)
	{
		this.tableId = tableId;
	}
	
	
	
	
}
