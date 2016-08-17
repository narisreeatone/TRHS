package com.nag.dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import com.nag.bean.*;
import com.nag.*;
import com.nag.formbean.*;
import com.nag.sql.*;

public class DataBaseConnection {
	private Connection conn = null;
	RequestStatus requestStatus = new RequestStatus();
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
	
	public Map<String, Object> getAllEmployeesDetails(){
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
	
	public List<String> getEmployeeName(String empName){
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
	
	public EmployeeDetails getEmployeeDetailsById(String empDetailsId){
		System.out.println("emp details By id:::"+empDetailsId);
		conn = getDBConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		EmployeeDetails empDetails = null;	
		if( empDetailsId != null){
			try{
				ps=conn.prepareStatement("select Emp.*, Dept.DEPARTMENTNAME, Desi.DESIGNATIONNAME   from TEMPLOYEEDETAILS Emp, TDEPARTMENTS Dept, TDESIGNATION Desi where Emp.DESIGNATIONID = Desi.DESIGNATIONID AND Emp.DEPARTMENTID = Dept.DEPARTMENTID and EMPLOYEEDETAILSID = ?");
				ps.setString(1, empDetailsId);			
				rs = ps.executeQuery();				
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
		return empDetails;
	}
	
	
	@SuppressWarnings("resource")
	public boolean saveTravelRequestForm(TravelRequestForm requestForm){
		System.out.println("saving travel request form");
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		boolean status = false;
		try{
			conn = getDBConnection();
			ps = conn.prepareStatement("select max(TRAVELREQUESTMASTERID) as NUMBEROFRECORDS from TTRAVELREQUESTMASTER");
			rs = ps.executeQuery();
			Integer requestMasterId = 0;
			if(rs.next())
				if(rs.getString(1) != null)
					requestMasterId = rs.getInt("NUMBEROFRECORDS");					
			//insert travel req form data to request master table
			ps=conn.prepareStatement("insert into TTRAVELREQUESTMASTER(TRAVELREQUESTMASTERID,SOURCE,DESTINATION,TRAVELMODEID,EXPENSES,PURPOSE,EMPLOYEEDETAILSID,ATTACHMENTPATH,TRAVELREQUESTSTATUS,TRAVELDATE,ACTIONDATE) values (?,?,?,?,?,?,?,?,?,?,sysdate)");
			requestMasterId = requestMasterId + 1;
			ps.setInt(1, requestMasterId);	
			ps.setString(2, requestForm.getSource());
			ps.setString(3, requestForm.getDestination());
			ps.setString(4, requestForm.getTravelType());
			ps.setString(5, requestForm.getExpenses());
			ps.setString(6, requestForm.getPurpose());
			ps.setString(7, requestForm.getEmployeeId());
			ps.setString(8, requestForm.getAttachmentPath());
			ps.setString(9, requestStatus.PENDING);
			ps.setDate(10, new java.sql.Date(requestForm.getTravelDate().getTime()));
			ps.executeQuery();		
			System.out.println("saving travel request master data");
			
			//insert data into travel version table
			ps1 = conn.prepareStatement("select max(TRAVELREQUESTVERSIONID) as NUMBEROFRECORDS from TTRAVELREQUESTVERSION");
			ResultSet rs1 = ps1.executeQuery();
			Integer requestVersionId = 0;
			if(rs1.next()){
				if(rs1.getString(1) != null)
					requestVersionId = rs.getInt("NUMBEROFRECORDS");
			}
			String aproveEmpOrder[] = requestForm.getApproveEmpOrder();
			ps2 = conn.prepareStatement("insert into TTRAVELREQUESTVERSION(TRAVELREQUESTVERSIONID,TRAVELREQUESTMASTERID,TRAVELAPPROVERID,APPROVERORDER,REQSTATUSID,ACTIONDATE) values (?,?,?,?,?,sysdate)");
			//while()
			for(int i=0; i < aproveEmpOrder.length; i++){
				String[] empIdAndOrder = aproveEmpOrder[i].split("-");
				requestVersionId= requestVersionId + 1;
				ps2.setInt(1, requestVersionId);
				ps2.setInt(2, requestMasterId);
				ps2.setString(3, empIdAndOrder[0]);
				System.out.println("emp appove order:::"+empIdAndOrder[1]);
				ps2.setInt(4, Integer.parseInt(empIdAndOrder[1]));
				ps2.setString(5, requestStatus.PENDING);
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
				ps1.close();
				ps2.close();
				conn.close();
			}catch(Exception e){}
		}			
		return status;
	}
	
	public Map<String, TravelRequestMaster> getPendingRequestForEmployee(String empDetailsId){
		Map<String, TravelRequestMaster> reqMasterMap = new HashMap<String, TravelRequestMaster>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		TravelRequestMaster reqMaster = null;
		System.out.println("begin - get pending req db query:::empDetailsId:::"+empDetailsId);
		try{
			conn = getDBConnection();
			ps = conn.prepareStatement("select * from TTRAVELREQUESTMASTER where TRAVELREQUESTSTATUS = ? and EMPLOYEEDETAILSID = ? ORDER BY ACTIONDATE");			
 			ps.setString(1, requestStatus.PENDING);
 			ps.setString(2, empDetailsId);
			rs = ps.executeQuery();				
			while(rs.next()){
				reqMaster = new TravelRequestMaster();
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
				
				/*System.out.println("reqMasterId:::"+reqMasterId);
				
				TravelRequestVersion reqVersion = null;
				PreparedStatement ps1 = conn.prepareStatement("select * from TTRAVELREQUESTVERSION where TRAVELREQUESTMASTERID = ?");
				ps1.setString(1, reqMasterId);			
				ResultSet rs1 = ps1.executeQuery();	
				List<TravelRequestVersion> reqVersionList = new ArrayList<TravelRequestVersion>();
				while(rs1.next()){
					reqVersion = new TravelRequestVersion();
					reqVersion.setTravelRequestVersionId(rs1.getString("TRAVELREQUESTVERSIONID"));
					reqVersion.setTravelRequestMasterId(reqMasterId);
					reqVersion.setTravelApproverId(rs1.getString("TRAVELAPPROVERID"));
					reqVersion.setApproverOrder(rs1.getString("APPROVERORDER"));
					reqVersion.setActionDate(rs1.getDate("ACTIONDATE"));
					reqVersion.setStatusId(rs1.getString("REQSTATUSID"));
					reqVersionList.add(reqVersion);
				}
				reqMaster.setReqVersionList(reqVersionList);*/
				reqMasterMap.put(reqMasterId, reqMaster);
			}
		}catch(Exception e){
			System.out.println("********\nexception in get pendingreq master table query:::"+e.getMessage());
		}
		finally{
			try{
				ps.close();
				rs.close();
				conn.close();
			}catch(Exception e){}
		}	
		System.out.println("end - get pending req db query");
		return reqMasterMap;
	}
	
	
	public Map<String, TravelRequestMaster> getAprrovedRequestForEmployee(String empDetailsId){
		Map<String, TravelRequestMaster> reqMasterMap = new HashMap<String, TravelRequestMaster>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		TravelRequestMaster reqMaster = null;
		System.out.println("***********\nbegin - get approved req db query:::empDetailsId:::"+empDetailsId);
		try{
			conn = getDBConnection();
			ps = conn.prepareStatement("select * from TTRAVELREQUESTMASTER where TRAVELREQUESTSTATUS = ? and EMPLOYEEDETAILSID = ? ORDER BY ACTIONDATE");
			ps.setString(1, requestStatus.APPROVED);
 			ps.setString(2, empDetailsId);			
			rs = ps.executeQuery();				
			while(rs.next()){
				reqMaster = new TravelRequestMaster();
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
				
				/*TravelRequestVersion reqVersion = null;
				PreparedStatement ps1 = conn.prepareStatement("select * from TTRAVELREQUESTVERSION where TRAVELREQUESTMASTERID = ?");
				ps1.setString(1, reqMasterId);			
				ResultSet rs1 = ps1.executeQuery();	
				List<TravelRequestVersion> reqVersionList = new ArrayList<TravelRequestVersion>();
				while(rs1.next()){
					reqVersion = new TravelRequestVersion();
					reqVersion.setTravelRequestVersionId(rs1.getString("TRAVELREQUESTVERSIONID"));
					reqVersion.setTravelRequestMasterId(reqMasterId);
					reqVersion.setTravelApproverId(rs1.getString("TRAVELAPPROVERID"));
					reqVersion.setApproverOrder(rs1.getString("APPROVERORDER"));
					reqVersion.setActionDate(rs1.getDate("ACTIONDATE"));
					reqVersion.setStatusId(rs1.getString("REQSTATUSID"));
					reqVersionList.add(reqVersion);
				}
				reqMaster.setReqVersionList(reqVersionList);*/
				reqMasterMap.put(reqMasterId, reqMaster);
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
		System.out.println("end - get approved req db query\n***************");
		return reqMasterMap;
	}
	
	public Map<String, TravelRequestMaster> getRejectedRequestForEmployee(String empDetailsId){
		Map<String, TravelRequestMaster> reqMasterMap = new HashMap<String, TravelRequestMaster>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		TravelRequestMaster reqMaster = null;
		System.out.println("***********\nbegin - get approved req db query:::empDetailsId:::"+empDetailsId);
		try{
			conn = getDBConnection();
			ps = conn.prepareStatement("select * from TTRAVELREQUESTMASTER where TRAVELREQUESTSTATUS = ? and EMPLOYEEDETAILSID = ? ORDER BY ACTIONDATE");
			ps.setString(1, requestStatus.REJECTED);
 			ps.setString(2, empDetailsId);			
			rs = ps.executeQuery();				
			while(rs.next()){
				reqMaster = new TravelRequestMaster();
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
				
				/*TravelRequestVersion reqVersion = null;
				PreparedStatement ps1 = conn.prepareStatement("select * from TTRAVELREQUESTVERSION where TRAVELREQUESTMASTERID = ?");
				ps1.setString(1, reqMasterId);			
				ResultSet rs1 = ps1.executeQuery();	
				List<TravelRequestVersion> reqVersionList = new ArrayList<TravelRequestVersion>();
				while(rs1.next()){
					reqVersion = new TravelRequestVersion();
					reqVersion.setTravelRequestVersionId(rs1.getString("TRAVELREQUESTVERSIONID"));
					reqVersion.setTravelRequestMasterId(reqMasterId);
					reqVersion.setTravelApproverId(rs1.getString("TRAVELAPPROVERID"));
					reqVersion.setApproverOrder(rs1.getString("APPROVERORDER"));
					reqVersion.setActionDate(rs1.getDate("ACTIONDATE"));
					reqVersion.setStatusId(rs1.getString("REQSTATUSID"));
					reqVersionList.add(reqVersion);
				}
				reqMaster.setReqVersionList(reqVersionList);*/
				reqMasterMap.put(reqMasterId, reqMaster);
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
		System.out.println("end - get approved req db query\n***************");
		return reqMasterMap;
	}
	
	public Map<String, TravelRequestMaster> getApproveRequestForEmployee(String empDetailsId){
		Map<String, TravelRequestMaster> reqMasterMap = new HashMap<String, TravelRequestMaster>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		TravelRequestMaster reqMaster = null;
		System.out.println("begin - get approve req db query:::empDetailsId:::"+empDetailsId);
		try{
			conn = getDBConnection();
			ps = conn.prepareStatement("select * from TTRAVELREQUESTMASTER where EMPLOYEEDETAILSID = ? and TRAVELREQUESTSTATUS = ? ORDER BY ACTIONDATE");			
 			
			ps.setString(1, empDetailsId);
			ps.setString(2, requestStatus.PENDING); 			
			rs = ps.executeQuery();				
						
			while(rs.next()){
				reqMaster = new TravelRequestMaster();
				String reqMasterId = rs.getString("TRAVELREQUESTMASTERID");
				reqMaster = new TravelRequestMaster();
				reqMaster.setTravelRequestMasterId(reqMasterId);
				reqMaster.setSource(rs.getString("SOURCE"));
				reqMaster.setDestination(rs.getString("DESTINATION"));
				reqMaster.setTravelModeId(rs.getString("TRAVELMODEID"));
				reqMaster.setExpenses(rs.getString("EXPENSES"));
				reqMaster.setPurpose(rs.getString("PURPOSE"));
				reqMaster.setEmployeeDetailsId(rs.getString("EMPLOYEEDETAILSID"));				
				reqMaster.setAttachmentPath(rs.getString("ATTACHMENTPATH"));
				reqMaster.setTravelRequestStatus(rs.getString("TRAVELREQUESTSTATUS"));
				reqMaster.setCreatedDate(rs.getDate("ACTIONDATE"));
				reqMaster.setTravelDate(rs.getDate("TRAVELDATE"));				
				
				EmployeeDetails empDetails = getEmployeeDetailsById(reqMaster.getEmployeeDetailsId());
				System.out.println("emp name:::"+empDetails.getEmployeeName());
				reqMaster.setRequestedEmpDetails(empDetails);
				reqMasterMap.put(reqMasterId, reqMaster);
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
		System.out.println("end - get approve req db query");
		return reqMasterMap;
	}
	
	public TravelRequestMaster getTravelRequestDetails(String travelRequestMasterId){		
		PreparedStatement ps = null;
		ResultSet rs = null;
		TravelRequestMaster reqMaster = null;
		System.out.println("begin - get req details db query:::travelRequestMasterId:::"+travelRequestMasterId);
		try{
			conn = getDBConnection();
			ps = conn.prepareStatement("select * from TTRAVELREQUESTMASTER where TRAVELREQUESTMASTERID = ?");			
 			ps.setString(1, travelRequestMasterId);			
			rs = ps.executeQuery();				
						
			while(rs.next()){
				reqMaster = new TravelRequestMaster();
				String reqMasterId = rs.getString("TRAVELREQUESTMASTERID");
				reqMaster = new TravelRequestMaster();
				reqMaster.setTravelRequestMasterId(reqMasterId);
				reqMaster.setSource(rs.getString("SOURCE"));
				reqMaster.setDestination(rs.getString("DESTINATION"));
				reqMaster.setTravelModeId(rs.getString("TRAVELMODEID"));
				reqMaster.setExpenses(rs.getString("EXPENSES"));
				reqMaster.setPurpose(rs.getString("PURPOSE"));
				reqMaster.setEmployeeDetailsId(rs.getString("EMPLOYEEDETAILSID"));				
				reqMaster.setAttachmentPath(rs.getString("ATTACHMENTPATH"));
				reqMaster.setTravelRequestStatus(rs.getString("TRAVELREQUESTSTATUS"));
				reqMaster.setCreatedDate(rs.getDate("ACTIONDATE"));
				reqMaster.setTravelDate(rs.getDate("TRAVELDATE"));
				
				System.out.println("reqMasterId:::"+reqMasterId);
				
				TravelRequestVersion reqVersion = null;
				PreparedStatement ps1 = conn.prepareStatement("select * from TTRAVELREQUESTVERSION where TRAVELREQUESTMASTERID = ? ORDER BY APPROVERORDER");
				ps1.setString(1, reqMasterId);			
				ResultSet rs1 = ps1.executeQuery();	
				List<TravelRequestVersion> reqVersionList = new ArrayList<TravelRequestVersion>();
				while(rs1.next()){
					reqVersion = new TravelRequestVersion();
					reqVersion.setTravelRequestVersionId(rs1.getString("TRAVELREQUESTVERSIONID"));
					reqVersion.setTravelRequestMasterId(reqMasterId);
					String approverEmpId = rs1.getString("TRAVELAPPROVERID");
					reqVersion.setTravelApproverId(approverEmpId);
					reqVersion.setApproverOrder(rs1.getString("APPROVERORDER"));
					reqVersion.setActionDate(rs1.getDate("ACTIONDATE"));
					reqVersion.setStatusId(rs1.getString("REQSTATUSID"));
					
					EmployeeDetails approverEmpDetails = getEmployeeDetailsById(approverEmpId);
					reqVersion.setApproverEmpDetails(approverEmpDetails);					
					reqVersionList.add(reqVersion);
				}
				reqMaster.setReqVersionList(reqVersionList);
				EmployeeDetails empDetails = getEmployeeDetailsById(reqMaster.getEmployeeDetailsId());
				System.out.println("emp name:::"+empDetails.getEmployeeName());
				reqMaster.setRequestedEmpDetails(empDetails);				
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
		System.out.println("end - get approve req db query");
		return reqMaster;
	}
	
	public boolean updateTravelRequest(String action, String travelReqVersionId, String approverEmpId){
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		TravelRequestMaster reqMaster = null;
		boolean updateStatus = false;
		System.out.println("begin - get req version update db query:::travelReqVersionId:::"+travelReqVersionId);
		try{
			conn = getDBConnection();
			ps = conn.prepareStatement("update TTRAVELREQUESTVERSION set REQSTATUSID = ?, ACTIONDATE = sysdate where TRAVELREQUESTVERSIONID = ?");
			if("approve".equalsIgnoreCase(action)){
				ps.setString(1, requestStatus.APPROVED);
			}else if("reject".equalsIgnoreCase(action)){
				ps.setString(1, requestStatus.REJECTED);
			}else{
				ps.setString(1, requestStatus.PENDING);	
			} 			
 			ps.setString(2, travelReqVersionId);						
 			ps.executeQuery();
 			
			ps1 = conn.prepareStatement("select REQSTATUSID, TRAVELREQUESTMASTERID from TTRAVELREQUESTVERSION where TRAVELREQUESTMASTERID = (select TRAVELREQUESTMASTERID from TTRAVELREQUESTVERSION where TRAVELREQUESTVERSIONID = ?)");
			ps1.setString(1, travelReqVersionId);			
			rs = ps1.executeQuery();
			boolean isTravelRequestVersionStatus = false;			
			while(rs.next()){				
				if(rs.getString("REQSTATUSID") == requestStatus.APPROVED)
					isTravelRequestVersionStatus = true;
				else
					break;
			}
			if(isTravelRequestVersionStatus){
				ps2 = conn.prepareStatement("update TTRAVELREQUESTMASTER set TRAVELREQUESTSTATUS = ?, ACTIONDATE = sysdate where TRAVELREQUESTMASTERID = (select TRAVELREQUESTMASTERID from TTRAVELREQUESTVERSION where TRAVELREQUESTVERSIONID = ?)");
				ps2.setString(1, requestStatus.APPROVED);
				ps2.setString(2, travelReqVersionId);			
				ps2.executeQuery();
			}
			updateStatus = true;
		}catch(Exception e){
			System.out.println("exception in updating req master and req version table query:::"+e.getMessage());
		}
		finally{
			try{
				ps.close();
				ps1.close();
				rs.close();
				conn.close();
			}catch(Exception e){}
		}	
		return updateStatus;
	}
	
	public Map<String, String> getTravelModes(){
		Statement st = null;
		ResultSet rs = null;		
		Map<String, String> travelModesMap = new HashMap<String, String>();
		try{
			conn = getDBConnection();
			st = conn.createStatement();
			rs = st.executeQuery("select * from TTRAVELMODES order by TRAVELMODEID");
 			
			while(rs.next()){
				travelModesMap.put(rs.getString("TRAVELMODEID"), rs.getString("TRAVELMODENAME"));
			} 
		}catch(Exception e){
			System.out.println("exception in updating req master and req version table query:::"+e.getMessage());
		}
		finally{
			try{
				st.close();				
				rs.close();
				conn.close();
			}catch(Exception e){}
		}	
		return travelModesMap;
	}
}
