package org.amc.servlet.model;

public class MouldingProcessUtil
{
	private final static float CLAMP_CLOSING_ACCELERATION=1250f;
	private final static float CLAMP_CLOSING_DEACCELERATION=1000f;
	
	
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
	
	public static float getMouldClosingTime(MouldingProcess process)
	{
		float sectionB=process.getMouldClosingOpenLimitPos()-process.getMouldClosedLimitPos();
		float sectionD=process.getMouldClosedLimitPos()-process.getClsSlowPos();
		float sectionF=process.getClsSlowPos()-process.getClsSPPos();
		
		float speed1=process.getMouldClosingOpenLimitSpeed();
		float speed2=process.getMouldClosedLimitSpeed();
		float speed3=process.getClsSPSpeed();
		
		float timeA=getTime(0,speed1,CLAMP_CLOSING_ACCELERATION);
		float distanceA=getDistance(0, timeA, CLAMP_CLOSING_ACCELERATION);
		
		float distanceB=sectionB-distanceA;
		float timeB=distanceB/speed1;
		
		
		// I finished HERE //
		return 0f;
	}
	
	
	public static void main(String[] args)
	{
		float sectionA=320-250;
		float sectionB=250-160;
		float sectionC=160-62;
		float sectionD=62-0;
		float speedA=350;
		float speedB=350;
		float speedC=200;
		float speedD=0;
		float totalTime=0;
		float acceleration=1250f;
		
		float timeA=getTime(0, 350, acceleration);
		float distanceA=getDistance(0, timeA, acceleration);
		
		float timeC=getTime(350,200,-1000);
		float distanceC=getDistance(350, timeC, -1000);
		
		float timeE=getTime(200,62,-1000);
		float distanceE=getDistance(200,timeE,-1000);
		
		float distanceB=sectionA-distanceA;
		float timeB=distanceB/speedA;
		
		float distanceC1=sectionB-(distanceC/2);
		float timeC1=distanceC1/speedB;
		
		float distanceD=sectionC-((distanceC/2)+distanceE);
		float timeD=distanceD/speedC;
		
		
		float timeF=getTime(200,0,-1000);
		float distanceF=getDistance(200, timeF, -1000);
		
		
		
		
		System.out.printf("(%.2f)+(%.2f)+(%.2f)+(%.2f)+(%.2f)+(%.2f)+(%.2f)=%.2f%n",timeA,timeB,timeC,timeC1,timeD,timeE,timeF,(timeA+timeB+timeC+timeC1+timeD+timeE+timeF));
		System.out.printf("(%.2f)+(%.2f)+(%.2f)+(%.2f)+(%.2f)+(%.2f)+(%.2f)=%.2f%n",distanceA,distanceB,distanceC,distanceC1,distanceD,distanceE,distanceF,(distanceA+distanceB+distanceC+distanceC1+distanceD+distanceE+distanceF));
		
		
		
		
		
		
	}
}


