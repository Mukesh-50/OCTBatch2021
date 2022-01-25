package com.learnautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.learnautomation.helper.Utility;

public class AdminPage 
{
	WebDriver driver;
	
	public AdminPage(WebDriver driver)
	{
		this.driver=driver;	
	}
	
	By addUser=By.name("btnAdd");
	
	By userRole=By.xpath("//select[contains(@id,'userType')]");
	
	By empName=By.xpath("//input[contains(@id,'empName')]");
	
	By username=By.xpath("//input[contains(@id,'userName')]");
	
	By pass=By.xpath("//input[contains(@id,'password')]");
	
	By confirmPass=By.id("systemUser_confirmPassword");
	
	By meter=By.xpath("//span[contains(@id,'strength_meter')]");
	
	By saveButton=By.id("btnSave");
	
	//old
	//By successMsg=By.xpath("//div[contains(text(),' Successfully Saved')]");

	//new
	By successMsg=By.xpath("//*[contains(text(),'Successfully Saved') and @class]");
	
	public boolean createUser(String uRole,String employeeName,String uName,String status,String password)
	{
		boolean userStatus=false;
		
		driver.findElement(addUser).click();
	
		Utility.selectFromDropDownUsingText(driver, userRole, uRole);
		
		driver.findElement(empName).sendKeys(employeeName);
		
		driver.findElement(username).sendKeys(uName);
		
		driver.findElement(pass).sendKeys(password);
		
		driver.findElement(confirmPass).sendKeys(password);
		
		if(Utility.waitForWebElement(driver, meter).isDisplayed())
		{
			driver.findElement(saveButton).click();
		}
		
		 if(Utility.waitForWebElement(driver, successMsg).isDisplayed())
		 {
			 userStatus=true;
		 }
		
		return userStatus;
	}
	
}
