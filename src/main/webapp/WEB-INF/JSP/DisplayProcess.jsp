<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>process: ${process.partId}</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/General.css">
<style>
.title 
{
	z-index: 1;
}

.navbox
{
	z-index: 2;
}

.informationbox
{
	position:absolute;
	border-style: solid;
	border-radius: 25px;
	background-color: white;
	margin-top:4px;
	margin-left:20px;
	margin-right:auto;
	top:100px;
	width:50%;
	left:5%;
	padding: 20px;
	overflow:auto;
	font-size:xx-large;
}

.information
{
	position:relative;
	top:420px;
	
}

.header
{
	font-weight: bold;
}
.chart
{
 padding-top: 5px;
 padding-bottom: 5px;
 
}
</style>
</head>
<body>
<DIV class="title">
<H1> Process:${process.partId }</H1>
</DIV>
<%@ include file="NavigationDiv.jspf" %>
<DIV class="informationbox">
<TABLE>
<TR><TD class="header">Machine Size:</TD><TD>${process.machineSize}</TD></TR>
<TR><TD class="header">Machine No.</TD><TD>${process.machineNo}</TD></TR>
<TR><TD class="header">Material:</TD><TD>${process.material}</TD></TR>
<TR><TD class="header">Masterbatch:</TD><TD>${process.masterbatchNo}</TD></TR>
<TR><TD class="header">Date of Issue:</TD><TD>${process.dateOfIssue}</TD></TR>
<TR><TD class="header">Signed of by:</TD><TD>${process.signOffBy}</TD></TR>
</TABLE>
</DIV>
<DIV class="information">
<tags:Chart>
	<jsp:attribute name="injectiontimespeed">
		<tags:InjectionTimeSpeed process="${process}"></tags:InjectionTimeSpeed>
	</jsp:attribute>
	<jsp:attribute name="injectiontimepressure">
		<tags:InjectionTimePressure process="${process}"></tags:InjectionTimePressure>
	</jsp:attribute>
	<jsp:attribute name="cycletimeline">
		<tags:CycleTimeLine process="${process}"></tags:CycleTimeLine>
	</jsp:attribute>
	<jsp:attribute name="mouldclosingtime">
		<tags:MouldClosing process="${process}"></tags:MouldClosing>
	</jsp:attribute>
</tags:Chart>
</DIV>
</body>
</html>