<%@tag import="org.amc.servlet.model.MouldingProcessUtil"%>
<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ tag import="java.util.ArrayList" import="java.util.List" import="org.amc.model.MouldingProcessBeanRemote" import="org.amc.servlet.model.MouldingProcessUtil" %>
<%@ attribute name="process" required="true" type="org.amc.model.MouldingProcessBeanRemote" %>
<%!

private float timeStart=0.f;
private float timeEnd=0.f;

/**
 To Calculate the time intervals for the TimeLine Chart
*/
private void getNextTime(float value)
{
	timeStart=timeEnd;
	//Multiplied by 1000 so to display correctly in chart
	timeEnd=timeStart+(value*1000);
}

public String getCycleTimeLineData()
{
	StringBuilder result=new StringBuilder();
	
	result.append("var cycleTimeLineRow=[['Phase','Start','End'],"); //Table headers - Three columns
	
	getNextTime(timeStart+MouldingProcessUtil.getTotalInjectionTime(process));
	
	result.append("['Injection',"+timeStart+","+timeEnd+"],"); //Injection time
	
	getNextTime(
            process.getHoldingTime_1()+
            process.getHoldingTime_2()+
            process.getHoldingTime_3()+
            process.getHoldingTime_4()+
            process.getHoldingTime_5()+
            process.getHoldingTime_6()
     );
	
	result.append("['Holding',"+timeStart+","+timeEnd+"],");
	
	getNextTime(process.getCoolTime());
	
	result.append("['Cooling',"+timeStart+","+timeEnd+"],");
	
	getNextTime(1); // Todo need the correct time for Mould Opening
	
	result.append("['Mould Opening',"+timeStart+","+timeEnd+"],");
	
	getNextTime(1); // Todo need the correct time for Ejectors
	
	result.append("['Ejectors',"+timeStart+","+timeEnd+"],");
	
	getNextTime(MouldingProcessUtil.getMouldClosingTime(process)); // Todo need the correct time for Mould Closing
	
	result.append("['Mould Closing',"+timeStart+","+timeEnd+"]");
	
	result.append("];"); //Close the JS array
	
	result.append("var cycleTimeLineData=google.visualization.arrayToDataTable(cycleTimeLineRow);");
	
	result.append("var cycleTimeLineOptions = {title: 'Cycle Time'};");
	
	result.append("var cycleTimeLineChart = new google.visualization.Timeline(document.getElementById('cycleTimeLineChart'));");
	
	result.append("cycleTimeLineChart.draw(cycleTimeLineData,cycleTimeLineOptions);");
	
	return result.toString();
}
%>
<%= getCycleTimeLineData() %>