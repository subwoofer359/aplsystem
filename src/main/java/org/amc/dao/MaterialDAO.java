package org.amc.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.amc.model.MaterialRemote;

import java.sql.PreparedStatement;

import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Stateless
public class MaterialDAO extends BasicDAO implements MaterialDAORemote, Serializable
{


	/**
	 * 
	 */
	private static final long serialVersionUID = -4397260307883862647L;

	public MaterialDAO()
	{
	}

	private static String tablename="material";
	
	@Override
	public void addMaterial(MaterialRemote material) throws SQLException
	{
		/*
		`id` int(11) NOT NULL AUTO_INCREMENT,
		  `company` varchar(100) NOT NULL,
		  `name` varchar(100) NOT NULL,
		  `type` varchar(100) NOT NULL,
		  `density` float DEFAULT NULL,
		  `linear_expansion` float DEFAULT NULL,
		  `water_absorption` float DEFAULT NULL,
		  `material_drying` float DEFAULT NULL,
		  `melting_temp_lower` float DEFAULT NULL,
		  `melting_temp_upper` float DEFAULT NULL,
		  `mould_shrinkage` float DEFAULT NULL,
		  `mould_temp_low` float DEFAULT NULL,
		  `mould_temp_upper` float DEFAULT NULL,
		  */
		Connection connection=getConnection();
		PreparedStatement statement=connection.prepareStatement("INSERT INTO "+tablename+" VALUES(NULL,?,?,?,?,?,?,?,?,?,?,?,?)");
		statement.setString(1, material.getCompany());
		statement.setString(2, material.getName());
		statement.setString(3, material.getType());
		statement.setFloat(4, material.getDensity());
		statement.setFloat(5, material.getLinear_expansion());
		statement.setFloat(6, material.getWater_absorption());
		statement.setFloat(7, material.getMaterial_drying());
		statement.setFloat(8, material.getMelting_temp_lower());
		statement.setFloat(9, material.getMelting_temp_upper());
		statement.setFloat(10, material.getMould_shrinkage());
		statement.setFloat(11, material.getMould_temp_low());
		statement.setFloat(12, material.getMould_temp_upper());
		
		statement.executeUpdate();
		closeDBObjects(null, statement, connection);
	}
	
	/* (non-Javadoc)
	 * @see org.amc.servlet.dao.JobTemplateDAO#updateJobTemplate(org.amc.servlet.model.JobTemplate)
	 */
	@Override
	public void updateMaterial(MaterialRemote material) throws SQLException
	{
		Connection connection=getConnection();
		PreparedStatement statement=connection.prepareStatement("UPDATE "+tablename
				+ " set company=?,"
				+ "name=?,"
				+ "type=?,"
				+ "density=?,"
				+ "linear_expansion=?,"
				+ "water_absorption=?,"
				+ "material_drying=?,"
				+ "melting_temp_lower=?,"
				+ "melting_temp_upper=?,"
				+ "mould_shrinkage=?,"
				+ "mould_temp_low=?,"
				+ "mould_temp_upper=? where id=?");
				statement.setString(1, material.getCompany());
				statement.setString(2, material.getName());
				statement.setString(3, material.getType());
				statement.setFloat(4, material.getDensity());
				statement.setFloat(5, material.getLinear_expansion());
				statement.setFloat(6, material.getWater_absorption());
				statement.setFloat(7, material.getMaterial_drying());
				statement.setFloat(8, material.getMelting_temp_lower());
				statement.setFloat(9, material.getMelting_temp_upper());
				statement.setFloat(10, material.getMould_shrinkage());
				statement.setFloat(11, material.getMould_temp_low());
				statement.setFloat(12, material.getMould_temp_upper());
				statement.setString(13, String.valueOf(material.getId()));
				
	
				statement.executeUpdate();
				closeDBObjects(null, statement, connection);
		
	}
	
	@Override
	public MaterialRemote getMaterial(String materialId) throws SQLException
	{
		Connection connection=getConnection();
		PreparedStatement p=connection.prepareStatement("select * from "+tablename+" where id=?");
		p.setString(1, materialId);
		
		ResultSet rs=p.executeQuery();
		MaterialRemote m=null;
		if(rs.next())
		{
			m=getMaterial(rs);
		}
		closeDBObjects(rs, p, connection);
		return m;
	}

	@Override
	public Map<Integer,MaterialRemote> findMaterials(String col, String value)
			throws SQLException
	{
		Connection connection=getConnection();
		PreparedStatement statement=connection.prepareStatement("select * from "+tablename+" where "+col+" REGEXP ?;");
		
		statement.setString(1, value);
		ResultSet rs=statement.executeQuery();
		Map<Integer,MaterialRemote> list=new HashMap<Integer,MaterialRemote>();
		
		while(rs.next())
		{
			MaterialRemote tempMaterial=getMaterial(rs);
			list.put(tempMaterial.getId(),tempMaterial);
		}
		closeDBObjects(rs, statement, connection);
		
		return list;
	}

	@Override
	public Map<Integer,MaterialRemote> findMaterials() throws SQLException
	{
		Connection connection=getConnection();
		Statement statement=connection.createStatement();
		ResultSet rs=statement.executeQuery("select * from "+tablename+";");
		Map<Integer,MaterialRemote> list=new HashMap<Integer,MaterialRemote>();
		
		while(rs.next())
		{
			MaterialRemote tempMaterial=getMaterial(rs);
			list.put(tempMaterial.getId(),tempMaterial);
		}
		
		closeDBObjects(rs, statement, connection);
		return list;
	}

	private MaterialRemote getMaterial(ResultSet rs) throws SQLException
	{
		System.out.println("Creating MaterialRemote");
		InitialContext ctx;
		MaterialRemote m = null;
		try
		{
			Properties props = new Properties();
			//props.put(Context.INITIAL_CONTEXT_FACTORY,"org.apache.openejb.client.RemoteInitialContextFactory");
			props.put(Context.INITIAL_CONTEXT_FACTORY,"org.apache.openejb.client.LocalInitialContextFactory");
			//props.put(Context.PROVIDER_URL, "ejbd://127.0.0.1:4201");
			ctx = new InitialContext(props);
			m = (MaterialRemote) ctx.lookup("MaterialRemote");

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
		} catch (NamingException ex)
		{
			ex.printStackTrace();
		}

		return m;
	}

}
