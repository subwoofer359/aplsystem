<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="timespeed" required="true" fragment="true" %>
<%@ attribute name="timepressure" required="true" fragment="true" %>
<%@ attribute name="cycletime" required="true" fragment="true" %>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
 <script type="text/javascript">
 	google.load("visualization", "1", {packages:["corechart"]});
    google.setOnLoadCallback(drawChart);
    function drawChart() {
    	// data to draw Time - Speed Chart
        var speedRow=[['time','speed'], <jsp:invoke fragment="timespeed"/>
        ];
    	
		var speedData=google.visualization.arrayToDataTable(speedRow);

        
        // data to draw Time - Pressure Chart	
		var dataRow=
          [
          ['Time','Pressure'], <jsp:invoke fragment="timepressure"/>
          ];
        var data = google.visualization.arrayToDataTable(dataRow);

     

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

    	<jsp:invoke fragment="cycletime" />
        
      }
    </script>