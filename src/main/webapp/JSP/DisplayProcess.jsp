<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<!-- 
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
 <script type="text/javascript">
 	google.load("visualization", "1", {packages:["corechart"]});
    google.setOnLoadCallback(drawChart);
    function drawChart() {
        // data to draw Time - Pressure Chart	
		var dataRow=
          [
          ['Time','Pressure'],
        <c:forEach items='${chartdata.timePressure}' var='data'>
			[${data[0]},${data[1]}],
        </c:forEach>
          ];
        var data = google.visualization.arrayToDataTable(dataRow);

     // data to draw Time - Speed Chart
        var speedRow=[['time','speed'],
        <c:forEach items='${chartdata.timeSpeed}' var='data'>
			[${data[0]},${data[1]}],
    	</c:forEach>

        ];
    	
		var speedData=google.visualization.arrayToDataTable(speedRow);

        var options = {
          title: 'Set Pressure',
          vAxis: {title: 'Pressure'},
          hAxis: {title: 'Time (sec)'},
          isStacked: false
        };
        var options2 = {
          title: 'Injection Speed',
          vAxis: {title: 'Speed (mm/s)'},
          hAxis: {title: 'Time (sec)'},
          isStacked: false
              };

        var chart = new google.visualization.AreaChart(document.getElementById('processChart'));
        var chart2 = new google.visualization.AreaChart(document.getElementById('processChart2'));
        chart.draw(data, options);
        chart2.draw(speedData,options2);
      }
    </script>
     -->
  <tags:Chart aprocess="${process}"></tags:Chart>
     
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>process: ${process.partId}</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/theme.css">
</head>
<body>
<DIV class="title">
<H1> Process:${process.partId }</H1>
</DIV>
<%@ include file="NavigationDiv.jspf" %>
<DIV>
<TABLE>
<TR><TD>Machine Size:</TD><TD>${process.machineSize}</TD></TR>
<TR><TD>Machine No.</TD><TD>${process.machineNo}</TD></TR>
<TR><TD>Material:</TD><TD>${process.material}</TD></TR>
<TR><TD>Masterbatch:</TD><TD>${process.masterbatchNo}</TD></TR>
<TR><TD>Date of Issue:</TD><TD>${process.dateOfIssue}</TD></TR>
<TR><TD>Signed of by:</TD><TD>${process.signOffBy}</TD></TR>
</TABLE>
</DIV>
<DIV   id="processChart">
</DIV>
<DIV   id="processChart2">
</DIV>

</body>
</html>