<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="ErrorPage.jsp" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ACME Plastics: Process Setup Sheet</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/General.css">


<style type="text/css">
select,textarea,fieldset legend
{
	font-size:xx-large;
}


.float input,.info input
{
	width:80%;
	font-size:xx-large;
}
.float td,.info td
{
	font-size:xx-large;
	
}
table
{
	
}

.info,
.injection,
.holding,
.injExtOptions,
.extrusion,
.barrelTemperatures,
.mouldOpening,
.mouldClosing,
.dme,
.ejectors
{
	position:fixed;
	top:200px;
	width:98%;
	height:100%;
	z-index:-1;
	
}
/* To make all divs invisible */
.injection,
.holding,
.injExtOptions,
.extrusion,
.barrelTemperatures,
.mouldOpening,
.mouldClosing,
.dme,
.ejectors
{
	visibility:hidden;
}

/* To be removed in the future*/
.buttons
{
	position:fixed;
	right:4px;
	bottom:4px;
}
.buttons input
{
	font-size:xx-large;
}
/* Tabs at the top of window */
.menu
{
	position:fixed; 
	top:80px; 
}

.menu span
{
	height:40px;
	line-height:40px;	/* #must be the same as height */
	border-style:solid;
	border-color:black;
	padding-right:4px;
	display:inline-block;
}

</style>

</head>

<body>

<!-- Javascript function to work tabbing -->
<SCRIPT>
var tabs=["info",
          "injection",
          "holding",
          "injExtOptions",
          "extrusion",
          "barrelTemperatures",
          "mouldOpening",
          "mouldClosing",
          "dme",
          "ejectors"
          ];
          
function hideInfo(element)
{
	console.log(element);
	var selectedTab=element.id;
	for(var i=0;i<tabs.length;i++)
	{
		if(tabs[i]!=selectedTab)
		{
			var elements=document.getElementsByClassName(tabs[i]);
			elements[0].style.visibility="hidden";
			console.log("Element:"+tabs[i]+" is hidden");	
		}
	}
	var elements=document.getElementsByClassName(selectedTab);
	elements[0].style.visibility="visible";
	console.log("Element:"+selectedTab+" is visible");
	
}
</SCRIPT>
<div class="title">
<H1>Process Setup Sheet</H1>
</div>
<%@ include file="NavigationDiv.jspf" %>

<!-- Display tabs -->
<SPAN class="menu">
<SPAN  id="info"			onclick='hideInfo(this)'>Information</SPAN>
<SPAN  id="injection"		onclick='hideInfo(this)'>Injection</SPAN>
<SPAN  id="holding"			onclick='hideInfo(this)'>Holding</SPAN>
<SPAN  id="injExtOptions"	onclick='hideInfo(this)'>Injection Options</SPAN>
<SPAN  id="extrusion"		onclick='hideInfo(this)'>Extrusion</SPAN>
<SPAN  id="barrelTemperatures"  onclick='hideInfo(this)'>Barrel Temperatures</SPAN>
<SPAN  id="mouldOpening"  	onclick='hideInfo(this)'>Moulding Opening</SPAN>
<SPAN  id="mouldClosing"	onclick='hideInfo(this)'>Moulding Closing</SPAN>
<SPAN  id="dme"  			onclick='hideInfo(this)'>DME</SPAN>
<SPAN  id="ejectors"  		onclick='hideInfo(this)'>Ejectors</SPAN>
</SPAN>

<%-- Display errors if there any --%>
<c:if test="${errors ne null }">
<SCRIPT>alert("${errors}");</SCRIPT>
</c:if>
<!-- Send info to JSP to be put into a bean todo integrate code into this page -->
<FORM method="post" action="${pageContext.request.contextPath}/Processing/ProcessSheetBean"> 
<%-- To be used in edit mode to store the id of the object being edited --%>
<input type="hidden" name='id' <c:if test='${form ne null}'>value='${form.id}'</c:if>/>

<DIV class="info">
<fieldset>
<legend>Basic Information</legend>
<TABLE>

<TR><TD>Part ID:</TD><TD><input type="text" name="partId" value='${form.partId}' autofocus="autofocus"/></TD></TR>
<TR><TD>Machine Size:</TD><TD><input type="text" name="machineSize"  value='${form.machineSize}'/></TD></TR>
<TR><TD>Machine No:</TD><TD><input type="text" name="machineNo" value='${form.machineNo}'/></TD></TR>
<TR><TD>Material:</TD><TD>
 
<select name="material">
	<c:forEach items='${materials}' var='material'>
		<option value='${material.key}' <c:if test="${material.key eq form.material}"><c:out value="selected='selected'"></c:out></c:if>>
				${material.value}
		</option>
	</c:forEach>
</select>

</TD></TR>
<TR><TD>MasterBatch:</TD><TD><input type="text" name="masterbatchNo" value='${form.masterbatchNo}'/></TD></TR>
<TR><TD>Date of Issue</TD><TD><input type="date" name="dateOfIssue" value='${form.dateOfIssue}'/></TD></TR>
<TR><TD>Sign Off by:</TD><TD><input type="text" name="signOffBy"  value='${form.signOffBy}'/></TD></TR>
<TR><TD>Notes:</TD><TD><input type="text" name="processNotes"  value='${form.processNotes}'/></TD></TR>
</TABLE>
</fieldset>
</DIV>
<DIV class="injection">
<fieldset>
<legend>Injection</legend>
<TABLE class="float">
	 <TR><TD>Speed:</TD><TD><input  type="text" name="injectionSpeed_1" value='${form.injectionSpeed_1}'/></TD><TD>Position:</TD><TD><input type="text" name="injSpeedPosition_1" id="injSpeedPosition_1" value='${form.injSpeedPosition_1}'/></TD></TR>
	 <TR><TD>Speed 2:</TD><TD><input type="text" name="injectionSpeed_2" value='${form.injectionSpeed_2}'/></TD><TD>Position 2:</TD><TD><input type="text" name="injSpeedPosition_2" id="injSpeedPosition_2" value='${form.injSpeedPosition_2}'/></TD></TR>
	 <TR><TD>Speed 3:</TD><TD><input type="text" name="injectionSpeed_3" value='${form.injectionSpeed_3}'/></TD><TD>Position 3:</TD><TD><input type="text" name="injSpeedPosition_3" id="injSpeedPosition_3" value='${form.injSpeedPosition_3}'/></TD></TR>
	 <TR><TD>Speed 4:</TD><TD><input type="text" name="injectionSpeed_4" value='${form.injectionSpeed_4}'/></TD><TD>Position 4:</TD><TD><input type="text" name="injSpeedPosition_4" id="injSpeedPosition_4" value='${form.injSpeedPosition_4}'/></TD></TR>
	 <TR><TD>Speed 5:</TD><TD><input type="text" name="injectionSpeed_5" value='${form.injectionSpeed_5}'/></TD><TD>Position 5:</TD><TD><input type="text" name="injSpeedPosition_5" id="injSpeedPosition_5" value='${form.injSpeedPosition_5}'/></TD></TR>
	 <TR><TD>Speed 6:</TD><TD><input type="text" name="injectionSpeed_6" value='${form.injectionSpeed_6}'/></TD><TD>Position 6:</TD><TD><input type="text" name="injSpeedPosition_6" id="injSpeedPosition_6" value='${form.injSpeedPosition_6}'/></TD></TR>
</TABLE>
</fieldset>
</DIV>

<DIV class="holding">
<fieldset>
<legend>Holding Phase</legend>
<TABLE class="float">
	<TR><TD>Pressure:</TD><TD><input type="text" name="holdingPressure_1" value='${form.holdingPressure_1}'/></TD><TD>Time:</TD><TD><input type="text" name="holdingTime_1" value='${form.holdingTime_1}'/></TD></TR>
	 <TR><TD>Pressure 2:</TD><TD><input type="text" name="holdingPressure_2" value='${form.holdingPressure_2}'/></TD><TD>Time 2:</TD><TD><input type="text" name="holdingTime_2" value='${form.holdingTime_2}'/></TD></TR>
	 <TR><TD>Pressure 3:</TD><TD><input type="text" name="holdingPressure_3" value='${form.holdingPressure_3}'/></TD><TD>Time 3:</TD><TD><input type="text" name="holdingTime_3" value='${form.holdingTime_3}'/></TD></TR>
	 <TR><TD>Pressure 4:</TD><TD><input type="text" name="holdingPressure_4" value='${form.holdingPressure_4}'/></TD><TD>Time 4:</TD><TD><input type="text" name="holdingTime_4" value='${form.holdingTime_4}'/></TD></TR>
	 <TR><TD>Pressure 5:</TD><TD><input type="text" name="holdingPressure_5" value='${form.holdingPressure_5}'/></TD><TD>Time 5:</TD><TD><input type="text" name="holdingTime_5" value='${form.holdingTime_5}'/></TD></TR>
	 <TR><TD>Pressure 6:</TD><TD><input type="text" name="holdingPressure_6" value='${form.holdingPressure_6}'/></TD><TD>Time 6:</TD><TD><input type="text" name="holdingTime_6" value='${form.holdingTime_6}'/></TD></TR>
</TABLE>
</fieldset>
</DIV>
<DIV class="injExtOptions">
<fieldset>
<legend>Injection Options</legend>
<TABLE class="float">
	 <TR><TD>Max Pack Velocity:</TD><TD><input type="text" name="maxPackVel" value='${form.maxPackVel}'/></TD></TR>
	 <TR><TD>PosTran:</TD><TD><input type="text" name="posTran" value='${form.posTran}'/></TD></TR><!-- Changeover -->
	 <TR><TD>Max Injection Pre:</TD><TD><input type="text" name="maxInjPre" value='${form.maxInjPre}'/></TD></TR>
	 <TR><TD>Max Injection Time:</TD><TD><input type="text" name="maxInjTime" value='${form.maxInjTime}' /></TD></TR>
	 <TR><TD>Shot Size(CC):</TD><TD><input type="text" name="shotSize" value='${form.shotSize}'/></TD></TR>
	 <TR><TD>Decompression Distance:</TD><TD><input type="text" name="decompressionDist" value='${form.decompressionDist}'/></TD></TR>
	 <TR><TD>Decompression Velocity:</TD><TD><input type="text" name="decompressionVel" value='${form.decompressionVel}'/></TD></TR>
	 <TR><TD>Cooling Time:</TD><TD><input type="text" name="coolTime" value='${form.coolTime}'/></TD></TR>
	 
</TABLE>
</fieldset>
</DIV>
<DIV class="extrusion">
<fieldset>
<legend>Extrusion</legend>
<TABLE class="float">
	<TR><TH>Back Pressure(BAR)</TH><TH>Screw Speed(RPM)</TH><TH>Position(MM)</TH></TR>
	<TR><TD><input type="text" name="backPressure_1" value='${form.backPressure_1}'/></TD><TD><input type="text" name="screwExtSpeed_1" value='${form.screwExtSpeed_1}'/></TD><TD><input type="text" name="extProfilePos_1" value='${form.extProfilePos_1}'/></TD></TR>
	<TR><TD><input type="text" name="backPressure_2" value='${form.backPressure_2}'/></TD><TD><input type="text" name="screwExtSpeed_2" value='${form.screwExtSpeed_2}'/></TD><TD><input type="text" name="extProfilePos_2" value='${form.extProfilePos_2}'/></TD></TR>
	<TR><TD><input type="text" name="backPressure_3" value='${form.backPressure_3}'/></TD><TD><input type="text" name="screwExtSpeed_3" value='${form.screwExtSpeed_3}'/></TD><TD><input type="text" name="extProfilePos_3" value='${form.extProfilePos_3}'/></TD></TR>
	<TR><TD><input type="text" name="backPressure_4" value='${form.backPressure_4}'/></TD><TD><input type="text" name="screwExtSpeed_4" value='${form.screwExtSpeed_4}'/></TD><TD><input type="text" name="extProfilePos_4" value='${form.extProfilePos_4}'/></TD></TR>
	<TR><TD><input type="text" name="backPressure_5" value='${form.backPressure_5}'/></TD><TD><input type="text" name="screwExtSpeed_5" value='${form.screwExtSpeed_5}'/></TD><TD><input type="text" name="extProfilePos_5" value='${form.extProfilePos_5}'/></TD></TR>
	<TR><TD><input type="text" name="backPressure_6" value='${form.backPressure_6}'/></TD><TD><input type="text" name="screwExtSpeed_6" value='${form.screwExtSpeed_6}'/></TD><TD><input type="text" name="extProfilePos_6" value='${form.extProfilePos_6}'/></TD></TR>
	
</TABLE>
</fieldset>
</DIV>

<DIV class="barrelTemperatures">
<fieldset>
<legend>Barrel Temperatures</legend>
<TABLE class="float">
<TR><TH>Nozzel</TH><TH>T1</TH><TH>T2</TH><TH>T3</TH><TH>T4</TH><TH>Throat</TH></TR>
<TR>
<TD><input type="text" name="nozzelTemperature" value='${form.nozzelTemperature}'/></TD>
<TD><input type="text" name="barrelTemperature_1" value='${form.barrelTemperature_1}'/></TD>
<TD><input type="text" name="barrelTemperature_2" value='${form.barrelTemperature_2}'/></TD>
<TD><input type="text" name="barrelTemperature_3" value='${form.barrelTemperature_3}'/></TD>
<TD><input type="text" name="barrelTemperature_4" value='${form.barrelTemperature_4}'/></TD>
<TD><input type="text" name="throatTemperature"   value='${form.throatTemperature}'/></TD>
</TR>
</TABLE>
</fieldset>
</DIV>
<DIV class="mouldClosing">
<fieldset>
<legend>Mould Closing</legend>
<TABLE class="float">
<TR><TH></TH><TH>Position(MM)</TH><TH>Speed(MM/S)</TH></TR>
<TR><TD>Open Limit:</TD>
<TD><input type="text" name="mouldClosingOpenLimitPos" value='${form.mouldClosingOpenLimitPos}'/></TD><TD><input type="text" name="mouldClosingOpenLimitSpeed" value='${form.mouldClosingOpenLimitSpeed}'/></TD></TR>
<TR><TD>Closed Limit:</TD>
<TD><input type="text" name="mouldClosedLimitPos" value='${form.mouldClosedLimitPos}'/></TD><TD><input type="text" name="mouldClosedLimitSpeed" value='${form.mouldClosedLimitSpeed}'/></TD></TR>
<TR><TD>CLS Slow</TD><TD>
<input type="text" name="clsSlowPos" value='${form.clsSlowPos}'/></TD><TD><input type="text" name="clsSlowSpeed" value='${form.clsSlowSpeed}'/></TD></TR>
<TR><TD>Close SP</TD><TD>
<input type="text" name="clsSPPos" value='${form.clsSPPos}'/></TD><TD><input type="text" name="clsSPSpeed" value='${form.clsSPSpeed}'/></TD></TR>
</TABLE> 
</fieldset>
</DIV>


<DIV class="mouldOpening">
<fieldset>
<legend>Mould Opening</legend>
<TABLE class="float">
<TR><TH></TH><TH>Position(MM)</TH><TH>Speed(MM/S)</TH></TR>
<TR><TD>Breakaway:</TD><TD></TD><TD><input type="text" name="mouldOpenBreakAwaySpeed" value='${form.mouldOpenBreakAwaySpeed}'/></TD></TR>
<TR><TD>Open 1 Step:</TD><TD><input type="text" name="mouldOpenStepPos_1" value='${form.mouldOpenStepPos_1}'/></TD><TD><input type="text" name="mouldOpenStepSpeed_1" value='${form.mouldOpenStepSpeed_1}'/></TD></TR>
<TR><TD>Open 2 Step:</TD><TD><input type="text" name="mouldOpenStepPos_2" value='${form.mouldOpenStepPos_2}'/></TD><TD><input type="text" name="mouldOpenStepSpeed_2" value='${form.mouldOpenStepSpeed_2}'/></TD></TR>
<TR><TD>Open 3 Step:</TD><TD><input type="text" name="mouldOpenStepPos_3" value='${form.mouldOpenStepPos_3}'/></TD><TD><input type="text" name="mouldOpenStepSpeed_3" value='${form.mouldOpenStepSpeed_3}'/></TD></TR>
<TR></TR>
<TR><TD>Mould Open Time</TD><TD><input type="text" name="mouldOpenTime" value='${form.mouldOpenTime}'/></TD><TD></TD></TR>
<TR><TD>Eject Start</TD><TD><input type="text" name="ejectStart" value='${form.ejectStart}'/></TD><TD></TD></TR>
</TABLE>
</fieldset>
</DIV>

<DIV class="ejectors">
<fieldset>
<legend>Ejectors</legend>
<TABLE class="float">
	<TR><TD>Ejector Mode</TD><TD><input type="text" name="ejectMode" value='${form.ejectMode}'/></TD><TD></TD><TD></TD></TR>
	<TR><TD>Ejector Pulse</TD><TD><input type="text" name="ejectPulse" value='${form.ejectPulse}'/></TD><TD>count</TD><TD></TD></TR>
	<TR><TD>Ejector Delay</TD><TD><input type="text" name="ejectDelay" value='${form.ejectDelay}'/></TD><TD>secs</TD><TD></TD></TR>
	<TR></TR>
	<TR><TD>FWD</TD><TD><input type="text" name="ejectorsFwdPos" value='${form.ejectorsFwdPos}'/></TD><TD><input type="text" name="ejectorsFwdSpeed" value='${form.ejectorsFwdSpeed}'/></TD><TD><input type="text" name="ejectorsFwdTime" value='${form.ejectorsFwdTime}'/></TD></TR>
	<TR><TD>STOP</TD><TD><input type="text" name="ejectorsStopPos" value='${form.ejectorsStopPos}'/></TD><TD><input type="text" name="ejectorsStopSpeed" value='${form.ejectorsStopSpeed}'/></TD><TD><input type="text" name="ejectorsStopTime" value='${form.ejectorsStopTime}'/></TD></TR>
	<TR><TD>REV</TD><TD><input type="text" name="ejectorsRevPos" value='${form.ejectorsRevPos}'/></TD><TD><input type="text" name="ejectorsRevSpeed" value='${form.ejectorsRevSpeed}'/></TD><TD><input type="text" name="ejectorsRevTime" value='${form.ejectorsRevTime}'/></TD></TR>
</TABLE>
</fieldset>
</DIV>

<DIV class="dme">

<fieldset>
<legend>DME and Water temperatures</legend>
<TABLE class="float">
<TR><TH>DMEs</TH></TR>
<TR><TD>1</TD><TD>2</TD><TD>3</TD><TD>4</TD><TD>5</TD><TD>6</TD><TD>7</TD><TD>8</TD></TR>
<TR>
	<TD><input type="text" name="dme_1" value='${form.dme_1}'/></TD>
	<TD><input type="text" name="dme_2" value='${form.dme_2}'/></TD>
	<TD><input type="text" name="dme_3" value='${form.dme_3}'/></TD>
	<TD><input type="text" name="dme_4" value='${form.dme_4}'/></TD>
	<TD><input type="text" name="dme_5" value='${form.dme_5}'/></TD>
	<TD><input type="text" name="dme_6" value='${form.dme_6}'/></TD>
	<TD><input type="text" name="dme_7" value='${form.dme_7}'/></TD>
	<TD><input type="text" name="dme_8" value='${form.dme_8}'/></TD>
</TR>
</TABLE>
<TABLE class="float">
	<TR><TD>Fixed Half Water Temp </TD><TD><input type="text" name="waterTempFixedHalf" value='${form.waterTempFixedHalf}'/></TD></TR>
	<TR><TD>Moving Half Water Temp </TD><TD><input type="text" name="waterTempMovingHalf" value='${form.waterTempMovingHalf}'/></TD></TR>
	<TR><TD>Notes</TD><TD><textarea name="waterTempNotes">${form.waterTempNotes}</textarea></TD></TR>
</TABLE>
</fieldset>
</DIV>
<SPAN class="buttons">
<%-- To tell the servlet which mode the page is submitting in --%>
<c:if test="${mode eq null }">
	<input type='submit'  name="mode" value='Enter'/>
</c:if>
<c:if test="${mode eq 'edit' }">
	<input type='submit'  name="mode" value='edit'/>
</c:if>
</SPAN>
</FORM>

</body>
</html>