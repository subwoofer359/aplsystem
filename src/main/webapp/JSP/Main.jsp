<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ACME Plastics Systems' Main Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/theme.css">
<STYLE>
BODY
{
	background-color: #7287d4;
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

DIV.icon 
{
	border-style:solid;
	border-color:black;
	border-width:1px;
	border-radius:25px;
	width:110px;
	height:110px;
	background-color: white;
	
}


DIV#partsearch
{
	position:fixed;
	top:200px;
	left:40px;
}

DIV#material
{
	position:fixed;
	top:400px;
	left:40px;
}

DIV#problemdb
{
	position:fixed;
	top:200px;
	left:340px;
}

DIV#process
{
	position:fixed;
	top:200px;
	left:640px;
}

.item
{
	position: relative;
	top: 30%;
	padding: 4px;
	text-align: center;
	margin-right: auto;
	margin-left: auto;
	
}


</STYLE>
</head>
<body>

<DIV class="title">
<H1>ACME Plastics System</H1>
</DIV>

<DIV class="loginbox">
	<span><a href="JSP/UserInfo.jsp"><c:out value='${pageContext.request.remoteUser}'/></a></span>
     
</DIV>

<DIV id="partsearch" class="icon">

<DIV class='item'> <a href="${pageContext.request.contextPath}/Part_search">Part Search</a></DIV>

</DIV>

<DIV id="problemdb" class="icon">

<DIV class="item">Problem Database</DIV>

</DIV>

<DIV id="process" class="icon">

<DIV class="item"><a href="${pageContext.request.contextPath}/ProcessSheet_search">Process Sheets</a></DIV>

</DIV>
<DIV id="material" class="icon">

<DIV class="item"><a href="${pageContext.request.contextPath}/Material_search">Material list</a></DIV>

</DIV>


</body>

</body>
</html>