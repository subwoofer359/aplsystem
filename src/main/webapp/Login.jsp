<!DOCTYPE html>
<%@page session="false"  %>
<%-- 
	@author Adrian Mclaughlin
 	@version 1.1
--%>
<html lang="en">
<head>
<%@ include file="./BootStrapHeader.jsp" %>

<title>ACME Plastics Systems' Login Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/General.css">
<style>
</style>
</head>


<body>
<div class="title"><H1>Login Page</H1></div>

<div class="login">
<form method="post" action="j_security_check" autocomplete="on">
<table>
<tbody>
	<TR><TD>Username:</TD><TD><INPUT type="text" name="j_username"/></TD></TR>
	<TR><TD>Password:</TD><TD><INPUT type="password" name="j_password"/></TD></TR>
</tbody>
</table>
<span class="buttons"><INPUT type="submit" value="login"/></span>
</form>
</div>

<%@ include file="./BootStrapFooter.jsp" %>
</body>
</html>