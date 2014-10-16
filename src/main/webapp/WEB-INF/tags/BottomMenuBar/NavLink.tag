<%--
	A link for a BootStrap menu
	Depends on BootStrap and JQuery.
	To be used as child tag of NavLinks.tag.
	@author Adrian McLaughlin 
 --%>
<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="link" type="java.lang.String" rtexprvalue="true" required="true"%>
<%@ attribute name="name" type="java.lang.String" rtexprvalue="true" required="true"%>
<%@ attribute name="glyphicon" type="java.lang.String" rtexprvalue="true" required="false"%>
<%@ attribute name="active" type="java.lang.Boolean" required="false" %>
<c:choose>
  <c:when test="${active}">
    <li class="active">
  </c:when>
  <c:otherwise>
    <li>
  </c:otherwise>
</c:choose>
<a href="${link}">
  <c:if test="${not empty glyphicon}">
    <span class="glyphicon ${glyphicon}"></span>
  </c:if>
${name}
</a>
