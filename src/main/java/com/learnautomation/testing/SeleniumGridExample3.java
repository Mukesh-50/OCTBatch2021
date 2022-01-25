package com.learnautomation.testing;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class SeleniumGridExample3 {

	
	@Test
	public void testOnChromeWin11() throws MalformedURLException
	{
		
		DesiredCapabilities cap=new DesiredCapabilities();
		cap.setCapability("os_version", 10);
		cap.setCapability("browser", "Chrome");
		cap.setCapability("os", "Windows");
		
		URL url=new URL("https://mukeshotwani_p5NVfe:5ZAx21YD45weRsZSecRy@hub-cloud.browserstack.com/wd/hub");
	
		WebDriver driver=new RemoteWebDriver(url, cap);
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		driver.quit();
		
	}

}
