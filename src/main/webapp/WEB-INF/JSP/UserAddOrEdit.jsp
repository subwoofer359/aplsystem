<%--  
	@author Adrian Mclaughlin
 	@version 1
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags/BottomMenuBar" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ACME Plastics:User page</title>
<%@ include file="/BootStrapHeader.jsp" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/General.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/SearchPage.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/EntryPage.css">
<style>
.btn-form
{
	color: black;
	background-color: white;
	border-color: black;
	margin-bottom: 11px;
}

.btn-form.active
{
	background-color: #4ce340;
}
</style>

<script src="${pageContext.request.contextPath}/js/General.js"></script>
<script src="${pageContext.request.contextPath}/js/InputFocus.js"></script>
<script src="${pageContext.request.contextPath}/js/SearchPage.js"></script>
<script src="${pageContext.request.contextPath}/js/User.js"></script>
<script>
$(document).ready(function(){
	showPanelMessage("#alert","${message}");
	showPanelMessage("#alert","${errors}");
	showPanelMessage("#result","${result}");

	$(".role-button").click(function(){
			var elem=$(this).children("input");
			if($(elem).prop("checked"))
			{
				$(elem).prop("checked",false);
				$(elem).removeAttr("title");
			}
			else
			{
				$(elem).prop("checked",true);
				$(elem).attr("title","selected");
			}
			
		});

	$(".role")
});
</script>
</head>

<body>
<DIV class="page-title"><H1>Users</H1></DIV>
<!-- The Alert box for error and info messages -->
<div id="alert" class="alert-panel panel panel-danger" role="alert" onclick="hide(this)">
	<div class="panel-heading">
		<h3 class="panel-title">Error</h3>
	</div>
	<div class="panel-body"></div>
</div>

<div class="container entry">
<div class=" row">
<form class="form-horizontal" method="post" action='${pageContext.request.contextPath}/app/user/User_Save' onsubmit="return checkPassword('passwordOne','passwordTwo')" autocomplete="off">

<input type="hidden" name='id' <c:if test='${user ne null}'>value='${user.id}'</c:if>/>

<div class="form-group">
	<label class="control-label col-sm-2 hidden-xs" for="name">Name</label>
	<div class="col-xs-12 col-sm-7">
		<input id="fullName" class="form-control" type='text' name='fullName' placeholder="Full Name" <c:if test='${user ne null}'>value="<c:out value='${user.fullName}' />"</c:if> autofocus="autofocus"/>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-sm-2 hidden-xs" for="userName">Username</label>
	<div class="col-xs-12 col-sm-7">
		<input id="userName" class="form-control" type='text'  name='userName'  placeholder="User Name" value="<c:if test='${user ne null}'> <c:out value='${user.userName}' /></c:if>" <c:if test="${mode eq 'edit'}">readonly='readonly'</c:if>  autocomplete='off'/>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-sm-2 hidden-xs" for="password">Password</label>
	<div class="col-xs-12 col-sm-7">
		<input id="passwordOne" class="form-control" type='password' name='password' placeholder="password" <c:if test='${user ne null}'> value="<c:out value='${user.password}'/>" </c:if> autocomplete='off' />
	</div>
</div>
<div class="form-group">
	<label class="control-label col-sm-2 hidden-xs" for="confirm_password">Confirm Password</label>
	<div class="col-xs-12 col-sm-7">
		<input id="passwordTwo" class="form-control" type='password' name='confirm_password' placeholder="Confirm Password" <c:if test='${user ne null}'> value="<c:out value='${user.password}'/>" </c:if> autocomplete='off'/>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-sm-2 hidden-xs" for="emailAddress">Email Address</label>
	<div class="col-xs-12 col-sm-7">
		<input id="emailAddress" class="form-control" type='email' name='emailAddress' placeholder="Email Address" <c:if test='${user ne null}'>value="<c:out value='${user.emailAddress}' />"</c:if> pattern="\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}\b" />
	</div>
</div>
<div class="form-group">
<label class="control-label col-xs-2" for="active">Active</label>
	<div class="col-sm-offset-3 col-xs-offset-4 checkbox">
		<input id="active" type='checkbox' name='active' <c:if test='${user ne null and user.active eq true}'>checked='checked'</c:if> />
	</div>
</div>

<fieldset>
<legend>Roles</legend>
<div class="container">
<div class="row">
<div class="btn-group-vertical col-xs-offset-2 col-xs-8 col-sm-3" data-toggle="buttons">
		<c:forEach items="${SECURITY_ROLES}" var="roleName" varStatus="count">
				<label class="role-button btn btn-form <c:forEach items="${user.roles}" var="userRole"><c:if test="${roleName == userRole.roleName}"> active</c:if></c:forEach>" for="role">
					<input id="role${count.index}" class="role" name="role" value="${roleName}" type="checkbox" <c:forEach items="${user.roles}" var="userRole"><c:if test="${roleName == userRole.roleName}">checked='checked'</c:if></c:forEach> /><c:out value="${roleName}"></c:out>
				</label>
		</c:forEach>
</div>	
</div>
</div>
</fieldset>

<%-- To tell the servlet which mode the page is submitting in --%>
<tags:BottomMenuBar>
<tags:NavLinks>
	<tags:NavLink name="Main" glyphicon="glyphicon-home" link="${pageContext.request.contextPath}/app/APLSystemServlet" />
	<tags:NavLink name="Users Page" glyphicon="glyphicon-user" link="${pageContext.request.contextPath}/app/user/Users"/>
</tags:NavLinks>
<tags:ButtonsMenu>
	<c:if test="${not(mode eq 'edit') }">
		<button id="edit" type='submit' class="btn btn-block" name="mode" value='enter'><span class="glyphicon glyphicon-plus pull-left"></span>Enter</button>
	</c:if>
	<c:if test="${mode eq 'edit' }">
		<button id="edit" type='submit' class="btn btn-block" name="mode" value='edit'><span class="glyphicon glyphicon-pencil pull-left"></span>Edit</button>
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