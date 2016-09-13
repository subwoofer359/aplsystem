<%--  
	@author Adrian Mclaughlin
 	@version 1
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags/BottomMenuBar" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/BootStrapHeader.jsp" %>
<title>ACME Plastics: Material Sheet</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/General.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/SearchPage.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/EntryPage.css">
<style>
</style>
<script src="${pageContext.request.contextPath}/js/General.js"></script>
<script src="${pageContext.request.contextPath}/js/InputFocus.js"></script>
<script>
$(document).ready(function(){
	showPanelMessage("#alert","${message}");
	showPanelMessage("#alert", '<c:out value="${errors.fieldError}"/>');
});
	
</script>
</head>

<body>
<div class="page-title"><h1>Material</h1></div>

<!-- The Alert box for error and info messages -->
<div id="alert" class="alert-panel panel panel-danger" role="alert" onclick="hide(this)">
	<div class="panel-heading">
		<h3 class="panel-title">Error</h3>
	</div>
	<div class="panel-body"></div>
</div>

<div class="container entry">
<div class="row">
<form class="form-horizontal" method="post" action="./Material_save"> 
<%-- To be used in edit mode to store the id of the object being edited --%>
<input type="hidden" name='id' <c:if test='${form ne null}'>value='${form.id}'</c:if>/>

<div class="form-group">
	<label class="control-label col-sm-2 " for="company">Company:</label>
	<div class="col-xs-12 col-sm-5">
		<input id="company" class="form-control" type="text" name="company"  placeholder="Company" value="<c:out value='${form.company}' />" autofocus="autofocus"/>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-sm-2 " for="name">Name:</label>
	<div class="col-xs-12 col-sm-5">
		<input id="name" class="form-control" type="text" name="name" placeholder="Name" value="<c:out value='${form.name}' />" />
	</div>
</div>
<div class="form-group">
	<label class="control-label col-sm-2 " for="type">Type:</label>
	<div class="col-xs-12 col-sm-5">
		<input id="type" class="form-control" type="text" name="type" placeholder="Type" value="<c:out value='${form.type}' />"/>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-sm-2 " for="density">Density:</label>
	<div class="col-xs-12 col-sm-5">
		<input id="density" class="form-control" type="text" name="density" placeholder="Density" value="<c:out value='${form.density}' />"/>
	</div>
</div>
<div class="form-group">
<label class="control-label col-sm-2 " for="linearExpansion">Linear Expansion:</label>
	<div class="col-xs-12 col-sm-5">
		<input id="linearExpansion" class="form-control" type="text" name="linearExpansion" placeholder="Linear Expansion" value="<c:out value='${form.linearExpansion}' />"/>
	</div>
</div>
<div class="form-group">
<label class="control-label col-sm-2 " for="waterAbsorption">Water Absorption:</label>
	<div class="col-xs-12 col-sm-5">
		<input id="waterAbsorption" class="form-control" type="text" name="waterAbsorption" placeholder="Water Absorption" value="<c:out value='${form.waterAbsorption}' />"/>
	</div>
</div>
<div class="form-group">
<label class="control-label col-sm-2 " for="materialDrying">Material Drying:</label>
	<div class="col-xs-12 col-sm-5">
		<input id="materialDrying" class="form-control" type="text" name="materialDrying" placeholder="Material Drying" value="<c:out value='${form.materialDrying}' />"/>
	</div>
</div>
<div class="form-group">
<label class="control-label col-sm-2 " for="meltingTempLower">Lower Melting temperature:</label>
	<div class="col-xs-12 col-sm-5">
		<input id="meltingTempLower" class="form-control" type="text" name="meltingTempLower" placeholder="Lower Melting temperature" value="<c:out value='${form.meltingTempLower}' />"/>
	</div>
</div>
<div class="form-group">
<label class="control-label col-sm-2 " for="meltingTempUpper">Upper Melting temperature:</label>
	<div class="col-xs-12 col-sm-5">
		<input id="meltingTempUpper" class="form-control" type="text" name="meltingTempUpper"  placeholder="Upper Melting temperature" value="<c:out value='${form.meltingTempUpper}' />"/>
	</div>
</div>
<div class="form-group">
<label class="control-label col-sm-2 " for="mouldShrinkage">Shrinkage:</label>
	<div class="col-xs-12 col-sm-5">
		<input id="mouldShrinkage" class="form-control" type="text" name="mouldShrinkage"  placeholder="Shrinkage" value="<c:out value='${form.mouldShrinkage}' />"/>
	</div>
</div>
<div class="form-group">
<label class="control-label col-sm-2 " for="mouldTempLow">Lower Mould temperature:</label>
	<div class="col-xs-12 col-sm-5">
		<input id="mouldTempLow" class="form-control" type="text" name="mouldTempLow"  placeholder="Lower Mould temperature" value="<c:out value='${form.mouldTempLow}' />"/>
	</div>
</div>
<div class="form-group">
<label class="control-label col-sm-2 " for="mould_temp_upper">Upper Mould temperature:</label>
	<div class="col-xs-12 col-sm-5">
		<input id="mould_temp_upper" class="form-control" type="text" name="mouldTempUpper"  placeholder="Upper Mould temperature" value="<c:out value='${form.mouldTempUpper}' />"/>
	</div>
</div>

<tags:BottomMenuBar>
<tags:NavLinks>
	<tags:NavLink name="Main" glyphicon="glyphicon-home" link="${pageContext.request.contextPath}/app/APLSystemServlet"/>
	<tags:NavLink name="Search Page" glyphicon="glyphicon-search" link="${pageContext.request.contextPath}/app/Material_search"/>
</tags:NavLinks>
<tags:ButtonsMenu>
<%-- To tell the servlet which mode the page is submitting in --%>
<c:if test="${mode eq null }">
	<button id="enter" class="btn btn-block" type='submit'  name="mode" value='Enter'><span class="glyphicon glyphicon-plus pull-left"></span>Enter</button>
</c:if>
<c:if test="${mode eq 'edit' }">
	<button id="edit"  class="btn btn-block" type='submit'  name="mode" value='Edit'><span class="glyphicon glyphicon-pencil pull-left"></span>Edit</button>
</c:if>
</tags:ButtonsMenu>
	<tags:UserListItem/>
</tags:BottomMenuBar>

</form>
</div> <!-- row -->
</div> <!-- container -->


<%@ include file="/BootStrapFooter.jsp" %>
</body>
</html>