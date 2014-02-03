package org.amc.servlet.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.amc.servlet.model.Material;

import java.sql.PreparedStatement;

import javax.sql.DataSource;

public class MaterialDAOImpl extends BasicDAO implements MaterialDAO
{


	public MaterialDAOImpl(DataSource dataSource)
	{
		super(dataSource);
		// TODO Auto-generated constructor stub
	}

	private static String tablename="material";
	@Override
	public Material getMaterial(String materialId) throws SQLException
	{
		Connection connection=getConnection();
		PreparedStatement p=connection.prepareStatement("select * from "+tablename+" where id=?");
		p.setString(1, materialId);
		
		ResultSet rs=p.executeQuery();
		Material m=null;
		if(rs.next())
		{
			m=getMaterial(rs);
		}
		closeDBObjects(rs, p, connection);
		return m;
	}

	@Override
	public List<Material> findMaterials(String col, String value)
			throws SQLException
	{
		Connection connection=getConnection();
		PreparedStatement statement=connection.prepareStatement("select * from "+tablename+" where "+col+" REGEXP ?;");
		
		statement.setString(1, value);
		ResultSet rs=statement.executeQuery();
		List<Material> list=new ArrayList<Material>();
		
		while(rs.next())
		{
			Material tempMaterial=getMaterial(rs);
			list.add(tempMaterial);
		}
		closeDBObjects(rs, statement, connection);
		
		return list;
	}

	@Override
	public List<Material> findMaterials() throws SQLException
	{
		Connection connection=getConnection();
		Statement statement=connection.createStatement();
		ResultSet rs=statement.executeQuery("select * from "+tablename+";");
		List<Material> list=new ArrayList<Material>();
		
		while(rs.next())
		{
			Material tempMaterial=getMaterial(rs);
			list.add(tempMaterial);
		}
		
		closeDBObjects(rs, statement, connection);
		return list;
	}

	private Material getMaterial(ResultSet rs) throws SQLException
	{
		Material m =new Material();
		
		m.setId(rs.getInt("id"));
		m.setCompany(rs.getString("company"));
		m.setName(rs.getString("name"));
		m.setDensity(rs.getFloat("density"));
		m.setLinear_expansion(rs.getFloat("linear_expansion"));	
		m.setType(rs.getString("type"));
		m.setWater_absorption(rs.getFloat("water_absorption"));
		m.setMaterial_drying(rs.getFloat("material_drying"));	  
		m.setMelting_temp_lower(rs.getFloat("melting_temp_lower"));
		m.setMelting_temp_upper(rs.getFloat("melting_temp_upper"));
		m.setMould_shrinkage(rs.getFloat("mould_shrinkage"));
		m.setMould_temp_low(rs.getFloat("mould_temp_low"));
		m.setMould_temp_upper(rs.getFloat("mould_temp_upper"));
		
		return m;
	}


}
