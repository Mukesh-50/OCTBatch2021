package com.learnautomation.listener;

import java.lang.reflect.Field;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class ReportListener implements ITestListener {

	static ExtentReports extent = ExtentManager.createInstance();

	ThreadLocal<ExtentTest> parentTest = new ThreadLocal<>();

	public void onTestStart(ITestResult result) 
	{
		System.out.println("LOG:INFO- Creating New Test Object");
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
		parentTest.set(extentTest);
	}

	public void onTestSuccess(ITestResult result) 
	{
		System.out.println("LOG:INFO- Test Passed");
		parentTest.get().pass("Test Passed");

	}

	public void onTestFailure(ITestResult result) {

		WebDriver driver=null;
		
		try 
		{
			Field driverField=result.getTestClass().getRealClass().getDeclaredField("driver");
			
			 Object obj= driverField.get(result.getInstance());
			
			 driver=(WebDriver)obj;
			 
		} catch (Exception e) {
			
			System.out.println("Cant access variables/fields");
		}
		
	
		TakesScreenshot ts=(TakesScreenshot)driver;
		

		System.out.println("LOG:INFO- Test Failed");
		parentTest.get().fail("Test Failed " + result.getThrowable().getMessage(),
				MediaEntityBuilder.createScreenCaptureFromBase64String(ts.getScreenshotAs(OutputType.BASE64)).build());
		
	}

	public void onTestSkipped(ITestResult result) {

		System.out.println("LOG:INFO- Test Skipped");
		parentTest.get().skip("Test Skipped " + result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext context) 
	{
		System.out.println("LOG:INFO- Report getting ready");
		extent.flush();
		System.out.println("LOG:INFO- Report Generated");
	}

}
