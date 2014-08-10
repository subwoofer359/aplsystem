<%--  
	@author Adrian Mclaughlin
 	@version 1
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://adrianmclaughlin.ie/myfunctions" prefix="my"  %>


<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--  <link rel="SHORTCUT ICON" HREF="${pageContext.request.contextPath}/images/xbill_01.jpg">  --> <!--  To be placed in header --> 
<title>ACME Plastics Systems' Main Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/General.css">
<STYLE>

DIV.icon 
{
	border-style: solid;
	border-color: black;
	border-width: 1px;
	border-radius: 25px;
	background-color: white;
	height: 150px;
	margin:	20px;
	line-height: 150px;
	text-align: center;
	font-size: xx-large;
}

DIV.icon:hover
{
	background-color: red;
}
div.icon:VISITED
{
	text-decoration: none;	
}

table
{
	position:fixed;
	top:100px;
	height:80%;
	width:100%;
}


</STYLE>
</head>
<body>

<DIV class="title">
<H1>ACME Plastics System</H1>
</DIV>

<%@ include file="NavigationDiv.jspf" %>
<table>
<tr>
<td>
	<a href="${pageContext.request.contextPath}/app/Part_search">
	<DIV id="partsearch" class="icon">
 		Part Search
	</DIV>
	</a>
</td>
<td>
	<DIV id="problemdb" class="icon">
		Problem Database
	</DIV>
</td>
<td>
	<a href="${pageContext.request.contextPath}/app/ProcessSheet_search">
	<DIV id="process" class="icon">
		Process Sheets
	</DIV>
	</a>
</td>
</tr>
<tr>
<td>
	<a href="${pageContext.request.contextPath}/app/Material_search">
	<DIV id="material" class="icon">
		Material list
	</DIV>
	</a>
</td>
<td>
	<c:if test="${my:isUserInRole(pageContext.request,'manager')}">
	<a href="${pageContext.request.contextPath}/app/user/Users">
	<DIV id="users" class="icon">
		Users
	</DIV>
	</a>
	</c:if>
	
</td>
<td>
	<c:if test="${my:isUserInRole(pageContext.request,'qc')}">
	<a href="${pageContext.request.contextPath}/app/spc/SPCListParts">
	<DIV id="spc" class="icon">
		Statistical process control
	</DIV>
	</a>
	</c:if>
</td>
</tr>
</table>
</body>
</html>