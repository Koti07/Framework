package com.opencart.tests;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCConnection {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		Connection conn = null;
		String dbName = "QET Database";
		String serverip="10.159.34.49";
		String serverport="1433";
		String url = "jdbc:sqlserver://"+serverip+"\\SQLEXPRESS:"+serverport+";databaseName="+dbName+"";
		java.sql.Statement stmt = null;
		ResultSet result = null;
	
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String databaseUserName = "appuser";
		String databasePassword = "pwd123";
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, databaseUserName, databasePassword);
			stmt = conn.createStatement();
			result = null;
			String FirstName,LastName,Email,Password,dob,gender,location,zipcode,mobile,Role_ID;


			conn = DriverManager.getConnection(url, databaseUserName, databasePassword);
			if (conn != null) {
				DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
				System.out.println("Driver name: " + dm.getDriverName());
				System.out.println("Driver version: " + dm.getDriverVersion());
				System.out.println("Product name: " + dm.getDatabaseProductName());
				System.out.println("Product version: " + dm.getDatabaseProductVersion());
			}

			
			stmt.executeUpdate("INSERT INTO dbo.Registration " + "VALUES ('Koteswararao', 'Majji','test4777@gmail.com','123456','01475236', 'M', 'Hyderabad', '500032', '1111111111','103')");
			




result = stmt.executeQuery("select * from dbo.Registration ");
			
			System.out.println("FirstName	"	+ "LastName	"+"Email		"	+	"Password	"+"dob	"+"gender	"+"location	"+"zipcode	"+"mobile	"+"Role	");
			while(result.next()){
				FirstName=result.getString("FirstName");
				LastName = result.getString("LastName");
				Email=result.getString("Email");
				Password=result.getString("Password");
				dob = result.getString("dob");
				gender=result.getString("gender");
				location=result.getString("location");
				zipcode = result.getString("zipcode");
				mobile=result.getString("mobile");
				Role_ID=result.getString("Role_ID");
			
				
				System.out.println(FirstName+"		"+LastName+"		"+Password+"		"+dob+" 		"+gender+"		"+location+"		"+zipcode+"		 "+mobile+"		"+Role_ID);
				
				
				
				
				
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
}
