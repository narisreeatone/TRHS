package com.nag.dao;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.nag.formbean.*;
import com.nag.mail.MailHandler;
import com.nag.bean.*;

public class UploadBulkDataToDB {
	private Connection conn = null;
	
	private Connection getDBConnection(){
		try{
			/*Driver myDriver = new oracle.jdbc.driver.OracleDriver();
			DriverManager.registerDriver( myDriver );	*/
			if(conn == null || conn.isClosed()){
				conn = null;
				Class.forName("oracle.jdbc.driver.OracleDriver");			
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","nagarjuna","nagarjuna");
			}
		}catch(Exception e){
			System.out.println("Exception in DB connection"+e.getMessage());
		}
		return conn;
	}
	
	public String uploadEmpDetailsToDB(String filePath){
		FileInputStream inputStream = null;
		Workbook workbook = null;
		String message = "true";
		String errorMessage = "There is a problem with Employee data. Please check employee data of Emp Id(s):::";
		try {
			inputStream = new FileInputStream(new File(filePath));
			workbook = new XSSFWorkbook(inputStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
         
        
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if(nextRow.getRowNum() == 0)
        		continue;
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            List<String> rowValues = new ArrayList<String>();          
            while (cellIterator.hasNext()) {            	         	
                Cell cell = cellIterator.next();                
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:                       
                        rowValues.add(cell.getStringCellValue());
                        break;                   
                    case Cell.CELL_TYPE_NUMERIC:                       
                        Double numericValue = cell.getNumericCellValue();
                        Integer intValue = numericValue.intValue();
                        rowValues.add(intValue.toString());
                        break;                    
                }                 
            }
            System.out.println("insert into database");
            DataBaseConnection dbHandler = new DataBaseConnection();
            RegisterEmployeeForm registerEmpForm = new RegisterEmployeeForm();
            String employeeId = rowValues.get(0);
            String employeeName = rowValues.get(1);
            String email = rowValues.get(2);
            String mobileNumber = rowValues.get(3);
            String landlineNumber = rowValues.get(4);
            String extension = rowValues.get(5);
            String dobString = rowValues.get(6);
            java.util.Date dob = null;
            try{
            	DateFormat df = new SimpleDateFormat("dd/mm/yyyy"); 
            	dob = df.parse(dobString);
            }catch(Exception e){
            	System.out.println("Unable to convert emp DOB to date object in bulk upload");
            	errorMessage = errorMessage + rowValues.get(1) + ", ";
            	message = errorMessage;
            	break;
            }
            String designationId = rowValues.get(7);
            String departmentId = rowValues.get(8);
            
            registerEmpForm.setEmployeeId(employeeId);
            registerEmpForm.setEmployeeName(employeeName);
            registerEmpForm.setEmail(email);
            registerEmpForm.setMobile(mobileNumber);
            registerEmpForm.setLandline(landlineNumber);
            registerEmpForm.setExtension(extension);
            registerEmpForm.setDob(dob);
            registerEmpForm.setDesignationId(designationId);
            registerEmpForm.setDepartmentId(departmentId);
            String dbStatus = dbHandler.registerEmployee(registerEmpForm);
            if("true".equals(dbStatus)){
            	MailHandler mailHandler = new MailHandler();
            	System.out.println("successfully inserted employee details. Emp Id is:::"+rowValues.get(0));
            	EmployeeLoginDetails empLoginDetails = dbHandler.getLoginDetails(employeeId);
            	EmployeeDetails employeeDetails = dbHandler.getEmployeeDetailsByEmpId(employeeId);
            	boolean mailStatus = mailHandler.sendEmployeeRegistrationDetails(empLoginDetails, employeeDetails);
				if(mailStatus){
					System.out.println("successfully sent employee credentials to Emp Id is:::"+rowValues.get(0));
				}
            }
            else{
            	System.out.println("There is a problem with Employee data. Please check data. Emp Id is:::"+rowValues.get(1));
            	errorMessage = errorMessage + rowValues.get(0) + ", ";
            	message = errorMessage;
            }            
        }
         
        try {
			workbook.close();
			inputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return message;
	}
}
