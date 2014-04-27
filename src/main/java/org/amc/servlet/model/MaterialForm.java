package org.amc.servlet.model;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.amc.model.MaterialBeanRemote;

/**
 * POJO to represent a material used for plastic injection moulding
 * @author adrian
 *
 */
public class MaterialForm
{
	private String id;
	private String company;
	private String name;
	private String type;
	private String density;
	private String linear_expansion;
	private String water_absorption;
	private String material_drying;	  
	private String melting_temp_lower;
	private String melting_temp_upper;
	private String mould_shrinkage;
	private String mould_temp_low;
	private String mould_temp_upper;

	public MaterialForm()
	{
		
	}

	public String getId()
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

	public String getDensity()
	{
		return density;
	}

	public String getLinear_expansion()
	{
		return linear_expansion;
	}

	public String getWater_absorption()
	{
		return water_absorption;
	}

	public String getMaterial_drying()
	{
		return material_drying;
	}

	public String getMelting_temp_lower()
	{
		return melting_temp_lower;
	}

	public String getMelting_temp_upper()
	{
		return melting_temp_upper;
	}

	public String getMould_shrinkage()
	{
		return mould_shrinkage;
	}

	public String getMould_temp_low()
	{
		return mould_temp_low;
	}

	public String getMould_temp_upper()
	{
		return mould_temp_upper;
	}

	public void setId(String id)
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

	public void setDensity(String density)
	{
		this.density = density;
	}

	public void setLinear_expansion(String linear_expansion)
	{
		this.linear_expansion = linear_expansion;
	}

	public void setWater_absorption(String water_absorption)
	{
		this.water_absorption = water_absorption;
	}

	public void setMaterial_drying(String material_drying)
	{
		this.material_drying = material_drying;
	}

	public void setMelting_temp_lower(String melting_temp_lower)
	{
		this.melting_temp_lower = melting_temp_lower;
	}

	public void setMelting_temp_upper(String melting_temp_upper)
	{
		this.melting_temp_upper = melting_temp_upper;
	}

	public void setMould_shrinkage(String mould_shrinkage)
	{
		this.mould_shrinkage = mould_shrinkage;
	}

	public void setMould_temp_low(String mould_temp_low)
	{
		this.mould_temp_low = mould_temp_low;
	}

	public void setMould_temp_upper(String mould_temp_upper)
	{
		this.mould_temp_upper = mould_temp_upper;
	}

	@Override
	public String toString()
	{
		return this.getCompany()+" "+this.getName()+" "+this.getType();
	}
	
	public static MaterialBeanRemote getMaterial(MaterialForm form) throws Exception
	{
		MaterialBeanRemote material = null;
		InitialContext ctx;
		try
		{
			Properties props = new Properties();
			props.put(Context.INITIAL_CONTEXT_FACTORY,
					"org.apache.openejb.client.RemoteInitialContextFactory");
			props.put(Context.PROVIDER_URL, "ejbd://127.0.0.1:4201");
			ctx = new InitialContext(props);
			material = (MaterialBeanRemote) ctx.lookup("MaterialBeanRemote");

			material.setId(Integer.parseInt(form.getId()));
			material.setCompany(form.getCompany());
			material.setDensity(Float.parseFloat(form.getDensity()));
			material.setLinear_expansion(Float.parseFloat(form
					.getLinear_expansion()));
			material.setMaterial_drying(Float.parseFloat(form
					.getMaterial_drying()));
			material.setMelting_temp_lower(Float.parseFloat(form
					.getMelting_temp_lower()));
			material.setMelting_temp_upper(Float.parseFloat(form
					.getMelting_temp_upper()));
			material.setMould_shrinkage(Float.parseFloat(form
					.getMould_shrinkage()));
			material.setMould_temp_low(Float.parseFloat(form
					.getMould_temp_low()));
			material.setMould_temp_upper(Float.parseFloat(form
					.getMould_temp_upper()));
			material.setWater_absorption(Float.parseFloat(form
					.getWater_absorption()));
			material.setType(form.getType());
			material.setName(form.getName());
		} catch (NumberFormatException e)
		{
			Exception exception = new Exception(
					"Couldn't parse MouldingProcessForm into MouldingProcess object");
			exception.addSuppressed(e);
			throw exception;
		} catch (NamingException ex)
		{
			ex.printStackTrace();
		}
		return material;
	}
}
