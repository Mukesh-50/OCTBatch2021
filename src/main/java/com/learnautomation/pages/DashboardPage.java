package com.learnautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage 
{

	WebDriver driver;
	
	public DashboardPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	By allTabsMenu=By.xpath("//ul[@id='mainMenuFirstLevelUnorderedList']//b");

	By welcomeText=By.id("welcome");
	
	By logoutText=By.linkText("Logout");
	
	By adminTab=By.xpath("//b[text()='Admin']");
	
	public String getWelcomeText()
	{
		return driver.findElement(welcomeText).getText();
	}
	
	public String getCurrentURL()
	{
		return driver.getCurrentUrl();
	}
	
	public void logoutFromApplication()
	{
		driver.findElement(welcomeText).click();
		driver.findElement(logoutText).click();
	}
	
	public AdminPage clickOnAdminTab()
	{
		driver.findElement(adminTab).click();
		AdminPage admin=new AdminPage(driver);
		return admin;
	}
	
}
