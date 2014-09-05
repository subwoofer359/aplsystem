<%--
	A bootstrap Menu fixed to the bottom of the html page 
	Depends on Bootstrap and JQuery
	
	@author Adrian McLaughlin
--%>
<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav role="navigation" class="navbar navbar-default navbar-fixed-bottom">
	<div class="container-fluid">
	<div class="navbar-header">
    	<button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
        	<span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
		</button>
        <a href="#" class="navbar-brand">Menu</a>
    </div><!--navbar-header-->
	<div id="navbarCollapse" class="collapse navbar-collapse">
    	<jsp:doBody/>
    </div><!-- nav-collapse -->    
    </div><!--container -->
</nav>