<!DOCTYPE html>
<%@page session="false"  %>
<%-- 
	@author Adrian Mclaughlin
 	@version 1.1
--%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/General.css">

<title>ACME Plastics Systems' Login Page</title>

<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
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

 <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
 <!-- Include all compiled plugins (below), or include individual files as needed -->
 <script src="js/bootstrap.min.js"></script>
</body>
</html>