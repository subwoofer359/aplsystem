<%-- 
	@author Adrian Mclaughlin
 	@version 1
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ACME Plastics :Material Search Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/General.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/SearchPage.css">
<STYLE>

</STYLE>
<script src="${pageContext.request.contextPath}/js/SearchPage.js"></script>
<script src="${pageContext.request.contextPath}/js/TablesSort.js"></script>
</head>
<body>
<DIV class="title">
<H1>Material</H1>
</DIV>
<%@ include file="NavigationDiv.jspf" %>


<FORM action="${pageContext.request.contextPath}/app/Material_search" method="post" onsubmit="return isChecked(this,'material')">
<DIV class="results">
<TABLE>
<thead>
<TR><TH onclick="tableSort(this, 'Company');selected(null);">Company</TH>
	<TH onclick="tableSort(this, 'Name');selected(null);">Name</TH>
	<TH onclick="tableSort(this, 'Type');selected(null);">Type</TH>
	<TH></TH>
</TR>
</thead>
<tbody>
<c:forEach items="${materials}" var="material">
<TR  onclick="selected(this)"><TD><c:out value="${material.value.company}"/></TD><TD><c:out value="${material.value.name}"/></TD><TD><c:out value="${material.value.type}"/></TD><TD  class="checkbox"><input type="checkbox" name="edit" value="${material.value.id}"/></TD></TR>
</c:forEach>
</tbody>
</TABLE>
</DIV>

<table class="search">
<tbody>
<tr><td>Company</td><td><input type="text" name="company"/></td><td></td></tr>
<tr><td>Name</td><td><input type="text" name="name"/></td><td></td></tr>
<tr><td>Type</td><td><input type="text" name="type"/></td><td><input type="submit" name="mode" value="search" onclick="addClicked(this)"/></td></tr>
</tbody>
</table>
<SPAN class="buttons"><input type="submit" name="mode" value="add" onclick="addClicked(this)"/><input type="submit" name="mode" value="edit"  /></SPAN>
</FORM>

</body>
</html>