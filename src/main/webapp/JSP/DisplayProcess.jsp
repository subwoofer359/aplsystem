<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Part: ${part.partId}</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/theme.css">
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
 <script type="text/javascript">
 	google.load("visualization", "1", {packages:["corechart"]});
    google.setOnLoadCallback(drawChart);
    function drawChart() {
        var startPosition=${process.shotSize + process.posTran};
        var injectionPressure=${process.maxInjPre};
		var posTran=${process.posTran};
        var data = google.visualization.arrayToDataTable([
          ['Position','Pressure'],
          [ startPosition,injectionPressure],
          [  posTran,injectionPresure],
          [ posTran,${process.holdingPressure_1 }],
          [ 0 ,${process.holdingPressure_1]
          ]);

        var options = {
          title: 'The decline of \'The 39 Steps\'',
          vAxis: {title: 'Accumulated Rating'},
          isStacked: true
        };

        var chart = new google.visualization.AreaChart(document.getElementById('processChart'));
        chart.draw(data, options);
      }
    </script>
</head>
<body>
<DIV class="title">
<H1> Process:${part.partId }</H1>
</DIV>
<%@ include file="NavigationDiv.jspf" %>
<DIV>
<TABLE>
<TR><TD>Machine Size:</TD><TD>${part.machineSize}</TD></TR>
<TR><TD>Machine No.</TD><TD>${part.machineNo}</TD></TR>
<TR><TD>Material:</TD><TD>${part.material}</TD></TR>
<TR><TD>Masterbatch:</TD><TD>${part.masterbatchNo}</TD></TR>
<TR><TD>Date of Issue:</TD><TD>${part.dateOfIssue}</TD></TR>
<TR><TD>Signed of by:</TD><TD>${part.signOffBy}</TD></TR>
</TABLE>
</DIV>
<DIV  class="processChart">
</DIV>
</body>
</html>