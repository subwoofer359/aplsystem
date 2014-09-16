<%-- 
	@author Adrian Mclaughlin
 	@version 1.1
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags/BottomMenuBar" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/BootStrapHeader.jsp" %>
<title>ACME Plastics :Material Search Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/General.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/SearchPage.css">
<style>
</style>
<script src="${pageContext.request.contextPath}/js/General.js"></script>
<script src="${pageContext.request.contextPath}/js/SearchPage.js"></script>
<script src="${pageContext.request.contextPath}/js/TablesSort.js"></script>
<script>
$(document).ready(function()
{
			
	showAlertMessage("#alert","${message}"); /* General.js */
			
});
</script>
</head>
<body>
<DIV class="page-title">
<H1>Material</H1>
</DIV>

<!-- The Alert box for error and info messages -->
<div id="alert" class="alert alert-danger" role="alert"></div>

<form action="${pageContext.request.contextPath}/app/Material_search" method="post" onsubmit="return isChecked(this,'material','alert')">
<div class="container results">
<div class="row">
<TABLE class="table col-xs-12">
<thead>
<TR><TH class="h3" onclick="tableSort(this, 'Company');selected(null);">Company</TH>
	<TH class="h3" onclick="tableSort(this, 'Name');selected(null);">Name</TH>
	<TH class="h3" onclick="tableSort(this, 'Type');selected(null);">Type</TH>
	<TH class="checkbox"></TH>
</TR>
</thead>
<tbody>
<c:forEach items="${materials}" var="material">
<TR  onclick="selected(this)"><TD><c:out value="${material.value.company}"/></TD><TD><c:out value="${material.value.name}"/></TD><TD><c:out value="${material.value.type}"/></TD><TD  class="checkbox"><input type="checkbox" name="edit" value="${material.value.id}"/></TD></TR>
</c:forEach>
</tbody>
</TABLE>
</DIV><!-- row -->
</div><!--  container -->
<tags:BottomMenuBar>
	<tags:NavLinks>
		<tags:NavLink name=" Main" link="${pageContext.request.contextPath}/app/APLSystemServlet" glyphicon="glyphicon-home"></tags:NavLink>
	</tags:NavLinks>
	<tags:ButtonsMenu>
		<button class="btn btn-block" id="add" type="submit" name="mode" value="add" onclick="addClicked(this)"><span class="glyphicon glyphicon-plus pull-left"></span>Add</button>
		<button class="btn btn-block" id="edit" type="submit" name="mode" value="edit"><span class="glyphicon glyphicon-pencil pull-left"></span>Edit</button> 
	</tags:ButtonsMenu>
	<tags:MenuSearchItem sessionVariable="MATERIALSEARCH" company="Company" name="Name" type="Type"/>
	<tags:UserListItem/>
</tags:BottomMenuBar>
</form>
<%@ include file="/BootStrapFooter.jsp" %>
</body>
</html>