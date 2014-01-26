package org.amc.servlet.model;
/**
 * POJO to represent a material used for plastic injection moulding
 * @author adrian
 *
 */
public class Material
{
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

	public Material()
	{
		
	}

	public int getId()
	{
		return id;
	}

	public String getCompany()
	{
		return company;
	}

	public String getName()
	{
		return name;
	}

	public String getType()
	{
		return type;
	}

	public float getDensity()
	{
		return density;
	}

	public float getLinear_expansion()
	{
		return linear_expansion;
	}

	public float getWater_absorption()
	{
		return water_absorption;
	}

	public float getMaterial_drying()
	{
		return material_drying;
	}

	public float getMelting_temp_lower()
	{
		return melting_temp_lower;
	}

	public float getMelting_temp_upper()
	{
		return melting_temp_upper;
	}

	public float getMould_shrinkage()
	{
		return mould_shrinkage;
	}

	public float getMould_temp_low()
	{
		return mould_temp_low;
	}

	public float getMould_temp_upper()
	{
		return mould_temp_upper;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public void setCompany(String company)
	{
		this.company = company;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public void setDensity(float density)
	{
		this.density = density;
	}

	public void setLinear_expansion(float linear_expansion)
	{
		this.linear_expansion = linear_expansion;
	}

	public void setWater_absorption(float water_absorption)
	{
		this.water_absorption = water_absorption;
	}

	public void setMaterial_drying(float material_drying)
	{
		this.material_drying = material_drying;
	}

	public void setMelting_temp_lower(float melting_temp_lower)
	{
		this.melting_temp_lower = melting_temp_lower;
	}

	public void setMelting_temp_upper(float melting_temp_upper)
	{
		this.melting_temp_upper = melting_temp_upper;
	}

	public void setMould_shrinkage(float mould_shrinkage)
	{
		this.mould_shrinkage = mould_shrinkage;
	}

	public void setMould_temp_low(float mould_temp_low)
	{
		this.mould_temp_low = mould_temp_low;
	}

	public void setMould_temp_upper(float mould_temp_upper)
	{
		this.mould_temp_upper = mould_temp_upper;
	}

	@Override
	public String toString()
	{
		return this.getCompany()+" "+this.getName()+" "+this.getType();
	}
}
