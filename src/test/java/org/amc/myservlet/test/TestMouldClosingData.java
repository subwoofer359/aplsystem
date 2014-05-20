package org.amc.myservlet.test;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.*;
import org.amc.model.MouldingProcessRemote;
import org.amc.servlet.model.*;

import static org.junit.Assert.*;

public class TestMouldClosingData
{
	@Ignore
	@Test
	public void testGetMouldClosingTimeData()
	{
		InitialContext ctx;

		try
		{
			Properties props = new Properties();
			props.put(Context.INITIAL_CONTEXT_FACTORY,
					"org.apache.openejb.client.RemoteInitialContextFactory");
			props.put(Context.PROVIDER_URL, "ejbd://127.0.0.1:4201");
			ctx = new InitialContext(props);

			int i = 0;
			while (i < 1000)
			{
				MouldingProcessRemote p = (MouldingProcessRemote) ctx
						.lookup("MouldingProcessRemote");
				float openlimit = 420f;
				float mouldClosingLimitPos = openlimit
						- (20 * (float) Math.random());
				float mouldClosedLimitPos = mouldClosingLimitPos
						- (200 * (float) Math.random());
				float clsSlowPos = mouldClosedLimitPos
						- (100 * (float) Math.random());
				float clsSPPos = clsSlowPos
						- (clsSlowPos * (float) Math.random());
				p.setMouldClosingOpenLimitPos(mouldClosingLimitPos);
				p.setMouldClosedLimitPos(mouldClosedLimitPos);
				p.setClsSlowPos(clsSlowPos);
				p.setClsSPPos(clsSPPos);

				float mouldClosingOpenLimitSpeed = 420 * (float) Math.random();
				float mouldClosedLimitSpeed = 420 * (float) Math.random();
				float clsSPSpeed = 100 * (float) Math.random();

				p.setMouldClosingOpenLimitSpeed(mouldClosingOpenLimitSpeed);
				p.setMouldClosedLimitSpeed(mouldClosedLimitSpeed);
				p.setClsSPSpeed(clsSPSpeed);

				float[][] data = MouldingProcessUtil.getMouldClosingTimeData(p);
				float result = 0f;
				System.out.printf("Expected:%f + %f + %f + %f = %f%n",
						mouldClosingLimitPos - mouldClosedLimitPos,
						mouldClosedLimitPos - clsSlowPos,
						clsSlowPos - clsSPPos, clsSPPos,
						(mouldClosingLimitPos - mouldClosedLimitPos)
								+ (mouldClosedLimitPos - clsSlowPos)
								+ (clsSlowPos - clsSPPos) + (clsSPPos));
				System.out.print("Actual:");
				for (float[] t : data)
				{
					result += t[1];
					System.out.printf("%f +", t[1]);
				}
				System.out.printf("=%f%n", result);
				System.out.printf("(%d)Expected:%f,Actual:%f%n", i,
						mouldClosingLimitPos, result);
				assertEquals(mouldClosingLimitPos, result, 0.01);
				i++;
			}
		} catch (NamingException ex)
		{
			ex.printStackTrace();
		}
	}

}
