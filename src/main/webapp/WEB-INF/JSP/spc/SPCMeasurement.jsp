<%--
    @author:Adrian McLaughlin
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/General.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/SearchPage.css">
<title>Statistical Process Control:Part's Dimensions list</title>
<STYLE>


</STYLE>
<script src="${pageContext.request.contextPath}/js/SearchPage.js"></script>
<script src="${pageContext.request.contextPath}/js/TablesSort.js"></script>
</head>
<body>
<DIV class="title">
<H1>SPC:${part.name}&nbsp;${part.version }&nbsp;${part.colour}&nbsp;(${part.part_id })</H1>
</DIV>
<%@ include file="/WEB-INF/JSP/NavigationDiv.jspf" %>
<tags:Navbox href="${pageContext.request.contextPath}/spc/SPCPartsList" value="Search Page" position="220px"></tags:Navbox>
<FORM method="post" onsubmit="return isChecked(this,'part')">
<input type="hidden" name="spcPart" value="${spcPart.id}"/>
<DIV class="results">
<TABLE>
<thead>
<TR>
	<!-- <TH onclick="tableSort(this, 'Id');selected(null);">Id</TH>  -->
	<TH onclick="tableSort(this, 'Dimension');selected(null);">Dimension</TH>
	<TH onclick="tableSort(this, 'Nominal');selected(null);">Nominal</TH>
	<TH onclick="tableSort(this, 'Upper Limit');selected(null);">Upper Limit</TH>
	<TH onclick="tableSort(this, 'Lower Limit');selected(null);">Lower Limit</TH>
	<TH onclick="tableSort(this, 'n');selected(null);">n</TH>
	<TH onclick="tableSort(this, 'active');selected(null);">active</TH>
	<TH></TH>
</TR>
</thead>
<tbody>
<c:forEach items="${dimensions}" var="dimension">
<TR  onclick="selected(this)">
<!--<TD><c:out value="${dimension.id}"/></TD>-->
	<TD><c:out value="${dimension.dimension}"/></TD>
	<TD><c:out value="${dimension.nominal}"/></TD>
	<TD><c:out value="${dimension.upperLimit}"/></TD>
	<TD><c:out value="${dimension.lowerLimit}"/></TD>
	<TD><c:out value="${dimension.noOfMeasurements}"/></TD>
	<TD><c:out value="${dimension.active}"/></TD>
	<TD  class="checkbox"><input type="checkbox" name="edit" value="${dimension.id}"/></TD>
</TR>
</c:forEach>
<!-- <TR><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD></TR> -->
</tbody>
</TABLE>
</DIV>
<SPAN class="buttons">
	<input type="submit" name="mode" value="add SPC Dimension" formaction="${pageContext.request.contextPath}" />
	<input type="submit" name="mode" value="de(activate)" formaction="${pageContext.request.contextPath}/spc/SPC/deActivate" />
	<!-- <input type="submit" name="mode" value="remove" formaction="${pageContext.request.contextPath}"/> -->
	</SPAN>
</FORM>
</body>
</html>
