package com.learnautomation.testing;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class SeleniumGridExample4 {

	
	@Test
	public void testOnChromeWin11() throws MalformedURLException
	{
		
		DesiredCapabilities cap=new DesiredCapabilities();
		cap.setCapability("platformName","linux");

		//cap.setPlatform(Platform.LINUX);
		
		cap.setCapability("browserName", "chrome");
		
		//cap.setBrowserName(BrowserType.CHROME);
		
		cap.setCapability("se:recordVideo",true);
		cap.setCapability("se:timeZone", "US/Pacific");
		cap.setCapability("se:screenResolution", "1920x1080");
		
	
		URL url=new URL("http://localhost:4444/wd/hub");
	
		WebDriver driver=new RemoteWebDriver(url, cap);
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		driver.quit();
		
	}

}
