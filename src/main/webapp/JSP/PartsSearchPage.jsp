<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="ErrorPage.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>APL System:Parts Search Page</title>
</head>
<body>
<FORM action="JobTemplate_search" method="get">
<SPAN>
<input type="text" name="search"/><input type="submit" name="mode" value="search"/>
</SPAN>
<TABLE>
<TR><TH>Id</TH><TH>Company</TH><TH>Name</TH><TH>Version</TH><TH>Colour</TH><TH>QSS no.</TH><TH></TH></TR>
<c:forEach items="${jobtemplates}" var="part">
<TR><TD>${part.id}</TD><TD>${part.company}</TD><TD>${part.name}</TD><TD>${part.version}</TD><TD>${part.colour}</TD><TD>${part.qss_no}</TD><TD><input type="checkbox" name="edit" value="${part.id}"/></TD></TR>
</c:forEach>
<TR><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD><input type="submit" name="mode" value="add"/></TD><TD><input type="submit" name="mode" value="edit"/></TD></TR>
</TABLE>
</FORM>

</body>
</html>