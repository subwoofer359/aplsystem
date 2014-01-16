package org.amc.servlet.model;

public class MouldingProcessUtil
{
	private final static float CLAMP_CLOSING_ACCELERATION=1250f;
	private final static float CLAMP_CLOSING_DEACCELERATION=-1000f;
	
	
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
			if(position[pointer+1]!=0)
			{
				totalInjectionTime+=(position[pointer]-position[pointer+1])/speed[pointer];
				
			}
			else
			{
				totalInjectionTime+=(position[pointer]-process.getPosTran())/speed[pointer];
				break;
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
		float distance=(float)((intialVelocity*timeInSeconds)+(0.5*acceleration*Math.pow(timeInSeconds, 2)));
		return distance;
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
	
	/**
	 * 
	 * @param process
	 * @return summation of Mould Closing Time
	 */
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
	public static float[][] getMouldClosingTimeData(MouldingProcess process)
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
		float timeB=distanceB/speed1;
		data[1][0]=timeB;
		data[1][1]=distanceB;
		data[1][2]=speed1;
		
		float distanceD=expectedDistance2-(0.5f*distanceC+0.5f*distanceE);
		float timeD=distanceD/speed2;
		data[3][0]=timeD;
		data[3][1]=distanceD;
		data[3][2]=speed2;
		
		float distanceF=expectedDistance3-(0.5f*distanceE+distanceG);
		float timeF=distanceF/speed3;
		data[5][0]=timeF;
		data[5][1]=distanceF;
		data[5][2]=speed3;
		//After reaching the mould protect point(ClsSPPos) the mould is accelerated to when the mould faces meet
		
		float timeH=(float)Math.sqrt((2*process.getClsSPPos())/CLAMP_CLOSING_ACCELERATION);//t=sqrt(2*s/a)
		data[7][0]=timeH;
		data[7][1]=process.getClsSPPos();
		data[7][2]=(CLAMP_CLOSING_ACCELERATION*timeH);
		
		return data;
	}
	
	
	public static void main(String[] args)
	{
		
		MouldingProcess p=new MouldingProcess();
		p.setMouldClosingOpenLimitPos(320);
		p.setMouldClosedLimitPos(250);
		p.setClsSlowPos(160);
		p.setClsSPPos(62);
		p.setMouldClosingOpenLimitSpeed(350);
		p.setMouldClosedLimitSpeed(350);
		p.setClsSPSpeed(200);
		
		System.out.println(getMouldClosingTime(p));
		
		
		
		
	}
}


