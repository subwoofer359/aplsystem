<%--
	A html list used as a menu item in a bootstrap menu.
	Depends on Bootstrap and JQuery.
	To be used as child tag of BottomMenuBar.tag
	
	@author Adrian McLaughlin
--%>
<%@ tag language="java" pageEncoding="UTF-8"  trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="userName" required="true" type="java.lang.String" rtexprvalue="true"%>
<ul class="nav navbar-nav navbar-right">
    <li>
       	<a href="${pageContext.request.contextPath}/app/UserInfo">User:<c:out value='${userName}'/></a>
    </li>
</ul>