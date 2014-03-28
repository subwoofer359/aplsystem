<%@tag import="org.amc.servlet.model.MouldingProcessUtil"%>
<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ tag import="java.util.ArrayList" import="java.util.List" import="org.amc.model.MouldingProcessBeanRemote"  %>
<%@ attribute name="process" required="true" type="org.amc.model.MouldingProcessBeanRemote" %>
<%! 
public String getCycleTimeData()
{
	StringBuilder result=new StringBuilder();
	
	
	result.append("var cycleTimeRow=[['Phase','Time(secs)'],"); //Table headers - Two columns
	
	result.append("['Injection',"+MouldingProcessUtil.getTotalInjectionTime(process)+"],"); //Injection time
	
	result.append("['Holding',"+(
	                process.getHoldingTime_1()+
	                process.getHoldingTime_2()+
	                process.getHoldingTime_3()+
	                process.getHoldingTime_4()+
	                process.getHoldingTime_5()+
	                process.getHoldingTime_6())+
	                "],");
	
	result.append("['Cooling',"+process.getCoolTime()+"],");
	
	result.append("['Mould Opening',1],");
	
	result.append("['Ejectors',1],");
	
	result.append("['Mould Closing',1]");
	
	result.append("];"); //Close the JS array
	
	result.append("var cycleTimeData=google.visualization.arrayToDataTable(cycleTimeRow);");
	
	result.append("var cycleTimeOptions = {title: 'Cycle Time',pieHole: 0.4};");
	
	result.append("var cycleTimeChart = new google.visualization.PieChart(document.getElementById('cycleTimeChart'));");
	
	result.append("cycleTimeChart.draw(cycleTimeData,cycleTimeOptions);");
	
	return result.toString();
}
%>
<%= getCycleTimeData() %>
