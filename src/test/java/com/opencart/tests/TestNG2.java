package com.opencart.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

import pageoperations.ExtentReportsClass;

import com.relevantcodes.extentreports.LogStatus;



public class TestNG2 extends ExtentReportsClass{


	@Test(priority = 3)
	public void Test4() {
		setLogger(getExtent().startTest(getClass().getSimpleName()+ ":: Test4"));
		//Assert.assertTrue(true);
		//To generate the log when the test case is passed
		System.out.println("Hello World4");
		getLogger().log(LogStatus.PASS, "first method is pass");



	}

	@Test(priority = 4)
	public void Test5() {
		setLogger(getExtent().startTest(getClass().getSimpleName()+ ":: Test5"));
		//Assert.assertTrue(true);
		//To generate the log when the test case is passed
		System.out.println("Hello World5");
		getLogger().log(LogStatus.PASS, "second method is fail");



	}

	@Test(priority = 5)
	public void Test6() {

		setLogger(getExtent().startTest(getClass().getSimpleName()+ ":: Test6"));
		//Assert.assertTrue(true);
		//To generate the log when the test case is passed
		System.out.println("Hello World6");
		getLogger().log(LogStatus.PASS, "third method is pass");



	}
	

}
