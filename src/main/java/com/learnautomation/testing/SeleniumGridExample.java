package com.learnautomation.testing;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.learnautomation.pages.HomePage;

public class SeleniumGridExample {

	WebDriver driver;
	
	@Test
	public void testOnChromeWin11() throws MalformedURLException
	{
		DesiredCapabilities cap=new DesiredCapabilities();
		cap.setCapability("os_version", 11);
		cap.setCapability("browser", "Chrome");
		cap.setCapability("os", "Windows");
		
		URL url=new URL("https://mukeshotwani_p5NVfe:5ZAx21YD45weRsZSecRy@hub-cloud.browserstack.com/wd/hub");
	
		driver=new RemoteWebDriver(url, cap);
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		HomePage home=new HomePage(driver);
		home.loginToApplication("Admin", "admin123");
	}
	
	
	@AfterClass
	public void closeBrowser()
	{
		driver.quit();
		
	}

}
