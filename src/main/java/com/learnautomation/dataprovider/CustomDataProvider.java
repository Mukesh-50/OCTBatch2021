package com.learnautomation.dataprovider;

import org.testng.annotations.DataProvider;

public class CustomDataProvider 
{
	@DataProvider(name = "loginDataExcel")
	public static Object[][] getLoginDataWithExcel()
	{
		return ExcelUtility.getDataFromSheet("Login");	
	}
	
	@DataProvider(name = "newUserDetails")
	public static Object[][] getUserDataWithExcel()
	{
		return ExcelUtility.getDataFromSheet("UserDetails");	
	}
	
}
