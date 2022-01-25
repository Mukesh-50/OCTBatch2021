package com.learnautomation.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.learnautomation.base.BaseClass;
import com.learnautomation.dataprovider.CustomDataProvider;
import com.learnautomation.pages.DashboardPage;
import com.learnautomation.pages.HomePage;

public class LoginWithPOM extends BaseClass {

	public WebDriver driver;
	HomePage home;
	DashboardPage dash;

	@BeforeMethod
	public void setupDriver()
	{
		driver=getDriver();
	}
	
	@Test(priority = 1,dataProvider = "loginDataExcel",dataProviderClass = CustomDataProvider.class)
	public void loginWithAdmin(String uname,String password) 
	{
		
	home=PageFactory.initElements(driver, HomePage.class);	
	
	dash=home.loginToApplication(uname, password);
	
	Assert.assertTrue(dash.getCurrentURL().contains("dashboard"),"Login failed please try again");
	
	}

	@Test(priority = 2, dependsOnMethods = "loginWithAdmin")
	public void logout() 
	{
		dash.logoutFromApplication();
		
		Assert.assertTrue(dash.getCurrentURL().contains("login"),"Logout did not complete");
	}

}
