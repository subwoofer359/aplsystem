<!--  
	@author Adrian Mclaughlin
 	@version 1
-->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/General.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/SearchPage.css">
<title>ACME Plastics:Parts Search Page</title>
<STYLE>


</STYLE>
<script src="${pageContext.request.contextPath}/js/SearchPage.js"></script>
<script src="${pageContext.request.contextPath}/js/TablesSort.js"></script>
<script>
window.onload=function()
		{
			var message="${message}";
			if(message!=null && message!="")
			{
				alert(message);
			}
		};
</script>
</head>
<body>
<DIV class="title">
<H1> Part Inventory</H1>
</DIV>
<%@ include file="NavigationDiv.jspf" %>

<FORM action="${pageContext.request.contextPath}/app/Part_search" method="post" onsubmit="return isChecked(this,'part')">
<DIV class="results">
<TABLE>
<thead>
<TR>
	<TH onclick="tableSort(this, 'Id');selected(null);">Id</TH>
	<TH onclick="tableSort(this, 'Company');selected(null);">Company</TH>
	<TH onclick="tableSort(this, 'Name');selected(null);">Name</TH>
	<TH onclick="tableSort(this, 'Version');selected(null);">Version</TH>
	<TH onclick="tableSort(this, 'Colour');selected(null);">Colour</TH>
	<TH onclick="tableSort(this, 'QSS no.');selected(null);">QSS no.</TH>
	<TH></TH>
</TR>
</thead>
<tbody>
<c:forEach items="${parts}" var="part">
<TR  onclick="selected(this)"><TD><c:out value="${part.id}"/></TD><TD><c:out value="${part.company}"/></TD><TD><c:out value="${part.name}"/></TD><TD><c:out value="${part.version}"/></TD><TD><c:out value="${part.colour}"/></TD><TD><c:out value="${part.qss_no}"/></TD><TD  class="checkbox"><input type="checkbox" name="edit" value="${part.id}"/></TD></TR>
</c:forEach>
<!-- <TR><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD></TR> -->
</tbody>
</TABLE>
</DIV>
<SPAN class="search">
Company<input type="text" name="company"/>
Name<input type="text" name="partName"/>
QSS No.<input type="text" name="qssNumber"/>
<input type="submit" name="mode" value="search" onclick="addClicked(this)"/>
</SPAN>
<SPAN class="buttons">
	<input id="add" type="submit" name="mode" value="add" onclick="addClicked(this)"/>
	<input id="edit" type="submit" name="mode" value="edit"/>
	<input id="edit" type="submit" value="add To SPC" formaction="${pageContext.request.contextPath}/app/spc/AddToSPC"/>
</SPAN>
</FORM>
</body>
</html>