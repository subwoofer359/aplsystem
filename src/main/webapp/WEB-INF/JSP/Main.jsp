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
<!--  <link rel="SHORTCUT ICON" HREF="${pageContext.request.contextPath}/images/xbill_01.jpg">  --> <!--  To be placed in header --> 
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

<div class="container">
<div class="row">
	<div class="col-sm-4">
		<a href="${pageContext.request.contextPath}/app/Part_search">Part Search</a>
	</div>
	
	<div id="problemdb" class="col-sm-4">
		Problem Database
	</div>
	
	<div class="col-sm-4">
		<a href="${pageContext.request.contextPath}/app/ProcessSheet_search">Process Sheets</a>
	</div>

	<div class="col-sm-4">
		<a href="${pageContext.request.contextPath}/app/Material_search">Material list</a>
	</div>

	<div class="col-sm-4">
	<c:if test="${my:isUserInRole(pageContext.request,'manager')}">
		<a href="${pageContext.request.contextPath}/app/user/Users">Users</a>
	</c:if>
	</div>

	<div class="col-sm-4">
	<c:if test="${my:isUserInRole(pageContext.request,'qc')}">
	<a href="${pageContext.request.contextPath}/app/spc/SPCListParts">Statistical process control</a>
	</c:if>
	</div>
</div>
</div><!-- container -->
<%@ include file="/BootStrapFooter.jsp" %>
</body>
</html>