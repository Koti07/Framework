package com.opencart.tests;

import utilities.ExcelUtils;

import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;




import com.relevantcodes.extentreports.LogStatus;

public class LoadingData extends JDBCConnection {

	public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		
	
		String Filepath = "D://framework//sqldata.xlsx";

 ArrayList<String> list1 = new ArrayList<String>();

		//Data Provider

		//@DataProvider(name="Mydatprovide")
		
		//public static void readExcel(String SheetName,String Filepath) throws IOException
		{
			String[][] arrayExcelData = null;
			//Create an object of File class to open xlsx file
			File src= new File(Filepath); //"D:\\SELENIUM_PRACTISE\\TestData.xlsx"
			//Create an object of FileInputStream class to read excel file
			FileInputStream fis = new FileInputStream(src);
			//create object of XSSFWorkbook class
			XSSFWorkbook wb= new XSSFWorkbook(fis); // load the the excel workbook  
			XSSFSheet sheet1= wb.getSheetAt(0);
			//Find number of rows and columns in excel file
			int rowCount=sheet1.getLastRowNum();
			int colcount=sheet1.getRow(0).getLastCellNum();
			//int totalNoRows = sheet1.getLastRowNum();

			System.out.println("Row count is " + rowCount);
			System.out.println("column count is "+ colcount);

			arrayExcelData = new String[rowCount][colcount];
			//Create a loop over all the rows of excel file to read it
			for (int i = 1; i < rowCount+1; i++) 
			{
				

				//Create a loop to print cell values in a row

				for (int j = 0; j < sheet1.getRow(0).getLastCellNum(); j++) {
					//Print Excel data in console
					list1.add(sheet1.getRow(i).getCell(j).getStringCellValue());
					

				}
   
				//System.out.println(list1);
			

			}
			JDBCConnection.insertdata(list1.get(0), list1.get(1), list1.get(2), list1.get(3), list1.get(4), list1.get(5), list1.get(6), list1.get(7), list1.get(8), list1.get(9));
		}
	}
}