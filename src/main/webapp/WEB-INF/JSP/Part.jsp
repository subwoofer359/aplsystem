<%--  
	@author Adrian Mclaughlin
 	@version 1.1
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags/BottomMenuBar" %>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/BootStrapHeader.jsp" %>
<title>ACME Plastics:Part Description Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/General.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/SearchPage.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/EntryPage.css">

<script src="${pageContext.request.contextPath}/js/General.js"></script>
<script src="${pageContext.request.contextPath}/js/InputFocus.js"></script>
<script src="${pageContext.request.contextPath}/js/SearchPage.js"></script>
<script>
	$(document).ready(function(){
	    <c:set var="inputFields" value="${fn:split('name,colour,company', ',')}"/>
	    
	    <c:forEach var="inputField" items="${inputFields}">
	    <c:if test='${not empty errors and not empty errors.getFieldError(inputField)}'>
	    	var $field = $("#${inputField}");
	    	$field.css('background-color', '#dc4040');
	    	$field.after('<span class="signup-error"><c:out value="${errors.getFieldError(inputField).code}"/></span>');
	    </c:if>
	    </c:forEach>
		showPanelMessage("#alert","${message}");
		showPanelMessage("#result","${result}");
	});
	
</script>

</head>
<body>
<DIV class="page-title"><H1> Part Description</H1></DIV>

<!-- The Alert box for error and info messages -->
<div id="alert" class="alert-panel panel panel-danger" role="alert" onclick="hide(this)">
	<div class="panel-heading">
		<h3 class="panel-title">Error</h3>
	</div>
	<div class="panel-body"></div>
</div>


<%-- To let the user know the part has been saved --%>

<div id="result" class="alert-panel panel panel-info" role="alert" onclick="hide(this)">
	<div class="panel-heading">
		<h3 class="panel-title">Update</h3>
	</div>
	<div class="panel-body"></div>
</div>


<div class="container entry">
<div class=" row">
<FORM class="form-horizontal" method='post' action='./Part_save'>

<input type="hidden" name='id' <c:if test='${form ne null}'>value='${form.id}'</c:if>/>
<%-- To be used in edit mode to store the id of the object being edited --%>
<div class="form-group">
	<label class="control-label col-sm-2 hidden-xs" for="name">Name</label>
	<div class="col-xs-12 col-sm-5">
		<input id="name" class="form-control" type='text' name='name' placeholder="Name" <c:if test='${form ne null}'>value="<c:out value='${form.name}' />"</c:if> autofocus="autofocus"/>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-sm-2 hidden-xs" for="company">Company</label>
	<div class="col-xs-12 col-sm-5">
		<input id="company" class="form-control" type='text' name='company' placeholder="Company" <c:if test='${form ne null}'>value="<c:out value='${form.company}' />"</c:if> />
	</div>
</div>

<div class="form-group">
	<label class="control-label col-sm-2 hidden-xs" for="part_id">Product No.</label>
	<div class="col-xs-12 col-sm-5">
		<input id="part_id" class="form-control" type='text' name='part_id' placeholder="Product No" <c:if test='${form ne null}'>value="<c:out value='${form.part_id}' />"</c:if> />
	</div>
</div>

<div class="form-group">
	<label class="control-label col-sm-2 hidden-xs" for="version">Version</label>
	<div class="col-xs-12 col-sm-5">
		<input id="version" class="form-control" type='text' name='version' placeholder="Version" <c:if test='${form ne null}'>value="<c:out value='${form.version}' />"</c:if> />
	</div>
</div>

<div class="form-group">
	<label class="control-label col-sm-2 hidden-xs" for="revision">Revision</label>
	<div class="col-xs-12 col-sm-5">
		<input id="revision" class="form-control" type='text' name='revision' placeholder="Revision" <c:if test='${form ne null}'>value="<c:out value='${form.revision}' />"</c:if> />
	</div>
</div>

<div class="form-group">
	<label class="control-label col-sm-2 hidden-xs" for="colour">Colour</label>
	<div class="col-xs-12 col-sm-5">
		<input id="colour" class="form-control" type='text' name='colour' placeholder="Colour" <c:if test='${form ne null}'>value="<c:out value='${form.colour}' />"</c:if> />
	</div>
</div>

<div class="form-group">
	<label class="control-label col-sm-2 hidden-xs" for="qss_no">QSS No.</label>
	<div class="col-xs-12 col-sm-5">
		<input id="qss_no" class="form-control" type='text' name='qss_no' placeholder="QSS number" <c:if test='${form ne null}'>value="<c:out value='${form.qss_no}' />"</c:if> />
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-2" for="external">External</label>
	<div class="col-sm-offset-3 col-xs-offset-4 checkbox">
		<input id="external" type='checkbox' name='external' <c:if test='${form ne null and form.external eq true}'>checked='checked'</c:if> />
	</div>
</div>

<%-- To tell the servlet which mode the page is submitting in --%>
<tags:BottomMenuBar>
<tags:NavLinks>
	<tags:NavLink name="Main" glyphicon="glyphicon-home" link="${pageContext.request.contextPath}/app/APLSystemServlet"/>
	<tags:NavLink name="Search Page" glyphicon="glyphicon-search" link="${pageContext.request.contextPath}/app/Part_search"/>
</tags:NavLinks>
<tags:ButtonsMenu>
<c:if test="${mode eq null }">
	<button id="enter" class="btn btn-block" type='submit'  name="mode" value="Enter"><span class="glyphicon glyphicon-plus pull-left"></span>Enter</button>
</c:if>
<c:if test="${mode eq 'edit' }">
	<button id="edit" class="btn btn-block" type='submit' name="mode" value="Edit"><span class="glyphicon glyphicon-pencil pull-left"></span>Edit</button>
</c:if>
</tags:ButtonsMenu>
<tags:UserListItem/>

</tags:BottomMenuBar>
</FORM>
</div><!-- row -->
</div><!-- container -->

<%@ include file="/BootStrapFooter.jsp" %>
</body>
</html>