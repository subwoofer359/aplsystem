<%--
	A html div used as MaterialSearch menu field in a MenuSearchItem.
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
<%@ taglib prefix="myfunc" uri="http://adrianmclaughlin.ie/myfunctions"%>
<%@ attribute name="name" required="true" rtexprvalue="true"%>
<%@ attribute name="displayName" required="true" rtexprvalue="true"%>

<div class="form-group">
	<label for="${name}" class="hidden-xs">${displayName}</label> 			
	<div>
		<select class="form-control" id="${name}" name="${name}">
			<c:forEach items='${materials}' var='material'>
				<c:choose>
				<c:when test="${not empty sessionScope[sessionVariable] && material.id eq sessionScope[sessionVariable][name]}">
					<option value='${material.id}' selected='selected'>
				</c:when>
				<c:otherwise>
					<option value='${material.id}'>
				</c:otherwise>
				</c:choose>
				<c:out value="${myfunc:toString(material)}"/>
				</option>
			</c:forEach>
		</select>	
	</div>
</div>