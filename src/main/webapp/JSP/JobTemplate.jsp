<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="ErrorPage.jsp" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>APL System:Part Description Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/theme.css">
<STYLE>

body
{
	background-color: #7287d4;
}

.error
{
	border-style: solid;
	border-color: red;
}

.title 
{
	text-align: center;
	border-style: solid;
	border-radius: 25px;
	background-color: white;
	width:50%;
	margin-left: auto;
	margin-right: auto;

}
.entrybox
{
	border-style: solid;
	border-radius: 25px;
	background-color: white;
	
	position:fixed;
	top:200px;
	left:10%;
	width:30%;
}
</STYLE>

</head>
<body>
<DIV class="title">
<H1> Part Description</H1>
</DIV>
<%-- Display errors if there any --%>
<c:if test="${errors ne null }">
<DIV class="error">
<c:forEach items="${errors}" var="error"> 
${error}<br/>
</c:forEach>
</DIV>
</c:if>
<%-- The Form to get the values for the new or edited JobTemplate Object--%>
<DIV class="entrybox">
<FORM method='get' action='./JobTemplate_save'>
<input type="hidden" name='id' <c:if test='${form ne null}'>value='${form.id}'</c:if>/>
<TABLE>
<%-- To be used in edit mode to store the id of the object being edited --%>
<TR><TD>Name</TD><TD>
	<input type='text' name='name' <c:if test='${form ne null}'>value='${form.name}'</c:if> />
</TD></TR>
<TR><TD>Company</TD><TD>
	<input type='text' name='company' <c:if test='${form ne null}'>value='${form.company}'</c:if> />
</TD></TR>
<TR><TD>Product No.</TD><TD>
	<input type='text' name='part_id' <c:if test='${form ne null}'>value='${form.part_id}'</c:if> />
</TD></TR>
<TR><TD>Version</TD><TD>
	<input type='text' name='version' <c:if test='${form ne null}'>value='${form.version}'</c:if> />
</TD></TR>
<TR><TD>Revision</TD><TD>
	<input type='text' name='revision' <c:if test='${form ne null}'>value='${form.revision}'</c:if> />
</TD></TR>
<TR><TD>Colour</TD><TD>
	<input type='text' name='colour' <c:if test='${form ne null}'>value='${form.colour}'</c:if> />
</TD></TR>
<TR><TD>External</TD><TD>
	<input type='checkbox' name='external' <c:if test='${form ne null and form.external eq true}'>checked='checked'</c:if> />
</TD></TR>
<TR><TD>QSS No.</TD><TD>
	<input type='text' name='qss_no' <c:if test='${form ne null}'>value='${form.qss_no}'</c:if> />
</TD></TR>
<TR><TD>
<SCRIPT>
function home()
{
	location="JobTemplate_search";
}
</SCRIPT>
<button type="button" value="home" onclick="home()">Home</button>
</TD><TD>
<%-- To tell the servlet which mode the page is submitting in --%>
<c:if test="${mode eq null }">
	<input type='submit'  name="mode" value='Enter'/>
</c:if>
<c:if test="${mode eq 'edit' }">
	<input type='submit'  name="mode" value='Edit'/>
</c:if>
</TD></TR>
</TABLE>

</FORM>


</DIV>
<DIV class="result">
<%-- To let the user know the part has been saved --%>
<c:if test="${result ne null }">
Part:${result}<br/>
</c:if>
</DIV>

</body>
</html>