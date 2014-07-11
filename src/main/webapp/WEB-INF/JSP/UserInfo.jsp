<!--  
	@author Adrian Mclaughlin
 	@version 1
-->

<%@ page import="org.amc.model.User"%>
<%@ page session="true"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://adrianmclaughlin.ie/myfunctions" prefix="my" %>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ACME Plastics: User Information</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/General.css">
<style>
.info
{
	position:relative;
	top:100px;
	font-size:xx-large;
}
.buttons
{
	position:fixed;
	right:4px;
	bottom:4px;
}
.buttons input,button
{
	font-size:xx-large;
}
</style>
</head>
<SCRIPT>
function goHome(button)
{
	window.location="${pageContext.request.contextPath}/app/APLSystemServlet";
}
</SCRIPT>
<body>
<DIV class="title"><H1>User Information</H1></DIV>
<%@ include file="NavigationDiv.jspf" %>

<DIV class="info">
<fieldset>
<legend>Basic</legend>
<table>
<tr><td>Full Name:</td><td>${sessionScope.USER.fullName}</td></tr>
<tr><td>UserName:</td><td>${pageContext.request.remoteUser}</td></tr>
<tr><td>Roles:</td><td> 
<c:forEach items="${USER.roles}" var="role">
${role.roleName}&nbsp;
</c:forEach></td></tr>
<tr><td>Email address:</td><td>${USER.emailAddress}</td></tr>
<tr><td>Logged in from:</td><td>${pageContext.request.remoteHost} on port ${pageContext.request.remotePort}</td></tr>
</table>
</fieldset>

<fieldset>
<legend>System info</legend>
Session count: ${session_count}<br/>
</fieldset>
</DIV>


<form id="logout" action="${pageContext.request.contextPath}/app/logout" method="post">
</form>
<span class="buttons">
<button id="home" onclick="goHome(this)">Go Home</button>
<button id="logout" form="logout" type="submit">Log Out</button>
</span>
</body>
</html>