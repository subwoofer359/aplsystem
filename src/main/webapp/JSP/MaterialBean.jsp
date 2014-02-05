<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="ErrorPage.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/theme.css">
</head>
<body>

<jsp:useBean id="material" class="org.amc.servlet.model.MaterialForm" scope="request"/>

<jsp:setProperty name="material" property="*" />
<jsp:forward page="/Material_save"/>
</body>
</html>