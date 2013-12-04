package org.amc.servlet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.amc.servlet.model.MouldingProcess;

public class MouldingProcessDAOImpl extends BasicDAO implements MouldingProcessDAO 
{
	private static String tablename="processSheets";

	@Override
	public void addProcessSheet(MouldingProcess process) throws SQLException 
	{
		System.out.println(process);
		Connection connection=getConnection();
		PreparedStatement statement=connection.prepareStatement("INSERT INTO "+tablename+" VALUES(DEFAULT,?,?,?,?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,?,?,?,"
				+ "?,?,?,?)");

		statement.setString(1, process.getPartId());
		statement.setInt(2, process.getMachineSize());
		statement.setString(3, process.getMachineNo());
		statement.setString(4, process.getMaterial());
		statement.setString(5, process.getMasterbatchNo());
		statement.setDate(6, process.getDateOfIssue());
		statement.setString(7, process.getSignOffBy());
		statement.setString(8, process.getProcessNotes());
		statement.setFloat(9, process.getInjectionSpeed_1());
		statement.setFloat(10, process.getInjectionSpeed_2());
		
		statement.setFloat(11, process.getInjectionSpeed_3());
		statement.setFloat(12, process.getInjectionSpeed_4());
		statement.setFloat(13, process.getInjectionSpeed_5());
		statement.setFloat(14, process.getInjectionSpeed_6());
		statement.setFloat(15, process.getInjSpeedPosition_1());
		statement.setFloat(16, process.getInjSpeedPosition_2());
		statement.setFloat(17, process.getInjSpeedPosition_3());
		statement.setFloat(18, process.getInjSpeedPosition_4());
		statement.setFloat(19, process.getInjSpeedPosition_5());
		
		statement.setFloat(20, process.getInjSpeedPosition_6());
		statement.setFloat(21, process.getHoldingPressure_1());
		statement.setFloat(22, process.getHoldingPressure_2());
		statement.setFloat(23, process.getHoldingPressure_3());
		statement.setFloat(24, process.getHoldingPressure_4());
		statement.setFloat(25, process.getHoldingPressure_5());
		statement.setFloat(26, process.getHoldingPressure_6());
		statement.setFloat(27, process.getHoldingTime_1());
		statement.setFloat(28, process.getHoldingTime_2());
		statement.setFloat(29, process.getHoldingTime_3());
		
		statement.setFloat(30, process.getHoldingTime_4());
		statement.setFloat(31, process.getHoldingTime_5());
		statement.setFloat(32, process.getHoldingTime_6());
		statement.setFloat(33, process.getMaxPackVel());
		statement.setFloat(34, process.getPosTran());
		statement.setFloat(35, process.getMaxInjPre());
		statement.setFloat(36, process.getMaxInjTime());
		statement.setFloat(37, process.getShotSize());
		statement.setFloat(38, process.getDecompressionDist());
		statement.setFloat(39, process.getDecompressionVel());
		
		statement.setFloat(40, process.getCoolTime());
		statement.setFloat(41, process.getBackPressure_1());
		statement.setFloat(42, process.getBackPressure_2());
		statement.setFloat(43, process.getBackPressure_3());
		statement.setFloat(44, process.getBackPressure_4());
		statement.setFloat(45, process.getBackPressure_5());
		statement.setFloat(46, process.getBackPressure_6());
		statement.setFloat(47, process.getScrewExtSpeed_1());
		statement.setFloat(48, process.getScrewExtSpeed_2());
		statement.setFloat(49, process.getScrewExtSpeed_3());
		
		statement.setFloat(50, process.getScrewExtSpeed_4());
		statement.setFloat(51, process.getScrewExtSpeed_5());
		statement.setFloat(52, process.getScrewExtSpeed_6());
		statement.setFloat(53, process.getExtProfilePos_1());
		statement.setFloat(54, process.getExtProfilePos_2());
		statement.setFloat(55, process.getExtProfilePos_3());
		statement.setFloat(56, process.getExtProfilePos_4());
		statement.setFloat(57, process.getExtProfilePos_5());
		statement.setFloat(58, process.getExtProfilePos_6());
		statement.setFloat(59, process.getNozzelTemperature());
		
		statement.setFloat(60, process.getBarrelTemperature_1());
		statement.setFloat(61, process.getBarrelTemperature_2());
		statement.setFloat(62, process.getBarrelTemperature_3());
		statement.setFloat(63, process.getBarrelTemperature_4());
		statement.setFloat(64, process.getThroatTemperature());
		statement.setFloat(65, process.getMouldClosingOpenLimitPos());
		statement.setFloat(66, process.getMouldClosingOpenLimitSpeed());
		statement.setFloat(67, process.getMouldClosedLimitPos());
		statement.setFloat(68, process.getMouldClosedLimitSpeed());
		statement.setFloat(69, process.getClsSlowPos());
		
		statement.setFloat(70, process.getClsSlowSpeed());
		statement.setFloat(71, process.getClsSPPos());
		statement.setFloat(72, process.getClsSPSpeed());
		statement.setFloat(73, process.getMouldOpenBreakAwaySpeed());
		statement.setFloat(74, process.getMouldOpenStepPos_1());
		statement.setFloat(75, process.getMouldOpenStepSpeed_1());
		statement.setFloat(76, process.getMouldOpenStepPos_2());
		statement.setFloat(77, process.getMouldOpenStepSpeed_2());
		statement.setFloat(78, process.getMouldOpenStepPos_3());
		statement.setFloat(79, process.getMouldOpenStepSpeed_3());
		
		statement.setFloat(80, process.getMouldOpenTime());
		statement.setString(81, process.getEjectStart());
		statement.setString(82, process.getEjectMode());
		statement.setFloat(83, process.getEjectPulse());
		statement.setFloat(84, process.getEjectDelay());
		statement.setFloat(85, process.getEjectorsFwdPos());
		statement.setFloat(86, process.getEjectorsFwdSpeed());
		statement.setFloat(87, process.getEjectorsFwdTime());
		statement.setFloat(88, process.getEjectorsStopPos());
		statement.setFloat(89, process.getEjectorsStopSpeed());
		
		statement.setFloat(90, process.getEjectorsStopTime());
		statement.setFloat(91, process.getEjectorsRevPos());
		statement.setFloat(92, process.getEjectorsRevSpeed());
		statement.setFloat(93, process.getEjectorsRevTime());
		statement.setFloat(94, process.getDme_1());
		statement.setFloat(95, process.getDme_2());
		statement.setFloat(96, process.getDme_3());
		statement.setFloat(97, process.getDme_4());
		statement.setFloat(98, process.getDme_5());
		statement.setFloat(99, process.getDme_6());
		
		statement.setFloat(100, process.getDme_7());
		statement.setFloat(101, process.getDme_8());
		statement.setFloat(102, process.getWaterTempFixedHalf());
		statement.setFloat(103, process.getWaterTempMovingHalf());
		statement.setString(104, process.getWaterTempNotes());
	
		System.out.println("Create:"+statement.toString());
		
		statement.executeUpdate();
		
		closeDBObjects(null, statement, connection);


	}

	@Override
	public void updateProcessSheet(MouldingProcess process) throws SQLException 
	{
		Connection connection=getConnection();
		PreparedStatement statement=connection.prepareStatement("UPDATE "+tablename+" set "
				+"partId =?,"
				+"machineSize =?,"
				+"machineNo =?,"
				+"material =?,"
				+"masterbatchNo =?,"
				+"dateOfIssue =?,"
				+"signOffBy =?,"
				+"processNotes =?,"
				+"injectionSpeed_1 =?,"
				+"injectionSpeed_2 =?,"
				+"injectionSpeed_3 =?,"
				+"injectionSpeed_4 =?,"
				+"injectionSpeed_5 =?,"
				+"injectionSpeed_6 =?,"
				+"injSpeedPosition_1 =?,"
				+"injSpeedPosition_2 =?,"
				+"injSpeedPosition_3 =?,"
				+"injSpeedPosition_4 =?,"
				+"injSpeedPosition_5 =?,"
				+"injSpeedPosition_6 =?,"	
				+"holdingPressure_1 =?,"
				+"holdingPressure_2 =?,"
				+"holdingPressure_3 =?,"
				+"holdingPressure_4 =?,"
				+"holdingPressure_5 =?,"
				+"holdingPressure_6 =?,"
				+"holdingTime_1 =?,"
				+"holdingTime_2 =?,"
				+"holdingTime_3 =?,"
				+"holdingTime_4 =?,"
				+"holdingTime_5 =?,"
				+"holdingTime_6 =?,"
				+"maxPackVel =?,"
				+"posTran =?,"
				+"maxInjPre =?,"
				+"maxInjTime =?,"
				+"shotSize =?,"
				+ "decompressionDist =?,"
				+"decompressionVel =?,"
				+"coolTime =?,"
				+"backPressure_1 =?,"
				+"backPressure_2 =?,"
				+"backPressure_3 =?,"
				+"backPressure_4 =?,"
				+"backPressure_5 =?,"
				+"backPressure_6 =?,"
				+"screwExtSpeed_1 =?,"
				+"screwExtSpeed_2 =?,"
				+"screwExtSpeed_3 =?,"
				+"screwExtSpeed_4 =?,"
				+"screwExtSpeed_5 =?,"
				+"screwExtSpeed_6 =?,"
				+"extProfilePos_1 =?,"
				+"extProfilePos_2 =?,"
				+"extProfilePos_3 =?,"
				+"extProfilePos_4 =?,"
				+"extProfilePos_5 =?,"
				+"extProfilePos_6 =?,"	
				+"nozzelTemperature =?,"
				+"barrelTemperature_1 =?,"
				+"barrelTemperature_2 =?,"
				+"barrelTemperature_3 =?,"
				+"barrelTemperature_4 =?,"
				+"throatTemperature =?,"
				+"mouldClosingOpenLimitPos =?,"
				+"mouldClosingOpenLimitSpeed =?,"
				+"mouldClosedLimitPos =?,"
				+"mouldClosedLimitSpeed =?,"
				+"clsSlowPos =?,"
				+"clsSlowSpeed =?,"
				+"clsSPPos =?,"
				+"clsSPSpeed =?,"
				+"mouldOpenBreakAwaySpeed =?,"
				+"mouldOpenStepPos_1 =?,"
				+"mouldOpenStepSpeed_1 =?,"
				+"mouldOpenStepPos_2 =?,"
				+"mouldOpenStepSpeed_2 =?,"
				+"mouldOpenStepPos_3 =?,"
				+"mouldOpenStepSpeed_3 =?,"
				+"mouldOpenTime =?,"
				+"ejectStart =?,"
				+"ejectMode =?,"
				+"ejectPulse =?,"
				+"ejectDelay =?,"
				+"ejectorsFwdPos =?,"
				+"ejectorsFwdSpeed =?,"
				+"ejectorsFwdTime =?,"
				+"ejectorsStopPos =?,"
				+"ejectorsStopSpeed =?,"
				+"ejectorsStopTime =?,"
				+"ejectorsRevPos =?,"
				+"ejectorsRevSpeed =?,"
				+"ejectorsRevTime =?,"
				+"dme_1 =?,"
				+"dme_2 =?,"
				+"dme_3 =?,"
				+"dme_4 =?,"
				+"dme_5 =?,"
				+"dme_6 =?,"
				+"dme_7 =?,"
				+"dme_8 =?,"
				+"waterTempFixedHalf =?,"
				+"waterTempMovingHalf =?,"
				+"waterTempNotes =? "
				+" WHERE id=?"
				);

		statement.setString(1, process.getPartId());
		statement.setInt(2, process.getMachineSize());
		statement.setString(3, process.getMachineNo());
		statement.setString(4, process.getMaterial());
		statement.setString(5, process.getMasterbatchNo());
		statement.setDate(6, process.getDateOfIssue());
		statement.setString(7, process.getSignOffBy());
		statement.setString(8, process.getProcessNotes());
		statement.setFloat(9, process.getInjectionSpeed_1());
		statement.setFloat(10, process.getInjectionSpeed_2());
		
		statement.setFloat(11, process.getInjectionSpeed_3());
		statement.setFloat(12, process.getInjectionSpeed_4());
		statement.setFloat(13, process.getInjectionSpeed_5());
		statement.setFloat(14, process.getInjectionSpeed_6());
		statement.setFloat(15, process.getInjSpeedPosition_1());
		statement.setFloat(16, process.getInjSpeedPosition_2());
		statement.setFloat(17, process.getInjSpeedPosition_3());
		statement.setFloat(18, process.getInjSpeedPosition_4());
		statement.setFloat(19, process.getInjSpeedPosition_5());
		
		statement.setFloat(20, process.getInjSpeedPosition_6());
		statement.setFloat(21, process.getHoldingPressure_1());
		statement.setFloat(22, process.getHoldingPressure_2());
		statement.setFloat(23, process.getHoldingPressure_3());
		statement.setFloat(24, process.getHoldingPressure_4());
		statement.setFloat(25, process.getHoldingPressure_5());
		statement.setFloat(26, process.getHoldingPressure_6());
		statement.setFloat(27, process.getHoldingTime_1());
		statement.setFloat(28, process.getHoldingTime_2());
		statement.setFloat(29, process.getHoldingTime_3());
		
		statement.setFloat(30, process.getHoldingTime_4());
		statement.setFloat(31, process.getHoldingTime_5());
		statement.setFloat(32, process.getHoldingTime_6());
		statement.setFloat(33, process.getMaxPackVel());
		statement.setFloat(34, process.getPosTran());
		statement.setFloat(35, process.getMaxInjPre());
		statement.setFloat(36, process.getMaxInjTime());
		statement.setFloat(37, process.getShotSize());
		statement.setFloat(38, process.getDecompressionDist());
		statement.setFloat(39, process.getDecompressionVel());
		
		statement.setFloat(40, process.getCoolTime());
		statement.setFloat(41, process.getBackPressure_1());
		statement.setFloat(42, process.getBackPressure_2());
		statement.setFloat(43, process.getBackPressure_3());
		statement.setFloat(44, process.getBackPressure_4());
		statement.setFloat(45, process.getBackPressure_5());
		statement.setFloat(46, process.getBackPressure_6());
		statement.setFloat(47, process.getScrewExtSpeed_1());
		statement.setFloat(48, process.getScrewExtSpeed_2());
		statement.setFloat(49, process.getScrewExtSpeed_3());
		
		statement.setFloat(50, process.getScrewExtSpeed_4());
		statement.setFloat(51, process.getScrewExtSpeed_5());
		statement.setFloat(52, process.getScrewExtSpeed_6());
		statement.setFloat(53, process.getExtProfilePos_1());
		statement.setFloat(54, process.getExtProfilePos_2());
		statement.setFloat(55, process.getExtProfilePos_3());
		statement.setFloat(56, process.getExtProfilePos_4());
		statement.setFloat(57, process.getExtProfilePos_5());
		statement.setFloat(58, process.getExtProfilePos_6());
		statement.setFloat(59, process.getNozzelTemperature());
		
		statement.setFloat(60, process.getBarrelTemperature_1());
		statement.setFloat(61, process.getBarrelTemperature_2());
		statement.setFloat(62, process.getBarrelTemperature_3());
		statement.setFloat(63, process.getBarrelTemperature_4());
		statement.setFloat(64, process.getThroatTemperature());
		statement.setFloat(65, process.getMouldClosingOpenLimitPos());
		statement.setFloat(66, process.getMouldClosingOpenLimitSpeed());
		statement.setFloat(67, process.getMouldClosedLimitPos());
		statement.setFloat(68, process.getMouldClosedLimitSpeed());
		statement.setFloat(69, process.getClsSlowPos());
		
		statement.setFloat(70, process.getClsSlowSpeed());
		statement.setFloat(71, process.getClsSPPos());
		statement.setFloat(72, process.getClsSPSpeed());
		statement.setFloat(73, process.getMouldOpenBreakAwaySpeed());
		statement.setFloat(74, process.getMouldOpenStepPos_1());
		statement.setFloat(75, process.getMouldOpenStepSpeed_1());
		statement.setFloat(76, process.getMouldOpenStepPos_2());
		statement.setFloat(77, process.getMouldOpenStepSpeed_2());
		statement.setFloat(78, process.getMouldOpenStepPos_3());
		statement.setFloat(79, process.getMouldOpenStepSpeed_3());
		
		statement.setFloat(80, process.getMouldOpenTime());
		statement.setString(81, process.getEjectStart());
		statement.setString(82, process.getEjectMode());
		statement.setFloat(83, process.getEjectPulse());
		statement.setFloat(84, process.getEjectDelay());
		statement.setFloat(85, process.getEjectorsFwdPos());
		statement.setFloat(86, process.getEjectorsFwdSpeed());
		statement.setFloat(87, process.getEjectorsFwdTime());
		statement.setFloat(88, process.getEjectorsStopPos());
		statement.setFloat(89, process.getEjectorsStopSpeed());
		
		statement.setFloat(90, process.getEjectorsStopTime());
		statement.setFloat(91, process.getEjectorsRevPos());
		statement.setFloat(92, process.getEjectorsRevSpeed());
		statement.setFloat(93, process.getEjectorsRevTime());
		statement.setFloat(94, process.getDme_1());
		statement.setFloat(95, process.getDme_2());
		statement.setFloat(96, process.getDme_3());
		statement.setFloat(97, process.getDme_4());
		statement.setFloat(98, process.getDme_5());
		statement.setFloat(99, process.getDme_6());
		
		statement.setFloat(100, process.getDme_7());
		statement.setFloat(101, process.getDme_8());
		statement.setFloat(102, process.getWaterTempFixedHalf());
		statement.setFloat(103, process.getWaterTempMovingHalf());
		statement.setString(104, process.getWaterTempNotes());
		
		
		//the ID of the entry in database
		
		statement.setString(105, String.valueOf(process.getId()));
		
		System.out.println("Update:"+statement.toString());
		
		statement.executeUpdate();
		closeDBObjects(null, statement, connection);
				
	
				

	}

	@Override
	public void deleteProcessSheet(MouldingProcess process) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public MouldingProcess getProcessSheet(String processId) throws SQLException {
		Connection connection=getConnection();
		PreparedStatement statement=connection.prepareStatement("select * from "+tablename+" where id=?;");
		statement.setString(1, processId);
		ResultSet rs=statement.executeQuery();
		MouldingProcess tempProcess=null;
		if(rs.next())
		{
			tempProcess=getMouldingProcess(rs);
		}
		closeDBObjects(rs, statement, connection);
		return tempProcess;
		
	}

	@Override
	public List<MouldingProcess> findProcessSheets(String col, String value)throws SQLException 
	{
		Connection connection=getConnection();
		PreparedStatement statement=connection.prepareStatement("select * from "+tablename+" where "+col+" REGEXP ?;");
		
		statement.setString(1, value);
		ResultSet rs=statement.executeQuery();
		List<MouldingProcess> list=new ArrayList<MouldingProcess>();
		
		while(rs.next())
		{
			MouldingProcess tempProcess=getMouldingProcess(rs);
			list.add(tempProcess);
		}
		closeDBObjects(rs, statement, connection);
		
		return list;
	}

	@Override
	public List<MouldingProcess> findProcessSheets() throws SQLException 
	{
		Connection connection=getConnection();
		Statement statement=connection.createStatement();
		ResultSet rs=statement.executeQuery("select * from "+tablename+";");
		List<MouldingProcess> list=new ArrayList<MouldingProcess>();
		
		while(rs.next())
		{
			MouldingProcess tempProcess=getMouldingProcess(rs);
			list.add(tempProcess);
		}
		
		closeDBObjects(rs, statement, connection);
		return list;
	}
	
	//Don't call next or close the ResultSet
	//TODO Replace with a better implementation than using Reflection
		private MouldingProcess getMouldingProcess(ResultSet rs) throws SQLException
		{
			MouldingProcess tempProcess=new MouldingProcess();
			for(String field:MouldingProcess.fields)
			{
				tempProcess.setField(field, rs.getObject(field));
			}
			return tempProcess;
		}

}
