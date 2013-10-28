<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>APL System:Part Description Page</title>
</head>
<body>
<DIV>
<FORM>
<TABLE>
<c:forEach items="${jobtemplate.fields}" var="field"> 
<TR><TD>${field}</TD><TD><input type="text" name="${field}"/></TD></TR>
</c:forEach>

</TABLE>

</FORM>


</DIV>


</body>
</html>