<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/General.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/SearchPage.css">
<title>Users</title>
</head>
<body>
<DIV class="title"><H1>Users</H1></DIV>
<%@ include file="NavigationDiv.jspf" %>

<form>
<div class="results">
<table>
<thead>
<tr><th>UserName</th><th>Full Name</th><th>Email</th><th>Active</th></tr>
</thead>
<tbody>
<c:forEach var="user" items="${users}">
<tr><td><c:out value="${user.userName}" /></td><td><c:out value="${user.fullName}"/></td><td><c:out value="${user.emailAddress}"/></td><td><c:out value="${user.active}"/></td></tr>
</c:forEach>
</tbody>
</table>
</div>
</form>
</body>
</html>