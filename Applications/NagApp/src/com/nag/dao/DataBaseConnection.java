package com.nag.dao;

import java.sql.*;
import java.util.*;
import com.nag.bean.*;

public class DataBaseConnection {
	private Connection conn = null;
	
	public Connection getDBConnection(){
		System.out.println("Get DB connection");
		try{
			/*Driver myDriver = new oracle.jdbc.driver.OracleDriver();
			DriverManager.registerDriver( myDriver );	*/
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","nagarjuna","nagarjuna");
		}catch(Exception e){
			System.out.println("Exception in DB connection"+e.getMessage());
		}
		return conn;
	}
	
	public String getLoginDetails(String username, String password, Connection conn){
		
		String employeeDetailsId = null;
		try{
			PreparedStatement stmt=conn.prepareStatement("select EMPLOYEEDETAILSID, LOGINPASSWORD from TLOGIN where USERNAME=?");			
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				if(password.equals(rs.getString("LOGINPASSWORD"))){
				
					employeeDetailsId = rs.getString("EMPLOYEEDETAILSID");				
				}
			}
		}catch(SQLException e){
			System.out.println("exception in login detials:::"+e.getMessage());
		}
		return employeeDetailsId;
	}
	
	public EmployeeDetails getEmployeeDetails(String username, String password){
		
		conn = getDBConnection();
		String employeeDetailsId = getLoginDetails(username,password,conn);
		EmployeeDetails empDetails = new EmployeeDetails();
				
		if( employeeDetailsId != null){
			try{
				PreparedStatement stmt=conn.prepareStatement("select * from TEMPLOYEEDETAILS where EMPLOYEEDETAILSID=?");
				stmt.setString(1, employeeDetailsId);			
				ResultSet rs = stmt.executeQuery();
		
				while(rs.next()){
					
					empDetails.setEmployeeId(rs.getString("EMPLOYEEID"));					
					empDetails.setEmployeeDetailsId(rs.getString("EMPLOYEEDETAILSID"));					
					empDetails.setEmployeeName(rs.getString("EMPLOYEENAME"));					
					empDetails.setEmailId(rs.getString("EMAILID"));					
					empDetails.setMobileNumber(rs.getString("MOBILENUMBER"));					
					empDetails.setLandLineNumber(rs.getString("LANDLINENUMBER"));
					empDetails.setExtNumber(rs.getString("EXTNNUMBER"));					
					empDetails.setDesignitionId(rs.getString("DESIGNATIONID"));
					empDetails.setDeptId(rs.getString("DEPARTMENTID"));	
					
					empDetails.setDob(rs.getDate("DATEOFBIRTH"));
					empDetails.setCreatedDate(rs.getDate("ACTIONDATE"));
				}
			}catch(Exception e){
				System.out.println("exception in get employee query:::"+e.getMessage());
			}
			finally{
				try{
					conn.close();
				}catch(Exception e){}
			}
		}
		return empDetails;
	}
	
	/*public boolean insertTravelRequest(){
		
	}*/

}
