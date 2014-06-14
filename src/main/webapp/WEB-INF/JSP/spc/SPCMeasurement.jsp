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

#dimensionEntry .buttons
{
	position: absolute;
	right: 10px;
	bottom: 10px;
}

#dimensionEntry input[type="submit"] 
{
	font-size: xx-large;
}

.hiddenColumn
{
	display:none;
}
</STYLE>
<script src="${pageContext.request.contextPath}/js/SearchPage.js"></script>
<script src="${pageContext.request.contextPath}/js/TablesSort.js"></script>
<script src="${pageContext.request.contextPath}/js/InputFocus.js"></script>
<script type="text/javascript">
/*
 * Makes the element visible
 */
function showEntryDiv(element,divName)
{
	var div=document.getElementById(divName);
	if(div!=null)
	{
		div.style.visibility="visible";
	}
}

/*
 * Makes the element hidden
 */
function hideEntryDiv(element,divName)
{
	var div=document.getElementById(divName);
	if(div!=null)
	{
		div.style.visibility="hidden";
	}
}

/*
 * Clears the value attribute of element
 */

function clearValueOfElementId(idOfElement)
{
	var element=document.getElementById(idOfElement);
	if(element!=null)
	{
		element.value="";
	}	
}

/*
 * Copies values from the html table to the <div> used for entry and edit
 */
function populateForm(divName)
{
	var table=document.getElementById("dimensionsList");
	var rows=table.getElementsByTagName("input");
	var rowSelected;
	var check=false;
	for(var i=0,rlen=rows.length;i<rlen;i++)
	{
			if(rows[i].getAttribute("name")=="edit")
			{
				if(rows[i].checked==true)
				{
					rowSelected=rows[i].parentNode.parentNode;
					check=true;
					break;				
				}
			}
	}
	if(check)
	{
		var div=document.getElementById(divName);
		var values=rowSelected.getElementsByTagName("td");
		if(values!=null && div!=null)
		{
			var inputs=div.getElementsByTagName("input");
			for(var i=1,j=0;i<inputs.length;i++,j++)
			{
				var input=inputs[i];
				if(input.type=="checkbox")
				{
					input.checked=values[j].childNodes[0].data;	
				}
				if(input.type=="submit")
				{
					j--;	
				}
				else
				{
					input.value=values[j].childNodes[0].data;	
				}
					
			}
		}	
	}
}
/*
 * Displays error messages if they exist when the page loads
 */
window.onload=function()
{
	var message="${message}";
	if(message!=null && message!="")
	{
		alert(message);
	}
};
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
<TABLE id="dimensionsList">
<thead>
<TR>
	<TH class="hiddenColumn">Id</TH>  
	<TH onclick="tableSort(this, 'Dimension');selected(null);">Dimension</TH>
	<TH onclick="tableSort(this, 'Nominal');selected(null);">Nominal</TH>
	<TH onclick="tableSort(this, 'Upper Limit');selected(null);">Upper Limit</TH>
	<TH onclick="tableSort(this, 'Lower Limit');selected(null);">Lower Limit</TH>
	<TH onclick="tableSort(this, 'n');selected(null);">n</TH>
	<TH onclick="tableSort(this, 'active');selected(null);">active</TH>
	<TH></TH>
	<TH class="hiddenColumn"></TH>
</TR>
</thead>
<tbody>
<c:forEach items="${dimensions}" var="dimension">
<TR  onclick="selected(this)">
	<TD class="hiddenColumn"><c:out value="${dimension.id}"/></TD>
	<TD><c:out value="${dimension.dimension}"/></TD>
	<TD><c:out value="${dimension.nominal}"/></TD>
	<TD><c:out value="${dimension.upperLimit}"/></TD>
	<TD><c:out value="${dimension.lowerLimit}"/></TD>
	<TD><c:out value="${dimension.noOfMeasurements}"/></TD>
	<TD><c:out value="${dimension.active}"/></TD>
	<td class="hiddenColumn">${dimension.tableId}</td>
	<TD  class="checkbox" class="hiddenColumn"><input type="checkbox" name="edit" value="${dimension.id}"/></TD>
	
</TR>
</c:forEach>
<!-- <TR><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD></TR> -->
</tbody>
</TABLE>
</DIV>
<SPAN class="buttons">
	<button onclick="populateForm('dimensionEntry');showEntryDiv(this,'dimensionEntry')" form="">add SPC Dimension</button>
	<input type="submit" name="mode" value="de(activate)" formaction="${pageContext.request.contextPath}/spc/SPC/deActivate" />
	<input type="submit" name="mode" value="remove" formaction="${pageContext.request.contextPath}/spc/SPC/deleteDimension" />
	</SPAN>
</FORM>

<div id="dimensionEntry">
<form method="post">
<input type="hidden" id="spcPart" name="spcPart" value="${spcPart.id}"/>
<input type="hidden" id="DimensionId" name="DimensionId"/> 
<table>
<tbody>
<tr><td>Dimension</td><td><input type="text" id="dimension" name="dimension"/></td></tr>
<tr><td>Nominal</td><td><input type="text" id="nominal" name="nominal"/></td></tr>
<tr><td>Upper Limit</td><td><input type="text" id="upperLimit" name="upperLimit"/></td></tr>
<tr><td>Lower Limit</td><td><input type="text" id="lowerLimit" name="lowerLimit"/></td></tr>
<tr><td>N</td><td><input type="text" id="noOfMeasurements" name="noOfMeasurements"/></td></tr>
<tr><td>active</td><td><input type="checkbox" id="active" name="active"/></td></tr>

</tbody>
</table>
<span class="buttons">
	<input type="submit" name="mode" value="Enter" formaction="${pageContext.request.contextPath}/spc/SPC/addDimension" onclick="clearValueOfElementId('tableId');clearValueOfElementId('DimensionId')" />
	<input type="submit" name="mode" value="Edit" formaction="${pageContext.request.contextPath}/spc/SPC/editDimension" />
</span>
<input type="hidden" id="tableId" name="tableId"/>
</form>

</div>
</body>
</html>
