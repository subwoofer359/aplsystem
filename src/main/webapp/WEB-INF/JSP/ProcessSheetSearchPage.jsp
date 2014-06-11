<!--  
	@author Adrian Mclaughlin
 	@version 1
-->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://adrianmclaughlin.ie/myfunctions" prefix="myfunc" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ACME Plastics :Process Sheets Search Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/General.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/SearchPage.css">
<style>

</style>
<script src="js/SearchPage.js"></script>
<script src="js/TablesSort.js"></script>
<script type="text/javascript">
/**
 * Needs to be in JSP. Contains EL Expression 
 */
function isDisplayChecked(id)
{
	var list=document.getElementsByName("edit");
	console.log(list.length+"\n");
	var checked=false;
	for(var t in list)
	{
		console.log(list[t]+" "+list[t].checked+"\n");
		if(list[t].checked)
		{
			checked=true;
		}
	}
	if(checked)
	{
		id.formAction="${pageContext.request.contextPath}/ProcessSheet_display";
	}
	else
	{
		alert("No Process Selected");
		id.formAction="";
		id.value="search";
		
		
	}	
}
</script>
</head>

<body>
<DIV class="title">
<H1> Process Sheets</H1>
</DIV>
<%@ include file="NavigationDiv.jspf" %>


<FORM action="${pageContext.request.contextPath}/ProcessSheet_search" method="post" onsubmit="return isChecked(this,'Process Sheet')">
<DIV class="results">
<TABLE>
<thead>
	<tr>
		<TH onclick="tableSort(this, 'Date Of Issue');selected(null);">Date Of Issue</TH>
		<TH onclick="tableSort(this, 'Product');selected(null);">Product</TH>
		<TH onclick="tableSort(this, 'Machine Size');selected(null);">Machine Size</TH>
		<TH onclick="tableSort(this, 'Machine No.');selected(null);">Machine No.</TH>
		<TH onclick="tableSort(this, 'Material');selected(null);">Material</TH>
		<TH></TH>
	</tr>
</thead>
<tbody>
<c:forEach items="${processSheets}" var="part">
<TR onclick="selected(this)"><TD><c:out value="${part.dateOfIssue}"/></TD><TD><c:out value="${part.partId}"/></TD><TD><c:out value="${part.machineSize}"/></TD><TD><c:out value="${part.machineNo}"/></TD><TD><c:out value='${myfunc:toString(materials[part.material])}'></c:out></TD><TD class="checkbox"><input type="checkbox" name="edit" value="${part.id}"/></TD></TR>
</c:forEach>
<!-- <TR><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD></TR> -->
<tbody>
</TABLE>
</DIV>
<SPAN class="search">
<input type="text" name="search"/><input type="submit" name="mode" value="search" onclick="addClicked(this)"/>
</SPAN>
<SPAN class="buttons"><input type="submit" name="mode" value="add" onclick="addClicked(this)"/><input type="submit" name="mode" value="edit" /><input type="submit" name="mode" value="display" formaction="${pageContext.request.contextPath}/ProcessSheet_display"/></SPAN>
</FORM>

</body>
</html>