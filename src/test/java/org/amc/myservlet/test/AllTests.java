package org.amc.myservlet.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.amc.myservlet.test.spc.*;
import org.amc.myservlet.test.ui.*;

@RunWith(Suite.class)
@SuiteClasses({ 
				TestAPLUserController.class, 
				TestMaterialDAO.class,
				TestPartandMouldingProcessDAO.class, 
				TestUser.class,
				TestSPCMeasurment.class,
				TestSPCData.class,
				TestAPLSpcController.class,
				TestAPLSpcControllerIntegration.class,
				TestPartUI.class,
				TestUserUI.class
				})
public class AllTests
{

}
