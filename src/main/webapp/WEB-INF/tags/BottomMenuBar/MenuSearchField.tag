<%--
	A html div used as Search menu field in a MenuSearchItem.
	Depends on BootStrap and JQuery.
	To be used as child tag of MenuSearchItem.tag.
	"sessionVariable" is defined as an attribute in scope request in MenuSearchItem.tag.
 
	The value of java.lang.String sessionVariable is the Session attribute name of the 
	org.amc.servlet.action.search.WebFormSearch object whos values should be used to fill 
	out the HTML input fields if it exists.
	 
	@author Adrian McLaughlin
--%>
<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="name" required="true" rtexprvalue="true"%>
<%@ attribute name="displayName" required="true" rtexprvalue="true"%>
<%@ attribute name="type" type="java.lang.String" required="false" %>

<div class="form-group">
	<label for="${name}" class="hidden-xs">${displayName}</label> 			
	<div>
		<c:choose>
			<c:when test="${not empty sessionScope[sessionVariable] and not empty sessionScope[sessionVariable][name]}">
				<input class="form-control" type="${type}" id="${name}" name="${name}" placeholder="${displayName}" value="${sessionScope[sessionVariable][name]}"/>
			</c:when>
			<c:otherwise>
				<input class="form-control" type="${type}" id="${name}" name="${name}" placeholder="${displayName}" />
			</c:otherwise>
		</c:choose>
	</div>
</div>