package com.opencart.tests;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageoperations.ExtentReportsClass;
import pageoperations.MultiBrowser;
import pageoperations.Operations;
import utilities.ExcelData;
import utilities.WriteData;
import com.opencart.pages.AccountLogoutPage;
import com.opencart.pages.GalaxyProductPage;
import com.opencart.pages.HomePage;
import com.opencart.pages.MyWishlistPage;
import com.opencart.pages.RegistrationPage;
import com.relevantcodes.extentreports.LogStatus;


public class demonew extends ExtentReportsClass {

	WebDriver driver = null;
	Operations ooperations;
	String url;

	
	RegistrationPage registrationPage;
	HomePage homePage;
	GalaxyProductPage galaxyProductPage;
	MyWishlistPage myWishlistPage;
	MultiBrowser multibrowser;
	Operations loginpageope;
	

	/*@BeforeClass

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

	@Test(priority='2')
	public void productcomparison() throws InterruptedException{
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
	
	@Test(priority='3')
	public void Orderhistory() throws InterruptedException{
		setLogger(getExtent().startTest("Orderhistory"));
		//logger = extent.createTest("Login");
		ooperations = new Operations(driver);

		//logger = extent.startTest("Login");

		ooperations.login();
		getLogger().log(LogStatus.INFO, "Login completed");
		ooperations.addtocart();
		getLogger().log(LogStatus.INFO, "Add to cart completed");
		try {
			ooperations.updatequantity("2");
			getLogger().log(LogStatus.INFO, "cart update completed");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ooperations.checkout();
		getLogger().log(LogStatus.INFO, "Checkout & Verifying order history completed");
		ooperations.returnordersubmition();
		getLogger().log(LogStatus.INFO, "Order submition completed");
		ooperations.logout();
		getLogger().log(LogStatus.INFO, "Logout completed");
		getLogger().log(LogStatus.PASS, "Checking order History Pass");





	}
	
	@Test(priority='4')
	public void addingPhonestocart() throws InterruptedException{
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

	@Test(priority=1, dataProvider="User Details")
	public void registration(String firstname,String lastname,String emailAddress,String telephoneNum,String address1,String cityName,String postcodeNum,String country,String zone,String pwd,String confirm_pwd, String reviewer,String review1, String review2, String rating) throws Exception{
		setLogger(getExtent().startTest("Registration and Add to Cart"));
	   
	      homePage = new HomePage(driver);
	      
	      //Calling method to click on 'Create Account' link
	      registrationPage = homePage.clickOnCreateLink();
	      
	      //Calling method to fill user details in Registration page and verify account is created
	registrationPage.fillDetailsAndRegister(firstname,lastname,emailAddress,telephoneNum,address1,cityName,postcodeNum,country,zone,pwd,confirm_pwd);
	      try{
	      Assert.assertEquals("Your Account Has Been Created!", driver.getTitle(),"Titles Not Matched: New Account Not Created");
	     // extentTest.log(LogStatus.INFO, "Registration Successful");
	      System.out.println("Your Account Has Been Created!");
	      }catch(Exception e){
	            //extentTest.log(LogStatus.INFO, "Registration is not successful");
	      }
	      
	      //Calling method to navigate to Home Page after registration
	      homePage=registrationPage.navigateToHome();//should be done from common actions class
	      //Calling method to click on Galaxy tab 10.1 advertisement and verify user is redirected to respective product page
	      galaxyProductPage=homePage.clickOnGalaxyAdvTab();
	      Assert.assertEquals("Samsung Galaxy Tab 10.1", galaxyProductPage.heading.getText(), "User is in " +"'"+driver.getTitle()+"'"+" page");
	      //extentTest.log(LogStatus.PASS,"User is in "+"'"+"Samsung Galaxy Tab 10.1"+"'"+" page");
	      
	      //Calling method to add reviews on the product
	      galaxyProductPage.reviewOnProduct(reviewer, review1, rating);
	      
	      //Handling the error toast if displayed, when adding reviews
	      try{
	            if(galaxyProductPage.warningToast.getText().equalsIgnoreCase("Warning: Review Text must be between 25 and 1000 characters!")){
	                  galaxyProductPage.reviewOnProduct(reviewer, review2, rating);}
	                  //extentTest.log(LogStatus.INFO,"Warning: Review Text must be between 25 and 1000 characters!");
	      }catch(org.openqa.selenium.NoSuchElementException e){
	            Thread.sleep(10000);
	            if(galaxyProductPage.successToast.getText().equalsIgnoreCase("Thank you for your review. It has been submitted to the webmaster for approval.")){
	         //   extentTest.log(LogStatus.INFO,"Thank you for your review. It has been submitted to the webmaster for approval.");
	            System.out.println("Added Reviews to the Product");
	           // extentTest.log(LogStatus.PASS, "Success:Reviews Added");
	            }
	      }
	                  
	      //Calling method to click on 'Add to Wishlist' link and verify success toast is displayed
	      galaxyProductPage.clickOnAddToWishlist();
	      Thread.sleep(1500);
	      Assert.assertTrue(galaxyProductPage.getSuccessMessage().contains("Success"), "Product is not added to Wishlist");
	      //extentTest.log(LogStatus.PASS,"Success: You have added Samsung Galaxy Tab 10.1 to your wish list!");
	      System.out.println("Success: You have added Samsung Galaxy Tab 10.1 to your wish list!");
	      
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
	      
	      //Calling method to add product to cart and verifying the success toast
	      myWishlistPage.addToCart();
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
	      
	      System.out.println("Product is removed from the Wishlist");
	      //extentTest.log(LogStatus.PASS,"Success: Product added to Cart and removed automatically from Wishlist");
	      
	      //Calling method to logout from the account and verify logout message
	      
	      AccountLogoutPage accountLogoutPage =myWishlistPage.logout();
	      Assert.assertTrue(accountLogoutPage.getLogoutMessage().equals("Account Logout"), "Account Logout message is not displayed");
	      System.out.println("Account Logout message is displayed and the user is signed out from the account");
	      
	    //  extentTest.log(LogStatus.PASS, "Registration And Add To Cart:Succcessful");
	      Reporter.log("Registration And Add To Cart:Succcessful");
	      ooperations.logout();
			getLogger().log(LogStatus.INFO, "Logout completed");
	}



//Data provider - Sending user details for registration
//Data provider - Sending user details for registration
    @DataProvider(name="User Details")
    public Object[][] userDetails() throws Exception{
          
          ExcelData excelData = new ExcelData("D:/newopencart/TestData_Opencart.xlsx",0);
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
