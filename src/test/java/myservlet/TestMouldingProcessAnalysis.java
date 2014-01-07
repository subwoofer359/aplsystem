package myservlet;

import junit.framework.Assert;

import org.amc.servlet.model.MouldProcessAnalysis;
import org.amc.servlet.model.MouldingProcess;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestMouldingProcessAnalysis
{

	private MouldingProcess m=new MouldingProcess();
	
	@Test
	public void caseOne()
	{
		m.setPosTran(14);
		m.setShotSize(100);
		m.setInjectionSpeed_1(80);
		m.setInjectionSpeed_2(60);
		m.setInjectionSpeed_3(40);
		m.setInjSpeedPosition_1(80);
		m.setInjSpeedPosition_2(20);
		
		float expectedStartPosition=114f;
		float expectedInjectionTime=1.57f;
		
		MouldProcessAnalysis a=new MouldProcessAnalysis(m);
		assertEquals(expectedStartPosition,a.getStartPosition(),0.1);
		assertEquals(expectedInjectionTime,a.getTotalInjectionTime(),0.1);
		float[][] sr=a.getTimeSpeed();
		float[] times={0f,0.425f,0.425f,1.425f,1.425f,1.575f,1.575f};
		float[] speed={80,80,60,60,40,40,0};
		
		for(int i=0;i<sr.length;i++)
		{
			assertEquals(sr[i][0],times[i],0.1);
			assertEquals(sr[i][1],speed[i],0.1);
		}
	}
	
	@Test
	public void caseTwo()
	{
		m.setPosTran(10);
		m.setShotSize(200);
		m.setInjectionSpeed_1(100);
		m.setInjectionSpeed_2(60);
		m.setInjectionSpeed_3(80);
		m.setInjectionSpeed_4(60);
		m.setInjectionSpeed_5(40);
		m.setInjectionSpeed_6(20);
		
		m.setInjSpeedPosition_1(180);
		m.setInjSpeedPosition_2(140);
		m.setInjSpeedPosition_3(100);
		m.setInjSpeedPosition_4(80);
		m.setInjSpeedPosition_5(50);
		m.setInjSpeedPosition_6(0);
		
		MouldProcessAnalysis a=new MouldProcessAnalysis(m);
		float[][] sr=a.getTimeSpeed();
		float[] times={0f,0.3f,0.3f,0.96f,0.96f,1.46f,1.46f,1.79f,1.79f,2.54f,2.54f,4.54f,4.54f};
		float[] speed={100,100,60,60,80,80,60,60,40,40,20,20,0};
		
		for(int i=0;i<sr.length;i++)
		{
			assertEquals(sr[i][0],times[i],0.1);
			assertEquals(sr[i][1],speed[i],0.1);
		}
		
	}
	
}
