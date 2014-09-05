<%--  
	@author Adrian Mclaughlin
 	@version 1.1
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags/BottomMenuBar" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/BootStrapHeader.jsp" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/General.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/SearchPage.css">
<title>ACME Plastics:Parts Search Page</title>
<style>

</style>
<script src="${pageContext.request.contextPath}/js/SearchPage.js"></script>
<script src="${pageContext.request.contextPath}/js/TablesSort.js"></script>
<script>
window.onload=function()
		{
			var message="${message}";
			if(message!=null && message!="")
			{
				$(".alert").html("${message}");
				$(".alert").show();
			}
		};

</script>
</head>
<body>
<DIV class="page-title">
<H1> Part Inventory</H1>
</DIV>


<FORM action="${pageContext.request.contextPath}/app/Part_search" method="post" onsubmit="return isChecked(this,'part','alert')">
<DIV class="container results">
<div class="row">
<TABLE class="table col-xs-12">
<thead>
<TR>
	<TH class="h3" onclick="tableSort(this, 'Id');selected(null);">Id</TH>
	<TH class="h3" onclick="tableSort(this, 'Company');selected(null);">Company</TH>
	<TH class="h3" onclick="tableSort(this, 'Name');selected(null);">Name</TH>
	<TH class="h3" onclick="tableSort(this, 'Version');selected(null);">Version</TH>
	<TH class="h3" onclick="tableSort(this, 'Colour');selected(null);">Colour</TH>
	<TH class="h3" onclick="tableSort(this, 'QSS no.');selected(null);">QSS no.</TH>
	<TH class="checkbox"></TH>
</TR>
</thead>
<tbody>
<c:forEach items="${parts}" var="part">
<TR  onclick="selected(this)"><TD><c:out value="${part.id}"/></TD><TD><c:out value="${part.company}"/></TD><TD><c:out value="${part.name}"/></TD><TD><c:out value="${part.version}"/></TD><TD><c:out value="${part.colour}"/></TD><TD><c:out value="${part.qss_no}"/></TD><TD  class="checkbox"><input type="checkbox" name="edit" value="${part.id}"/></TD></TR>
</c:forEach>
</tbody>
</TABLE>
</div><!--  row -->
</div><!--  container -->


<tags:BottomMenuBar>
	<jsp:body>
		<tags:ButtonsMenu>
			<input class="btn btn-block" id="add" type="submit" name="mode" value="add Part" onclick="addClicked(this)">
			<input class="btn btn-block" id="edit" type="submit" name="mode" value="edit Part">
			<input class="btn btn-block" id="edit" type="submit" value="add Part To SPC" formaction="${pageContext.request.contextPath}/app/spc/AddToSPC">
		</tags:ButtonsMenu>
		<tags:UserListItem userName="${pageContext.request.remoteUser}"></tags:UserListItem>
		<tags:MenuSearchItem sessionVariable="PARTSEARCH" company="Company" partName="Name of Part" QSSNumber="QSS No."/>
	</jsp:body>
</tags:BottomMenuBar>

</FORM>
<%@ include file="/BootStrapFooter.jsp" %>

<!-- Stops the search menu closing when clicked on -->
<%--http://stackoverflow.com/questions/10863821/bootstrap-dropdown-closing-when-clicked --%>
<script type="text/javascript">
    $('.navbar-form').click(function(e) {
        e.stopPropagation();
    });

    /* The user's enter keypress on the search element should submit a search */
    $('.navbar-form').keypress(function(e) {
    	var code = (e.keyCode ? e.keyCode : e.which);
    	if(code==13)
    	{
    		$("#search-btn").click();
            e.stopPropagation();
    		return false;
    	}	
        });
</script>
<div id="alert" class="alert alert-danger" role="alert" onclick="hide(this)"></div>
</body>
</html>