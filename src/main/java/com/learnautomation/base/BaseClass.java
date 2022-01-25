package com.learnautomation.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.learnautomation.dataprovider.ConfigUtility;
import com.learnautomation.factory.BrowserFactory;

/*
 * 	Each and Every testcase will use this class
 * 
 * 
 */


public class BaseClass {
	
	public WebDriver driver;
	
	public WebDriver getDriver()
	{
		return driver;		
	}
	
	@Parameters({"remote","os","os_version","browser","browser_version"})
	@BeforeClass
	public void setUP(@Optional("false")String remote,@Optional("")String os,@Optional("") String os_version,@Optional("")String browser,@Optional("")String browser_version)
	{	
		
		if(remote.equalsIgnoreCase("true"))
		{
			System.out.println("LOG:INFO- Test will be executing on cloud");
			
			BrowserFactory obj=new BrowserFactory();
			
			driver=obj.startBrowser(os, os_version, browser,browser_version);
			
			driver.get(ConfigUtility.getProperty("url"));
			
			System.out.println("LOG:INFO- Session started on cloud");
		}
		else
		{
			System.out.println("LOG:INFO- Test will be executing locally");
			
			System.out.println("Thread Information "+Thread.currentThread().getId());
			
			BrowserFactory obj=new BrowserFactory();
			
			driver= obj.startBrowser(ConfigUtility.getProperty("browser"));
			
			driver.get(ConfigUtility.getProperty("url"));
			
			System.out.println("LOG:INFO- Browser Started");
		}
			
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
		System.out.println("LOG:INFO- Browser closed ");
	}

}
