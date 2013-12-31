<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
 <script type="text/javascript">
 	google.load("visualization", "1", {packages:["corechart"]});
    google.setOnLoadCallback(drawChart);
    function drawChart() {
        var startPosition=${process.shotSize + process.posTran};
        var injectionTime=${process.shotSize / process.injectionSpeed_1}; // Needs to an average

        //Calculating times for Max injection pressure and holding times
        var times=[0,injectionTime];
        times[2]=times[1]+${process.holdingTime_1};
        times[3]=times[2]+${process.holdingTime_2};
        times[4]=times[3]+${process.holdingTime_3};
        times[5]=times[4]+${process.holdingTime_4};
        times[6]=times[5]+${process.holdingTime_5};
        times[7]=times[6]+${process.holdingTime_6};
        
        var injectionPressure=${process.maxInjPre};
		var posTran=${process.posTran};

		//Calculating times when the injection speed changes
		var speed=[
		   		${process.injectionSpeed_1},
		   		${process.injectionSpeed_2},
		   		${process.injectionSpeed_3},
		   		${process.injectionSpeed_4},
		   		${process.injectionSpeed_5},
		   		${process.injectionSpeed_6}
		   		];
		var position=[
		      		startPosition,
		      		${process.injSpeedPosition_1},
		      		${process.injSpeedPosition_2},
		      		${process.injSpeedPosition_3},
		      		${process.injSpeedPosition_4},
		      		${process.injSpeedPosition_5},
		      		${process.injSpeedPosition_6}
		      		];

		var pointer=0;
		var timeSpeed=[];
		var previousTime=0;

		while(pointer<speed.length)
		{
			if(position[pointer+1]!=0)
			{
				timeSpeed[pointer]=(position[pointer]-position[pointer+1])/speed[pointer]+previousTime;
				previousTime=timeSpeed[pointer];
			}
			else
			{
				timeSpeed[pointer]=(position[pointer]-posTran)/speed[pointer]+previousTime;
				previousTime=timeSpeed[pointer];
				break;
			}
			pointer++;
		}		
		
		var dataRow=
          [
          ['Time','Pressure'],
          [ times[0],injectionPressure],
          [  times[1],injectionPressure],
          [ times[1],${process.holdingPressure_1 }],
          [ times[2]  ,${process.holdingPressure_1}],
          [ times[2]  ,${process.holdingPressure_2}],
          [ times[3]  ,${process.holdingPressure_2}],
          [ times[3]  ,${process.holdingPressure_3}],
          [ times[4]  ,${process.holdingPressure_3}],
          [ times[4]  ,${process.holdingPressure_4}],
          [ times[5]  ,${process.holdingPressure_4}],
          [ times[5]  ,${process.holdingPressure_5}],
          [ times[6]  ,${process.holdingPressure_5}],
          [ times[6]  ,${process.holdingPressure_6}],
          [ times[7] , ${process.holdingPressure_6}]
          ];
        var data = google.visualization.arrayToDataTable(dataRow);

        var speedRow=[['time','speed']];
    	previousTime=0;
    	for(var i=0;i<timeSpeed.length;i++)
    	{
    		speedRow.push([previousTime,speed[i]]);
    		speedRow.push([timeSpeed[i],speed[i]]);
    		previousTime=timeSpeed[i];
    	}
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