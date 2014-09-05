<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="user" fragment="true" required="true" %>
<%@ attribute name="buttons" fragment="true" required="true" %>
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
            <ul class="nav navbar-nav">
                <li class="active"><a href="${pageContext.request.contextPath}/app/APLSystemServlet">Home</a></li>
				<li class="dropdown">
					<a data-toggle="dropdown" class="dropdown-toggle" href="#">Actions<b class="caret"></b></a>
					<ul role="menu" class="dropdown-menu">
					<li>
	            		<jsp:invoke fragment="buttons"></jsp:invoke>
					</li>
					</ul>
				</li>
	   		</ul>
	   		<jsp:doBody/>
            <jsp:invoke fragment="user"></jsp:invoke>
        </div><!-- nav-collapse -->
        
    </div><!--container -->
</nav>