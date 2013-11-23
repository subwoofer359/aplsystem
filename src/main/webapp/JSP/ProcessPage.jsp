<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Process Setup Sheet</title>
<link rel="stylesheet" type="text/css" href="../theme.css">
</head>
<H1 class="title">Process Setup Sheet</H1>
<body>
<FORM method="post" action="/todo">
<DIV class="info">
<TABLE>
<TR><TD>Part ID:</TD><TD><input type="text" name="partId" id="partId"/></TD></TR>
<TR><TD>Machine Size:</TD><TD><input type="text" name="machineSize" id="machineSize"/></TD></TR>
<TR><TD>Machine No:</TD><TD><input type="text" name="machineNo" id="machineNo"/></TD></TR>
<TR><TD>Material:</TD><TD><input type="text" name="material" id="material"/></TD></TR>
<TR><TD>MasterBatch:</TD><TD><input type="text" name="masterbatchNo" id="masterbatchNo"/></TD></TR>
<TR><TD>Date of Issue</TD><TD><input type="date" name="dateOfIssue" id="dateOfIssue"/></TD></TR>
<TR><TD>Sign Off by:</TD><TD><input type="text" name="signOffBy" id="signOffBy"/></TD></TR>
<TR><TD>Notes:</TD><TD><input type="text" name="processNotes" id="ProcessNotes"/></TD></TR>
</TABLE>

</DIV>
<DIV class="injection">
<TABLE>
	 <TR><TD>Speed:</TD><TD><input type="text" name="injectionSpeed_1" id="injectionSpeed_1"/></TD><TD>Position:</TD><TD><input type="text" name="injSpeedPosition_1" id="injSpeedPosition_1"/></TD></TR>
	 <TR><TD>Speed 2:</TD><TD><input type="text" name="injectionSpeed_2" id="injectionSpeed_2"/></TD><TD>Position 2:</TD><TD><input type="text" name="injSpeedPosition_2" id="injSpeedPosition_2"/></TD></TR>
	 <TR><TD>Speed 3:</TD><TD><input type="text" name="injectionSpeed_3" id="injectionSpeed_3"/></TD><TD>Position 3:</TD><TD><input type="text" name="injSpeedPosition_3" id="injSpeedPosition_3"/></TD></TR>
	 <TR><TD>Speed 4:</TD><TD><input type="text" name="injectionSpeed_4" id="injectionSpeed_4"/></TD><TD>Position 4:</TD><TD><input type="text" name="injSpeedPosition_4" id="injSpeedPosition_4"/></TD></TR>
	 <TR><TD>Speed 5:</TD><TD><input type="text" name="injectionSpeed_5" id="injectionSpeed_5"/></TD><TD>Position 5:</TD><TD><input type="text" name="injSpeedPosition_5" id="injSpeedPosition_5"/></TD></TR>
	 <TR><TD>Speed 6:</TD><TD><input type="text" name="injectionSpeed_6" id="injectionSpeed_6"/></TD><TD>Position 6:</TD><TD><input type="text" name="injSpeedPosition_6" id="injSpeedPosition_6"/></TD></TR>
</TABLE>
</DIV>

<DIV class="holding">
<TABLE>
	<TR><TD>Pressure:</TD><TD><input type="text" name="holdingPressure_1"/></TD><TD>Time:</TD><TD><input type="text" name="holdingTime_1"/></TD></TR>
	 <TR><TD>Pressure 2:</TD><TD><input type="text" name="holdingPressure_2"/></TD><TD>Time 2:</TD><TD><input type="text" name="holdingTime_2"/></TD></TR>
	 <TR><TD>Pressure 3:</TD><TD><input type="text" name="holdingPressure_3"/></TD><TD>Time 3:</TD><TD><input type="text" name="holdingTime_3"/></TD></TR>
	 <TR><TD>Pressure 4:</TD><TD><input type="text" name="holdingPressure_4"/></TD><TD>Time 4:</TD><TD><input type="text" name="holdingTime_4"/></TD></TR>
	 <TR><TD>Pressure 5:</TD><TD><input type="text" name="holdingPressure_5"/></TD><TD>Time 5:</TD><TD><input type="text" name="holdingTime_5"/></TD></TR>
	 <TR><TD>Pressure 6:</TD><TD><input type="text" name="holdingPressure_6"/></TD><TD>Time 6:</TD><TD><input type="text" name="holdingTime_6"/></TD></TR>
</TABLE>
</DIV>
<DIV class="InjExtOptions">
<TABLE><!-- 
private float maxPackVel;
	private float posTran;
	private float maxInjPre;
	private float maxInjTime;
	private float shotSize;
	private float DecompressionVel;
	private float coolTime;
	 -->
	 <TR><TD>Max Pack Velocity:</TD><TD><input type="text" name="maxPackVel"/></TD></TR>
	 <TR><TD>PosTran:</TD><TD><input type="text" name="posTran"/></TD></TR><!-- Changeover -->
	 <TR><TD>Max Injection Pre:</TD><TD><input type="text" name="maxInjPre" /></TD></TR>
	 <TR><TD>Max Injection Time:</TD><TD><input type="text" name="maxInjTime" /></TD></TR>
	 <TR><TD>Shot Size(CC):</TD><TD><input type="text" name="shotSize" /></TD></TR>
	 <TR><TD>Decompression Velocity:</TD><TD><input type="text" name="decompressionVel" /></TD></TR>
	 <TR><TD>Cooling Time:</TD><TD><input type="text" name="coolTime" /></TD></TR>
	 
</TABLE>
</DIV>
<DIV class="extrusion">
<TABLE>
	<TR><TH>Back Pressure(BAR)</TH><TH>Screw Speed(RPM)</TH><TH>Position(MM)</TH></TR>
	<TR><TD><input type="text" name="backPressure_1"/></TD><TD><input type="text" name="screwExtSpeed_1"/></TD><TD><input type="text" name="extProfilePos_1"/></TD></TR>
	<TR><TD><input type="text" name="backPressure_2"/></TD><TD><input type="text" name="screwExtSpeed_2"/></TD><TD><input type="text" name="extProfilePos_1"/></TD></TR>
	<TR><TD><input type="text" name="backPressure_3"/></TD><TD><input type="text" name="screwExtSpeed_3"/></TD><TD><input type="text" name="extProfilePos_1"/></TD></TR>
	<TR><TD><input type="text" name="backPressure_4"/></TD><TD><input type="text" name="screwExtSpeed_4"/></TD><TD><input type="text" name="extProfilePos_1"/></TD></TR>
	<TR><TD><input type="text" name="backPressure_5"/></TD><TD><input type="text" name="screwExtSpeed_5"/></TD><TD><input type="text" name="extProfilePos_1"/></TD></TR>
	<TR><TD><input type="text" name="backPressure_6"/></TD><TD><input type="text" name="screwExtSpeed_6"/></TD><TD><input type="text" name="extProfilePos_1"/></TD></TR>
	
</TABLE>
</DIV>

<DIV class="barrelTempetures">
<TABLE>
<TR><TH>Nozzel</TH><TH>T1</TH><TH>T2</TH><TH>T3</TH><TH>T4</TH><TH>Throat</TH></TR>
<TR>
<TD><input type="text" name="nozzelTemperature"/></TD>
<TD><input type="text" name="barrelTemperature_1"/></TD>
<TD><input type="text" name="barrelTemperature_2"/></TD>
<TD><input type="text" name="barrelTemperature_3"/></TD>
<TD><input type="text" name="barrelTemperature_4"/></TD>
<TD><input type="text" name="throatTemperature"/></TD>
</TR>
</TABLE>

</DIV>
<DIV class="mouldClosing">
<TABLE>
<TR><TH></TH><TH>Position(MM)</TH><TH>Speed(MM/S)</TH></TR>
<TR><TD>Open Limit:</TD><TD><input type="text" name="mouldClosingOpenLimitPos"/></TD><TD><input type="text" name="mouldClosingOpenLimitSpeed"/></TD></TR>
<TR><TD>Closed Limit:</TD><TD><input type="text" name="mouldClosedLimitPos"/></TD><TD><input type="text" name="mouldClosedLimitSpeed"/></TD></TR>
<TR><TD>CLS Slow</TD><TD><input type="text" name="clsSlowPos"/></TD><TD><input type="text" name="clsSlowSpeed"/></TD></TR>
<TR><TD>Close SP</TD><TD><input type="text" name="clsSPPos"/></TD><TD><input type="text" name="clsSPSpeed"/></TD></TR>
</TABLE>
</DIV>

<DIV class="mouldOpening">
<TABLE>
<TR><TH></TH><TH>Position(MM)</TH><TH>Speed(MM/S)</TH></TR>
<TR><TD>Breakaway:</TD><TD></TD><TD><input type="text" name="mouldOpenBreakAwaySpeed"/></TD></TR>
<TR><TD>Open 1 Step:</TD><TD><input type="text" name="mouldOpenStepPos_1"/></TD><TD><input type="text" name="mouldOpenStepSpeed_1"/></TD></TR>
<TR><TD>Open 2 Step:</TD><TD><input type="text" name="mouldOpenStepPos_2"/></TD><TD><input type="text" name="mouldOpenStepSpeed_2"/></TD></TR>
<TR><TD>Open 3 Step:</TD><TD><input type="text" name="mouldOpenStepPos_3"/></TD><TD><input type="text" name="mouldOpenStepSpeed_3"/></TD></TR>
<TR></TR>
<TR><TD>Mould Open Time</TD><TD><input type="text" name="mouldOpenTime"/></TD><TD></TD></TR>
<TR><TD>Eject Start</TD><TD><input type="text" name="ejectStart"/></TD><TD></TD></TR>
</TABLE>
</DIV>

<DIV class="ejectors">
<TABLE>
<!-- 	private String ejectMode;
	private float ejectPulse;
	private float ejectDelay;
	
	private float ejectorsFwdPos;
	private float ejectorsFwdSpeed;
	private float ejectorsFwdTime;
	
	private float ejectorsStopPos;
	private float ejectorsStopSpeed;
	private float ejectorsStopTime;
	
	private float ejectorsRevPos;
	private float ejectorsRevSpeed;
	private float ejectorsRevTime; -->
	
	<TR><TD>Ejector Mode</TD><TD><input type="text" name="ejectMode"/></TD><TD></TD><TD></TD></TR>
	<TR><TD>Ejector Pulse</TD><TD><input type="text" name="ejectPulse"/></TD><TD>count</TD><TD></TD></TR>
	<TR><TD>Ejector Delay</TD><TD><input type="text" name="ejectDelay"/></TD><TD>secs</TD><TD></TD></TR>
	<TR></TR>
	<TR><TD>FWD</TD><TD><input type="text" name="ejectorsFwdPos"/></TD><TD><input type="text" name="eejectorsFwdSpeed"/></TD><TD><input type="text" name="ejectorsFwdTime"/></TD></TR>
	<TR><TD>STOP</TD><TD><input type="text" name="ejectorsStopPos"/></TD><TD><input type="text" name="eejectorsStopSpeed"/></TD><TD><input type="text" name="ejectorsStopTime"/></TD></TR>
	<TR><TD>REV</TD><TD><input type="text" name="ejectorsRevPos"/></TD><TD><input type="text" name="eejectorsRevSpeed"/></TD><TD><input type="text" name="ejectorsRevTime"/></TD></TR>
</TABLE>
</DIV>

<DIV class="dme">
<TABLE>
<TR><TH>DMEs</TH></TR>
<TR><TD>1</TD><TD>2</TD><TD>3</TD><TD>4</TD><TD>5</TD><TD>6</TD><TD>7</TD><TD>8</TD></TR>
<TR>
	<TD><input type="text" name="dme_1"/></TD>
	<TD><input type="text" name="dme_2"/></TD>
	<TD><input type="text" name="dme_3"/></TD>
	<TD><input type="text" name="dme_4"/></TD>
	<TD><input type="text" name="dme_5"/></TD>
	<TD><input type="text" name="dme_6"/></TD>
	<TD><input type="text" name="dme_7"/></TD>
	<TD><input type="text" name="dme_8"/></TD>
</TR>
</TABLE>
<TABLE>
	<TR><TD>Fixed Half Water Temp </TD><TD><input type="text" name="waterTempFixedHalf"/></TD></TR>
	<TR><TD>Moving Half Water Temp </TD><TD><input type="text" name="waterTempMovingHalf"/></TD></TR>
	<TR><TD>Notes</TD><TD><input type="text" name="waterTempNotes"/></TD></TR>
</TABLE>
</DIV>
</FORM>
</body>
</html>