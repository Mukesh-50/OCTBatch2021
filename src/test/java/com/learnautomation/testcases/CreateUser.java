package com.learnautomation.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.learnautomation.base.BaseClass;
import com.learnautomation.dataprovider.CustomDataProvider;
import com.learnautomation.pages.AdminPage;
import com.learnautomation.pages.DashboardPage;
import com.learnautomation.pages.HomePage;

public class CreateUser extends BaseClass 
{
	
	public WebDriver driver;
	HomePage home;
	DashboardPage dash;
	AdminPage admin;

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
	
	Assert.assertTrue(dash.getCurrentURL().contains("dashboard"),"Login failed");
	
	}
	
	@Test(priority = 2,dataProvider = "newUserDetails",dataProviderClass = CustomDataProvider.class)
	public void createUser(String userRole,String empName,String uname,String status,String password)
	{
		admin=dash.clickOnAdminTab();
		
		boolean result=admin.createUser(userRole, empName, uname, status, password);
		
		Assert.assertTrue(result,"User creation failed");
		
	}
	

	@Test(priority = 3, dependsOnMethods = "loginWithAdmin")
	public void logout() 
	{
		dash.logoutFromApplication();
		
		Assert.assertTrue(dash.getCurrentURL().contains("login"),"Logout did not complete");
	}

}
