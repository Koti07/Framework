package com.opencart.tests;



import java.beans.Statement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Retrive {

	public static void main (String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException{

		
		//void insertdata(String sFirstName,String sLastName,String semail,String spassword,String sdob,String sgender,String slocation,String szipcode,String smobile,String sRoleid) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

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
		
	//	String sFirstName = "ramakanth";
	//	String sLastName = "nandivada";
		//String semail = "test4777@gmail.com";
//		String spassword = "123456";
	//	String sdob = "05120000";
		//String sgender = "M";
//		String slocation = "Hyderabad";
	//	String szipcode = "500082";
	//	String smobile = "0123456789";
	//	String sRoleid = "101";
		
		String ID,A,B;
		
		
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, databaseUserName, databasePassword);
			stmt = conn.createStatement();
			result = null;
			String FirstName,LastName,Email,Password,dob,gender,location,zipcode,mobile,Role_ID;


			conn = DriverManager.getConnection(url, databaseUserName, databasePassword);
		/*	if (conn != null) {
				DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
				System.out.println("Driver name: " + dm.getDriverName());
				System.out.println("Driver version: " + dm.getDriverVersion());
				System.out.println("Product name: " + dm.getDatabaseProductName());
				System.out.println("Product version: " + dm.getDatabaseProductVersion());
			}*/

			
		//	stmt.executeUpdate("INSERT INTO dbo.Registration " + "VALUES ('Firstname', 'Majji','test4777@gmail.com','123456','25413697', 'M', 'Hyderabad', '500032', '1111111111','103')");
		//	stmt.executeUpdate("INSERT INTO dbo.Registration (FirstName, LastName, Email,Password,dob,gender,location,zipcode,zipcode,Role_ID) values ("+"'sFirstName'"+", '+sLastName+', '+semail+','123456','45871032', 'M', 'Hyderabad', '500082', '1111111111','+sRole_ID+')");
			
		//	stmt.executeUpdate("INSERT INTO Registration VALUES ('"+sFirstName+"','"+sLastName+"','"+semail+"','"+spassword+"','"+sdob+"', '"+sgender+"', '"+slocation+"', '"+szipcode+"', '"+smobile+"', '"+sRoleid+"')");	



result = stmt.executeQuery("select * from dbo.t1 ");
			
			//System.out.println("FirstName	"	+ "LastName	"+"Email		"	+	"Password	"+"dob	"+"gender	"+"location	"+"zipcode	"+"mobile	"+"Role	");
			while(result.next()){
				ID=result.getString("id");
				A = result.getString("a");
				B=result.getString("b");
			
			
				
				System.out.println(ID+"		"+A+"		"+B);
				
				
				
				
				
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
