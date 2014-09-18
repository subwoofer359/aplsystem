<%--  
	@author Adrian Mclaughlin
 	@version 1
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://adrianmclaughlin.ie/myfunctions" prefix="my"  %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags/BottomMenuBar" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ACME Plastics Systems' Main Page</title>
<%@ include file="/BootStrapHeader.jsp" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/General.css">
<style>
</style>
</head>
<body>

<div class="page-title">
<H1>ACME Plastics System</H1>
</div>

<tags:BottomMenuBar>
	<tags:NavLinks>
	<tags:NavLink name="Part Search" link="${pageContext.request.contextPath}/app/Part_search"/>
	<%-- Be careful of items in the navbar dropping down a line
	<tags:NavLink name="Problem Database" link="#"/>
	--%>
	
	<tags:NavLink name="Process Sheets" link="${pageContext.request.contextPath}/app/ProcessSheet_search"/>		
	 
	<tags:NavLink name="Material list" link="${pageContext.request.contextPath}/app/Material_search"/>	
	<c:if test="${my:isUserInRole(pageContext.request,'manager')}">
		<tags:NavLink name="Users" link="${pageContext.request.contextPath}/app/user/Users"/>
	</c:if>
	<c:if test="${my:isUserInRole(pageContext.request,'qc')}">
		<tags:NavLink name="Statistical process control" link="${pageContext.request.contextPath}/app/spc/SPCListParts"/>
	</c:if>
	</tags:NavLinks>
	<tags:UserListItem/>
</tags:BottomMenuBar>
<%@ include file="/BootStrapFooter.jsp" %>
</body>
</html>