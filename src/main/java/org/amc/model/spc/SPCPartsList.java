package org.amc.model.spc;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.amc.model.Part;
import org.amc.model.WorkEntity;

/**
 * @author Adrian Mclaughlin
 * @version 1
 *
 */

@Entity
@Table(name="SPCPartsList")
public class SPCPartsList implements WorkEntity,Serializable 
{

	private static final long serialVersionUID = -1921013244039524530L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="part_id",unique=true)
	private Part part;

	public int getId()
	{
		return id;
	}

	public Part getPart()
	{
		return part;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public void setPart(Part part)
	{
		this.part = part;
	}

	
}
