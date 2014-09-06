<%--  
	@author Adrian Mclaughlin
 	@version 1
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://adrianmclaughlin.ie/myfunctions" prefix="my"  %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ACME Plastics Systems' Main Page</title>
<%@ include file="/BootStrapHeader.jsp" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/General.css">
<style>
</style>
</head>
<body>

<div class="page-title">
<H1>ACME Plastics System</H1>
</div>

<%@ include file="NavigationDiv.jspf" %>

<nav role="navigation" class="navbar navbar-default navbar-fixed-bottom">
<div class="container-fluid">
	<div class="navbar-header">
		<button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
			<span class="sr-only">Toggle Navigation</span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>
		<a href="#" class="navbar-brand">Menu</a>
	</div>

	<div id="navbarCollapse" class="collapse navbar-collapse">
	<ul class="nav navbar-nav">
		<li>
			<a href="${pageContext.request.contextPath}/app/Part_search">Part Search</a>
		</li>
		<li>
			<a href="#" >Problem Database</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/app/ProcessSheet_search">Process Sheets</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/app/Material_search">Material list</a>
		</li>
		<li>
		<c:if test="${my:isUserInRole(pageContext.request,'manager')}">
			<a href="${pageContext.request.contextPath}/app/user/Users">Users</a>
		</c:if>
		</li>
		<li>
		<c:if test="${my:isUserInRole(pageContext.request,'qc')}">
			<a href="${pageContext.request.contextPath}/app/spc/SPCListParts">Statistical process control</a>
		</c:if>
		</li>
	</ul>
	<ul class="nav navbar-nav navbar-right">
		<li>
			<a href="${pageContext.request.contextPath}/app/UserInfo">User:<c:out value='${pageContext.request.remoteUser}'/></a>
		</li>
	</ul>
	<div>
<div><!-- container -->
</nav>
<%@ include file="/BootStrapFooter.jsp" %>
</body>
</html>