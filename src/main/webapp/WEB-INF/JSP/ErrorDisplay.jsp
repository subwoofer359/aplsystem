<%--  
	@author Adrian Mclaughlin
 	@version 1
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<script>
function hide(element)
{
	element.style.visibility="hidden";
}
</script>
<div id="errorMessage" class="errorMessage" onclick="hide(this)">
<h1>Input error</h1>
<c:if test="${not empty errors}">
${errors}
</c:if>

<c:if test="${not empty message}">
${message}
</c:if>
</div>