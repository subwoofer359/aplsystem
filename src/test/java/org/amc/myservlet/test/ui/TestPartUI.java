package org.amc.myservlet.test.ui;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestPartUI
{
	
	private WebDriver driver;
	@BeforeClass
	public static void setUpTestFixture()
	{
		System.setProperty("webdriver.chrome.driver", "/home/adrian/Programming/Chromedriver/chromedriver");
	}
	
	@Before
	public void setUp()
	{
		driver = new ChromeDriver();
	}
	
	@After
	public void tearDown()
	{
		driver.close();
	}
	
	/**
	 * Log in to the System
	 */
	public void login()
	{
		driver.get("http://192.168.1.6:8080/myservlet");
		WebElement element =driver.findElement(By.className("login"));
		element.click();
		
		//Login
		WebElement userName=driver.findElement(By.name("j_username"));
		WebElement password=driver.findElement(By.name("j_password"));
		userName.sendKeys("jody");
		password.sendKeys("cr2032ux");
		userName.submit();
	}
	
	/**
	 * Log out of the System
	 */
	public void logout()
	{
		WebElement element=driver.findElement(By.id("userBox"));
		element.click();
	
		//Test that we are on the UserInfo page
		assertTrue(driver.getCurrentUrl().endsWith("myservlet/UserInfo"));
		
		List<WebElement> buttons=driver.findElements(By.id("logout"));
		
		for(WebElement button:buttons)
		{
			if(button.getTagName().equals("button"))
			{
				button.click();
				break;
			}
		}
	}
	@Test
	public void testLogin()
	{
		
		driver.get("http://192.168.1.6:8080/myservlet");
		
		//Test logging 5 fives to see if there any issues with Database connections
		for(int i=0;i<5;i++)
		{
			login();
			//Test we are on the Main Page
			assertTrue(driver.getCurrentUrl().endsWith("myservlet/APLSystemServlet"));
		
			logout();
		
			//Test we have logged out
			assertTrue(driver.getCurrentUrl().endsWith("myservlet/"));
		}

	}
	
	@Test
	public void addPart()
	{
		login();
		//Open Parts page
		WebElement element=driver.findElement(By.id("partsearch"));
		element.click();
		
		element=driver.findElement(By.id("add"));
		
		element.click();
		
		String[] names={"name","company","part_id","version","revision","colour","external","qss_no"};
		String[] values={"Roof Tile","ARCKIT","A2039349","1","1","white","true","AR:300"};
		
		for(int i=0;i<names.length;i++)
		{
			element=driver.findElement(By.name(names[i]));
			element.sendKeys(values[i]);
		}
		element.submit();
	}
	
	public WebDriver getWebDriver()
	{
		return driver;
	}
}
