package org.amc.model;

import java.io.Serializable;

import javax.ejb.Stateful;

/**
 * POJO to represent a material used for plastic injection moulding
 * @author adrian
 *
 */
@Stateful
public class MaterialBean implements MaterialBeanRemote, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2611230247508857580L;
	private int id;
	private String company;
	private String name;
	private String type;
	private float density;
	private float linear_expansion;
	private float water_absorption;
	private float material_drying;	  
	private float melting_temp_lower;
	private float melting_temp_upper;
	private float mould_shrinkage;
	private float mould_temp_low;
	private float mould_temp_upper;

	public MaterialBean()
	{
		
	}

	/* (non-Javadoc)
	 * @see org.amc.model.MaterialBeanRemote#getId()
	 */
	@Override
	public int getId()
	{
		return id;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.MaterialBeanRemote#getCompany()
	 */
	@Override
	public String getCompany()
	{
		return company;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.MaterialBeanRemote#getName()
	 */
	@Override
	public String getName()
	{
		return name;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.MaterialBeanRemote#getType()
	 */
	@Override
	public String getType()
	{
		return type;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.MaterialBeanRemote#getDensity()
	 */
	@Override
	public float getDensity()
	{
		return density;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.MaterialBeanRemote#getLinear_expansion()
	 */
	@Override
	public float getLinear_expansion()
	{
		return linear_expansion;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.MaterialBeanRemote#getWater_absorption()
	 */
	@Override
	public float getWater_absorption()
	{
		return water_absorption;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.MaterialBeanRemote#getMaterial_drying()
	 */
	@Override
	public float getMaterial_drying()
	{
		return material_drying;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.MaterialBeanRemote#getMelting_temp_lower()
	 */
	@Override
	public float getMelting_temp_lower()
	{
		return melting_temp_lower;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.MaterialBeanRemote#getMelting_temp_upper()
	 */
	@Override
	public float getMelting_temp_upper()
	{
		return melting_temp_upper;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.MaterialBeanRemote#getMould_shrinkage()
	 */
	@Override
	public float getMould_shrinkage()
	{
		return mould_shrinkage;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.MaterialBeanRemote#getMould_temp_low()
	 */
	@Override
	public float getMould_temp_low()
	{
		return mould_temp_low;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.MaterialBeanRemote#getMould_temp_upper()
	 */
	@Override
	public float getMould_temp_upper()
	{
		return mould_temp_upper;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.MaterialBeanRemote#setId(int)
	 */
	@Override
	public void setId(int id)
	{
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.MaterialBeanRemote#setCompany(java.lang.String)
	 */
	@Override
	public void setCompany(String company)
	{
		this.company = company;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.MaterialBeanRemote#setName(java.lang.String)
	 */
	@Override
	public void setName(String name)
	{
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.MaterialBeanRemote#setType(java.lang.String)
	 */
	@Override
	public void setType(String type)
	{
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.MaterialBeanRemote#setDensity(float)
	 */
	@Override
	public void setDensity(float density)
	{
		this.density = density;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.MaterialBeanRemote#setLinear_expansion(float)
	 */
	@Override
	public void setLinear_expansion(float linear_expansion)
	{
		this.linear_expansion = linear_expansion;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.MaterialBeanRemote#setWater_absorption(float)
	 */
	@Override
	public void setWater_absorption(float water_absorption)
	{
		this.water_absorption = water_absorption;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.MaterialBeanRemote#setMaterial_drying(float)
	 */
	@Override
	public void setMaterial_drying(float material_drying)
	{
		this.material_drying = material_drying;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.MaterialBeanRemote#setMelting_temp_lower(float)
	 */
	@Override
	public void setMelting_temp_lower(float melting_temp_lower)
	{
		this.melting_temp_lower = melting_temp_lower;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.MaterialBeanRemote#setMelting_temp_upper(float)
	 */
	@Override
	public void setMelting_temp_upper(float melting_temp_upper)
	{
		this.melting_temp_upper = melting_temp_upper;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.MaterialBeanRemote#setMould_shrinkage(float)
	 */
	@Override
	public void setMould_shrinkage(float mould_shrinkage)
	{
		this.mould_shrinkage = mould_shrinkage;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.MaterialBeanRemote#setMould_temp_low(float)
	 */
	@Override
	public void setMould_temp_low(float mould_temp_low)
	{
		this.mould_temp_low = mould_temp_low;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.MaterialBeanRemote#setMould_temp_upper(float)
	 */
	@Override
	public void setMould_temp_upper(float mould_temp_upper)
	{
		this.mould_temp_upper = mould_temp_upper;
	}

	@Override
	public String toString()
	{
		return this.getCompany()+" "+this.getName()+" "+this.getType();
	}
	
	@Override
	public boolean equals(Object material)
	{
		MaterialBeanRemote m2=(MaterialBeanRemote)material;
		
		if(m2 instanceof MaterialBean)
		{
			if(this.getName().equals(m2.getName()) && this.getCompany().equals(m2.getCompany()) && getType().equals(m2.getType()))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
}
