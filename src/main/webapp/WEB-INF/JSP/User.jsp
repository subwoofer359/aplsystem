<!--  
	@author Adrian Mclaughlin
 	@version 1
-->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ACME Plastics:User page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/General.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/SearchPage.css">
<SCRIPT>
function home()
{
	location="user/Users";
}
</SCRIPT>
<STYLE>

TABLE
{
	position:fixed;
	top:110px;
	background-color:white;
	width:95%;	
	margin-left:2%;
	margin-right:auto;

}

TD
{
	text-align:left;
	font-size:xx-large;
	line-height: 77px;
}

TH
{
	text-align:left;
	font-size:xx-large;
}

input[type="text"]
{
	width:70%;
	font-size:xx-large;
}

</STYLE>
<script src="${pageContext.request.contextPath}/js/InputFocus.js"></script>
</head>
<body>
<DIV class="title">
<H1> Part Description</H1>
</DIV>

<%@ include file="NavigationDiv.jspf" %>
<tags:Navbox href="${pageContext.request.contextPath}/user/Users" value="Search Page" position="220px"></tags:Navbox>
<%-- Display errors if there any --%>
<c:if test="${errors ne null }">
<SCRIPT>alert("${errors}");</SCRIPT>
</c:if>
<%-- The Form to get the values for the new or edited JobTemplate Object--%>

<FORM method='post' action='${pageContext.request.contextPath}/user/User_Save'>
<input type="hidden" name='id' <c:if test='${user ne null}'>value='${user.id}'</c:if>/>
<TABLE>
<%-- To be used in edit mode to store the id of the object being edited --%>
<TR><TD class="description">Name</TD><TD>
	<input type='text' name='fullName' <c:if test='${user ne null}'>value="<c:out value='${user.fullName}' />"</c:if> autofocus="autofocus"/>
</TD></TR>
<TR><TD class="description">Username</TD><TD>
	<input type='text' name='userName' <c:if test='${user ne null}'>value="<c:out value='${user.userName}' />"</c:if> />
</TD></TR>
<TR><TD class="description">Email Address</TD><TD>
	<input type='text' name='emailAddress' <c:if test='${user ne null}'>value="<c:out value='${user.emailAddress}' />"</c:if> />
</TD></TR>
<TR><TD class="description">Activel</TD><TD>
	<input type='checkbox' name='active' <c:if test='${user ne null and user.active eq true}'>checked='checked'</c:if> />
</TD></TR>


</TABLE>

<span class="buttons">
<!-- <button type="button" value="home" onclick="home()">Home</button>  -->
<%-- To tell the servlet which mode the page is submitting in --%>
<c:if test="${mode eq null }">
	<input type='submit'  name="mode" value='Enter'/>
</c:if>
<c:if test="${mode eq 'edit' }">
	<input type='submit'  name="mode" value='Edit'/>
</c:if>
</span>
</FORM>

</body>
</html>