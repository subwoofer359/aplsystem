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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/General.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/SearchPage.css">
<title>Users</title>
<script src="${pageContext.request.contextPath}/js/SearchPage.js"></script>
<script>
window.onload=function()
{
	var message="${message}";
	if(message!=null && message!="")
	{
		$(".alert").html("${message}");
		$(".alert").show();
	}
};

</script>
</head>
<body>
<DIV class="page-title"><H1>Users</H1></DIV>

<form method="post" action="${pageContext.request.contextPath}/app/user/Users_edit" onsubmit="return isChecked(this, 'user','alert')">
<div class="container results">
<div class="row">
<table class="table col-xs-12">
<thead>
<tr><th>UserName</th><th>Full Name</th><th>Email</th><th>Active</th><th class="checkbox"></th></tr>
</thead>
<tbody>
<c:forEach var="user" items="${users}">
<tr  onclick="selected(this)"><td><c:out value="${user.userName}" /></td><td><c:out value="${user.fullName}"/></td><td><c:out value="${user.emailAddress}"/></td><td><c:out value="${user.active}"/></td><td  class="checkbox"><input type="checkbox" name="edit" value="${user.id}"/></td></tr>
</c:forEach>
</tbody>
</table>
</div><!-- row -->
</div><!-- container -->
<tags:BottomMenuBar>
	<ul class="nav navbar-nav">
		<li class="active"><a href="${pageContext.request.contextPath}/app/APLSystemServlet"><span class="glyphicon glyphicon-home"></span> Main</a></li>
	</ul>
<tags:ButtonsMenu>
	<input class="btn btn-block" id="add" type="submit" name="mode" value="add" onclick="addClicked(this)"/>
	<input class="btn btn-block" id="edit" type="submit" name="mode" value="edit" />
	<input class="btn btn-block" id="delete" type="submit" name="mode" value="delete"/>
	</tags:ButtonsMenu>
	<tags:UserListItem/>
</tags:BottomMenuBar>
</form>
<%@ include file="/BootStrapFooter.jsp" %>
<div id="alert" class="alert alert-danger" role="alert" onclick="hide(this)"></div>
</body>
</html>