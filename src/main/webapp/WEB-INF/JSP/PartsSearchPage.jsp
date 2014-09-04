<%--  
	@author Adrian Mclaughlin
 	@version 1.1
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/BootStrapHeader.jsp" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/General.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/SearchPage.css">
<title>ACME Plastics:Parts Search Page</title>
<style>
.alert{
	position: relative;
	top: 50px;
	width: 40%;
	margin-left: auto;
	margin-right: auto;
	display:none;
	text-align: center;
	
}
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

function hide(element)
{
	element.style.display="none";
}
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

<nav role="navigation" class="navbar navbar-default navbar-fixed-bottom">
	<div class="container-fluid">
	<div class="navbar-header">
            <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="#" class="navbar-brand">Menu</a>
    </div><!--navbar-header-->
	
	<div id="navbarCollapse" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="${pageContext.request.contextPath}/app/APLSystemServlet">Home</a></li>
				<li class="dropdown">
					<a data-toggle="dropdown" class="dropdown-toggle" href="#">Actions<b class="caret"></b></a>
					<ul role="menu" class="dropdown-menu">
					<li>
	            		<input class="btn btn-block" id="add" type="submit" name="mode" value="add Part" onclick="addClicked(this)">
						<input class="btn btn-block" id="edit" type="submit" name="mode" value="edit Part">
						<input class="btn btn-block" id="edit" type="submit" value="add Part To SPC" formaction="${pageContext.request.contextPath}/app/spc/AddToSPC">
					</li>
					</ul>
				</li>
	   		</ul>
	   		<ul class="nav navbar-nav">
				<li class="dropdown">
					<a  data-toggle="dropdown" class="dropdown-toggle" href="#">Search<b class="caret"></b></a>
					<ul role="menu" class="dropdown-menu">
						<li>
							<div role="search" class="navbar-form">
								<div class="form-group">
									<label for="company" class="hidden-xs">Company</label> 			
									<div>
										<input class="form-control" type="text" id="company" name="company" placeholder="Company" <c:if test="${not empty PARTSEARCH and not empty PARTSEARCH.company}">value="${PARTSEARCH.company}"</c:if>/>
									</div>
								</div>
								<div class="form-group">
									<label class="hidden-xs" for="partName">Name</label>
									<div>
										<input class="form-control" type="text" id="partName" name="partName"  placeholder="Name of Part"  <c:if test="${not empty PARTSEARCH and not empty PARTSEARCH.partName}">value="${PARTSEARCH.partName}"</c:if>/>
									</div>
								</div>
								<div class="form-group">
									<label class="hidden-xs" for="qssNumber">QSS No.</label>
									<div>
										<input class="form-control" type="text" id="qssNumber" name="qssNumber"  placeholder="QSS Number" <c:if test="${not empty PARTSEARCH and not empty PARTSEARCH.QSSNumber}">value="${PARTSEARCH.QSSNumber}"</c:if>/>
									</div>
								</div>
								<div>
									<input id="search-btn" class="btn btn-primary form-control" type="submit" name="mode" value="search" onclick="addClicked(this)">
								</div>
							</div>
						</li>
					</ul>
				</li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>
                	<a href="${pageContext.request.contextPath}/app/UserInfo">User:<c:out value='${pageContext.request.remoteUser}'/></a>
                </li>
            </ul>
        </div><!-- nav-collapse -->
        
    </div><!--container -->
</nav>

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