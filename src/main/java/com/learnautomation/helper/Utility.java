package com.learnautomation.helper;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Utility {

	
	
	public static void selectFromDropDownUsingText(WebDriver driver,By locator,String valueToBeSelected)
	{
		Select sel=new Select(driver.findElement(locator));
		sel.selectByVisibleText(valueToBeSelected);
	}
	
	public static void selectFromDropDownUsingValue(WebDriver driver,By locator,String valueToBeSelected)
	{
		Select sel=new Select(driver.findElement(locator));
		sel.selectByValue(valueToBeSelected);
	}
	
	public static void selectFromDropDownUsingIndex(WebDriver driver,By locator,int indexToBeSelected)
	{
		Select sel=new Select(driver.findElement(locator));
		sel.selectByIndex(indexToBeSelected);
	}
	

	public static void highLightElement(WebDriver driver, WebElement element)
	{
	JavascriptExecutor js=(JavascriptExecutor)driver; 
	 
	js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
	 
	try 
	{
	Thread.sleep(1000);
	} 
	catch (InterruptedException e) {
	 
	System.out.println(e.getMessage());
	} 
	 
	js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", element); 
	 
	}
	
	
	public static WebElement waitForWebElement(WebDriver driver,By locator)
	{
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		
		//WebElement ele= wait.until(ExpectedConditions.elementToBeClickable(locator));
		
		WebElement ele= wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
		//highLightElement(driver, ele);
		return ele;
	}

	// method will try first with webelement click then JS Click
	public static void clickElement(WebDriver driver,By locator)
	{	
		
		try
		{
			waitForWebElement(driver, locator).click();
		}
		catch(Exception e)
		{
			System.out.println("LOG:INFO- WebElement click did not work Trying with JS Click");
			
			JavascriptExecutor js=(JavascriptExecutor)driver;
			
			js.executeScript("arguments[0].click()",waitForWebElement(driver, locator));
			
		}	
	}
	
	// method will try first with webelement click then JS Click
	public static void typeElement(WebDriver driver,By locator,String text)
	{	
		
		try
		{
			waitForWebElement(driver, locator).sendKeys(text);
		}
		catch(Exception e)
		{
			System.out.println("LOG:INFO- WebElement sendKeys did not work Trying with JS values");
			
			JavascriptExecutor js=(JavascriptExecutor)driver;
			
			js.executeScript("arguments[0].value=arguments[1]",waitForWebElement(driver, locator),text);
			
		}	
	}
	
	
	
	/*
	 * public static <T> void switchToFrame(WebDriver driver, T element) {
	 * 
	 * if (element.getClass().getName().contains("Integer")) {
	 * driver.switchTo().frame((int) element); } else if
	 * (element.getClass().getName().contains("String")) {
	 * driver.switchTo().frame((String) element); } else {
	 * driver.switchTo().frame((WebElement) element); }
	 * 
	 * }
	 */

	public static void switchToParentFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public static void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public static void dismissAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public static String captureAlertTextAndAccept(WebDriver driver) {
		Alert alt = driver.switchTo().alert();

		String alt_msg = alt.getText();

		alt.accept();

		return alt_msg;
	}

	public static String captureDateTime() {
		// SimpleDateFormat myformat=new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");

		return new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
	}

	// It wont take screenshot of Alerts
	public static String captureScreenShot(WebDriver driver) throws WebDriverException, IOException {
		String screenshotPath = System.getProperty("user.dir") + "/ScreenShots/Selenium" + captureDateTime() + ".png";

		FileHandler.copy(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE), new File(screenshotPath));

		return screenshotPath;
	}

	public static String captureFullPageScreenShot(WebDriver driver) throws WebDriverException, IOException {
		String screenshotPath = System.getProperty("user.dir") + "/ScreenShots/Selenium_" + captureDateTime() + ".png";

		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(driver);

		ImageIO.write(screenshot.getImage(), "png", new File(screenshotPath));

		return screenshotPath;
	}

	// it will capture ss of webelement
	public static String captureScreenShot(WebDriver driver, WebElement element) throws IOException {
		File src = element.getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/ScreenShots/Selenium" + captureDateTime() + ".png";

		FileHandler.copy(src, new File(path));

		return path;
	}

	public static void selectValueFromAutoSuggestion(WebDriver driver, By locator, String valueToSelect) {

		List<WebElement> allValues = driver.findElements(locator);

		for (WebElement ele : allValues) {
			String values = ele.getText();

			System.out.println(valueToSelect);

			if (values.contains(valueToSelect)) {
				ele.click();
				break;
			}
		}
	}

	public static void selectValueFromAutoSuggestion(WebDriver driver, By locator1, By locator2, String valueToSelect1,
			String valueToSelect2) {

		List<WebElement> allValues = driver.findElements(locator1);

		for (WebElement ele : allValues) {
			String values = ele.getText();

			System.out.println(valueToSelect1);

			if (values.contains(valueToSelect1)) {
				ele.click();
				break;
			}
		}

		List<WebElement> allValues2 = driver.findElements(locator2);

		for (WebElement ele : allValues2) {
			String valuesnew = ele.getText();

			System.out.println(valuesnew);

			if (valuesnew.contains(valueToSelect2)) {
				ele.click();
				break;
			}
		}

	}

	public static List<String> imageChecker(List<WebElement> allURL) {

		List<String> allBrokenLink = new ArrayList<>();

		for (WebElement ele : allURL) {

			String linkURL = ele.getAttribute("src");

			try {
				URL url = new URL(linkURL);

				System.out.println("Log:INFO -Opening Connection");

				HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();

				urlConn.connect();

				System.out.println("Log:INFO -Connection Done");

				int code = urlConn.getResponseCode();

				String responseMsg = urlConn.getResponseMessage();

				System.out.println("Response from server Code: " + code + " Server message :" + responseMsg);

				if (code < 400) {
					System.out.println("Log:INFO -Image  is valid");
				} else if (code > 400) {
					System.out.println("Log:INFO -Image is broken " + linkURL);
					allBrokenLink.add(linkURL);

				}

			} catch (Exception e) {
				System.out.println("Exception " + e.getMessage());
			}

		}

		return allBrokenLink;

	}

	public static List<String> linkChecker(List<WebElement> allURL) {

		List<String> allBrokenLink = new ArrayList<>();

		for (WebElement ele : allURL) {

			String linkURL = ele.getAttribute("href");

			try {
				URL url = new URL(linkURL);

				System.out.println("Log:INFO -Opening Connection");

				HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();

				urlConn.connect();

				System.out.println("Log:INFO -Connection Done");

				int code = urlConn.getResponseCode();

				String responseMsg = urlConn.getResponseMessage();

				System.out.println("Response from server Code: " + code + " Server message :" + responseMsg);

				if (code < 400) {
					System.out.println("Log:INFO -Link is valid");
				} else if (code > 400) {
					System.out.println("Log:INFO -Link is broken " + linkURL);
					allBrokenLink.add(linkURL);

				}

			} catch (Exception e) {
				System.out.println("Exception " + e.getMessage());
			}

		}

		return allBrokenLink;

	}
}
