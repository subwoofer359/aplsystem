<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="ErrorPage.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ACME Plastics :Material Search Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/theme.css">
<STYLE>
TH
{
	text-align: left;
}
</STYLE>
</head>
<SCRIPT type="text/javascript">
function isChecked(id)
{
	var list=document.getElementsByName("edit");
	console.log(list.length+"\n");
	var checked=false;
	for(var t in list)
	{
		console.log(list[t]+" "+list.checked+"\n");
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

function enable(id)
{
	id.value="edit";	
}
</SCRIPT>
<body>
<DIV class="title">
<H1>Material</H1>
</DIV>
<%@ include file="NavigationDiv.jspf" %>

<DIV  class="entrybox">

<FORM action="${pageContext.request.contextPath}/Material_search" method="post">

<SPAN>
<input type="text" name="search"/><input type="submit" name="mode" value="search"/>
</SPAN>
<DIV>
<TABLE>
<TR><TH>Company</TH><TH>Name</TH><TH>Type</TH><TH></TH></TR>
<c:forEach items="${materials}" var="material">
<TR><TD>${material.value.company}</TD><TD>${material.value.name}</TD><TD>${material.value.type}</TD><TD><input type="checkbox" name="edit" value="${material.value.id}"/></TD></TR>
</c:forEach>
<TR><TD></TD><TD></TD><TD></TD></TR>
</TABLE>
</DIV>
<SPAN><input type="submit" name="mode" value="add"/><input type="submit" name="mode" value="edit" onmouseout="enable(this)" onmouseover="isChecked(this)" /></SPAN>
</FORM>

</DIV>

</body>
</html>