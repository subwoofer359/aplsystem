<%--
    @author:Adrian McLaughlin
    Test View for  APLSPCDataController
    Not final view
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SPC Data Entry</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/General.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/SearchPage.css"/>
<style>
#spcmeasurements
{
	position: absolute;
	top: 10%;
	left: 2%;
	border-style: double;
}
#measurements 
{
	position: absolute;
	left: 2%;
	bottom: 10%;
}
#submitButton
{
	position:fixed;
	right:2%;
	bottom:2%;
}
#message
{
	position:absolute;
	top:40%;
	right:0;
}
</style>
</head>

<body>

<div class="title">
<h1>SPC:${part.name}&nbsp;${part.version }&nbsp;${part.colour}&nbsp;(${part.part_id })</h1>
</div>

<%@ include file="/WEB-INF/JSP/NavigationDiv.jspf" %>
<tags:Navbox href="${pageContext.request.contextPath}/app/spc/SPCPartsList" value="Search Page" position="220px"></tags:Navbox>

<table id="spcmeasurements">
<thead>
<tr>
<td>Dimension</td>
<td>SPC Measurement</td>
<td>Lower Limit</td>
<td>Upper Limit</td>
</tr>
</thead>
<tbody>
	<c:forEach items="${spcmeasurements }" var="spcmeasurement">
		<tr><td>${spcmeasurement.dimension}</td>
			<td>${spcmeasurement.nominal}</td>
			<td>${spcmeasurement.lowerLimit}</td>
			<td>${spcmeasurement.upperLimit}</td>
			<td><c:if test="${spcmeasurement.id eq CURRENT_SPC_MEASUREMENT.id}">*</c:if></td>
		</tr>
	</c:forEach>
</tbody>
</table>
<form action="post">
<table id="measurements">
<c:forEach begin="1" end="${CURRENT_SPC_MEASUREMENT.noOfMeasurements}" var="count">
<tr><td>${count}</td><td><input name="measurement" type="text"/></td></tr>
<tr><td></td><td><input name="measurementNumber" type="hidden" value="${count}"/></td></tr>
<tr><td></td><td><input name="date" type="hidden" value="2013-01-19"/></td></tr>
</c:forEach>
</table>
<input id="submitButton" type="submit" name="Engaged" formaction="${pageContext.request.contextPath}/app/spc/SPC/saveSPCData"/>
</form>
<div id="message">
	${message}
</div>
</body>
</html>
