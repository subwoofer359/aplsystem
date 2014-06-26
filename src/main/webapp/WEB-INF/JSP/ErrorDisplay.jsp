<%--  
	@author Adrian Mclaughlin
 	@version 1
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<script>

/* Display Error Messages */
window.onload=function()
{
	 var errors=false;
	 var message=false;
	 <c:if test="${not empty errors}">
	 errors=true;
	 </c:if>
	 <c:if test="${not empty message}">
	 message=true;
	 </c:if>
	 if(errors||message)
	 {
		 $("#errorMessage").show();
	 }
};

function hide(element)
{
	element.style.display="none";
}
</script>
<div id="errorMessage" class="inputErrorMessage" onclick="hide(this)">
<h1>Input error</h1>
<c:if test="${not empty errors}">
<table>
<c:forEach items="${errors}" var="error">
<tr><td>${error.key}:</td><td>${error.value}</td></tr>
</c:forEach>
</table>
</c:if>

<c:if test="${not empty message}">
${message}
</c:if>
</div>