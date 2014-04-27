<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytagsandfunctions.tld" prefix="my" %>
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
	window.location="${pageContext.request.contextPath}/APLSystemServlet";
}
</SCRIPT>
<body>
<DIV class="title"><H1>User Information</H1></DIV>
<%@ include file="NavigationDiv.jspf" %>

<DIV class="info">
<fieldset>
<legend>Basic</legend>
UserName: ${pageContext.request.remoteUser}<br/>
Role: 
<c:if test="${my:isUserInRole(pageContext.request,('qc'))}">
	QC
</c:if> 
<c:if test="${my:isUserInRole(pageContext.request,('manager'))}">
	Manager
</c:if>
<c:if test="${my:isUserInRole(pageContext.request,('guest'))}">
	Guest
</c:if>
<br/>
Logged in form: ${pageContext.request.remoteHost} on port ${pageContext.request.remotePort}

</fieldset>

<fieldset>
<legend>System info</legend>
Session count: ${session_count}<br/>
</fieldset>
</DIV>
<!--  <form action="APLSystemServlet">
<input type="button" value="Go Home"/> 
</form>-->


<form id="logout" action="${pageContext.request.contextPath}/logout" method="post">
</form>
<span class="buttons">
<button onclick="goHome(this)">Go Home</button>
<button form="logout" type="submit">Log Out</button>
</span>
</body>
</html>