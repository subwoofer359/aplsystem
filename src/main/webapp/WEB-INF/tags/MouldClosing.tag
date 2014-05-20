<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ tag import="java.util.ArrayList" import="java.util.List" import="org.amc.model.MouldingProcessRemote"  %>
<%@tag import="org.amc.servlet.model.MouldingProcessUtil"%>
<%@ attribute name="process" required="true" type="org.amc.model.MouldingProcessRemote" %>
<%!
public String getMouldClosingData()
{
	StringBuilder result=new StringBuilder();
	
	result.append("var mouldClosingTimeRow=[['Time(secs)','Distance(mm)','Velocity(mm/s)'],"); //Table headers - Three columns
	
	float[][] data=MouldingProcessUtil.getMouldClosingTimeData(process);
	
	float totalDistance=0f;
	float totalTime=0f;
	float velocity=0f;
	result.append("[0,0,0],");
	for(int i=0;i<data.length;i++)
	{
		
		
		
		totalTime+=data[i][0];
		totalDistance+=data[i][1];
		velocity=data[i][2];
		result.append("["+totalTime+","+totalDistance+","+velocity+"]");
		if(i<data.length-1)
		{
			result.append(",");
		}
		else
		{
			result.append("];");
		}
	}
	
	result.append("var mouldClosingTimeData = google.visualization.arrayToDataTable(mouldClosingTimeRow);");
	result.append("var mouldClosingTimeOptions = {title: 'Mould Closing Time'};");
	result.append("var mouldClosingTimeChart = new google.visualization.LineChart(document.getElementById('mouldclosingtimechart'));");
	result.append("mouldClosingTimeChart.draw(mouldClosingTimeData, mouldClosingTimeOptions);");
	
	
	
	return result.toString();
}
%>
<%= getMouldClosingData() %>