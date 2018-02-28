package com.opencart.tests;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

import pageoperations.ExtentReportsClass;
import pageoperations.MultiBrowser;
import pageoperations.Operations;
import utilities.ExcelData;
import utilities.WriteData;

import com.opencart.pages.AccountLogoutPage;
import com.opencart.pages.GalaxyProductPage;
import com.opencart.pages.HomePage;
import com.opencart.pages.LoginPage;
import com.opencart.pages.MyWishlistPage;
import com.opencart.pages.RegistrationPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class RegistrationAddToCart extends ExtentReportsClass {
	
	WebDriver driver = null;
	RegistrationPage registrationPage;
	HomePage homePage;
	GalaxyProductPage galaxyProductPage;
	MyWishlistPage myWishlistPage;
	MultiBrowser multibrowser;
	Operations loginpageope;
	String url;
	
	
/*	@BeforeSuite

	public void Initialize(){

		System.setProperty("webdriver.chrome.driver", "D:/Softwaresdump/chromedriver.exe");
		driver = new ChromeDriver();
		//	driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://10.207.182.108:81/opencart");
	}
	*/
	
	
	/*	@BeforeSuite

	public void Initialize(){

		System.setProperty("webdriver.chrome.driver", "D:/Softwaresdump/chromedriver.exe");
		driver = new ChromeDriver();
		//	driver = new FirefoxDriver();
		url = "http://localhost:4444/wd/hub";
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("chrome");
            capabilities.setPlatform(Platform.WINDOWS);
            driver = new RemoteWebDriver(new URL (url), capabilities);
           
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    		driver.manage().window().maximize();
    		driver.get("http://10.207.182.108:81/opencart");
        }catch(Exception e){
            e.printStackTrace();
		
        }*/
	
	@BeforeClass

	public void Initialize(){

		System.setProperty("webdriver.chrome.driver", "D:/Softwaresdump/chromedriver.exe");
		driver = new ChromeDriver();
		//	driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://10.207.182.108:81/opencart");
	}
	
	//Test to create a new account for the user - [Used Apache POI to read details from Excel]
		@Test(priority=1, dataProvider="User Details")
		public void registration(String firstname,String lastname,String emailAddress,String telephoneNum,String address1,String cityName,String postcodeNum,String country,String zone,String pwd,String confirm_pwd, String reviewer,String review1, String review2, String rating) throws Exception{
			setLogger(getExtent().startTest("Registration and Add to Cart"));
		   
		      homePage = new HomePage(driver);
		      
		      //Calling method to click on 'Create Account' link
		      registrationPage = homePage.clickOnCreateLink();
		      getLogger().log(LogStatus.INFO, "Moved to Account Creation page");
		      
		      //Calling method to fill user details in Registration page and verify account is created
		registrationPage.fillDetailsAndRegister(firstname,lastname,emailAddress,telephoneNum,address1,cityName,postcodeNum,country,zone,pwd,confirm_pwd);
		      try{
		      Assert.assertEquals("Your Account Has Been Created!", driver.getTitle(),"Titles Not Matched: New Account Not Created");
		     // extentTest.log(LogStatus.INFO, "Registration Successful");
		      getLogger().log(LogStatus.INFO, "Account Creation completed");
		      }catch(Exception e){
		            //extentTest.log(LogStatus.INFO, "Registration is not successful");
		      }
		      
		      //Calling method to navigate to Home Page after registration
		      homePage=registrationPage.navigateToHome();
		      galaxyProductPage=homePage.clickOnGalaxyAdvTab();
		      Assert.assertEquals("Samsung Galaxy Tab 10.1", galaxyProductPage.heading.getText(), "User is in " +"'"+driver.getTitle()+"'"+" page");
		    
		      
		      //Calling method to add reviews on the product
		      galaxyProductPage.reviewOnProduct(reviewer, review1, rating);
		      getLogger().log(LogStatus.INFO, "First review completed");
		      //Handling the error toast if displayed, when adding reviews
		      try{
		            if(galaxyProductPage.warningToast.getText().equalsIgnoreCase("Warning: Review Text must be between 25 and 1000 characters!")){
		                  galaxyProductPage.reviewOnProduct(reviewer, review2, rating);}
		                
		      }catch(org.openqa.selenium.NoSuchElementException e){
		            Thread.sleep(10000);
		            if(galaxyProductPage.successToast.getText().equalsIgnoreCase("Thank you for your review. It has been submitted to the webmaster for approval.")){
		         
		          
		          
		            }
		      }
		      getLogger().log(LogStatus.INFO, "Second review completed");            
		      //Calling method to click on 'Add to Wishlist' link and verify success toast is displayed
		      galaxyProductPage.clickOnAddToWishlist();
		      Thread.sleep(1500);
		      Assert.assertTrue(galaxyProductPage.getSuccessMessage().contains("Success"), "Product is not added to Wishlist");
		      //extentTest.log(LogStatus.PASS,"Success: You have added Samsung Galaxy Tab 10.1 to your wish list!");
		    
		      
		      //Calling method to close the success toast
		      galaxyProductPage.closeSuccesstoast();
		      
		      //Calling method to click on 'Wishlist' link and check user is redirected to 'My Wishlist' page
		      myWishlistPage = galaxyProductPage.clickOnWishlist();
		      
		      Assert.assertTrue(myWishlistPage.getTitle().equals("My Wish List"), "User is not redirected to wishlist page");
		      //extentTest.log(LogStatus.INFO,"User is redirected to My Wishlist Page");
		      
		      
		      //Verifying count in 'Wishlist' link is equal to number of products in the page
		      Assert.assertEquals(myWishlistPage.valueInWishlistLink(), myWishlistPage.numOfProductsInTable(), "Value shown in wishlist link is different from number of records in the table");
		      //extentTest.log(LogStatus.INFO,"Product added: Value shown in wishlist link is equal to number of records in the table");
		    //  extentTest.log(LogStatus.INFO, "Success: Product added to Wishlist and Verified");
		      
		      
		      //Calling method to get the unit prices of product and write to text file
		      for(String price: myWishlistPage.storeUnitPrices()){
		            
		            /*WriteData is the library class created to write data to text file
		            * Created object of WriteData class and passed file name to create in specified location
		            */
		            WriteData writeData = new WriteData("unitprices");
		            writeData.writeTextToFile(price);
		      }           
		      getLogger().log(LogStatus.INFO, "Price stored");
		      //Calling method to add product to cart and verifying the success toast
		      myWishlistPage.addToCart();
		      getLogger().log(LogStatus.INFO, "Add to cart completed");
		      Thread.sleep(1500);
		      Assert.assertTrue(myWishlistPage.isSuccessToastDisplayed(), "Success message is not displayed");
		      
		      System.out.println("Add to cart: Success message is displayed");
		      Thread.sleep(3000);
		      
		      //Calling method to close the success toast
		      myWishlistPage.closeSuccessToast();
		      Thread.sleep(3000);           
		   //   extentTest.log(LogStatus.INFO, "Added product to Cart");
		      //Calling method to remove product from the list and click on continue
		      myWishlistPage.removeProductFromWishlistAndContinue();
		      
		      getLogger().log(LogStatus.INFO, "Product removed");
		      
		      AccountLogoutPage accountLogoutPage =myWishlistPage.logout();
		      Assert.assertTrue(accountLogoutPage.getLogoutMessage().equals("Account Logout"), "Account Logout message is not displayed");
		    
		      
		    //  extentTest.log(LogStatus.PASS, "Registration And Add To Cart:Succcessful");
		      Reporter.log("Registration And Add To Cart:Succcessful");
		      getLogger().log(LogStatus.PASS, "Registration And Add To Cart:Succcessful");
		}
	
	
	
	//Data provider - Sending user details for registration
	//Data provider - Sending user details for registration
        @DataProvider(name="User Details")
        public Object[][] userDetails() throws Exception{
              
              ExcelData excelData = new ExcelData("D:/framework/TestData_Opencart.xlsx",0);
              int rowscount =excelData.numOfRows();
              System.out.println("row"+rowscount);
              Object[][] data = new Object[rowscount][15];
              
              for(int row=0;row<rowscount;row++){
              for(int cells=0;cells<15;cells++){
                    
                    data[row][cells] = excelData.getData(row+1, cells);
              }
              }
              return data;
        }
    	
    	@AfterTest
    	public void closingbrowser(){
    		driver.close();

    	}


}
