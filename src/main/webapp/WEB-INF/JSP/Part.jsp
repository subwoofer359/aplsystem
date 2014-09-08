<%--  
	@author Adrian Mclaughlin
 	@version 1.1
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<SCRIPT>
function home()
{
	location="Part_search";
}
</SCRIPT>
<style>

</style>
<script src="${pageContext.request.contextPath}/js/InputFocus.js"></script>
</head>
<body>
<DIV class="page-title"><H1> Part Description</H1></DIV>

<%-- Display errors if there any --%>
<c:if test="${errors ne null }">
<SCRIPT>alert("${errors}");</SCRIPT>
</c:if>
<%-- The Form to get the values for the new or edited JobTemplate Object--%>

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
	<label class="control-label col-xs-2 " for="external">External</label>
	<div class="col-xs-1 col-offset-sm-0 col-offset-xs-2">
		<input id="external" class="form-control" type='checkbox' name='external' <c:if test='${form ne null and form.external eq true}'>checked='checked'</c:if> />
	</div>
</div>



<!-- <button type="button" value="home" onclick="home()">Home</button>  -->
<%-- To tell the servlet which mode the page is submitting in --%>
<tags:BottomMenuBar>
<tags:NavLinks>
	<tags:NavLink name="Main" glyphicon="glyphicon-home" link="${pageContext.request.contextPath}/app/APLSystemServlet" active="true"/>
	<tags:NavLink name="Search Page" glyphicon="glyphicon-search" link="${pageContext.request.contextPath}/app/Part_search"/>
</tags:NavLinks>
<tags:ButtonsMenu>
<c:if test="${mode eq null }">
	<button id="enter" class="btn btn-block" type='submit'  name="mode" value="Enter"><span class="glyphicon glyphicon-plus pull-left"></span>Enter</button>
</c:if>
<c:if test="${mode eq 'edit Part' }">
	<button id="edit" class="btn btn-block" type='submit' name="mode" value="Edit"><span class="glyphicon glyphicon-pencil pull-left"></span>Edit</button>
</c:if>
</tags:ButtonsMenu>
<tags:UserListItem/>

</tags:BottomMenuBar>
</FORM>
</div><!-- row -->
</div><!-- container -->

<DIV class="result">
<%-- To let the user know the part has been saved --%>
<c:if test="${result ne null }">
Part:${result}<br/>
</c:if>
</DIV>
<%@ include file="/BootStrapFooter.jsp" %>
<div id="alert" class="alert alert-danger" role="alert" onclick="hide(this)"></div>
</body>
</html>