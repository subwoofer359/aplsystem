<%--
	A html list used as a menu around buttons in bootstrap menubar.
	Depends on Bootstrap and JQuery.
	To be used as child tag of BottomMenuBar.tag
	
	@author Adrian McLaughlin
--%>
<%@ tag language="java" pageEncoding="UTF-8"%>
 <ul class="nav navbar-nav">
	 <li class="dropdown">
		<a data-toggle="dropdown" class="dropdown-toggle" href="#"><span class="glyphicon glyphicon-th-list"></span> Actions<b class="caret"></b></a>
		<ul role="menu" class="dropdown-menu">
			<li>
	   			<jsp:doBody/>
			</li>
		</ul>
	</li>
</ul>