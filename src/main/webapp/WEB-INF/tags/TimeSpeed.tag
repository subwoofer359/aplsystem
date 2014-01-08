<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag import="java.util.ArrayList" import="java.util.List" import="org.amc.servlet.model.MouldingProcess"  %>
<%@ attribute name="process" required="true" type="org.amc.servlet.model.MouldingProcess" %>
<%@ variable name-given="totalInjectionTime" variable-class="java.lang.Float"  %>
<%!
	float startPosition=0;
	float totalInjectionTime;
	float[][] timeSpeed;
		
	public String createTimeSpeedData()
	{
		startPosition=process.getShotSize()+process.getPosTran();
		String result=getTimeSpeedData();
		getJspContext().setAttribute("totalInjectionTime", totalInjectionTime);
		return result;
	}

	private String getTimeSpeedData()
	{
		StringBuilder result= new StringBuilder();
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
		return result.toString();
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
