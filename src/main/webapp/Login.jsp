<!DOCTYPE html>
<!--  
	@author Adrian Mclaughlin
 	@version 1
-->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ACME Plastics Systems' Login Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/General.css">

<style>
.login
{
	position:fixed;
	top:200px;
	font-size:xx-large;
	width: 75%;
	left: 50%;
	margin: 0 0 0 -37.5%;
}

input
{
	font-size:inherit;
}

.buttons
{
	position:fixed;
	right:4px;
	bottom:4px;
}
.buttons input,button
{
	font-size:xx-large;
}

</style>
</head>
<body>
<DIV class="title"><H1>Login Page</H1></DIV>

<DIV class="login">
<FORM method="post" action="j_security_check" autocomplete="on">
<TABLE>
<TR><TD>Username:</TD><TD><INPUT type="text" name="j_username"/></TD></TR>
<TR><TD>Password:</TD><TD><INPUT type="password" name="j_password"/></TD></TR>
</TABLE>
<span class="buttons"><INPUT type="submit" value="login"/></span>
</FORM>
</DIV>

</body>
</html>