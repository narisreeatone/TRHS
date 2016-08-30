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
	
	public boolean uploadEmpDetailsToDB(String filePath){
		FileInputStream inputStream = null;
		Workbook workbook = null;		
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
            	System.out.println("Row number:::"+nextRow.getRowNum());            	
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
            boolean dbStatus = dbHandler.registerEmployee(registerEmpForm);
            if(!dbStatus)
            	return dbStatus;
            /*conn = getDBConnection();
            PreparedStatement ps = null;
            PreparedStatement ps1 = null;
    		ResultSet rs = null;
            try{
            	ps = conn.prepareStatement("select max(EMPLOYEEDETAILSID) as NUMBEROFRECORDS from TEMPLOYEEDETAILS");
    			rs = ps.executeQuery();
    			Integer empDetailsId = 0;
    			if(rs.next())
    				if(rs.getString(1) != null)
    					empDetailsId = rs.getInt("NUMBEROFRECORDS");					
    			
    			ps1=conn.prepareStatement("insert into TEMPLOYEEDETAILS(EMPLOYEEDETAILSID,EMPLOYEEID,EMPLOYEENAME,EMAILID,MOBILENUMBER,LANDLINENUMBER,EXTNNUMBER,DATEOFBIRTH,DESIGNATIONID,DEPARTMENTID,ISACTIVE,ACTIONDATE) values (?,?,?,?,?,?,?,?,?,?,?,sysdate)");
    			empDetailsId = empDetailsId + 1;
    			ps1.setInt(1, empDetailsId);	
    			for(int i = 0, j = 2; i < rowValues.size(); i++,j++){
    				if(i == 6){
    					DateFormat df = new SimpleDateFormat("dd/mm/yyyy"); 
    					java.util.Date dob = df.parse(rowValues.get(i));
						ps1.setDate(j, new java.sql.Date(dob.getTime()));
    				}
    				else{    					
    					ps1.setString(j, rowValues.get(i));
    				}
    			}
    			ps1.setString(11, "1");
    			ps1.executeQuery();
    			System.out.println("successfully inserted employee details. Id is:::"+rowValues.get(1)); 
            }catch(Exception e){
            	System.out.println("exception inserting employee details:::employee id:::"+rowValues.get(0)+" exception is:::"+e.getMessage());
            }finally{
            	try{
    				ps.close();
    				ps1.close();    				
    				conn.close();
    			}catch(Exception e){}
    		}*/
        }
         
        try {
			workbook.close();
			inputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return true;
	}
}
