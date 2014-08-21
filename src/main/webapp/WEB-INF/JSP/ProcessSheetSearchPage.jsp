<%--  
	@author Adrian Mclaughlin
 	@version 1
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
.search
{
	background-color: rgba(100,100,100,0.8);
}
</style>
<script src="${pageContext.request.contextPath}/js/SearchPage.js"></script>
<script src="${pageContext.request.contextPath}/js/TablesSort.js"></script>
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
		id.formAction="${pageContext.request.contextPath}/app/ProcessSheet_display";
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


<FORM action="${pageContext.request.contextPath}/app/ProcessSheet_search" method="post" onsubmit="return isChecked(this,'Process Sheet')">
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
<table class="search">
<tbody>
<tr><td>Part Name:</td><td><input type="text" name="partId" <c:if test="${not empty PROCESSSEARCH and not empty PROCESSSEARCH.partId}">value="${PROCESSSEARCH.partId}"</c:if>/></td><td></td></tr>
<tr><td>Machine No.:</td><td><input type="text" name="machineNo" <c:if test="${not empty PROCESSSEARCH and not empty PROCESSSEARCH.machineNo}">value="${PROCESSSEARCH.machineNo}"</c:if>/></td><td></td></tr>
<tr><td>Material:</td><td><input type="text" name="material" <c:if test="${not empty PROCESSSEARCH and not empty PROCESSSEARCH.material}">value="${PROCESSSEARCH.material}"</c:if>/></td><td></td></tr>
<tr><td>Masterbatch:</td><td><input type="text" name="masterbatchNo" <c:if test="${not empty PROCESSSEARCH and not empty PROCESSSEARCH.masterBatchNo}">value="${PROCESSSEARCH.masterBatchNo}"</c:if>/></td><td></td></tr>
<tr><td>Date from:</td><td><input type="date" name="startDate" <c:if test="${not empty PROCESSSEARCH and not empty PROCESSSEARCH.startDate}">value="${PROCESSSEARCH.startDate}"</c:if>/></td><td></td></tr>
<tr><td>Date to:</td><td><input type="date" name="endDate" <c:if test="${not empty PROCESSSEARCH and not empty PROCESSSEARCH.endDate}">value="${PROCESSSEARCH.endDate}"</c:if>/></td><td></td></tr>
<tr><td>Signed Off By:</td><td><input type="text" name="signedOffBy" <c:if test="${not empty PROCESSSEARCH and not empty PROCESSSEARCH.signedOffBy}">value="${PROCESSSEARCH.signedOffBy}"</c:if>/></td><td><input type="submit" name="mode" value="search" onclick="addClicked(this)"/></td></tr>
</tbody>
</table>
<SPAN class="buttons"><input type="submit" name="mode" value="add" onclick="addClicked(this)"/><input type="submit" name="mode" value="edit" /><input type="submit" name="mode" value="display" formaction="${pageContext.request.contextPath}/app/ProcessSheet_display"/></SPAN>
</FORM>

</body>
</html>