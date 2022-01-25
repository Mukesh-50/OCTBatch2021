package com.learnautomation.factory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.learnautomation.dataprovider.ConfigUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {

	
	public WebDriver startBrowser(String os,String os_version,String browser,String browser_version)
	{
		
		WebDriver driver = null;
		DesiredCapabilities cap=new DesiredCapabilities();
		cap.setCapability("os_version",os_version);
		cap.setCapability("browser",browser);
		cap.setVersion(browser_version);
		cap.setCapability("os",os);
		
		String uname=ConfigUtility.getProperty("browserStackUname");
		String pass=ConfigUtility.getProperty("browserPassword");
		
		URL url;
		try 
		{
			url = new URL("https://"+uname+":"+pass+"@hub-cloud.browserstack.com/wd/hub");
			driver=new RemoteWebDriver(url, cap);
		} catch (MalformedURLException e) 
		{
			System.out.println("Could not connect to remote hub "+e.getMessage());
		}
	
		return driver;
	}
	
	
	
	
	public WebDriver startBrowser(String browser) 
	{
		WebDriver driver = null;

		if (browser.equalsIgnoreCase("Chrome")) {

			ChromeOptions opt = new ChromeOptions();
			opt.addArguments("--disable-notifications");
			opt.addArguments("--start-maximized");
			opt.addExtensions(new File("C:\\Users\\l\\Downloads\\selectorhub.crx"));

			Proxy p = new Proxy();
			//p.set
			
			p.setHttpProxy("http://129.123.90.0:5555");
			// opt.setCapability("proxy", p);
			// opt.setCapability(CapabilityType.PROXY,p);

			Map<String, Object> preference = new HashMap<>();
			preference.put("download.default_directory", "C:\\SeleniumDownload");
			opt.setExperimentalOption("prefs", preference);
			opt.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
			opt.setAcceptInsecureCerts(true);

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(opt);
			
			
		} else if (browser.equalsIgnoreCase("ChromeHeadless")) {
			ChromeOptions opt = new ChromeOptions();
			opt.setHeadless(true);
			//WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(opt);

		} else if (browser.equalsIgnoreCase("FF") || browser.equalsIgnoreCase("Firefox")) {

			FirefoxOptions opt = new FirefoxOptions();
			opt.setAcceptInsecureCerts(true);

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(opt);
		} else if (browser.equalsIgnoreCase("Edge")) {
			EdgeOptions opt = new EdgeOptions();
			opt.setAcceptInsecureCerts(true);
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver(opt);
		} else {
			System.out.println("Log-INFO-We do not support this browser");
		}

		driver.manage().window().maximize();
		// driver.manage().window().fullscreen();
		
		
		/*
		 * Timeouts time= driver.manage().timeouts();
		 * 
		 * // call before get method time.pageLoadTimeout(Duration.ofSeconds(20));
		 */
		
		// call before get method and call them only once
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
		
		/*
		 * 	Only waits for element presence/visibility (x,y>0)
		 * 	Global Wait 
		 *  no need to define multiple times
		 *  Don't mix with explicit wait
		 *  default polling is 250ms
		 *  
		 */
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		return driver;
	}
	
	public void closeBrowser(WebDriver driver)
	{
		driver.quit();
	}
	
}
