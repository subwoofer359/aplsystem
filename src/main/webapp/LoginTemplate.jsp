<%@page session="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- 
	@author Adrian Mclaughlin
 	@version 1.1
 	
 	To be included in Login(Error).jsp
--%>

<%@ include file="./BootStrapHeader.jsp" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/General.css">
<title>ACME Plastics Systems' Login Page</title>

<style>
.login{
	clear:both;
	padding-top: 50px;
}

</style>
</head>


<body>
<div class="page-title"><H1>ACME Plastics Systems' Login</H1></div>

<div class="container login">
<div class="row">
<form class="form-horizontal" method="post" action="j_security_check" autocomplete="on">
<div class="form-group">
	<label for="username" class="control-label col-sm-2 hidden-xs">Username</label>
	<div class="col-xs-12 col-sm-5">
		<INPUT id="username" class="form-control" type="text" name="j_username" placeholder="Username" required="required"/>
	</div>
</div>
<div class="form-group">
	<label for="password" class="control-label col-sm-2 hidden-xs" >Password</label>
	<div class="col-xs-12 col-sm-5">
		<INPUT id="password" class="form-control" type="password" name="j_password" placeholder="Password" required="required"/>
	</div>
</div>
<div class="form-group">
	<div class="col-sm-offset-2 col-xs-10">
		<INPUT class="btn btn-primary" type="submit" value="login"/>
	</div>
</div>
</form>
<div class="panel panel-danger col-sm-offset-2 col-sm-5 col-xs-12 error" style="padding:0;display:none;">
	<div class="panel-heading">
		<h3 class="panel-title">Login failed: <small> Username or Password is incorrect</small></h3>
	</div>
</div>
</div><!-- class row -->
</div>
<%@ include file="./BootStrapFooter.jsp" %>

