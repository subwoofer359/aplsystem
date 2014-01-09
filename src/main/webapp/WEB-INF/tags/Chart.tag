<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="injectiontimespeed" required="true" fragment="true" %>
<%@ attribute name="injectiontimepressure" required="true" fragment="true" %>
<%@ attribute name="cycletime" required="true" fragment="true" %>

<script type="text/javascript" src="https://www.google.com/jsapi"></script>
 <script type="text/javascript">
google.load("visualization", "1", {packages:["corechart"]});
google.setOnLoadCallback(drawChart);function drawChart()
{   	
    	<jsp:invoke fragment="injectiontimespeed" />
    	<jsp:invoke fragment="injectiontimepressure" />
    	<jsp:invoke fragment="cycletime" />
}
</script>
<DIV   id="injectionTimePressureChart">
</DIV>
<DIV   id="injectionTimeSpeedChart">
</DIV>
<DIV   id="cycleTimeChart" style="height:700px">
</DIV>
