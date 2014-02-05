package org.amc.myservlet.test;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.amc.servlet.action.SearchMaterialAction;
import org.amc.servlet.dao.MaterialDAO;
import org.amc.servlet.dao.MaterialDAOImpl;
import org.amc.servlet.model.Material;
import org.junit.*;

import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.Map;

import javax.sql.DataSource;

import static org.junit.Assert.*;
public class MaterialActionTest
{
	private DataSource dataSource;
	private static final String DATABASE="test_myservlet";
	private static Material material_1;
	private static Material material_2;
	
	@BeforeClass
	public static void setUpDatabase() throws SQLException
	{
		try
		{
			DataSource dataSource=TestLocalDataSourceCache.getInstance().getDefaultDataSource();
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			connection.createStatement().execute("drop database if exists "+DATABASE+";");
			statement.execute("create database "+DATABASE+";");
			statement.execute("use "+DATABASE+";");
			statement
					.execute("CREATE TABLE material (id int(11) NOT NULL AUTO_INCREMENT,"
							+ "company varchar(100) NOT NULL,"
							+ "name varchar(100) NOT NULL,"
							+ "type varchar(100) NOT NULL,"
							+ "density float DEFAULT NULL,"
							+ "linear_expansion float DEFAULT NULL,"
							+ "water_absorption float DEFAULT NULL,"
							+ "material_drying float DEFAULT NULL,"
							+ "melting_temp_lower float DEFAULT NULL,"
							+ "melting_temp_upper float DEFAULT NULL,"
							+ "mould_shrinkage float DEFAULT NULL,"
							+ "mould_temp_low float DEFAULT NULL,"
							+ "mould_temp_upper float DEFAULT NULL,"
							+ "PRIMARY KEY (id)) ENGINE=InnoDB");

			statement
					.execute("CREATE TABLE processSheets (id int(11) NOT NULL AUTO_INCREMENT,"
							+ "partId varchar(20) DEFAULT NULL,"
							+ " machineSize int(11) DEFAULT NULL,"
							+ " machineNo varchar(20) DEFAULT NULL,"
							+ " material int(11) NOT NULL,"
							+ " masterbatchNo varchar(20) DEFAULT NULL,"
							+ " dateOfIssue date DEFAULT NULL,"
							+ " signOffBy varchar(20) DEFAULT NULL,"
							+ " processNotes varchar(100) DEFAULT NULL,"
							+ " injectionSpeed_1 float DEFAULT NULL,"
							+ " injectionSpeed_2 float DEFAULT NULL,"
							+ " injectionSpeed_3 float DEFAULT NULL,"
							+ " injectionSpeed_4 float DEFAULT NULL,"
							+ " injectionSpeed_5 float DEFAULT NULL,"
							+ " injectionSpeed_6 float DEFAULT NULL,"
							+ " injSpeedPosition_1 float DEFAULT NULL,"
							+ " injSpeedPosition_2 float DEFAULT NULL,"
							+ " injSpeedPosition_3 float DEFAULT NULL,"
							+ " injSpeedPosition_4 float DEFAULT NULL,"
							+ " injSpeedPosition_5 float DEFAULT NULL,"
							+ " injSpeedPosition_6 float DEFAULT NULL,"
							+ " holdingPressure_1 float DEFAULT NULL,"
							+ " holdingPressure_2 float DEFAULT NULL,"
							+ " holdingPressure_3 float DEFAULT NULL,"
							+ " holdingPressure_4 float DEFAULT NULL,"
							+ " holdingPressure_5 float DEFAULT NULL,"
							+ " holdingPressure_6 float DEFAULT NULL,"
							+ " holdingTime_1 float DEFAULT NULL,"
							+ " holdingTime_2 float DEFAULT NULL,"
							+ " holdingTime_3 float DEFAULT NULL,"
							+ " holdingTime_4 float DEFAULT NULL,"
							+ " holdingTime_5 float DEFAULT NULL,"
							+ " holdingTime_6 float DEFAULT NULL,"
							+ " maxPackVel float DEFAULT NULL,"
							+ " posTran float DEFAULT NULL,"
							+ " maxInjPre float DEFAULT NULL,"
							+ " maxInjTime float DEFAULT NULL,"
							+ " shotSize float DEFAULT NULL,"
							+ " decompressionDist float DEFAULT NULL,"
							+ " decompressionVel float DEFAULT NULL,"
							+ " coolTime float DEFAULT NULL,"
							+ " backPressure_1 float DEFAULT NULL,"
							+ " backPressure_2 float DEFAULT NULL,"
							+ " backPressure_3 float DEFAULT NULL,"
							+ " backPressure_4 float DEFAULT NULL,"
							+ " backPressure_5 float DEFAULT NULL,"
							+ " backPressure_6 float DEFAULT NULL,"
							+ " screwExtSpeed_1 float DEFAULT NULL,"
							+ " screwExtSpeed_2 float DEFAULT NULL,"
							+ " screwExtSpeed_3 float DEFAULT NULL,"
							+ " screwExtSpeed_4 float DEFAULT NULL,"
							+ " screwExtSpeed_5 float DEFAULT NULL,"
							+ " screwExtSpeed_6 float DEFAULT NULL,"
							+ " extProfilePos_1 float DEFAULT NULL,"
							+ " extProfilePos_2 float DEFAULT NULL,"
							+ " extProfilePos_3 float DEFAULT NULL,"
							+ " extProfilePos_4 float DEFAULT NULL,"
							+ " extProfilePos_5 float DEFAULT NULL,"
							+ " extProfilePos_6 float DEFAULT NULL,"
							+ " nozzelTemperature float DEFAULT NULL,"
							+ " barrelTemperature_1 float DEFAULT NULL,"
							+ " barrelTemperature_2 float DEFAULT NULL,"
							+ " barrelTemperature_3 float DEFAULT NULL,"
							+ " barrelTemperature_4 float DEFAULT NULL,"
							+ " throatTemperature float DEFAULT NULL,"
							+ " mouldClosingOpenLimitPos float DEFAULT NULL,"
							+ " mouldClosingOpenLimitSpeed float DEFAULT NULL,"
							+ " mouldClosedLimitPos float DEFAULT NULL,"
							+ " mouldClosedLimitSpeed float DEFAULT NULL,"
							+ " clsSlowPos float DEFAULT NULL,"
							+ " clsSlowSpeed float DEFAULT NULL,"
							+ " clsSPPos float DEFAULT NULL,"
							+ " clsSPSpeed float DEFAULT NULL,"
							+ " mouldOpenBreakAwaySpeed float DEFAULT NULL,"
							+ " mouldOpenStepPos_1 float DEFAULT NULL,"
							+ " mouldOpenStepSpeed_1 float DEFAULT NULL,"
							+ " mouldOpenStepPos_2 float DEFAULT NULL,"
							+ " mouldOpenStepSpeed_2 float DEFAULT NULL,"
							+ " mouldOpenStepPos_3 float DEFAULT NULL,"
							+ " mouldOpenStepSpeed_3 float DEFAULT NULL,"
							+ " mouldOpenTime float DEFAULT NULL,"
							+ " ejectStart varchar(10) DEFAULT NULL,"
							+ " ejectMode varchar(10) DEFAULT NULL,"
							+ " ejectPulse float DEFAULT NULL,"
							+ " ejectDelay float DEFAULT NULL,"
							+ " ejectorsFwdPos float DEFAULT NULL,"
							+ " ejectorsFwdSpeed float DEFAULT NULL,"
							+ " ejectorsFwdTime float DEFAULT NULL,"
							+ " ejectorsStopPos float DEFAULT NULL,"
							+ " ejectorsStopSpeed float DEFAULT NULL,"
							+ " ejectorsStopTime float DEFAULT NULL,"
							+ " ejectorsRevPos float DEFAULT NULL,"
							+ " ejectorsRevSpeed float DEFAULT NULL,"
							+ " ejectorsRevTime float DEFAULT NULL,"
							+ " dme_1 float DEFAULT NULL,"
							+ " dme_2 float DEFAULT NULL,"
							+ " dme_3 float DEFAULT NULL,"
							+ " dme_4 float DEFAULT NULL,"
							+ " dme_5 float DEFAULT NULL,"
							+ " dme_6 float DEFAULT NULL,"
							+ " dme_7 float DEFAULT NULL,"
							+ " dme_8 float DEFAULT NULL,"
							+ " waterTempFixedHalf float DEFAULT NULL,"
							+ " waterTempMovingHalf float DEFAULT NULL,"
							+ " waterTempNotes varchar(100) DEFAULT NULL,"
							+ " PRIMARY KEY (id),"
							+ " index material (material),"
							+ " CONSTRAINT fk_material FOREIGN KEY (material) REFERENCES material (id)) ENGINE=InnoDB;");

			material_1 = new Material();
			material_1.setCompany("ACME");
			material_1.setName("ll6201");
			material_1.setDensity(0.5f);
			material_1.setLinear_expansion(22.2f);
			material_1.setMaterial_drying(50);
			material_1.setMelting_temp_lower(60);
			material_1.setMelting_temp_upper(120);
			material_1.setMould_shrinkage(.03f);
			material_1.setMould_temp_low(20);
			material_1.setMould_temp_upper(200);
			material_1.setType("Resin");
			material_1.setWater_absorption(2.3f);

			material_2 = new Material();
			material_2.setCompany("TT");
			material_2.setName("PPDE");
			material_2.setDensity(0.2f);
			material_2.setLinear_expansion(12.1f);
			material_2.setMaterial_drying(60);
			material_2.setMelting_temp_lower(100);
			material_2.setMelting_temp_upper(220);
			material_2.setMould_shrinkage(.05f);
			material_2.setMould_temp_low(60);
			material_2.setMould_temp_upper(100);
			material_2.setType("Resin Pellets");
			material_2.setWater_absorption(4.2f);

			statement.close();

			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into material VALUES(DEFAULT,?,?,?,?,?,?,?,?,?,?,?,?);");
			preparedStatement.setString(1, material_1.getCompany());
			preparedStatement.setString(2, material_1.getName());
			preparedStatement.setString(3, material_1.getType());
			preparedStatement.setFloat(4, material_1.getDensity());
			preparedStatement.setFloat(5, material_1.getLinear_expansion());
			preparedStatement.setFloat(6, material_1.getWater_absorption());
			preparedStatement.setFloat(7, material_1.getMaterial_drying());
			preparedStatement.setFloat(8, material_1.getMelting_temp_lower());
			preparedStatement.setFloat(9, material_1.getMelting_temp_upper());
			preparedStatement.setFloat(10, material_1.getMould_shrinkage());
			preparedStatement.setFloat(11, material_1.getMould_temp_low());
			preparedStatement.setFloat(12, material_1.getMould_temp_upper());

			preparedStatement.execute();

			preparedStatement.setString(1, material_2.getCompany());
			preparedStatement.setString(2, material_2.getName());
			preparedStatement.setString(3, material_2.getType());
			preparedStatement.setFloat(4, material_2.getDensity());
			preparedStatement.setFloat(5, material_2.getLinear_expansion());
			preparedStatement.setFloat(6, material_2.getWater_absorption());
			preparedStatement.setFloat(7, material_2.getMaterial_drying());
			preparedStatement.setFloat(8, material_2.getMelting_temp_lower());
			preparedStatement.setFloat(9, material_2.getMelting_temp_upper());
			preparedStatement.setFloat(10, material_2.getMould_shrinkage());
			preparedStatement.setFloat(11, material_2.getMould_temp_low());
			preparedStatement.setFloat(12, material_2.getMould_temp_upper());

			preparedStatement.execute();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public static void  closeDatabase() throws SQLException
	{
		DataSource dataSource=TestLocalDataSourceCache.getInstance().getDataSource();
		Connection connection=dataSource.getConnection();
		connection.createStatement().execute("use test_myservlet;");
		connection.createStatement().execute("drop table processSheets;");
		connection.createStatement().execute("drop table material;");
		connection.createStatement().execute("drop database test_myservlet;");
		connection.close();
	}

	@Before
	public void setUp()
	{
		this.dataSource=TestLocalDataSourceCache.getInstance().getDataSource();
	}
	
	@Test
	public void testSearch()
	{
		MaterialDAO materialDAO=new MaterialDAOImpl(dataSource);
		SearchMaterialAction search=new SearchMaterialAction(materialDAO);
		
		try
		{
			Map<Integer,Material> list=search.search();
			assertEquals(list.size(),2);
			
			Material m=search.getMaterial("1");
			assertTrue(m.equals(material_1));
			
			list=search.search("material_drying", "60");
			assertEquals(list.size(),1);
			Iterator<Material> i=list.values().iterator();
			Material m2=i.next();
			assertTrue(m2.equals(material_2));
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
