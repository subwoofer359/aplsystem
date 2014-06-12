package org.amc.myservlet.test.ui;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByTagName;

import static org.junit.Assert.*;
public class TestUserUI
{
	private static TestPartUI fixture;
	
	@BeforeClass
	public static void setUpTestFixture()
	{
		TestPartUI.setUpTestFixture();
		
	}

	@Before
	public void setUp()
	{
		fixture=new TestPartUI();
		fixture.setUp();
	}
	
	@After
	public void tearDown()
	{
		fixture.tearDown();
	}
	
	/**
	 * Bug 0001: On updating of user's roles a JPA exception is thrown
	 */
	@Test
	public void testBug0001()
	{
		WebDriver driver=fixture.getWebDriver();
		fixture.login();
		for(int t=0;t<2;t++)
		{
			driver.get("http://192.168.1.6:8080/myservlet/APLSystemServlet");
			//Enter User page
			WebElement element=driver.findElement(By.id("users"));
			element.click();
		
		
			//Select first user and submit form
			List<WebElement> elements=driver.findElements(By.tagName("tr"));
		
			//Element at index 0 is the table header
			if(elements.size()>1)
			{
				element=elements.get(1);
				element.click();
				element=driver.findElement(By.id("edit"));
				element.click();
			
				//Change roles
				elements=driver.findElements(By.name("role"));
				for(WebElement tElement:elements)
				{
					tElement.click();
				}
				element=driver.findElement(By.id("edit"));
				element.click();
				try
				{
					Thread.sleep(1000);
				} 
				catch (InterruptedException e)
				{
					//Do nothing
				}
				System.out.println(driver.getCurrentUrl());
				System.out.println(driver.getTitle());
				assertFalse(driver.getTitle().contains("Error has occurred"));
			}
			else
			{
				assertTrue("No users found in table to test",false);
			}
		}
		fixture.logout();
	}
}
