package com.opencart.tests;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageoperations.ExtentReportsClass;


import pageoperations.Operations;

import com.relevantcodes.extentreports.LogStatus;

public class OpencartDemo extends ExtentReportsClass {

	WebDriver driver = null;
	Operations ooperations;
	String url;



	@BeforeClass

	public void Initialize(){

		System.setProperty("webdriver.chrome.driver", "D:/Softwaresdump/chromedriver.exe");
		driver = new ChromeDriver();
		//	driver = new FirefoxDriver();
		url = "http://localhost:4444/wd/hub";
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("chrome");
            capabilities.setPlatform(Platform.WINDOWS);
            driver = new RemoteWebDriver(new URL(url), capabilities);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    		driver.manage().window().maximize();
    		driver.get("http://10.207.182.108:81/opencart");
        }catch(Exception e){
            e.printStackTrace();

        }
	}


	/*@BeforeClass

	public void Initialize(){

		System.setProperty("webdriver.chrome.driver", "D:/Softwaresdump/chromedriver.exe");
		driver = new ChromeDriver();
		//	driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}*/

	@Test(priority='1')
	public void productcomparison() throws InterruptedException{
		driver.get("http://10.207.182.108:81/opencart");
		setLogger(getExtent().startTest("productcomparison"));
		//logger = extent.createTest("Login");
		ooperations = new Operations(driver);

		//logger = extent.startTest("Login");

		ooperations.login();
		getLogger().log(LogStatus.INFO, "Login completed");

		ooperations.search();
		getLogger().log(LogStatus.INFO, "comparing the products completed");
		ooperations.addtocartfromcomp();
		getLogger().log(LogStatus.INFO, "adding the product from comparison page is completed");


		ooperations.checkoutfromcomp();

		getLogger().log(LogStatus.INFO, "Checkout is completed");
		ooperations.subscribenewsletter();
		getLogger().log(LogStatus.INFO, "subscribe to newsletter is completed");
		ooperations.logout();
		getLogger().log(LogStatus.INFO, "Logout completed");
		getLogger().log(LogStatus.PASS, "Product comparison test case is completed");
	}
	

	
	@Test(priority='2')
	public void addingPhonestocart() throws InterruptedException{
		driver.get("http://10.207.182.108:81/opencart");
		setLogger(getExtent().startTest("addingPhonestocart"));
		//logger = extent.createTest("Login");
		ooperations = new Operations(driver);

		//logger = extent.startTest("Login");

		ooperations.login();
		getLogger().log(LogStatus.INFO, "Login completed");
		
		ooperations.addtocartfromhome();
		getLogger().log(LogStatus.INFO, "Add to cart completed");
		
		ooperations.checkoutfromhome();
		
		getLogger().log(LogStatus.INFO, "checkout completed");
		
		//ooperations.ordersubmition();
		ooperations.logout();
		getLogger().log(LogStatus.INFO, "Logout completed");
		getLogger().log(LogStatus.PASS, "adding phones form home is pass");
		
	

	}

	
	
	
	@AfterClass
	public void closingbrowser(){
		driver.close();

	}

}
