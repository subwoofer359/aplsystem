<%--
	A html list used as Search menu item in a bootstrap menu nav.
	Depends on BootStrap and JQuery
	To be used as child tag of BottomMenuBar.tag
	
	@author Adrian McLaughlin
--%>
<%@ tag language="java" pageEncoding="UTF-8"  trimDirectiveWhitespaces="true"%>
<%@ tag dynamic-attributes="searchFields" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- sessionVariable is a session attribute holding a org.amc.servlet.action.search.WebFormSearch object --%>
<%@ attribute name="sessionVariable" required="false" rtexprvalue="false"%>

<%--Making  sessionVariable available to nested classes, variable action not use --%>
<c:set var="sessionVariable" value="${pageScope['sessionVariable']}" scope="request"/>
<ul class="nav navbar-nav">
  <li class="dropdown">
    <a  data-toggle="dropdown" class="dropdown-toggle" href="#"><span class="glyphicon glyphicon-search"></span> Search<b class="caret"></b></a>
    <ul role="menu" class="dropdown-menu">
      <li>
        <div role="search" class="navbar-form">
          <c:forEach items="${searchFields}" var="searchField">
            <div class="form-group">
              <label for="${searchField.key}" class="hidden-xs">${searchField.value}</label> 			
                <div>
                  <c:choose>                     
                    <c:when test="${not empty sessionVariable and not empty sessionScope[sessionVariable][searchField.key]}">
                      <input class="form-control" type="text" id="${searchField.key}" 
                             name="${searchField.key}" placeholder="${searchField.value}" 
                             value="${sessionScope[sessionVariable][searchField.key]}"/>
                    </c:when>
                    <c:otherwise>
                      <input class="form-control" type="text" id="${searchField.key}" 
                             name="${searchField.key}" placeholder="${searchField.value}" />
                    </c:otherwise>
                  </c:choose>
                </div>
              </div>
            </c:forEach>
            <jsp:doBody/>
            <div>
              <input id="search-btn" class="btn btn-primary form-control" type="submit" 
                     name="mode" value="search" onclick="addClicked(this)"/>
            </div>
          </div>
        </li>
      </ul>
  </li>
</ul>