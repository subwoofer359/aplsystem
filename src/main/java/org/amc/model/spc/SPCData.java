package org.amc.model.spc;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.amc.model.User;
import org.amc.model.WorkEntity;

/**
 * @author Adrian McLaughlin
 * @version 1
 * Represents saved SPC data
 */
@Entity
@Table //not sure what to put for this value
public class SPCData implements Serializable, WorkEntity
{
	private static final long serialVersionUID = 5840756393419920613L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	/**
	 * Required by JPA for concurrency
	 */
	@Version
	private long version;
	
	/**
	 * The date the measurement was taken
	 */
	@NotNull
	@Column
	private Date date;
	
	/**
	 * The user that took the measurement
	 */
	@NotNull
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id",nullable=false)
	private User user;
	
	/**
	 * The number of this measurement e.g. 3 of 5
	 */
	@NotNull
	@Column
	private int measurementNumber;
	
	/**
	 * The actual measurement
	 */
	@NotNull
	@Column
	private float measurement;
	
	/**
	 * The SPCMeasure object this SPCData is reference by
	 */
	@NotNull
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="measurement_id",nullable=false)
	private SPCMeasurement spcMeasurement;

	public int getId()
	{
		return id;
	}

	public long getVersion()
	{
		return version;
	}

	public Date getDate()
	{
		return date;
	}

	public User getUser()
	{
		return user;
	}

	public int getMeasurementNumber()
	{
		return measurementNumber;
	}

	public float getMeasurement()
	{
		return measurement;
	}

	public SPCMeasurement getSpcMeasurement()
	{
		return spcMeasurement;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public void setMeasurementNumber(int measurementNumber)
	{
		this.measurementNumber = measurementNumber;
	}

	public void setMeasurement(float measurement)
	{
		this.measurement = measurement;
	}

	public void setSpcMeasurement(SPCMeasurement spcMeasurement)
	{
		this.spcMeasurement = spcMeasurement;
	}
	
	

}
