<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="ErrorPage.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>APL System:Parts Search Page</title>

<STYLE>

body
{
	background-color: #7287d4;
}

.error
{
	border-style: solid;
	border-color: red;
}

.title 
{
	text-align: center;
	border-style: solid;
	border-radius: 25px;
	background-color: white;
	width:50%;
	margin-left: auto;
	margin-right: auto;

}
.entrybox
{
	border-style: solid;
	border-radius: 25px;
	background-color: white;
	
	position:fixed;
	top:100px;
	width:60%;
	left:10%;
	padding: 20px;
	
	
}
.entrybox DIV
{
	border-style:solid;
	padding: 10px;
	width:90%;
	height:400px;
	overflow:auto;	
}
TABLE
{
	width:100%;
}
TD 
{ 
	text-align: left;
}
TH
{
	border-bottom-style: solid;
	
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
<DIV  class="entrybox">

<FORM action="JobTemplate_search" method="get">

<SPAN>
<input type="text" name="search"/><input type="submit" name="mode" value="search"/>
</SPAN>
<DIV>

<TABLE>
<TR><TH>Id</TH><TH>Company</TH><TH>Name</TH><TH>Version</TH><TH>Colour</TH><TH>QSS no.</TH><TH></TH></TR>
<c:forEach items="${jobtemplates}" var="part">
<TR><TD>${part.id}</TD><TD>${part.company}</TD><TD>${part.name}</TD><TD>${part.version}</TD><TD>${part.colour}</TD><TD>${part.qss_no}</TD><TD><input type="checkbox" name="edit" value="${part.id}"/></TD></TR>
</c:forEach>
<TR><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD></TR>
</TABLE>
</DIV>
<SPAN><input type="submit" name="mode" value="add"/><input type="submit" name="mode" value="edit" onmouseout="enable(this)" onmouseover="isChecked(this)" /></SPAN>
</FORM>

</DIV>
</body>
</html>