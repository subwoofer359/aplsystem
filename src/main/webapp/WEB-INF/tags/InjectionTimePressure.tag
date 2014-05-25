<!--  
	@author Adrian Mclaughlin
 	@version 1
-->
<%@tag import="org.amc.servlet.model.MouldingProcessUtil"%>
<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ tag import="java.util.ArrayList" import="java.util.List" import="org.amc.model.MouldingProcess"  %>
<%@ attribute name="process" required="true" type="org.amc.model.MouldingProcess" %>
<%!
public String getTimePressureData()
{
		StringBuilder result=new StringBuilder();
        
		float totalInjectionTime=MouldingProcessUtil.getTotalInjectionTime(process);
		
        result.append("var injectionTimePressureRow=[['Time','Pressure'],");
        
		float[][] timePressure=null;
		//float totalInjectionTime=process.getTotalInjectionTime();
		List<Float> times=new ArrayList<Float>();
		times.add(0f);//Time 
		times.add(totalInjectionTime);// First part is the injectionTime
		
		float previous=totalInjectionTime;
		times.add(previous=previous+process.getHoldingTime_1());
		times.add(previous=previous+process.getHoldingTime_2());
		times.add(previous=previous+process.getHoldingTime_3());
		times.add(previous=previous+process.getHoldingTime_4());
		times.add(previous=previous+process.getHoldingTime_5());
		times.add(previous=previous+process.getHoldingTime_6());
		
		timePressure=new float[14][2];
		
		result.append("["+times.get(0)+","+process.getMaxInjPre()+"],");
		result.append("["+times.get(1)+","+process.getMaxInjPre()+"],");
		result.append("["+times.get(1)+","+process.getHoldingPressure_1()+"],");
		result.append("["+times.get(2)+","+process.getHoldingPressure_1()+"],");
		result.append("["+times.get(2)+","+process.getHoldingPressure_2()+"],");
		result.append("["+times.get(3)+","+process.getHoldingPressure_2()+"],");
		result.append("["+times.get(3)+","+process.getHoldingPressure_3()+"],");
		result.append("["+times.get(4)+","+process.getHoldingPressure_3()+"],");
		result.append("["+times.get(4)+","+process.getHoldingPressure_4()+"],");
		result.append("["+times.get(5)+","+process.getHoldingPressure_4()+"],");
		result.append("["+times.get(5)+","+process.getHoldingPressure_5()+"],");
		result.append("["+times.get(6)+","+process.getHoldingPressure_5()+"],");
		result.append("["+times.get(6)+","+process.getHoldingPressure_6()+"],");
		result.append("["+times.get(7)+","+process.getHoldingPressure_6()+"]");
		
		result.append("];");

        
        result.append("var injectionTimePressureData = google.visualization.arrayToDataTable(injectionTimePressureRow);");
        
        result.append("var injectionTimePressureOptions = {title: 'Set Pressure',vAxis: {title: 'Pressure'},hAxis: {title: 'Time (sec)'},isStacked: false};");
		
        result.append("var injectionTimePressureChart = new google.visualization.AreaChart(document.getElementById('injectionTimePressureChart'));");
        
        result.append("injectionTimePressureChart.draw(injectionTimePressureData, injectionTimePressureOptions);");
        
		return result.toString();
}


%>
<%= getTimePressureData() %>