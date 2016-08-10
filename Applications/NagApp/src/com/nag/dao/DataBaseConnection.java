package com.nag.dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import com.nag.bean.*;
import com.nag.*;
import com.nag.formbean.*;

public class DataBaseConnection {
	private Connection conn = null;
	
	public Connection getDBConnection(){		
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
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String employeeDetailsId = null;
		try{
			stmt=conn.prepareStatement("select EMPLOYEEDETAILSID, LOGINPASSWORD from TLOGIN where USERNAME=?");			
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				if(password.equals(rs.getString("LOGINPASSWORD"))){
				
					employeeDetailsId = rs.getString("EMPLOYEEDETAILSID");				
				}
			}
		}catch(SQLException e){
			System.out.println("exception in login detials:::"+e.getMessage());
		}
		finally{			
			try{
				stmt.close();
				rs.close();				
			}catch(Exception e){}
		}
		return employeeDetailsId;
	}
	
	public EmployeeDetails getEmployeeDetailsByLogin(String username, String password){
		
		conn = getDBConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String employeeDetailsId = getLoginDetails(username,password,conn);
		EmployeeDetails empDetails = new EmployeeDetails();
				
		if( employeeDetailsId != null){
			try{
				stmt=conn.prepareStatement("select * from TEMPLOYEEDETAILS where EMPLOYEEDETAILSID=?");
				stmt.setString(1, employeeDetailsId);			
				rs = stmt.executeQuery();
		
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
				System.out.println("exception in get employee query by login:::"+e.getMessage());
			}
			finally{
				try{
					stmt.close();
					rs.close();
					conn.close();
				}catch(Exception e){}
			}
		}
		return empDetails;
	}
	
	public Map<String, Object> getEmployeesDetails(){
		conn = getDBConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		EmployeeDetails empDetails = null;				
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			stmt=conn.prepareStatement("select Emp.*, Dept.DEPARTMENTNAME, Desi.DESIGNATIONNAME   from TEMPLOYEEDETAILS Emp, TDEPARTMENTS Dept, TDESIGNATION Desi where Emp.DESIGNATIONID = Desi.DESIGNATIONID AND Emp.DEPARTMENTID = Dept.DEPARTMENTID and Emp.status = '1'");						
			rs = stmt.executeQuery();
	
			while(rs.next()){
				empDetails = new EmployeeDetails();
				empDetails.setEmployeeId(rs.getString("EMPLOYEEID"));					
				empDetails.setEmployeeDetailsId(rs.getString("EMPLOYEEDETAILSID"));					
				empDetails.setEmployeeName(rs.getString("EMPLOYEENAME"));					
				empDetails.setEmailId(rs.getString("EMAILID"));					
				empDetails.setMobileNumber(rs.getString("MOBILENUMBER"));					
				empDetails.setLandLineNumber(rs.getString("LANDLINENUMBER"));
				empDetails.setExtNumber(rs.getString("EXTNNUMBER"));					
				empDetails.setDesignitionId(rs.getString("DESIGNATIONID"));
				empDetails.setDeptId(rs.getString("DEPARTMENTID"));
				empDetails.setDepartmentName(rs.getString("DEPARTMENTNAME"));
				empDetails.setDesignationName(rs.getString("DESIGNATIONNAME"));
				
				empDetails.setDob(rs.getDate("DATEOFBIRTH"));
				empDetails.setCreatedDate(rs.getDate("ACTIONDATE"));
				map.put(rs.getString("EMPLOYEEID"), empDetails);
			}
		}catch(Exception e){
			System.out.println("exception in get employee all details query:::"+e.getMessage());
		}
		finally{
			try{
				stmt.close();
				rs.close();
				conn.close();
			}catch(Exception e){}
		}		
		return map;
	}
	
	public List<String> getEmployeeDetailsByName(String empName){
		conn = getDBConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List <String> empNamesList = new ArrayList<String>();
		if( empName != null){
			try{
				ps=conn.prepareStatement("select EMPLOYEENAME from TEMPLOYEEDETAILS where EMPLOYEENAME like ?%");
				ps.setString(1, empName);			
				rs = ps.executeQuery();				
				while(rs.next()){					
					String empolyeeName = rs.getString("EMPLOYEENAME");
					empNamesList.add(empolyeeName);
				}
			}catch(Exception e){
				System.out.println("exception in get employee query:::"+e.getMessage());
			}
			finally{
				try{
					ps.close();
					rs.close();
					conn.close();
				}catch(Exception e){}
			}			
		}
		return empNamesList;
	}
	
	public boolean saveTravelRequestForm(TravelRequestForm requestForm){
		System.out.println("saving travel request form");
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		boolean status = false;
		try{
			conn = getDBConnection();
			ps = conn.prepareStatement("select max(TRAVELREQUESTMASTERID) from TTRAVELREQUESTMASTER");
			rs = ps.executeQuery();
			Integer requestMasterId = 0;
			if(rs.next())
				if(rs.getString(1) != null)
					requestMasterId = Integer.parseInt(rs.getString(1));					
			//insert travel req form data to request master table
			ps=conn.prepareStatement("insert into TTRAVELREQUESTMASTER(TRAVELREQUESTMASTERID,SOURCE,DESTINATION,TRAVELMODEID,EXPENSES,PURPOSE,EMPLOYEEDETAILSID,ATTACHMENTPATH,TRAVELREQUESTSTATUS,TRAVELDATE,ACTIONDATE) values (?,?,?,?,?,?,?,?,?,?,sysdate)");
			requestMasterId = requestMasterId + 1;
			ps.setString(1, requestMasterId.toString());	
			ps.setString(2, requestForm.getSource());
			ps.setString(3, requestForm.getDestination());
			ps.setString(4, requestForm.getTravelType());
			ps.setString(5, requestForm.getExpenses());
			ps.setString(6, requestForm.getPurpose());
			ps.setString(7, requestForm.getEmployeeId());
			ps.setString(8, requestForm.getAttachmentPath());
			ps.setString(9, "N");
			ps.setDate(10, new java.sql.Date(requestForm.getTravelDate().getTime()));
			ps.executeQuery();		
			System.out.println("saving travel request master data");
			
			//insert data into travel version table
			PreparedStatement ps1 = conn.prepareStatement("select max(TRAVELREQUESTVERSIONID) from TTRAVELREQUESTVERSION");
			ResultSet rs1 = ps1.executeQuery();
			Integer requestVersionId = 0;
			if(rs1.next()){
				if(rs1.getString(1) != null)
					requestVersionId = Integer.parseInt(rs1.getString(1));
			}
			String aproveEmpIds[] = requestForm.getApproveEmpId();
			ps2 = conn.prepareStatement("insert into TTRAVELREQUESTVERSION(TRAVELREQUESTVERSIONID,TRAVELREQUESTMASTERID,TRAVELAPPROVERID,APPROVERORDER,ACTIONDATE) values (?,?,?,?,sysdate)");
			for(int i=0; i < aproveEmpIds.length; i++){
				requestVersionId= requestVersionId + 1;
				ps2.setString(1, requestVersionId.toString());
				ps2.setString(2, requestMasterId.toString());
				ps2.setString(3, aproveEmpIds[i]);
				ps2.setInt(4, 0);
				ps2.executeQuery();
			}
			System.out.println("saving travel request version");
			status = true;
		}catch(Exception e){
			System.out.println("exception in saving travel request data:::"+e.getMessage());
		}
		finally{
			try{
				ps.close();
				ps2.close();
				conn.close();
			}catch(Exception e){}
		}			
		return status;
	}
	
	public TravelRequestMaster getPendingRequest(String empDetailsId){
		Map<String, Object> map = new HashMap<String, Object>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		TravelRequestMaster reqMaster = null;
		System.out.println("begin - get pending req db query:::empDetailsId:::"+empDetailsId);
		try{
			conn = getDBConnection();
			ps = conn.prepareStatement("select * from TTRAVELREQUESTMASTER where TRAVELREQUESTSTATUS = 'N' and EMPLOYEEDETAILSID = ?");
			//PreparedStatement ps=conn.prepareStatement("select TRAVELREQUESTMASTERID from TTRAVELREQUESTMASTER");
			ps.setString(1, empDetailsId);			
			rs = ps.executeQuery();				
			while(rs.next()){
				String reqMasterId = rs.getString("TRAVELREQUESTMASTERID");
				reqMaster = new TravelRequestMaster();
				reqMaster.setTravelRequestMasterId(reqMasterId);
				reqMaster.setSource(rs.getString("SOURCE"));
				reqMaster.setDestination(rs.getString("DESTINATION"));
				reqMaster.setTravelModeId(rs.getString("TRAVELMODEID"));
				reqMaster.setExpenses(rs.getString("EXPENSES"));
				reqMaster.setPurpose(rs.getString("PURPOSE"));
				reqMaster.setEmployeeDetailsId(empDetailsId);				
				reqMaster.setAttachmentPath(rs.getString("ATTACHMENTPATH"));
				reqMaster.setTravelRequestStatus(rs.getString("TRAVELREQUESTSTATUS"));
				reqMaster.setCreatedDate(rs.getDate("ACTIONDATE"));
				reqMaster.setTravelDate(rs.getDate("TRAVELDATE"));
				
				System.out.println("reqMasterId:::"+reqMasterId);
				
				TravelRequestVersion reqVersion = null;
				PreparedStatement ps1 = conn.prepareStatement("select * from TTRAVELREQUESTVERSION where TRAVELREQUESTMASTERID = ?");
				ps.setString(1, reqMasterId);			
				ResultSet rs1 = ps1.executeQuery();	
				List<TravelRequestVersion> reqVersionList = new ArrayList<TravelRequestVersion>();
				while(rs1.next()){
					reqVersion = new TravelRequestVersion();
					reqVersion.setTravelRequestVersionId(rs1.getString("TRAVELREQUESTVERSIONID"));
					reqVersion.setTravelRequestMasterId(reqMasterId);
					reqVersion.setTravelApproverId(rs1.getString("TRAVELAPPROVERID"));
					reqVersion.setApproverOrder(rs1.getString("APPROVERORDER"));
					reqVersion.setApprovedDate(rs.getDate("APPROVEDDATE"));
					reqVersion.setCreatedDate(rs1.getTimestamp("ACTIONDATE"));
					reqVersionList.add(reqVersion);
				}
				reqMaster.setReqVersionList(reqVersionList);
			}
		}catch(Exception e){
			System.out.println("exception in get req master table query:::"+e.getMessage());
		}
		finally{
			try{
				ps.close();
				rs.close();
				conn.close();
			}catch(Exception e){}
		}	
		System.out.println("end - get pending req db query");
		return reqMaster;
	}
	/*public boolean insertTravelRequest(){
		
	}*/

}
