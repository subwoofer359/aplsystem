<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>process: ${process.partId}</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/theme.css">
</head>
<body>
<DIV class="title">
<H1> Process:${process.partId }</H1>
</DIV>
<%@ include file="NavigationDiv.jspf" %>
<DIV>
<TABLE>
<TR><TD>Machine Size:</TD><TD>${process.machineSize}</TD></TR>
<TR><TD>Machine No.</TD><TD>${process.machineNo}</TD></TR>
<TR><TD>Material:</TD><TD>${process.material}</TD></TR>
<TR><TD>Masterbatch:</TD><TD>${process.masterbatchNo}</TD></TR>
<TR><TD>Date of Issue:</TD><TD>${process.dateOfIssue}</TD></TR>
<TR><TD>Signed of by:</TD><TD>${process.signOffBy}</TD></TR>
</TABLE>
</DIV>
<tags:Chart >
	<jsp:attribute name="injectiontimespeed">
		<tags:InjectionTimeSpeed process="${process}"> 
			<c:set var="injectionTime" value="${totalInjectionTime}" scope="session"/> <%-- The time pressure chart needs the total Injection Time  --%>
		</tags:InjectionTimeSpeed>
	</jsp:attribute>
	<jsp:attribute name="injectiontimepressure">
		<tags:InjectionTimePressure process="${process}" totalInjectionTime="${injectionTime}"></tags:InjectionTimePressure>
	</jsp:attribute>
	<jsp:attribute name="cycletime">
		<tags:CycleTime process="${process}"></tags:CycleTime>
	</jsp:attribute>
</tags:Chart>
</body>
</html>