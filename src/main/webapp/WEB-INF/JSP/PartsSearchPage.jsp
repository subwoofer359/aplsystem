<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="ErrorPage.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/General.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/SearchPage.css">
<title>ACME Plastics:Parts Search Page</title>
<STYLE>


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
<H1> Part Inventory</H1>
</DIV>
<%@ include file="NavigationDiv.jspf" %>

<FORM action="${pageContext.request.contextPath}/Part_search" method="post">
<DIV class="results">
<TABLE>
<TR><TH>Id</TH><TH>Company</TH><TH>Name</TH><TH>Version</TH><TH>Colour</TH><TH>QSS no.</TH><TH></TH></TR>
<c:forEach items="${parts}" var="part">
<TR><TD>${part.id}</TD><TD>${part.company}</TD><TD>${part.name}</TD><TD>${part.version}</TD><TD>${part.colour}</TD><TD>${part.qss_no}</TD><TD><input type="checkbox" name="edit" value="${part.id}"/></TD></TR>
</c:forEach>
<TR><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD></TR>
</TABLE>
</DIV>
<SPAN class="search">
<input type="text" name="search"/><input type="submit" name="mode" value="search"/>
</SPAN>
<SPAN class="buttons"><input type="submit" name="mode" value="add"/><input type="submit" name="mode" value="edit" onmouseout="enable(this)" onmouseover="isChecked(this)" /></SPAN>
</FORM>
</body>
</html>