<%-- 
	@author Adrian Mclaughlin
 	@version 1.1
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://adrianmclaughlin.ie/myfunctions" prefix="myfunc" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags/BottomMenuBar" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/BootStrapHeader.jsp" %>
<title>ACME Plastics: Process Setup Sheet</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/General.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/SearchPage.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/EntryPage.css">
<style>
#info,
 #injection,
 #holding,
 #injExtOptions,
 #extrusion,
 #barrelTemperatures,
 #mouldClosing,
 #mouldOpening,
 #ejectors,
 #dme{
 	position:absolute;
 	top:150px;
 	width:100%;
 }
 
 #injection,
 #holding,
 #injExtOptions,
 #extrusion,
 #barrelTemperatures,
 #mouldClosing,
 #mouldOpening,
 #ejectors,
 #dme{ 
 	visibility:hidden;
 }
</style>
</head>

<body>

<!-- Javascript function to work tabbing -->
<script src="${pageContext.request.contextPath}/js/General.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/InputFocus.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/SelectDiv.js"></script>
<script>


// List of the HTML div to display

var tabs=["info",
          "injection",
          "holding",
          "injExtOptions",
          "extrusion",
          "barrelTemperatures",
          "mouldClosing",
          "mouldOpening",
          "ejectors",
          "dme"
         ];
//When the last input element in the div is entered the script will display the next div
window.addEventListener("load",function(){addChangePageListenerInput(tabs);},true);

$(document).ready(function(){
	showPanelMessage("#alert","${message}");
	showPanelMessage("#alert","${errors}");
	showPanelMessage("#result","${result}");
});
</script>

<div class="page-title"><H1>Process Setup Sheet</H1></div>

<!-- The Alert box for error and info messages -->
<div id="alert" class="alert-panel panel panel-danger" role="alert" onclick="hide(this)">
	<div class="panel-heading">
		<h3 class="panel-title">Error</h3>
	</div>
	<div class="panel-body"></div>
</div>

<div class="container entry">
<div class="row">
<!-- Selection box -->
<select id="pageSelect" onchange='displayDiv(this,tabs)'>
<option value="info">Information</option>
<option value="injection">Injection</option>
<option value="holding">Holding</option>
<option value="injExtOptions">Injection/Extrusion Options</option>
<option value="extrusion">Extrusion</option>
<option value="barrelTemperatures">Barrel Temperatures</option>
<option value="mouldClosing">Mould Closing</option>
<option value="mouldOpening">Mould Opening</option>
<option value="ejectors">Ejectors</option>
<option value="dme">DME</option>
</select>
</div><!-- row -->
</div><!-- container -->
<!-- Send info to JSP to be put into a bean todo integrate code into this page -->
<FORM class="form-horizontal" method="post" action="${pageContext.request.contextPath}/app/Processing/ProcessSheetBean"> 
<%-- To be used in edit mode to store the id of the object being edited --%>
<input type="hidden" name='id' <c:if test='${form ne null}'>value='${form.id}'</c:if>/>

<%@ include file="ProcessPage/BasicInformation.jspf" %>

<%@ include file="ProcessPage/Injection.jspf" %>

<%@ include file="ProcessPage/Holding.jspf" %>

<%@ include file="ProcessPage/InjectionExtrusionOptions.jspf" %>

<%@ include file="ProcessPage/Extrusion.jspf" %>

<%@ include file="ProcessPage/BarrelTemperatures.jspf" %>

<%@ include file="ProcessPage/MouldClosing.jspf" %>

<%@ include file="ProcessPage/MouldOpening.jspf" %>

<%@ include file="ProcessPage/Ejectors.jspf" %>

<%@ include file="ProcessPage/DME.jspf" %>

<tags:BottomMenuBar>
<tags:NavLinks>
	<tags:NavLink name="Main" glyphicon="glyphicon-home" link="${pageContext.request.contextPath}/app/APLSystemServlet"/>
	<tags:NavLink name="Search Page" glyphicon="glyphicon-search" link="${pageContext.request.contextPath}/app/ProcessSheet_search"/>
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
<%@ include file="/BootStrapFooter.jsp" %>
</body>
</html>