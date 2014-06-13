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
#dimensionEntry 
{
	position: absolute;
	z-index: 100;
	background-color: red;
	height: 312px;
	width: 545px;
	top: 30%;
	
	left: 29%;
	font-size: xx-large;
	padding: 5px;
	visibility:hidden;
}

#dimensionEntry table 
{
	width: 100%;
}

#dimensionEntry input[type="text"] 
{
	font-size: x-large;
}

#dimensionEntry input[type="submit"] 
{
	font-size: xx-large;
	position: absolute;
	right: 6px;
	bottom: 14px;
}
</STYLE>
<script src="${pageContext.request.contextPath}/js/SearchPage.js"></script>
<script src="${pageContext.request.contextPath}/js/TablesSort.js"></script>
<script src="${pageContext.request.contextPath}/js/InputFocus.js"></script>
<script type="text/javascript">
function showEntryDiv(element,divName)
{
	var div=document.getElementById(divName);
	if(div!=null)
	{
		div.style.visibility="visible";
	}
}
</script>
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
	<button onclick="showEntryDiv(this,'dimensionEntry')" form="">add SPC Dimension</button>
	<input type="submit" name="mode" value="de(activate)" formaction="${pageContext.request.contextPath}/spc/SPC/deActivate" />
	<!-- <input type="submit" name="mode" value="remove" formaction="${pageContext.request.contextPath}"/> -->
	</SPAN>
</FORM>

<div id="dimensionEntry">
<form method="post">
<input type="hidden" name="spcPart" value="${spcPart.id}"/>
<table>
<tbody>
<tr><td>Dimension</td><td><input type="text" name="dimension"/></td></tr>
<tr><td>Nominal</td><td><input type="text" name="nominal"/></td></tr>
<tr><td>Upper Limit</td><td><input type="text" name="upperLimit"/></td></tr>
<tr><td>Lower Limit</td><td><input type="text" name="lowerLimit"/></td></tr>
<tr><td>N</td><td><input type="text" name="noOfMeasurements"/></td></tr>
<tr><td>active</td><td><input type="checkbox" name="active"/></td></tr>
</tbody>
</table>
<span>
	<input type="submit" name="mode" value="Enter" formaction="${pageContext.request.contextPath}/spc/SPC/addDimension" />
</span>
</form>

</div>
</body>
</html>
