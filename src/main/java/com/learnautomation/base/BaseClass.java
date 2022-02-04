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
	
	@Parameters({"remote","os","os_version","browser","browser_version","url"})
	@BeforeClass
	public void setUP(@Optional("false")String remote,@Optional("")String os,@Optional("") String os_version,@Optional("")String browser,@Optional("")String browser_version,
			@Optional("")String url)
	{	
		BrowserFactory obj;
		
		if(remote.equalsIgnoreCase("true"))
		{
			System.out.println("LOG:INFO- Test will be executing on cloud");
			
			obj=new BrowserFactory();
			
			driver=obj.startBrowser(os, os_version, browser,browser_version);
			
			System.out.println("LOG:INFO- Session started on cloud");
		}
		else
		{
			System.out.println("LOG:INFO- Test will be executing locally");
			
			System.out.println("Thread Information "+Thread.currentThread().getId());
			
			obj=new BrowserFactory();	
			System.out.println("LOG:INFO- Browser Started");
		}
		driver= obj.startBrowser(browser);
		driver.get(url);
			
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
		System.out.println("LOG:INFO- Browser closed ");
	}

}
