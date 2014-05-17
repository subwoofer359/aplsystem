package org.amc.model;

import javax.ejb.Remote;

@Remote
public interface MaterialBeanRemote
{

	public int getId();

	public String getCompany();

	public String getName();

	public String getType();

	public float getDensity();

	public float getLinear_expansion();

	public float getWater_absorption();

	public float getMaterial_drying();

	public float getMelting_temp_lower();

	public float getMelting_temp_upper();

	public float getMould_shrinkage();

	public float getMould_temp_low();

	public float getMould_temp_upper();

	public void setId(int id);

	public void setCompany(String company);

	public void setName(String name);

	public void setType(String type);

	public void setDensity(float density);

	public void setLinear_expansion(float linear_expansion);

	public void setWater_absorption(float water_absorption);

	public void setMaterial_drying(float material_drying);

	public void setMelting_temp_lower(float melting_temp_lower);

	public void setMelting_temp_upper(float melting_temp_upper);

	public void setMould_shrinkage(float mould_shrinkage);

	public void setMould_temp_low(float mould_temp_low);

	public void setMould_temp_upper(float mould_temp_upper);
	
	@Override
	public String toString();

}