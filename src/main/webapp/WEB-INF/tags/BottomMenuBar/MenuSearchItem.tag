<%--
	A html list used as Search menu item in a bootstrap menu nav.
	Depends on BootStrap and JQuery
	To be used as child tag of BottomMenuBar.tag
	
	@author Adrian McLaughlin
--%>
<%@ tag language="java" pageEncoding="UTF-8"  trimDirectiveWhitespaces="true"%>
<%@ tag dynamic-attributes="searchFields" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- sessionVariable is a session variable holding the previous search values --%>
<%@ attribute name="sessionVariable" required="true" rtexprvalue="false"%>
<c:set var="sessionVariable" value="${pageScope['sessionVariable']}" scope="request"/>
<ul class="nav navbar-nav">
	<li class="dropdown">
		<a  data-toggle="dropdown" class="dropdown-toggle" href="#">Search<b class="caret"></b></a>
		<ul role="menu" class="dropdown-menu">
			<li>
				<div role="search" class="navbar-form">
					<c:forEach items="${searchFields}" var="searchField">
						<div class="form-group">
							<label for="${searchField.key}" class="hidden-xs">${searchField.value}</label> 			
							<div>
								<c:if test="${not empty sessionVariable}">
									<input class="form-control" type="text" id="${searchField.key}" name="${searchField.key}" placeholder="${searchField.value}" <c:if test="${not empty sessionScope[sessionVariable] and not empty sessionScope[sessionVariable][searchField.key]}">value="${sessionScope[sessionVariable][searchField.key]}"</c:if>/>
								</c:if>
								<c:if test="${empty sessionVariable}">
									<input class="form-control" type="text" id="${searchField.key}" name="${searchField.key}" placeholder="${searchField.value}" />
								</c:if>
							</div>
						</div>
					</c:forEach>
					<jsp:doBody/>
					<div>
						<input id="search-btn" class="btn btn-primary form-control" type="submit" name="mode" value="search" onclick="addClicked(this)">
					</div>
				</div>
			</li>
		</ul>
	</li>
</ul>