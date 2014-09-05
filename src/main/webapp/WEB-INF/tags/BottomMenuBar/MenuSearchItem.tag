<%--
	Tag file for a Search menu item in a NavBar.
	Depends on BootStrap 
	@author Adrian McLaughlin
--%>
<%@ tag language="java" pageEncoding="UTF-8"  trimDirectiveWhitespaces="true"%>
<%@ tag dynamic-attributes="searchFields" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
								<input class="form-control" type="text" id="${searchField.key}" name="${searchField.key}" placeholder="${searchField.value}" <c:if test="${not empty PARTSEARCH and not empty PARTSEARCH[searchField.key]}">value="${PARTSEARCH[searchField.key]}"</c:if>/>
							</div>
						</div>
					</c:forEach>
					<div>
						<input id="search-btn" class="btn btn-primary form-control" type="submit" name="mode" value="search" onclick="addClicked(this)">
					</div>
				</div>
			</li>
		</ul>
	</li>
</ul>