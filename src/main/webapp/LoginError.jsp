<!DOCTYPE html>
<%@page session="false"  %>
<%-- 
	@author Adrian Mclaughlin
 	@version 1.1
--%>
<html lang="en">
<head>
<%@ include file="LoginTemplate.jsp"%>
<!-- Change the labels and inputs to red -->
<!-- Make the Error box visible -->
<script type="text/javascript">
$(".form-group").addClass("has-error");
$(".error").css("display","block");
</script>
</body>
</html>