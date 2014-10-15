<%--  
	@author Adrian Mclaughlin
 	@version 1
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="chart" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags/BottomMenuBar" %>
<%@ taglib uri="http://adrianmclaughlin.ie/myfunctions" prefix="myfunc" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/BootStrapHeader.jsp" %>
<title>process: ${process.partId}</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/General.css">
<script src="${pageContext.request.contextPath}/js/General.js"></script>
<script src="${pageContext.request.contextPath}/js/InputFocus.js"></script>
<script src="${pageContext.request.contextPath}/js/SelectDiv.js"></script>
<SCRIPT>
//An array of Chart IDs
var charts=[
		"injectionTimePressureChart",
		"injectionTimeSpeedChart",
		"mouldclosingtimechart",
		"cycleTimeLineChart"	
          ];

</SCRIPT>
<style>
</style>
</head>
<body>
<div class="page-title"><H1> Process:${process.partId }</H1></div>

<div class="container">
<div class="row">
	<DIV class="informationbox col-xs-4">
	<table class="table">
		<TR><TD class="header">Machine Size:</TD><TD>${process.machineSize}</TD></TR>
		<TR><TD class="header">Machine No.</TD><TD>${process.machineNo}</TD></TR>
		<TR><TD class="header">Material:</TD><TD><c:out value='${myfunc:toString(materials[process.material])}'></c:out></TD></TR>
		<TR><TD class="header">Masterbatch:</TD><TD>${process.masterbatchNo}</TD></TR>
		<TR><TD class="header">Date of Issue:</TD><TD>${process.dateOfIssue}</TD></TR>
		<TR><TD class="header">Signed of by:</TD><TD>${process.signOffBy}</TD></TR>
	</TABLE>
	</div>
</div>
<div class="row">
<chart:Chart>
	<jsp:attribute name="injectiontimespeed">
		<chart:InjectionTimeSpeed process="${process}"></chart:InjectionTimeSpeed>
	</jsp:attribute>
	<jsp:attribute name="injectiontimepressure">
		<chart:InjectionTimePressure process="${process}"></chart:InjectionTimePressure>
	</jsp:attribute>
	<jsp:attribute name="cycletimeline">
		<chart:CycleTimeLine process="${process}"></chart:CycleTimeLine>
	</jsp:attribute>
	<jsp:attribute name="mouldclosingtime">
		<chart:MouldClosing process="${process}"></chart:MouldClosing>
	</jsp:attribute>
</chart:Chart>
</div><!-- row -->
</div><!-- container -->

<%@ include file="/BootStrapFooter.jsp" %>
</body>
</html>
