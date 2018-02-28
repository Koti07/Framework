package com.opencart.tests;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageoperations.ExtentReportsClass;
import pageoperations.Operations;
import utilities.ExcelUtils;

import com.relevantcodes.extentreports.LogStatus;

public class OrderHistory extends ExtentReportsClass {

	WebDriver driver = null;
	Operations ooperations;
	String url; 

	/*@BeforeClass

	public void Initialize(){

		//System.setProperty("webdriver.chrome.driver", "D:/Softwaresdump/chromedriver.exe");
		//driver = new ChromeDriver();
		//	driver = new FirefoxDriver();
		url = "http://localhost:4444/wd/hub";
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("firefox");
            capabilities.setPlatform(Platform.WINDOWS);
            driver = new RemoteWebDriver(new URL(url), capabilities);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    		driver.manage().window().maximize();
    		driver.get("http://10.207.182.108:81/opencart");
        }catch(Exception e){
            e.printStackTrace();

        }
	}
	 */

	@BeforeClass

	public void Initialize(){

		System.setProperty("webdriver.chrome.driver", "D:/Softwaresdump/chromedriver.exe");
		driver = new ChromeDriver();
		//	driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test(priority='1')
	public void Orderhistory() throws Exception{
		driver.get("http://10.207.182.108:81/opencart");
		setLogger(getExtent().startTest("Orderhistory"));
		String sPath = "D://framework//DataEngine.xlsx";
		ooperations = new Operations(driver);

		// Here we are passing the Excel path and SheetName as arguments to connect with Excel file 
		ExcelUtils.setExcelFile(sPath, "TC");

		//Hard coded values are used for Excel row & columns for now
		//In later chapters we will replace these hard coded values with varibales
		//This is the loop for reading the values of the column 3 (Action Keyword) row by row
		for (int iRow=1;iRow<=6;iRow++){
			//Storing the value of excel cell in sActionKeyword string variable
			String sActionKeyword = ExcelUtils.getCellData(iRow, 3);

			//Comparing the value of Excel cell with all the project keywords
			if(sActionKeyword.equals("login")){
				//This will execute if the excel cell value is 'openBrowser'
				//Action Keyword is called here to perform action
				//ActionKeywords.openBrowser();
				ooperations.login();
				getLogger().log(LogStatus.INFO, "Login completed");
				System.out.println("Login");}
			else if(sActionKeyword.equals("addtocart")){
				//ActionKeywords.navigate();
				ooperations.addtocart();
				getLogger().log(LogStatus.INFO, "Add to cart completed");
				System.out.println("addtocart completed");}
			else if(sActionKeyword.equals("updatequantity")){
				//ActionKeywords.click_MyAccount();
				try {
					ooperations.updatequantity("2");
					getLogger().log(LogStatus.INFO, "cart update completed");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(sActionKeyword.equals("checkout")){
				//ActionKeywords.navigate();
				ooperations.checkout();
				getLogger().log(LogStatus.INFO, "Checkout & Verifying order history completed");
				System.out.println("checkout completed");}

			else if(sActionKeyword.equals("returnordersubmition")){
				//ActionKeywords.navigate();
				ooperations.returnordersubmition();
				getLogger().log(LogStatus.INFO, "Order submition completed");
				System.out.println("returnordersubmition completed");}

			else if(sActionKeyword.equals("logout")){
				//ActionKeywords.navigate();
				ooperations.logout();
				getLogger().log(LogStatus.INFO, "Logout completed");
				System.out.println("logout completed");}


		}
		getLogger().log(LogStatus.PASS, "Checking order History Pass");




	}

	@AfterClass
	public void closingbrowser(){
		driver.close();

	}
}
