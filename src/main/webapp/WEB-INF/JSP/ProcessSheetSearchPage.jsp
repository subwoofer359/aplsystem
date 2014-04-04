<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="ErrorPage.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ACME Plastics :Process Sheets Search Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/General.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/SearchPage.css">
<style>
.checkbox
{
	visibility:hidden;
	width:0;
}
</style>

</head>
<SCRIPT type="text/javascript">

function selected(element)
{
	var elementsArray=document.getElementsByTagName("input");
	for(var i=0;i<elementsArray.length;i++)
	{
		if(elementsArray[i].type=="checkbox")
		{
			elementsArray[i].checked=false;
			//Set parent <TR> element background to original colour
			elementsArray[i].parentNode.parentNode.style.backgroundColor="";  
		}
	}
	
	console.log(element);
	var checkbox=element.getElementsByTagName("input");
	if(checkbox != null || checkbox.length>0)
	{
		console.log(checkbox[0]);
		checkbox[0].checked=true;
		element.style.backgroundColor="red";
	}
	else
	{
		console.log("Checkbox element not found");
	}
	
}

function isChecked(id)
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
	if(!checked)
	{
		id.value="add";
	}
	else
	{
		id.value="edit";
	}	
}

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

function enable(id)
{
	id.value="edit";	
}
</SCRIPT>
<body>
<DIV class="title">
<H1> Process Sheets</H1>
</DIV>
<%@ include file="NavigationDiv.jspf" %>


<FORM action="${pageContext.request.contextPath}/ProcessSheet_search" method="post">
<DIV class="results">
<TABLE>
<TR><TH>Date Of Issue</TH><TH>Product</TH><TH>machineSize</TH><TH>Machine No.</TH><TH>Material</TH><TH></TH></TR>
<c:forEach items="${processSheets}" var="part">
<TR onclick="selected(this)"><TD>${part.dateOfIssue}</TD><TD>${part.partId}</TD><TD>${part.machineSize}</TD><TD>${part.machineNo}</TD><TD><c:out value='${materials[part.material]}'></c:out></TD><TD class="checkbox"><input type="checkbox" name="edit" value="${part.id}"/></TD></TR>
</c:forEach>
<TR><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD></TR>
</TABLE>
</DIV>
<SPAN class="search">
<input type="text" name="search"/><input type="submit" name="mode" value="search"/>
</SPAN>
<SPAN class="buttons"><input type="submit" name="mode" value="add"/><input type="submit" name="mode" value="edit" onmouseout="enable(this)" onmouseover="isChecked(this)" /><input type="submit" name="mode" value="display" onclick="isDisplayChecked(this)" formaction="${pageContext.request.contextPath}/ProcessSheet_display"/></SPAN>
</FORM>

</body>
</html>