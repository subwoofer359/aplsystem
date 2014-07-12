<%--
    @author:Adrian McLaughlin
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/General.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/SearchPage.css">
<title>Statistical Process Control:Parts list</title>
<STYLE>


</STYLE>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/SearchPage.js"></script>
<script src="${pageContext.request.contextPath}/js/TablesSort.js"></script>


</head>
<body>
<DIV class="title">
<H1>Statistical Process Control:Parts list</H1>
</DIV>
<%@ include file="/WEB-INF/JSP/NavigationDiv.jspf" %>

<FORM method="post" onsubmit="return isChecked(this,'part')">
<DIV class="results">
<TABLE>
<thead>
<TR>
	<TH onclick="tableSort(this, 'Id');selected(null);">Id</TH>
	<TH onclick="tableSort(this, 'Company');selected(null);">Company</TH>
	<TH onclick="tableSort(this, 'Name');selected(null);">Name</TH>
	<TH onclick="tableSort(this, 'Version');selected(null);">Version</TH>
	<TH onclick="tableSort(this, 'Colour');selected(null);">Colour</TH>
	<TH onclick="tableSort(this, 'QSS no.');selected(null);">QSS no.</TH>
	<TH></TH>
</TR>
</thead>
<tbody>
<c:forEach items="${parts}" var="spc">
<TR  onclick="selected(this)">
	<TD><c:out value="${spc.part.id}"/></TD>
	<TD><c:out value="${spc.part.company}"/></TD>
	<TD><c:out value="${spc.part.name}"/></TD>
	<TD><c:out value="${spc.part.version}"/></TD>
	<TD><c:out value="${spc.part.colour}"/></TD>
	<TD><c:out value="${spc.part.qss_no}"/></TD>
	<TD  class="checkbox"><input type="checkbox" name="edit" value="${spc.id}"/></TD>
</TR>
</c:forEach>
<!-- <TR><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD></TR> -->
</tbody>
</TABLE>
</DIV>
<SPAN class="search">
<input type="text" name="search"/><input type="submit" name="mode" value="search" onclick="addClicked(this)"/>
</SPAN>
<SPAN class="buttons">
    <input type="submit" name="mode" value="SPC Dimensions" formaction="${pageContext.request.contextPath}/app/spc/Dimensions" />
	<input type="submit" name="mode" value="add SPC Data" formaction="${pageContext.request.contextPath}/app/spc/SPC/addData" onclick="addClicked(this)"/>
	<input type="submit" name="mode" value="view SPC Data" formaction="${pageContext.request.contextPath}/app/spc/SPC/editData"/>
	<input type="submit" name="mode" value="remove" formaction="${pageContext.request.contextPath}/app/spc/SPC/removePart"/>
	</SPAN>
</FORM>

<jsp:include page="/WEB-INF/JSP/ErrorDisplay.jsp">
	<jsp:param name="errors" value="${errors}"/>
	<jsp:param name="message" value="${message}"/>
</jsp:include>
</body>
</html>