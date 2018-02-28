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



public class TestNG1 extends ExtentReportsClass{


	@Test(priority = 0)
	public void Test1() {
		setLogger(getExtent().startTest(getClass().getSimpleName()+ ":: Test1"));
		//Assert.assertTrue(true);
		//To generate the log when the test case is passed
		System.out.println("Hello World1");
		getLogger().log(LogStatus.PASS, "first method is pass");



	}

	@Test(priority = 1)
	public void Test2() {
		setLogger(getExtent().startTest(getClass().getSimpleName()+ ":: Test2"));
		//Assert.assertTrue(true);
		//To generate the log when the test case is passed
		System.out.println("Hello World2");
		getLogger().log(LogStatus.PASS, "second method is fail");



	}

	@Test(priority = 2)
	public void Test3() {

		setLogger(getExtent().startTest(getClass().getSimpleName()+ ":: Test3"));
		//Assert.assertTrue(true);
		//To generate the log when the test case is passed
		System.out.println("Hello World3");
		getLogger().log(LogStatus.PASS, "third method is pass");



	}
	

}
