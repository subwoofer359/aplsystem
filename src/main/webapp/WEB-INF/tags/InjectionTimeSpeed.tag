<!--  
	@author Adrian Mclaughlin
 	@version 1
-->
<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ tag import="java.util.ArrayList" import="java.util.List" import="org.amc.model.MouldingProcess"  %>
<%@ attribute name="process" required="true" type="org.amc.model.MouldingProcess" %>
<%!
	float startPosition=0;
	float totalInjectionTime;
	float[][] timeSpeed;
		
	public String createTimeSpeedData()
	{
		StringBuilder result= new StringBuilder();
		//Calculate Start position of the Screw for injection
		startPosition=process.getShotSize()+process.getPosTran();
		
		getTimeSpeedData(result); // Fill the StringBuilder with chart data
		
        result.append("var injectionTimeSpeedData=google.visualization.arrayToDataTable(injectionTimeSpeedRow);");
        
        result.append("var injectionTimeSpeedOptions = {title: 'Injection Speed',vAxis: {title: 'Speed (mm/s)',minValue:0,maxValue:100},hAxis: {title: 'Time (sec)'},isStacked: false};");
        
        result.append("var injectionTimeSpeedChart = new google.visualization.AreaChart(document.getElementById('injectionTimeSpeedChart'));");
        
        result.append("injectionTimeSpeedChart.draw(injectionTimeSpeedData,injectionTimeSpeedOptions);");
        		
		return result.toString();
	}

	private void getTimeSpeedData(StringBuilder result)
	{
		result.append("var injectionTimeSpeedRow=[['time','speed'], ");
		List<Float> times=new ArrayList<Float>();
		times.add(0f);//Zero at start of time
		
		
		float[] position=
			{
				getStartPosition(),
				process.getInjSpeedPosition_1(),
				process.getInjSpeedPosition_2(),
				process.getInjSpeedPosition_3(),
				process.getInjSpeedPosition_4(),
				process.getInjSpeedPosition_5(),
				process.getInjSpeedPosition_6()
			};
		
		float[] speed=
			{
				process.getInjectionSpeed_1(),
				process.getInjectionSpeed_2(),
				process.getInjectionSpeed_3(),
				process.getInjectionSpeed_4(),
				process.getInjectionSpeed_5(),
				process.getInjectionSpeed_6()
			};
		
		for(int pointer=0;pointer<speed.length;pointer++)
		{
			if(position[pointer+1]!=0)
			{
				totalInjectionTime+=(position[pointer]-position[pointer+1])/speed[pointer];
				times.add(totalInjectionTime);
				times.add(totalInjectionTime);
				
				
				System.out.println(position[pointer]+"-"+position[pointer+1]+"/"+speed[pointer]);
				
			}
			else
			{
				totalInjectionTime+=(position[pointer]-process.getPosTran())/speed[pointer];
				times.add(totalInjectionTime);
				//times.add(totalInjectionTime);
		
				System.out.println(position[pointer]+"-"+process.getPosTran()+"/"+speed[pointer]);
				break;
			}
		}
		
		timeSpeed=new float[times.size()][2];
		int ptr=0;
		for(int i=0;i<times.size();i++)
		{
			
			timeSpeed[i][0]=times.get(i);timeSpeed[i][1]=speed[ptr];
			result.append("["+timeSpeed[i][0]+","+timeSpeed[i][1]+"]");
			//To use each speed variable twice
			if(i%2!=0 && ptr<speed.length-1)
			{
				ptr++;
			}
			if(i<times.size()-1)
			{
				result.append(",");
			}
		
		}
		
		result.append("];");
	}
	

	
	public float getStartPosition()
	{
		return startPosition;
	}

	public float getTotalInjectionTime()
	{
		return totalInjectionTime;
	}

	public float[][] getTimeSpeed()
	{
		return timeSpeed;
	}
%>

<%=createTimeSpeedData() %>
<jsp:doBody/>