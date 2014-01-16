<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="injectiontimespeed" required="true" fragment="true" %>
<%@ attribute name="injectiontimepressure" required="true" fragment="true" %>
<%@ attribute name="cycletime" required="false" fragment="true" %>
<%@ attribute name="cycletimeline" required="true" fragment="true" %>
<%@ attribute name="mouldclosingtime" required="true" fragment="true" %>

<script type="text/javascript" src="https://www.google.com/jsapi"></script>
 <script type="text/javascript">
google.load("visualization", "1", {packages:["corechart","timeline"]});
google.setOnLoadCallback(drawChart);function drawChart()
{   	
    	<jsp:invoke fragment="injectiontimespeed" />
    	<jsp:invoke fragment="injectiontimepressure" />
    	<jsp:invoke fragment="cycletime" />
    	<jsp:invoke fragment="cycletimeline" />
    	<jsp:invoke fragment="mouldclosingtime" />
    	
}
</script>
<DIV   id="injectionTimePressureChart" class="chart">
</DIV>
<DIV   id="injectionTimeSpeedChart" class="chart">
</DIV>
<c:if test="${cycletime ne null}">
<DIV   id="cycleTimeChart" style="height:700px" class="chart">
</DIV>
</c:if>
<DIV   id="mouldclosingtimechart" style="height:400px" class="chart">
</DIV>
<DIV   id="cycleTimeLineChart" style="height:400px" class="chart">
</DIV>
