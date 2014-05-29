<!--  
	@author Adrian Mclaughlin
 	@version 1
-->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/General.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/SearchPage.css">
<title>Users</title>
<script src="${pageContext.request.contextPath}/js/SearchPage.js"></script>
</head>
<body>
<DIV class="title"><H1>Users</H1></DIV>
<%@ include file="NavigationDiv.jspf" %>

<form method="post" action="${pageContext.request.contextPath}/user/Users_edit">
<div class="results">
<table>
<thead>
<tr><th>UserName</th><th>Full Name</th><th>Email</th><th>Active</th><th></th></tr>
</thead>
<tbody>
<c:forEach var="user" items="${users}">
<tr  onclick="selected(this)"><td><c:out value="${user.userName}" /></td><td><c:out value="${user.fullName}"/></td><td><c:out value="${user.emailAddress}"/></td><td><c:out value="${user.active}"/></td><td  class="checkbox"><input type="checkbox" name="edit" value="${user.id}"/></td></tr>
</c:forEach>
</tbody>
</table>
</div>
<SPAN class="buttons"><input type="submit" name="mode" value="add"/><input type="submit" name="mode" value="edit"/><input type="submit" name="mode" value="delete"/></SPAN>
</form>
</body>
</html>