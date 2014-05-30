<%--  
	@author Adrian Mclaughlin
 	@version 1
 --%>
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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/EntryPage.css">
<SCRIPT>
function home()
{
	location="user/Users";
}
</SCRIPT>
<STYLE>
.roles
{
	position: absolute;
	border-style: dashed;
	width: 400px;
	background-color: cornflowerblue;
	padding: 15px;
	font-size: x-large;
	top: 599px;
	left: 25px;
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

<FORM method="post" action='${pageContext.request.contextPath}/user/User_Save' autocomplete="off">
<div class="entry">
<input type="hidden" name='id' <c:if test='${user ne null}'>value='${user.id}'</c:if>/>
<TABLE>
<%-- To be used in edit mode to store the id of the object being edited --%>
<TR><TD class="description">Name</TD><TD>
	<input type='text' name='fullName' <c:if test='${user ne null}'>value="<c:out value='${user.fullName}' />"</c:if> autofocus="autofocus"/>
</TD></TR>
<TR><TD class="description">Username</TD><TD><input type='text'  name='userName'  value="<c:if test='${user ne null}'> <c:out value='${user.userName}' /></c:if>" <c:if test="${mode eq 'edit'}">readonly='readonly'</c:if>  autocomplete='off'/>
</TD></TR>
<TR><TD class="description">Password</TD><TD>
	<input type='password' name='password' <c:if test='${user ne null}'> value="<c:forEach items='${user.password}' var='letter'>${letter}</c:forEach>" </c:if> autocomplete='off' />
</TD></TR>
<TR><TD class="description">Confirm Password</TD><TD>
	<input type='password' name='confirm_password' value='' autocomplete='off'/>
</TD></TR>
<TR><TD class="description">Email Address</TD><TD>
	<input type='text' name='emailAddress' <c:if test='${user ne null}'>value="<c:out value='${user.emailAddress}' />"</c:if> />
</TD></TR>
<TR><TD class="description">Active</TD><TD>
	<input type='checkbox' name='active' <c:if test='${user ne null and user.active eq true}'>checked='checked'</c:if> />
</TD></TR>

<span class="buttons">
<%-- To tell the servlet which mode the page is submitting in --%>
<c:if test="${not(mode eq 'edit') }">
	<input type='submit'  name="mode" value='enter'/>
</c:if>
<c:if test="${mode eq 'edit' }">
	<input type='submit'  name="mode" value='edit'/>
</c:if>
</span>
</TABLE>
</div>


<div class="roles">
	<table>
		<c:forEach items="${SECURITY_ROLES}" var="roleName">
			<tr><td><c:out value="${roleName}"></c:out></td><td><input name="role" value="${roleName}" type="checkbox"
			<c:forEach items="${user.roles}" var="userRole"> 
			<c:if test="${roleName == userRole.roleName}">checked='checked'</c:if>
			</c:forEach> 
			/></td></tr>
		</c:forEach>
	</table>
</div>
</FORM>
</body>
</html>