<%--  
	@author Adrian Mclaughlin
 	@version 1.1
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://adrianmclaughlin.ie/myfunctions" prefix="myfunc" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags/BottomMenuBar" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/BootStrapHeader.jsp" %>
<title>ACME Plastics :Process Sheets Search Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/General.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/SearchPage.css">
<style>
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

$(document).ready(function()
{
	showAlertMessage("#alert","${message}"); /* General.js */
			
});
</script>
</head>

<body>
<DIV class="page-title">
<H1> Process Sheets</H1>
</DIV>
<div id="alert" class="alert alert-danger" role="alert"></div>

<FORM action="${pageContext.request.contextPath}/app/ProcessSheet_search" method="post" onsubmit="return isChecked(this,'Process Sheet','alert')">
<DIV class="container results">
<div class="row">
<TABLE class="table col-xs-12">
<thead>
	<tr>
		<TH class="h3" onclick="tableSort(this, 'Date Of Issue');selected(null);">Date Of Issue</TH>
		<TH class="h3" onclick="tableSort(this, 'Product');selected(null);">Product</TH>
		<TH class="h3" onclick="tableSort(this, 'Machine Size');selected(null);">Machine Size</TH>
		<TH class="h3" onclick="tableSort(this, 'Machine No.');selected(null);">Machine No.</TH>
		<TH class="h3" onclick="tableSort(this, 'Material');selected(null);">Material</TH>
		<TH class="checkbox"></TH>
	</tr>
</thead>
<tbody>
<c:forEach items="${processSheets}" var="part">
<TR onclick="selected(this)"><TD><c:out value="${part.dateOfIssue}"/></TD><TD><c:out value="${part.partId}"/></TD><TD><c:out value="${part.machineSize}"/></TD><TD><c:out value="${part.machineNo}"/></TD><TD><c:out value='${myfunc:toString(materials[part.material])}'></c:out></TD><TD class="checkbox"><input type="checkbox" name="edit" value="${part.id}"/></TD></TR>
</c:forEach>
<tbody>
</TABLE>
</div><!-- row -->
</div><!-- container -->
<tags:BottomMenuBar>
	<tags:NavLinks>
		<tags:NavLink name=" Main" link="${pageContext.request.contextPath}/app/APLSystemServlet" glyphicon="glyphicon-home"></tags:NavLink>
	</tags:NavLinks>
	<tags:ButtonsMenu>
		<button class="btn btn-block" id="add" type="submit" name="mode" value="add" onclick="addClicked(this)"><span class="glyphicon glyphicon-plus pull-left"></span>Add</button>
		<button class="btn btn-block" id="edit" type="submit" name="mode" value="edit" ><span class="glyphicon glyphicon-pencil pull-left"></span>Edit</button>
		<button class="btn btn-block" id="display" type="submit" name="mode" value="display" formaction="${pageContext.request.contextPath}/app/ProcessSheet_display"><span class="glyphicon glyphicon-info-sign pull-left"></span>Display Process</button>
	</tags:ButtonsMenu>
	<tags:MenuSearchItem sessionVariable="PROCESSSEARCH" partId="Part Name" machineNo="Machine No." material="Material" masterBatchNo="Masterbatch No." signedOffBy="Signed Off By">
		<tags:MenuSearchField name="startDate" displayName="Date from" type="date"/> 
		<tags:MenuSearchField name="endDate" displayName="Date to" type="date"/>
	</tags:MenuSearchItem>
	<tags:UserListItem/>
</tags:BottomMenuBar>

</FORM>
<%@ include file="/BootStrapFooter.jsp" %>
</body>
</html>