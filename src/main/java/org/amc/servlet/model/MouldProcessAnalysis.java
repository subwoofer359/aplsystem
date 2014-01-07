package org.amc.servlet.model;

import java.util.ArrayList;
import java.util.List;

public class MouldProcessAnalysis
{
	private final MouldingProcess process;
	
	float startPosition=0;
	float totalInjectionTime;
	float[][] timePressure;
	float[][] timeSpeed;
	
	public MouldProcessAnalysis(final MouldingProcess process)
	{
		this.process=process;
		
		//calculate Start Position
		startPosition=process.getShotSize()+process.getPosTran();
		
		getTimeSpeedData();
		getTimePressureData();
		
	}
	
	private void getTimeSpeedData()
	{
		
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
				times.add(totalInjectionTime);
		
				System.out.println(position[pointer]+"-"+process.getPosTran()+"/"+speed[pointer]);
				break;
			}
		}
		
		timeSpeed=new float[times.size()][2];
		int ptr=0;
		for(int i=0;i<times.size();i++)
		{
			timeSpeed[i][0]=times.get(i);timeSpeed[i][1]=speed[ptr];
			//To use each speed variable twice
			if(i%2!=0)
			{
				ptr++;
			}
		
		}
		
	}

	/**
	 * Must be called after method getTimeSpeedData()
	 */
	private void getTimePressureData()
	{
			if(totalInjectionTime<=0)
			{
				return;
			}
			
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
			
			timePressure[0][0]=times.get(0);timePressure[0][1]=process.getMaxInjPre();
			timePressure[1][0]=times.get(1);timePressure[1][1]=process.getMaxInjPre();
			timePressure[2][0]=times.get(1);timePressure[2][1]=process.getHoldingPressure_1();
			timePressure[3][0]=times.get(2);timePressure[3][1]=process.getHoldingPressure_1();
			timePressure[4][0]=times.get(2);timePressure[4][1]=process.getHoldingPressure_2();
			timePressure[5][0]=times.get(3);timePressure[5][1]=process.getHoldingPressure_2();
			timePressure[6][0]=times.get(3);timePressure[6][1]=process.getHoldingPressure_3();
			timePressure[7][0]=times.get(4);timePressure[7][1]=process.getHoldingPressure_3();
			timePressure[8][0]=times.get(4);timePressure[8][1]=process.getHoldingPressure_4();
			timePressure[9][0]=times.get(5);timePressure[9][1]=process.getHoldingPressure_4();
			timePressure[10][0]=times.get(5);timePressure[10][1]=process.getHoldingPressure_5();
			timePressure[11][0]=times.get(6);timePressure[11][1]=process.getHoldingPressure_5();
			timePressure[12][0]=times.get(6);timePressure[12][1]=process.getHoldingPressure_6();
			timePressure[13][0]=times.get(7);timePressure[13][1]=process.getHoldingPressure_6();
			
			
			
	}
	
	public float getStartPosition()
	{
		return startPosition;
	}

	public float getTotalInjectionTime()
	{
		return totalInjectionTime;
	}

	public float[][] getTimePressure()
	{
		return timePressure;
	}

	public float[][] getTimeSpeed()
	{
		return timeSpeed;
	}
	
	

}
