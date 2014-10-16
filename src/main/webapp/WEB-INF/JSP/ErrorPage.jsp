<%--  
	@author Adrian Mclaughlin
 	@version 1
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/General.css">
<head>
<style>

</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error has occurred</title>
</head>
<body>
<DIV class="title">
<H1> An exception has occurred in the application</H1>
</DIV>
<DIV class="error_message">
<strong>${pageContext.exception}</strong>
<br/>
<br/>


</DIV>
</body>
</html>