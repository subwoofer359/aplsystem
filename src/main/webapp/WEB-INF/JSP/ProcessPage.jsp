<%-- 
	@author Adrian Mclaughlin
 	@version 1.1
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://adrianmclaughlin.ie/myfunctions" prefix="myfunc" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags/BottomMenuBar" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/BootStrapHeader.jsp" %>
<title>ACME Plastics: Process Setup Sheet</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/General.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/SearchPage.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/EntryPage.css">
<style>
</style>
</head>

<body>

<!-- Javascript function to work tabbing -->
<script src="${pageContext.request.contextPath}/js/General.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/InputFocus.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/SelectDiv.js"></script>
<script>


// List of the HTML div to display

var tabs=["info",
          "injection",
          "holding",
          "injExtOptions",
          "extrusion",
          "barrelTemperatures",
          "mouldClosing",
          "mouldOpening",
          "ejectors",
          "dme"
         ];
//When the last input element in the div is entered the script will display the next div
window.addEventListener("load",function(){addChangePageListenerInput(tabs);},true);

</script>

<div class="page-title"><H1>Process Setup Sheet</H1></div>

<!-- The Alert box for error and info messages -->
<div id="alert" class="alert-panel panel panel-danger" role="alert" onclick="hide(this)">
	<div class="panel-heading">
		<h3 class="panel-title">Error</h3>
	</div>
	<div class="panel-body"></div>
</div>

<div class="container entry">
<div class="row">
<!-- Selection box -->
<select id="pageSelect" onchange='displayDiv(this,tabs)'>
<option value="info">Information</option>
<option value="injection">Injection</option>
<option value="holding">Holding</option>
<option value="injExtOptions">Inejection/Extrusion Options</option>
<option value="extrusion">Extrusion</option>
<option value="barrelTemperatures">Barrel Temperatures</option>
<option value="mouldClosing">Mould Closing</option>
<option value="mouldOpening">Mould Opening</option>
<option value="ejectors">Ejectors</option>
<option value="dme">DME</option>
</select>
</div>
<div class="row">
<!-- Send info to JSP to be put into a bean todo integrate code into this page -->
<FORM class="form-horizontal" method="post" action="${pageContext.request.contextPath}/app/Processing/ProcessSheetBean"> 
<%-- To be used in edit mode to store the id of the object being edited --%>
<input type="hidden" name='id' <c:if test='${form ne null}'>value='${form.id}'</c:if>/>

<DIV id="info">
<fieldset>
<legend>Basic Information</legend>
<div class="form-group">
	<label class="control-label col-xs-2" for="partId">Part ID</label>
	<div class="col-xs-10">
		<input id="partId" class="form-control" type="text" name="partId" value="<c:out value='${form.partId}' />" autofocus="autofocus" placeholder="Part Identification"/>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-2" for="machineSize">Machine Size</label>
	<div class="col-xs-10">
		<input id="machineSize" class="form-control" type="text" name="machineSize"  placeholder="Machine size (Tons)" value="<c:out value='${form.machineSize}' />"/>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-2" for="machineNo">Machine No.</label>
	<div class="col-xs-10">
		<input id="machineNo" class="form-control" type="text" name="machineNo" placeholder="Machine Number" value="<c:out value='${form.machineNo}' />"/>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-2" for="material">Material:</label>
	<div class="col-xs-10">
		<select id="material" name="material">
			<c:forEach items='${materials}' var='material'>
				<option value='${material.key}' <c:if test="${material.key eq form.material}"><c:out value="selected='selected'"></c:out></c:if>>
					<c:out value="${myfunc:toString(material.value)}"/>
				</option>
			</c:forEach>
		</select>
	</div>
</div>


<div class="form-group">
	<label class="control-label col-xs-2" for="masterbatchNo">MasterBatch</label>
	<div class="col-xs-10">
		<input id="masterbatchNo" class="form-control" type="text" name="masterbatchNo" placeholder="Masterbatch" value="<c:out value='${form.masterbatchNo}' />"/>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-2" for="dateOfIssue">Date of Issue</label>
	<div class="col-xs-10">
		<input id="dateOfIssue" class="form-control" type="date" name="dateOfIssue" placeholder="Date of Issue" value="<c:out value='${form.dateOfIssue}' />"/>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-2" for="signOffBy">Signed Off by</label>
	<div class="col-xs-10">
		<input id="signOffBy" class="form-control" type="text" name="signOffBy" placeholder="Signed Off By" value="<c:out value='${form.signOffBy}' />"/>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-2" for="processNotes">Notes</label>
	<div class="col-xs-10">
		<input id="processNotes" class="end form-control" type="text" name="processNotes" placeholder="Notes" value="<c:out value='${form.processNotes}'/>" />
	</div>
</div>

</fieldset>
</DIV>
<DIV id="injection">
<fieldset>
<legend>Injection</legend>
<TABLE class="float">
	 <TR><TD>Speed:</TD><TD><input  type="text" name="injectionSpeed_1" value="<c:out value='${form.injectionSpeed_1}' />"/></TD><TD>Position:</TD><TD><input type="text" name="injSpeedPosition_1" id="injSpeedPosition_1" value="<c:out value='${form.injSpeedPosition_1}' />"/></TD></TR>
	 <TR><TD>Speed 2:</TD><TD><input type="text" name="injectionSpeed_2" value="<c:out value='${form.injectionSpeed_2}' />"/></TD><TD>Position 2:</TD><TD><input type="text" name="injSpeedPosition_2" id="injSpeedPosition_2" value="<c:out value='${form.injSpeedPosition_2}' />"/></TD></TR>
	 <TR><TD>Speed 3:</TD><TD><input type="text" name="injectionSpeed_3" value="<c:out value='${form.injectionSpeed_3}' />"/></TD><TD>Position 3:</TD><TD><input type="text" name="injSpeedPosition_3" id="injSpeedPosition_3" value="<c:out value='${form.injSpeedPosition_3}' />"/></TD></TR>
	 <TR><TD>Speed 4:</TD><TD><input type="text" name="injectionSpeed_4" value="<c:out value='${form.injectionSpeed_4}' />"/></TD><TD>Position 4:</TD><TD><input type="text" name="injSpeedPosition_4" id="injSpeedPosition_4" value="<c:out value='${form.injSpeedPosition_4}' />"/></TD></TR>
	 <TR><TD>Speed 5:</TD><TD><input type="text" name="injectionSpeed_5" value="<c:out value='${form.injectionSpeed_5}' />"/></TD><TD>Position 5:</TD><TD><input type="text" name="injSpeedPosition_5" id="injSpeedPosition_5" value="<c:out value='${form.injSpeedPosition_5}' />"/></TD></TR>
	 <TR><TD>Speed 6:</TD><TD><input type="text" name="injectionSpeed_6" value="<c:out value='${form.injectionSpeed_6}' />"/></TD><TD>Position 6:</TD><TD><input class="end" type="text" name="injSpeedPosition_6" id="injSpeedPosition_6" value="<c:out value='${form.injSpeedPosition_6}' />"/></TD></TR>
</TABLE>
</fieldset>
</DIV>

<DIV id="holding">
<fieldset>
<legend>Holding Phase</legend>
<TABLE class="float">
	<TR><TD>Pressure:</TD><TD><input type="text" name="holdingPressure_1" value="<c:out value='${form.holdingPressure_1}' />"/></TD><TD>Time:</TD><TD><input type="text" name="holdingTime_1" value="<c:out value='${form.holdingTime_1}' />"/></TD></TR>
	 <TR><TD>Pressure 2:</TD><TD><input type="text" name="holdingPressure_2" value="<c:out value='${form.holdingPressure_2}' />"/></TD><TD>Time 2:</TD><TD><input type="text" name="holdingTime_2" value="<c:out value='${form.holdingTime_2}' />"/></TD></TR>
	 <TR><TD>Pressure 3:</TD><TD><input type="text" name="holdingPressure_3" value="<c:out value='${form.holdingPressure_3}' />"/></TD><TD>Time 3:</TD><TD><input type="text" name="holdingTime_3" value="<c:out value='${form.holdingTime_3}' />"/></TD></TR>
	 <TR><TD>Pressure 4:</TD><TD><input type="text" name="holdingPressure_4" value="<c:out value='${form.holdingPressure_4}' />"/></TD><TD>Time 4:</TD><TD><input type="text" name="holdingTime_4" value="<c:out value='${form.holdingTime_4}' />"/></TD></TR>
	 <TR><TD>Pressure 5:</TD><TD><input type="text" name="holdingPressure_5" value="<c:out value='${form.holdingPressure_5}' />"/></TD><TD>Time 5:</TD><TD><input type="text" name="holdingTime_5" value="<c:out value='${form.holdingTime_5}' />"/></TD></TR>
	 <TR><TD>Pressure 6:</TD><TD><input type="text" name="holdingPressure_6" value="<c:out value='${form.holdingPressure_6}' />"/></TD><TD>Time 6:</TD><TD><input class="end" type="text" name="holdingTime_6" value="<c:out value='${form.holdingTime_6}' />"/></TD></TR>
</TABLE>
</fieldset>
</DIV>
<DIV id="injExtOptions">
<fieldset>
<legend>Injection Options</legend>
<TABLE class="float">
	 <TR><TD>Max Pack Velocity:</TD><TD><input type="text" name="maxPackVel" value="<c:out value='${form.maxPackVel}' />"/></TD></TR>
	 <TR><TD>PosTran:</TD><TD><input type="text" name="posTran" value="<c:out value='${form.posTran}' />"/></TD></TR><!-- Changeover -->
	 <TR><TD>Max Injection Pre:</TD><TD><input type="text" name="maxInjPre" value="<c:out value='${form.maxInjPre}' />"/></TD></TR>
	 <TR><TD>Max Injection Time:</TD><TD><input type="text" name="maxInjTime" value="<c:out value='${form.maxInjTime}' />" /></TD></TR>
	 <TR><TD>Shot Size(CC):</TD><TD><input type="text" name="shotSize" value="<c:out value='${form.shotSize}' />"/></TD></TR>
	 <TR><TD>Decompression Distance:</TD><TD><input type="text" name="decompressionDist" value="<c:out value='${form.decompressionDist}' />"/></TD></TR>
	 <TR><TD>Decompression Velocity:</TD><TD><input type="text" name="decompressionVel" value="<c:out value='${form.decompressionVel}' />"/></TD></TR>
	 <TR><TD>Cooling Time:</TD><TD><input class="end" type="text" name="coolTime" value="<c:out value='${form.coolTime}' />"/></TD></TR>
	 
</TABLE>
</fieldset>
</DIV>
<DIV id="extrusion">
<fieldset>
<legend>Extrusion</legend>
<TABLE class="float">
	<TR><TH>Back Pressure(BAR)</TH><TH>Screw Speed(RPM)</TH><TH>Position(MM)</TH></TR>
	<TR><TD><input type="text" name="backPressure_1" value="<c:out value='${form.backPressure_1}' />"/></TD><TD><input type="text" name="screwExtSpeed_1" value="<c:out value='${form.screwExtSpeed_1}' />"/></TD><TD><input type="text" name="extProfilePos_1" value="<c:out value='${form.extProfilePos_1}' />"/></TD></TR>
	<TR><TD><input type="text" name="backPressure_2" value="<c:out value='${form.backPressure_2}' />"/></TD><TD><input type="text" name="screwExtSpeed_2" value="<c:out value='${form.screwExtSpeed_2}' />"/></TD><TD><input type="text" name="extProfilePos_2" value="<c:out value='${form.extProfilePos_2}' />"/></TD></TR>
	<TR><TD><input type="text" name="backPressure_3" value="<c:out value='${form.backPressure_3}' />"/></TD><TD><input type="text" name="screwExtSpeed_3" value="<c:out value='${form.screwExtSpeed_3}' />"/></TD><TD><input type="text" name="extProfilePos_3" value="<c:out value='${form.extProfilePos_3}' />"/></TD></TR>
	<TR><TD><input type="text" name="backPressure_4" value="<c:out value='${form.backPressure_4}' />"/></TD><TD><input type="text" name="screwExtSpeed_4" value="<c:out value='${form.screwExtSpeed_4}' />"/></TD><TD><input type="text" name="extProfilePos_4" value="<c:out value='${form.extProfilePos_4}' />"/></TD></TR>
	<TR><TD><input type="text" name="backPressure_5" value="<c:out value='${form.backPressure_5}' />"/></TD><TD><input type="text" name="screwExtSpeed_5" value="<c:out value='${form.screwExtSpeed_5}' />"/></TD><TD><input type="text" name="extProfilePos_5" value="<c:out value='${form.extProfilePos_5}' />"/></TD></TR>
	<TR><TD><input type="text" name="backPressure_6" value="<c:out value='${form.backPressure_6}' />"/></TD><TD><input type="text" name="screwExtSpeed_6" value="<c:out value='${form.screwExtSpeed_6}' />"/></TD><TD><input class="end" type="text" name="extProfilePos_6" value="<c:out value='${form.extProfilePos_6}' />"/></TD></TR>
	
</TABLE>
</fieldset>
</DIV>

<DIV id="barrelTemperatures">
<fieldset>
<legend>Barrel Temperatures</legend>
<TABLE class="float">
<TR><TH>Nozzel</TH><TH>T1</TH><TH>T2</TH><TH>T3</TH><TH>T4</TH><TH>Throat</TH></TR>
<TR>
<TD><input type="text" name="nozzelTemperature" value="<c:out value='${form.nozzelTemperature}' />"/></TD>
<TD><input type="text" name="barrelTemperature_1" value="<c:out value='${form.barrelTemperature_1}' />"/></TD>
<TD><input type="text" name="barrelTemperature_2" value="<c:out value='${form.barrelTemperature_2}' />"/></TD>
<TD><input type="text" name="barrelTemperature_3" value="<c:out value='${form.barrelTemperature_3}' />"/></TD>
<TD><input type="text" name="barrelTemperature_4" value="<c:out value='${form.barrelTemperature_4}' />"/></TD>
<TD><input class="end" type="text" name="throatTemperature" value="<c:out value='${form.throatTemperature}' />"/></TD>
</TR>
</TABLE>
</fieldset>
</DIV>
<DIV id="mouldClosing">
<fieldset>
<legend>Mould Closing</legend>
<TABLE class="float">
<TR><TH></TH><TH>Position(MM)</TH><TH>Speed(MM/S)</TH></TR>
<TR><TD>Open Limit:</TD>
<TD><input type="text" name="mouldClosingOpenLimitPos" value="<c:out value='${form.mouldClosingOpenLimitPos}' />"/></TD><TD><input type="text" name="mouldClosingOpenLimitSpeed" value="<c:out value='${form.mouldClosingOpenLimitSpeed}' />"/></TD></TR>
<TR><TD>Closed Limit:</TD>
<TD><input type="text" name="mouldClosedLimitPos" value="<c:out value='${form.mouldClosedLimitPos}' />"/></TD><TD><input type="text" name="mouldClosedLimitSpeed" value="<c:out value='${form.mouldClosedLimitSpeed}' />"/></TD></TR>
<TR><TD>CLS Slow</TD><TD>
<input type="text" name="clsSlowPos" value="<c:out value='${form.clsSlowPos}' />"/></TD><TD><input type="text" name="clsSlowSpeed" value="<c:out value='${form.clsSlowSpeed}' />"/></TD></TR>
<TR><TD>Close SP</TD><TD>
<input type="text" name="clsSPPos" value="<c:out value='${form.clsSPPos}' />"/></TD><TD><input class="end" type="text" name="clsSPSpeed" value="<c:out value='${form.clsSPSpeed}' />"/></TD></TR>
</TABLE> 
</fieldset>
</DIV>


<DIV id="mouldOpening">
<fieldset>
<legend>Mould Opening</legend>
<TABLE class="float">
<TR><TH></TH><TH>Position(MM)</TH><TH>Speed(MM/S)</TH></TR>
<TR><TD>Breakaway:</TD><TD></TD><TD><input type="text" name="mouldOpenBreakAwaySpeed" value="<c:out value='${form.mouldOpenBreakAwaySpeed}' />"/></TD></TR>
<TR><TD>Open 1 Step:</TD><TD><input type="text" name="mouldOpenStepPos_1" value="<c:out value='${form.mouldOpenStepPos_1}' />"/></TD><TD><input type="text" name="mouldOpenStepSpeed_1" value="<c:out value='${form.mouldOpenStepSpeed_1}' />"/></TD></TR>
<TR><TD>Open 2 Step:</TD><TD><input type="text" name="mouldOpenStepPos_2" value="<c:out value='${form.mouldOpenStepPos_2}' />"/></TD><TD><input type="text" name="mouldOpenStepSpeed_2" value="<c:out value='${form.mouldOpenStepSpeed_2}' />"/></TD></TR>
<TR><TD>Open 3 Step:</TD><TD><input type="text" name="mouldOpenStepPos_3" value="<c:out value='${form.mouldOpenStepPos_3}' />"/></TD><TD><input type="text" name="mouldOpenStepSpeed_3" value="<c:out value='${form.mouldOpenStepSpeed_3}' />"/></TD></TR>
<TR></TR>
<TR><TD>Mould Open Time</TD><TD><input type="text" name="mouldOpenTime" value="<c:out value='${form.mouldOpenTime}' />"/></TD><TD></TD></TR>
<TR><TD>Eject Start</TD><TD><input class="end" type="text" name="ejectStart" value="<c:out value='${form.ejectStart}' />"/></TD><TD></TD></TR>
</TABLE>
</fieldset>
</DIV>

<DIV id="ejectors">
<fieldset>
<legend>Ejectors</legend>
<TABLE class="float">
	<TR><TD>Ejector Mode</TD><TD><input type="text" name="ejectMode" value="<c:out value='${form.ejectMode}' />"/></TD><TD></TD><TD></TD></TR>
	<TR><TD>Ejector Pulse</TD><TD><input type="text" name="ejectPulse" value="<c:out value='${form.ejectPulse}' />"/></TD><TD>count</TD><TD></TD></TR>
	<TR><TD>Ejector Delay</TD><TD><input type="text" name="ejectDelay" value="<c:out value='${form.ejectDelay}' />"/></TD><TD>secs</TD><TD></TD></TR>
	<TR></TR>
	<TR><TD>FWD</TD><TD><input type="text" name="ejectorsFwdPos" value="<c:out value='${form.ejectorsFwdPos}' />"/></TD><TD><input type="text" name="ejectorsFwdSpeed" value="<c:out value='${form.ejectorsFwdSpeed}' />"/></TD><TD><input type="text" name="ejectorsFwdTime" value="<c:out value='${form.ejectorsFwdTime}' />"/></TD></TR>
	<TR><TD>STOP</TD><TD><input type="text" name="ejectorsStopPos" value="<c:out value='${form.ejectorsStopPos}' />"/></TD><TD><input type="text" name="ejectorsStopSpeed" value="<c:out value='${form.ejectorsStopSpeed}' />"/></TD><TD><input type="text" name="ejectorsStopTime" value="<c:out value='${form.ejectorsStopTime}' />"/></TD></TR>
	<TR><TD>REV</TD><TD><input type="text" name="ejectorsRevPos" value="<c:out value='${form.ejectorsRevPos}' />"/></TD><TD><input type="text" name="ejectorsRevSpeed" value="<c:out value='${form.ejectorsRevSpeed}' />"/></TD><TD><input class="end" type="text" name="ejectorsRevTime" value="<c:out value='${form.ejectorsRevTime}' />"/></TD></TR>
</TABLE>
</fieldset>
</DIV>

<DIV id="dme">

<fieldset>
<legend>DME and Water temperatures</legend>
<TABLE class="float">
<TR><TH>DMEs</TH></TR>
<TR><TD>1</TD><TD>2</TD><TD>3</TD><TD>4</TD><TD>5</TD><TD>6</TD><TD>7</TD><TD>8</TD></TR>
<TR>
	<TD><input type="text" name="dme_1" value="<c:out value='${form.dme_1}' />"/></TD>
	<TD><input type="text" name="dme_2" value="<c:out value='${form.dme_2}' />"/></TD>
	<TD><input type="text" name="dme_3" value="<c:out value='${form.dme_3}' />"/></TD>
	<TD><input type="text" name="dme_4" value="<c:out value='${form.dme_4}' />"/></TD>
	<TD><input type="text" name="dme_5" value="<c:out value='${form.dme_5}' />"/></TD>
	<TD><input type="text" name="dme_6" value="<c:out value='${form.dme_6}' />"/></TD>
	<TD><input type="text" name="dme_7" value="<c:out value='${form.dme_7}' />"/></TD>
	<TD><input type="text" name="dme_8" value="<c:out value='${form.dme_8}' />"/></TD>
</TR>
</TABLE>
<TABLE class="float">
	<TR><TD>Fixed Half Water Temp </TD><TD><input type="text" name="waterTempFixedHalf" value="<c:out value='${form.waterTempFixedHalf}' />"/></TD></TR>
	<TR><TD>Moving Half Water Temp </TD><TD><input type="text" name="waterTempMovingHalf" value="<c:out value='${form.waterTempMovingHalf}' />"/></TD></TR>
	<TR><TD>Notes</TD><TD><textarea name="waterTempNotes"><c:out value="${form.waterTempNotes}"/></textarea></TD></TR>
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
</div><!-- row -->
</div><!-- container -->
<%@ include file="/BootStrapFooter.jsp" %>
</body>
</html>