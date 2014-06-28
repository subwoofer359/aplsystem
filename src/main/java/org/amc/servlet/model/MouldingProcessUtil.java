package org.amc.servlet.model;

import org.amc.model.MouldingProcess;
import org.amc.servlet.APLMaterialServlet;
import org.apache.log4j.Logger;
/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
public final class MouldingProcessUtil
{
	private static Logger logger=Logger.getLogger(APLMaterialServlet.class);
	private final static float CLAMP_CLOSING_ACCELERATION=1250f;
	private final static float CLAMP_CLOSING_DEACCELERATION=-1000f;
	
	
	private MouldingProcessUtil()
	{
		//An utility class
	}
	
	public static float getTotalInjectionTime(MouldingProcess process)
	{
		float totalInjectionTime=0f;
		float[] position=
			{
				process.getShotSize()+process.getPosTran(),
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
			if(position[pointer+1]==0)
			{
				totalInjectionTime+=(position[pointer]-process.getPosTran())/speed[pointer];
				break;
			}
			else
			{
				totalInjectionTime+=(position[pointer]-position[pointer+1])/speed[pointer];
				
			}
		}
		return totalInjectionTime;
	}
	
	/**
	 * s=ut+1/2at^2
	 * @param intialVelocity
	 * @param timeInSeconds
	 * @param acceleration
	 * @return Distance
	 */
	private static float getDistance(float intialVelocity,float timeInSeconds,float acceleration)
	{
		return (float)((intialVelocity*timeInSeconds)+(0.5*acceleration*Math.pow(timeInSeconds, 2)));
		
	}
	/**
	 * a=(v-u)/t
	 * @param intialVelocity
	 * @param finalVelocity
	 * @param timeInSeconds
	 * @return acceleration
	 */
	private static float getAcceleration(float intialVelocity,float finalVelocity,float timeInSeconds)
	{
		return (finalVelocity-intialVelocity)/timeInSeconds;
	}
	
	/**
	 * t=(v-u)/a
	 * @param intialVelocity
	 * @param finalVelocity
	 * @param acceleration
	 * @return time
	 */
	private static float getTime(float intialVelocity,float finalVelocity,float acceleration)
	{
		return (finalVelocity-intialVelocity)/acceleration;
	}
	
	public static float getMouldClosingTime(MouldingProcess process)
	{
		float[][] data=getMouldClosingTimeData(process);
		
		float result=0f;
		for(int i=0;i<data.length;i++)
		{
			result+=data[i][0];
		}
		return result;
	}
	
	/**
	 * 
	 * @param process
	 * @return an array of floats [[time_1,distance_1],...]
	 */
	@Deprecated
	public static float[][] getOldMouldClosingTimeData(MouldingProcess process)
	{
		float[][] data=new float[8][3];
		float expectedDistance1=process.getMouldClosingOpenLimitPos()-process.getMouldClosedLimitPos();
		float expectedDistance2=process.getMouldClosedLimitPos()-process.getClsSlowPos();
		float expectedDistance3=process.getClsSlowPos()-process.getClsSPPos();
		
		float speed1=process.getMouldClosingOpenLimitSpeed();
		float speed2=process.getMouldClosedLimitSpeed();
		float speed3=process.getClsSPSpeed();
		
		
		//Calculate transitions in the graph
		float timeA=getTime(0,speed1,CLAMP_CLOSING_ACCELERATION);
		float distanceA=getDistance(0, timeA, CLAMP_CLOSING_ACCELERATION);
		data[0][0]=timeA;
		data[0][1]=distanceA;
		data[0][2]=speed1;
		
		//Zone C
		float timeC=0;
		float distanceC=0;
		//Find out if there's an increase or decrease acceleration
		if(getAcceleration(speed1, speed2, 1)<0)
		{
				timeC=getTime(speed1, speed2,CLAMP_CLOSING_DEACCELERATION);
				distanceC=getDistance(speed1,timeC, CLAMP_CLOSING_DEACCELERATION);
		}
		else
		{
				timeC=getTime(speed1, speed2,CLAMP_CLOSING_ACCELERATION);
				distanceC=getDistance(speed1,timeC, CLAMP_CLOSING_ACCELERATION);
		}
		data[2][0]=timeC;
		data[2][1]=distanceC;
		data[2][2]=speed2;
		//Zone E
		
		float timeE=0;
		float distanceE;
		if(getAcceleration(speed2, speed3, 1)<0)
		{
			timeE=getTime(speed2, speed3,CLAMP_CLOSING_DEACCELERATION);
			distanceE=getDistance(speed2,timeE, CLAMP_CLOSING_DEACCELERATION);
		}
		else
		{
			timeE=getTime(speed2, speed3,CLAMP_CLOSING_ACCELERATION);
			distanceE=getDistance(speed2,timeE, CLAMP_CLOSING_ACCELERATION);
		}
		data[4][0]=timeE;
		data[4][1]=distanceE;
		data[4][2]=speed3;
		
		//Zone G
		float timeG=0;
		float distanceG=0;
		if(getAcceleration(speed3, 0, 1)<0)
		{
			timeG=getTime(speed3, 0,CLAMP_CLOSING_DEACCELERATION);
			distanceG=getDistance(speed3,timeG, CLAMP_CLOSING_DEACCELERATION);
		}
		else
		{
			timeG=getTime(speed3, 0,CLAMP_CLOSING_ACCELERATION);
			distanceG=getDistance(speed3,timeG, CLAMP_CLOSING_ACCELERATION);
		}
		
		data[6][0]=timeG;
		data[6][1]=distanceG;
		data[6][2]=0;
		
		float distanceB=expectedDistance1-(distanceA+0.5f*distanceC);
		distanceB=(distanceB<0)?0:distanceB;
		float timeB=(distanceB/speed1<0)?0:distanceB/speed1;
		data[1][0]=timeB;
		data[1][1]=distanceB;
		data[1][2]=speed1;
		
		float distanceD=expectedDistance2-(0.5f*distanceC+0.5f*distanceE);
		distanceD=(distanceD<0)?0:distanceD;
		float timeD=(distanceD/speed2<0)?0:distanceD/speed2;
		data[3][0]=timeD;
		data[3][1]=distanceD;
		data[3][2]=speed2;
		
		float distanceF=expectedDistance3-(0.5f*distanceE+distanceG);
		distanceF=(distanceF<0)?0:distanceF;
		float timeF=(distanceF/speed3<0)?0:distanceF/speed3;
		data[5][0]=timeF;
		data[5][1]=distanceF;
		data[5][2]=speed3;
		//After reaching the mould protect point(ClsSPPos) the mould is accelerated to when the mould faces meet
		
		float timeH=(float)Math.sqrt((2*process.getClsSPPos())/CLAMP_CLOSING_ACCELERATION);//t=sqrt(2*s/a)
		data[7][0]=timeH;
		data[7][1]=process.getClsSPPos();
		data[7][2]=(CLAMP_CLOSING_ACCELERATION*timeH);
		
		for(int i=0;i<data.length;i++)
		{
			logger.debug("("+data[i][1]+","+data[i][0]+")");
		}
		return data;
}
	

	
	/**
	 * todo Fix the problem when acceleration/deceleration distance is greater than the pre-set distance
	 * @param process
	 * @return an array of floats [[time_1,distance_1],...]
	 */
	public static float[][] getMouldClosingTimeData(MouldingProcess process)
	{
		float[][] data=new float[8][3];
		/*
		 * Calculate the time taken from mould protect position to closed position
		 * Initial velocity is zero
		 * Distance: Mould protect position to zero = Mould protect position
		 * t=sqrt(2*s/a)
		 */
		float closingTime=(float)Math.sqrt((2*process.getClsSPPos())/CLAMP_CLOSING_ACCELERATION);
		data[data.length-1][0]=closingTime;
		data[data.length-1][1]=process.getClsSPPos();
		data[data.length-1][2]=(CLAMP_CLOSING_ACCELERATION*closingTime);
		
		/*
		 * Calculate the time taken to reach mould protect position where mould velocity is zero
		 * Initial velocity is CLS SP Speed
		 * Final velocity is zero (At mould protect position)
		 * Distance: Time taken to decelerate to zero velocity.
		 * Time/Distance is inclusive of the time at mould cls sp pos
		 */
		data[data.length-2][0]=getTime(process.getClsSPSpeed(), 0,CLAMP_CLOSING_DEACCELERATION);
		data[data.length-2][1]=getDistance(process.getClsSPSpeed(), data[data.length-2][0], CLAMP_CLOSING_DEACCELERATION);
		data[data.length-2][2]=0;
	
		//Distance at each set mould clsoing speed
		float[] posData={
				process.getMouldClosingOpenLimitPos()-process.getMouldClosedLimitPos(),
				process.getMouldClosedLimitPos()-process.getClsSlowPos(),
				process.getClsSlowPos()-process.getClsSPPos()
		};
		
		//Defined mould closing speed
		float[] speedData={
				process.getMouldClosingOpenLimitSpeed(),
				process.getMouldClosedLimitSpeed(),
				process.getClsSPSpeed()
		};
		
		int i=0;// Pointer for posData and speedData array
		int t=0;// Pointer for the result array: Data[][]
		float time=0f;
		float distance=0f;
		float speed=0f;
		//Calculate the areas of velocity change
		while(i<posData.length)
		{
			//Check if there's an acceleration or deceleration
			if(MouldingProcessUtil.getAcceleration(speed, speedData[i],2)>0)
			{
				//Calculate transitions in the graph
				time=getTime(speed,speedData[i],CLAMP_CLOSING_ACCELERATION);
				distance=getDistance(speed, time, CLAMP_CLOSING_ACCELERATION);
				speed=speedData[i];
				data[t][0]=time;
				data[t][1]=distance;
				data[t][2]=speed;
			}
			else
			{
				time=getTime(speed,speedData[i],CLAMP_CLOSING_DEACCELERATION);
				distance=getDistance(speed, time, CLAMP_CLOSING_DEACCELERATION);
				speed=speedData[i];
				data[t][0]=time;
				data[t][1]=distance;
				data[t][2]=speed;
			}
			i++;
			t=t+2;//Store result in every odd index in the result array so we can calculate the even index results later
			
			
		}
		//Reset Counters and temporary variables
		i=0;t=1;//T=1 so we can work on the even index results in the results array (data[][])
		distance=0;speed=0;time=0;
		while(i<posData.length)
		{
			distance=posData[i];
			speed=speedData[i];
			float distanceDelta=0f;
			/*
			 * The initial mould acceleration distance is included in the distance ClosingOpenLimitPos->ClosedLimitPos
			 * Between the initial acceleration and final deceleration the time/distance is halved between each 
			 * set distance
			 */
			if(i==0)
			{
				distanceDelta=data[t-1][1]+data[t+1][1]/2;
			}
			else
			/*
			 * The final deceleration to zero velocity at mould protect position
			 */
			if(i==posData.length-1)
			{
				distanceDelta=data[t-1][1]/2+data[t+1][1];
			
			}
			else
			{
				distanceDelta=data[t-1][1]/2+data[t+1][1]/2;
			}
			
			//If the distance change is greater than the distance of pre set position then distance is set to zero
			if(distanceDelta>distance)
			{
				distance=0;
			}
			else
			{
				distance-=distanceDelta;
			}
			//Calculate time and save in result array
			time=distance/speed;
			data[t][0]=time;
			data[t][1]=distance;
			data[t][2]=speed;
			i++;
			t=t+2;
		}
		

//		distance=0f;
//		time=0f;
//		for(float[] f:data)
//		{
//			distance+=f[1];
//			time+=f[0];
//			System.out.printf("Distance(%.3f) Time(%.3f) Speed(%.3f)%n",f[1],f[0],f[2]);
//			//System.out.printf("Distance(%.3f) Time(%.3f) Speed(%.3f)%n",distance,time,f[2]);
//		}

		
		
		return data;
	}
	
	

}


