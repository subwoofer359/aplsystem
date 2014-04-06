<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="ErrorPage.jsp" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ACME Plastics: Material Sheet</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/General.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/SearchPage.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/EntryPage.css">
<style type="text/css">
TD
{
	font-size: x-large;
	line-height: 50px;
}
input[type="text"]
{
	width:70%;
	font-size:x-large;
}
</style>

</head>

<body>
<div class="title"><h1>Material</h1></div>
<%@ include file="NavigationDiv.jspf" %>
<%-- Display errors if there any --%>
<c:if test="${errors ne null }">
<SCRIPT>alert("${errors}");</SCRIPT>
</c:if>
<!-- Send info to JSP to be put into a bean todo integrate code into this page -->
<FORM method="post" action="${pageContext.request.contextPath}/Processing/MaterialBean"> 
<%-- To be used in edit mode to store the id of the object being edited --%>
<input type="hidden" name='id' <c:if test='${form ne null}'>value='${form.id}'</c:if>/>


<TABLE>
<TR><TD class="description">Company:</TD><TD><input type="text" name="company"  value='${form.company}' autofocus="autofocus"/></TD></TR>
<TR><TD class="description">Name:</TD><TD><input type="text" name="name" value='${form.name}' /></TD></TR>
<TR><TD class="description">Type:</TD><TD><input type="text" name="type" value='${form.type}'/></TD></TR>
<TR><TD class="description">Density:</TD><TD><input type="text" name="density" value='${form.density}'/></TD></TR>
<TR><TD class="description">Linear Expansion:</TD><TD><input type="text" name="linear_expansion" value='${form.linear_expansion}'/></TD></TR>
<TR><TD class="description">Water Absorption:</TD><TD><input type="text" name="water_absorption"  value='${form.water_absorption}'/></TD></TR>
<TR><TD class="description">Material Drying:</TD><TD><input type="text" name="material_drying"  value='${form.material_drying}'/></TD></TR>
<TR><TD class="description">Lower Melting temperature:</TD><TD><input type="text" name="melting_temp_lower"  value='${form.melting_temp_lower}'/></TD></TR>
<TR><TD class="description">Upper Melting temperature:</TD><TD><input type="text" name="melting_temp_upper"  value='${form.melting_temp_upper}'/></TD></TR>
<TR><TD class="description">Shrinkage:</TD><TD><input type="text" name="mould_shrinkage"  value='${form.mould_shrinkage}'/></TD></TR>
<TR><TD class="description">Lower Mould temperature:</TD><TD><input type="text" name="mould_temp_low"  value='${form.mould_temp_low}'/></TD></TR>
<TR><TD class="description">Upper Mould temperature:</TD><TD><input type="text" name="mould_temp_upper"  value='${form.mould_temp_upper}'/></TD></TR>
</TABLE>
<span class="buttons">
<%-- To tell the servlet which mode the page is submitting in --%>
<c:if test="${mode eq null }">
	<input type='submit'  name="mode" value='Enter'/>
</c:if>
<c:if test="${mode eq 'edit' }">
	<input type='submit'  name="mode" value='edit'/>
</c:if>
</span>
</FORM>

</body>
</html>