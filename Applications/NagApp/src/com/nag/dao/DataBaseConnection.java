package com.nag.dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import com.nag.bean.*;
import com.nag.*;
import com.nag.formbean.*;
import com.nag.sql.*;
import com.nag.util.PasswordGenerator;

import oracle.jdbc.OracleTypes;

public class DataBaseConnection {
	private Connection conn = null;
	RequestStatus requestStatus = new RequestStatus();
	public Connection getDBConnection(){		
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
	
	public EmployeeLoginDetails getLoginDetails(String employeeId){		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		EmployeeLoginDetails empLoginDetails = new EmployeeLoginDetails();
		//conn = getDBConnection();
		/*Connection conn1 = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");			
			conn1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","nagarjuna","nagarjuna");
		}catch(Exception e){
			
		}*/
		try{
			stmt=conn.prepareStatement("select * from TLOGIN where USERNAME=?");			
			stmt.setString(1, employeeId);
			rs = stmt.executeQuery();			
			while(rs.next()){
				empLoginDetails.setLoginId(rs.getInt("LOGINID"));
				empLoginDetails.setUserName(rs.getString("USERNAME"));
				empLoginDetails.setLoginPassword(rs.getString("LOGINPASSWORD"));
				empLoginDetails.setEmployeeDetailsId(rs.getString("EMPLOYEEDETAILSID"));
				empLoginDetails.setLastLoginDate(rs.getDate("LASTLOGINDATE"));
				empLoginDetails.setIsRandomPwd(rs.getString("ISRANDOMPWD"));
				empLoginDetails.setIsActive(rs.getString("ISACTIVE"));
				empLoginDetails.setEmployeeRoleId(rs.getString("EMPLOYEEROLEID"));
			}
		}catch(SQLException e){
			System.out.println("exception in getting login detials:::"+e.getMessage());
		}
		finally{			
			try{
				stmt.close();
				rs.close();	
				//conn.close();
			}catch(Exception e){}
		}
		return empLoginDetails;
	}
	
	public EmployeeDetails getEmployeeDetailsByLogin(String username, String password){
		
		conn = getDBConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;	
		EmployeeDetails empDetails = new EmployeeDetails();
		EmployeeLoginDetails empLoginDetails = new EmployeeLoginDetails();
		boolean isAdmin = false;
		boolean isRandomPwd = false;
		
		//PreparedStatement stmt1 = null;
		//ResultSet rs1 = null;
		String employeeDetailsId = null;
		empLoginDetails = getLoginDetails(username);
		
		if(empLoginDetails != null){
			if(password.equals(empLoginDetails.getLoginPassword())){
			
				employeeDetailsId = empLoginDetails.getEmployeeDetailsId();
				isAdmin = "1".equals(empLoginDetails.getEmployeeRoleId())?true:false;
				isRandomPwd = "Y".equals(empLoginDetails.getIsRandomPwd())?true:false;
			}
		}
		
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
					empDetails.setDesignationId(rs.getString("DESIGNATIONID"));
					empDetails.setDeptId(rs.getString("DEPARTMENTID"));	
					
					empDetails.setDob(rs.getDate("DATEOFBIRTH"));
					empDetails.setCreatedDate(rs.getDate("ACTIONDATE"));
					//isAdmin = "1".equals(rs.getString("EMPLOYEEROLEID"))?true:false;
					empDetails.setAdmin(isAdmin);
					empDetails.setRandomPwd(isRandomPwd);
					empDetails.setActive("Y".equals(rs.getString("ISACTIVE")));
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
		//boolean isempActive =false;
		try{
			stmt=conn.prepareStatement("select Emp.*, Dept.DEPARTMENTNAME, Desi.DESIGNATIONNAME   from TEMPLOYEEDETAILS Emp, TDEPARTMENTS Dept, TDESIGNATION Desi where Emp.DESIGNATIONID = Desi.DESIGNATIONID AND Emp.DEPARTMENTID = Dept.DEPARTMENTID and Emp.ISACTIVE = 'Y'");						
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
				empDetails.setDesignationId(rs.getString("DESIGNATIONID"));
				empDetails.setDeptId(rs.getString("DEPARTMENTID"));
				empDetails.setDepartmentName(rs.getString("DEPARTMENTNAME"));
				empDetails.setDesignationName(rs.getString("DESIGNATIONNAME"));
				
				empDetails.setDob(rs.getDate("DATEOFBIRTH"));
				empDetails.setCreatedDate(rs.getDate("ACTIONDATE"));				
				empDetails.setActive("Y".equals(rs.getString("ISACTIVE")));
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
		conn = getDBConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		EmployeeDetails empDetails = null;	
		if( empDetailsId != null){
			try{
				ps=conn.prepareStatement("select Emp.*, Dept.DEPARTMENTNAME AS EMPDEPARTMENTNAME, Desi.DESIGNATIONNAME AS EMPDESIGNATIONNAME from TEMPLOYEEDETAILS Emp, TDEPARTMENTS Dept, TDESIGNATION Desi where Emp.DESIGNATIONID = Desi.DESIGNATIONID AND Emp.DEPARTMENTID = Dept.DEPARTMENTID and EMPLOYEEDETAILSID = ?");
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
					empDetails.setDesignationId(rs.getString("DESIGNATIONID"));
					empDetails.setDeptId(rs.getString("DEPARTMENTID"));
					empDetails.setDepartmentName(rs.getString("EMPDEPARTMENTNAME"));
					empDetails.setDesignationName(rs.getString("EMPDESIGNATIONNAME"));
					
					empDetails.setDob(rs.getDate("DATEOFBIRTH"));
					empDetails.setCreatedDate(rs.getDate("ACTIONDATE"));
					empDetails.setActive("Y".equals(rs.getString("ISACTIVE")));
				}
			}catch(Exception e){
				System.out.println("exception in get employee query:::"+e.getMessage());
			}
			finally{
				try{
					ps.close();
					rs.close();
					//conn1.close();
				}catch(Exception e){}
			}			
		}
		return empDetails;
	}
	
	public EmployeeDetails getEmployeeDetailsByEmpId(String empId){		
		conn = getDBConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		EmployeeDetails empDetails = null;	
		if( empId != null){
			try{
				ps=conn.prepareStatement("select Emp.*, Dept.DEPARTMENTNAME AS EMPDEPARTMENTNAME, Desi.DESIGNATIONNAME AS EMPDESIGNATIONNAME from TEMPLOYEEDETAILS Emp, TDEPARTMENTS Dept, TDESIGNATION Desi where Emp.DESIGNATIONID = Desi.DESIGNATIONID AND Emp.DEPARTMENTID = Dept.DEPARTMENTID and EMPLOYEEID = ?");
				ps.setString(1, empId);			
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
					empDetails.setDesignationId(rs.getString("DESIGNATIONID"));
					empDetails.setDeptId(rs.getString("DEPARTMENTID"));
					empDetails.setDepartmentName(rs.getString("EMPDEPARTMENTNAME"));
					empDetails.setDesignationName(rs.getString("EMPDESIGNATIONNAME"));
					
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
				ps2.setInt(4, Integer.parseInt(empIdAndOrder[1]));
				ps2.setString(5, requestStatus.PENDING);
				ps2.executeQuery();
			}			
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
		try{
			conn = getDBConnection();
			ps = conn.prepareStatement("select * from TTRAVELREQUESTMASTER where TRAVELREQUESTSTATUS = ? and EMPLOYEEDETAILSID = ? ORDER BY ACTIONDATE");			
 			ps.setString(1, requestStatus.PENDING);
 			ps.setString(2, empDetailsId);
			rs = ps.executeQuery();				
			while(rs.next()){
				reqMaster = new TravelRequestMaster();
				String reqMasterId = rs.getString("TRAVELREQUESTMASTERID");				
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
			System.out.println("exception in get pending req from master table query:::"+e.getMessage());
		}
		finally{
			try{
				ps.close();
				rs.close();
				conn.close();
			}catch(Exception e){}
		}			
		return reqMasterMap;
	}
	
	
	public Map<String, TravelRequestMaster> getAprrovedRequestForEmployee(String empDetailsId){
		Map<String, TravelRequestMaster> reqMasterMap = new HashMap<String, TravelRequestMaster>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		TravelRequestMaster reqMaster = null;		
		try{
			conn = getDBConnection();
			ps = conn.prepareStatement("select * from TTRAVELREQUESTMASTER where TRAVELREQUESTSTATUS = ? and EMPLOYEEDETAILSID = ? ORDER BY ACTIONDATE");
			ps.setString(1, requestStatus.APPROVED);
 			ps.setString(2, empDetailsId);			
			rs = ps.executeQuery();				
			while(rs.next()){
				reqMaster = new TravelRequestMaster();
				String reqMasterId = rs.getString("TRAVELREQUESTMASTERID");				
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
		return reqMasterMap;
	}
	
	public Map<String, TravelRequestMaster> getRejectedRequestForEmployee(String empDetailsId){
		Map<String, TravelRequestMaster> reqMasterMap = new HashMap<String, TravelRequestMaster>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		TravelRequestMaster reqMaster = null;
		
		try{
			conn = getDBConnection();
			ps = conn.prepareStatement("select * from TTRAVELREQUESTMASTER where TRAVELREQUESTSTATUS = ? and EMPLOYEEDETAILSID = ? ORDER BY ACTIONDATE");
			ps.setString(1, requestStatus.REJECTED);
 			ps.setString(2, empDetailsId);			
			rs = ps.executeQuery();				
			while(rs.next()){
				reqMaster = new TravelRequestMaster();
				String reqMasterId = rs.getString("TRAVELREQUESTMASTERID");				
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
		return reqMasterMap;
	}
	
	public Map<String, TravelRequestMaster> getApproveRequestForEmployee(String empDetailsId){
		Map<String, TravelRequestMaster> reqMasterMap = new HashMap<String, TravelRequestMaster>();
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		Connection conn1 = null;
		TravelRequestMaster reqMaster = null;
		
		try{
			conn1 = getDBConnection();
			ps = conn1.prepareStatement("SELECT TRAVELREQUESTMASTERID  FROM TTRAVELREQUESTVERSION WHERE TRAVELAPPROVERID = ? AND REQSTATUSID = ? order by ACTIONDATE desc");			
 			
			ps.setString(1, empDetailsId);
			ps.setString(2, requestStatus.PENDING); 			
			rs = ps.executeQuery();				
						
			while(rs.next()){
				Integer reqMasterId = rs.getInt(1);
				int empApproveOrder = 1;
				ps2 = conn1.prepareStatement("SELECT APPROVERORDER FROM TTRAVELREQUESTVERSION WHERE TRAVELAPPROVERID = ? AND TRAVELREQUESTMASTERID = ? AND REQSTATUSID = ?");
				ps2.setString(1, empDetailsId);
				ps2.setInt(2, reqMasterId);				
				ps2.setString(3, requestStatus.PENDING);
				rs2 = ps2.executeQuery();
				if(rs2.next()){
					empApproveOrder = rs2.getInt("APPROVERORDER");
				}
				if (empApproveOrder == 1){					
					reqMaster = getTravelRequestDetails(reqMasterId.toString());
					reqMasterMap.put(reqMasterId.toString(), reqMaster);
				}else{					
					ps1 = conn1.prepareStatement("SELECT COUNT(*)  FROM TTRAVELREQUESTVERSION WHERE APPROVERORDER < ? AND TRAVELREQUESTMASTERID = ? AND REQSTATUSID = ? order by ACTIONDATE desc");
					ps1.setInt(1, empApproveOrder);				
					ps1.setInt(2, reqMasterId);				
					ps1.setString(3, requestStatus.APPROVED);
					rs1 = ps1.executeQuery();					
					while(rs1.next()){										
						if(rs1.getInt(1) > 0){
							reqMaster = getTravelRequestDetails(reqMasterId.toString());
							reqMasterMap.put(reqMasterId.toString(), reqMaster);
						}
					}
				}
			}
		}catch(Exception e){
			System.out.println("exception in get approve req for emplyoee query:::"+e.getMessage());
		}
		finally{
			try{
				ps.close();
				rs.close();
				ps1.close();
				rs1.close();
				ps2.close();
				rs2.close();
				conn1.close();
			}catch(Exception e){}
		}		
		return reqMasterMap;	
	}
	
	public Map<String, TravelRequestMaster> getAprrovedRequestByEmployee(String empDetailsId){
		Map<String, TravelRequestMaster> reqMasterMap = new HashMap<String, TravelRequestMaster>();
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		TravelRequestMaster reqMaster = null;
		
		try{
			conn = getDBConnection();
			ps = conn.prepareStatement("select TRAVELREQUESTMASTERID from TTRAVELREQUESTVERSION where REQSTATUSID = ? and TRAVELAPPROVERID = ? ORDER BY ACTIONDATE");
			ps.setString(1, requestStatus.APPROVED);
 			ps.setString(2, empDetailsId);			
			rs = ps.executeQuery();				
			while(rs.next()){
				String reqMasterId = rs.getString("TRAVELREQUESTMASTERID");
				ps1 = conn.prepareStatement("select * from TTRAVELREQUESTMASTER where TRAVELREQUESTMASTERID = ?");
				ps1.setString(1, reqMasterId);
				rs1 = ps1.executeQuery();
				while(rs1.next()){
					reqMaster = new TravelRequestMaster();					
					reqMaster.setTravelRequestMasterId(reqMasterId);
					reqMaster.setSource(rs1.getString("SOURCE"));
					reqMaster.setDestination(rs1.getString("DESTINATION"));
					reqMaster.setTravelModeId(rs1.getString("TRAVELMODEID"));
					reqMaster.setExpenses(rs1.getString("EXPENSES"));
					reqMaster.setPurpose(rs1.getString("PURPOSE"));
					reqMaster.setEmployeeDetailsId(empDetailsId);				
					reqMaster.setAttachmentPath(rs1.getString("ATTACHMENTPATH"));
					reqMaster.setTravelRequestStatus(rs1.getString("TRAVELREQUESTSTATUS"));
					reqMaster.setCreatedDate(rs1.getDate("ACTIONDATE"));
					reqMaster.setTravelDate(rs1.getDate("TRAVELDATE"));
				}
				reqMasterMap.put(reqMasterId, reqMaster);
			}
		}catch(Exception e){
			System.out.println("exception in get approve req by emp table query:::"+e.getMessage());
		}
		finally{
			try{
				ps.close();
				rs.close();
				conn.close();
			}catch(Exception e){}
		}			
		return reqMasterMap;
	}
	
	public Map<String, TravelRequestMaster> getRejectedRequestByEmployee(String empDetailsId){
		Map<String, TravelRequestMaster> reqMasterMap = new HashMap<String, TravelRequestMaster>();
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		TravelRequestMaster reqMaster = null;
		
		try{
			conn = getDBConnection();
			ps = conn.prepareStatement("select TRAVELREQUESTMASTERID from TTRAVELREQUESTVERSION where REQSTATUSID = ? and TRAVELAPPROVERID = ? ORDER BY ACTIONDATE");
			ps.setString(1, requestStatus.REJECTED);
 			ps.setString(2, empDetailsId);			
			rs = ps.executeQuery();				
			while(rs.next()){
				Integer reqMasterId = rs.getInt(1);
				ps1 = conn.prepareStatement("select * from TTRAVELREQUESTMASTER where TRAVELREQUESTMASTERID = ?");
				ps1.setInt(1, reqMasterId);
				rs1 = ps1.executeQuery();
				while(rs1.next()){
					reqMaster = new TravelRequestMaster();					
					reqMaster.setTravelRequestMasterId(reqMasterId.toString());
					reqMaster.setSource(rs1.getString("SOURCE"));
					reqMaster.setDestination(rs1.getString("DESTINATION"));
					reqMaster.setTravelModeId(rs1.getString("TRAVELMODEID"));
					reqMaster.setExpenses(rs1.getString("EXPENSES"));
					reqMaster.setPurpose(rs1.getString("PURPOSE"));
					reqMaster.setEmployeeDetailsId(empDetailsId);				
					reqMaster.setAttachmentPath(rs1.getString("ATTACHMENTPATH"));
					reqMaster.setTravelRequestStatus(rs1.getString("TRAVELREQUESTSTATUS"));
					reqMaster.setCreatedDate(rs1.getDate("ACTIONDATE"));
					reqMaster.setTravelDate(rs1.getDate("TRAVELDATE"));
				}
				reqMasterMap.put(reqMasterId.toString(), reqMaster);
			}
		}catch(Exception e){
			System.out.println("exception in get rejected req by emp table query:::"+e.getMessage());
		}
		finally{
			try{
				ps.close();
				rs.close();
				conn.close();
			}catch(Exception e){}
		}			
		return reqMasterMap;
	}
	
	public TravelRequestMaster getTravelRequestDetails(String travelRequestMasterId){		
		PreparedStatement ps = null;
		ResultSet rs = null;
		TravelRequestMaster reqMaster = null;
		
		try{
			conn = getDBConnection();
			ps = conn.prepareStatement("select * from TTRAVELREQUESTMASTER where TRAVELREQUESTMASTERID = ?");			
 			ps.setString(1, travelRequestMasterId);			
			rs = ps.executeQuery();				
						
			while(rs.next()){
				reqMaster = new TravelRequestMaster();
				String reqMasterId = rs.getString("TRAVELREQUESTMASTERID");				
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
		return reqMaster;
	}
	
	public String updateTravelRequest(String action, String travelReqVersionId, String travelRequestMasterId, String approverEmpId){
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;		
		String updateStatus = "";
		
		try{
			conn = getDBConnection();
			ps = conn.prepareStatement("update TTRAVELREQUESTVERSION set REQSTATUSID = ?, ACTIONDATE = sysdate where TRAVELREQUESTVERSIONID = ? AND TRAVELAPPROVERID = ?");
			if("approve".equalsIgnoreCase(action)){
				ps.setString(1, requestStatus.APPROVED);
			}else if("reject".equalsIgnoreCase(action)){
				ps.setString(1, requestStatus.REJECTED);
			}else{
				ps.setString(1, requestStatus.PENDING);	
			} 			
 			ps.setInt(2, Integer.parseInt(travelReqVersionId));
 			ps.setString(3, approverEmpId);
 			ps.executeQuery();
 			updateStatus = "partialApprove";
 			
 			if("reject".equalsIgnoreCase(action)){
 				ps2 = conn.prepareStatement("update TTRAVELREQUESTMASTER set TRAVELREQUESTSTATUS = ?, ACTIONDATE = sysdate where TRAVELREQUESTMASTERID = ?");
				ps2.setString(1, requestStatus.REJECTED);
				ps2.setInt(2, Integer.parseInt(travelRequestMasterId));			
				ps2.executeQuery();
				updateStatus = "rejected";
 			}else if("approve".equalsIgnoreCase(action)){
				ps1 = conn.prepareStatement("select REQSTATUSID from TTRAVELREQUESTVERSION where TRAVELREQUESTMASTERID = ?");
				ps1.setString(1, travelRequestMasterId);			
				rs = ps1.executeQuery();
				boolean isTravelRequestApprovedByAll = false;			
				while(rs.next()){				
					if(requestStatus.APPROVED.equals(rs.getString("REQSTATUSID")))
						isTravelRequestApprovedByAll = true;
					else{
						isTravelRequestApprovedByAll = false;
						break;
					}					
				}
				if(isTravelRequestApprovedByAll){
					ps2 = conn.prepareStatement("update TTRAVELREQUESTMASTER set TRAVELREQUESTSTATUS = ?, ACTIONDATE = sysdate where TRAVELREQUESTMASTERID = ?");
					ps2.setString(1, requestStatus.APPROVED);
					ps2.setString(2, travelRequestMasterId);			
					ps2.executeQuery();
				}
				updateStatus = "allApproved";
 			}
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
	
	public boolean registerEmployee(RegisterEmployeeForm registerEmployeeForm){
		PreparedStatement ps = null;		
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		Connection conn1 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		boolean status = false;
		try{
			conn1 = getDBConnection();
			ps1 = conn1.prepareStatement("select max(EMPLOYEEDETAILSID) as NUMBEROFRECORDS from TEMPLOYEEDETAILS");
			rs1 = ps1.executeQuery();
			Integer empDetailsId = 0;
			if(rs1.next())
				if(rs1.getString(1) != null)
					empDetailsId = rs1.getInt("NUMBEROFRECORDS");					
			//insert employee details to emp details table
			ps=conn1.prepareStatement("insert into TEMPLOYEEDETAILS(EMPLOYEEDETAILSID,EMPLOYEEID,EMPLOYEENAME,EMAILID,MOBILENUMBER,LANDLINENUMBER,EXTNNUMBER,DATEOFBIRTH,DESIGNATIONID,DEPARTMENTID,ACTIONDATE,ISACTIVE) values (?,?,?,?,?,?,?,?,?,?,sysdate,?)");
			empDetailsId = empDetailsId + 1;
			ps.setInt(1, empDetailsId);	
			ps.setString(2, registerEmployeeForm.getEmployeeId());
			ps.setString(3, registerEmployeeForm.getEmployeeName());
			
			ps.setString(4, registerEmployeeForm.getEmail());
			ps.setString(5, registerEmployeeForm.getMobile());
			ps.setString(6, registerEmployeeForm.getLandline());
			ps.setString(7, registerEmployeeForm.getExtension());
			ps.setDate(8, new java.sql.Date(registerEmployeeForm.getDob().getTime()));
			ps.setString(9, registerEmployeeForm.getDesignationId());
			ps.setString(10, registerEmployeeForm.getDepartmentId());			
			ps.setString(11, "Y");			
			ps.executeQuery();	
			
		// insert employee login details to login table
			ps3 = conn1.prepareStatement("select max(LOGINID) as NUMBEROFRECORDS from TLOGIN");
			rs2 = ps3.executeQuery();
			Integer loginId = 0;
			if(rs2.next())
				if(rs2.getString(1) != null)
					loginId = rs2.getInt("NUMBEROFRECORDS");
			PasswordGenerator pwdGenerator = new PasswordGenerator();
			ps2 = conn1.prepareStatement("insert into TLOGIN(LOGINID,USERNAME,LOGINPASSWORD,EMPLOYEEDETAILSID,LASTLOGINDATE,ISRANDOMPWD,ISACTIVE,EMPLOYEEROLEID) values (?,?,?,?,sysdate,?,?,?)");
			ps2.setInt(1, loginId);
			ps2.setString(2, registerEmployeeForm.getEmployeeId());
			ps2.setString(3, pwdGenerator.generateRandomString());
			ps2.setInt(4, empDetailsId);
			ps2.setString(5, "Y");			
			ps2.setString(6, "Y");
			ps2.setString(7, null);
			ps2.executeQuery();
			status = true;
		}catch(Exception e){
			System.out.println("exception in saving employee registration:::"+e.getMessage());
		}
		finally{
			try{
				ps.close();
				ps1.close();
				ps2.close();
				ps3.close();
				rs1.close();
				rs2.close();
				conn1.close();
			}catch(Exception e){}
		}			
		return status;
	}
	
	public Map<String, TravelRequestMaster> getAllTravelRequestByStatus(String reqStatus){
		Map<String, TravelRequestMaster> reqMasterMap = new HashMap<String, TravelRequestMaster>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		TravelRequestMaster reqMaster = null;		
		try{
			conn = getDBConnection();
			ps = conn.prepareStatement("select * from TTRAVELREQUESTMASTER where TRAVELREQUESTSTATUS = ? ORDER BY ACTIONDATE");
			if(requestStatus.APPROVED.equals(reqStatus))
				ps.setString(1, requestStatus.APPROVED);
			else if(requestStatus.PENDING.equals(reqStatus))
				ps.setString(1, requestStatus.PENDING);
			else if(requestStatus.REJECTED.equals(reqStatus))
				ps.setString(1, requestStatus.REJECTED);
			rs = ps.executeQuery();				
			while(rs.next()){
				reqMaster = new TravelRequestMaster();
				Integer reqMasterId = rs.getInt("TRAVELREQUESTMASTERID");				
				reqMaster.setTravelRequestMasterId(rs.getString("TRAVELREQUESTMASTERID"));
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
				
				EmployeeDetails requestedEmpDetails = getEmployeeDetailsById(rs.getString("EMPLOYEEDETAILSID"));
				reqMaster.setRequestedEmpDetails(requestedEmpDetails);
				reqMasterMap.put(reqMasterId.toString(), reqMaster);
				
			}			
		}catch(Exception e){
			System.out.println("exception in getting all approved requests query:::"+e.getMessage());
		}
		finally{
			try{
				ps.close();
				rs.close();
				conn.close();
			}catch(Exception e){}
		}			
		return reqMasterMap;
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
			System.out.println("exception in getting travel modes table query:::"+e.getMessage());
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
	
	public boolean updatePassword(String empDetailsId, boolean isRandomPwd, String newPwd){
		boolean status = false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			conn = getDBConnection();
			if(isRandomPwd){
				ps = conn.prepareStatement("update TLOGIN set LOGINPASSWORD = ?, ISRANDOMPWD = 'N' where EMPLOYEEDETAILSID = ?");
			}else{
				ps = conn.prepareStatement("update TLOGIN set LOGINPASSWORD = ? where EMPLOYEEDETAILSID = ?");
			}
			ps.setString(1, newPwd);
			ps.setString(2, empDetailsId);
			rs = ps.executeQuery();
			if(rs.next()){
				status = true;
			}		
		}catch(Exception e){
			System.out.println("exception in update password query:::"+e.getMessage());
		}
		finally{
			try{
				ps.close();
				rs.close();
				conn.close();
			}catch(Exception e){}
		}	
		return status;
	}
	
	public Map<String, String> getDepartment(){
		Statement st = null;
		ResultSet rs = null;		
		Map<String, String> departmentMap = new HashMap<String, String>();
		try{
			conn = getDBConnection();
			st = conn.createStatement();
			rs = st.executeQuery("select * from TDEPARTMENTS order by DEPARTMENTID");
 			
			while(rs.next()){
				departmentMap.put(rs.getString("DEPARTMENTID"), rs.getString("DEPARTMENTNAME"));
			} 
		}catch(Exception e){
			System.out.println("exception in getting travel modes table query:::"+e.getMessage());
		}
		finally{
			try{
				st.close();				
				rs.close();
				conn.close();
			}catch(Exception e){}
		}	
		return departmentMap;
	}
	
	public Map<String, String> getDesignation(){
		Statement st = null;
		ResultSet rs = null;		
		Map<String, String> designationMap = new HashMap<String, String>();
		try{
			conn = getDBConnection();
			st = conn.createStatement();
			rs = st.executeQuery("select * from TDESIGNATION order by DESIGNATIONID");
 			
			while(rs.next()){
				designationMap.put(rs.getString("DESIGNATIONID"), rs.getString("DESIGNATIONNAME"));
			} 
		}catch(Exception e){
			System.out.println("exception in getting travel modes table query:::"+e.getMessage());
		}
		finally{
			try{
				st.close();				
				rs.close();
				conn.close();
			}catch(Exception e){}
		}	
		return designationMap;
	}
	
	public boolean addNewService(String serviceType, String serviceName){
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps1 = null;
		//ResultSet rs1 = null;
		Integer id = 0;
		boolean status = true;
		try{
			conn = getDBConnection();
			if( "dept".equals(serviceType)){
				ps = conn.prepareStatement("select max(DEPARTMENTID) from TDEPARTMENTS");
				rs = ps.executeQuery();				
				if(rs.next()){
					id = rs.getInt(1);
				}
				
				ps1 = conn.prepareStatement("insert into TDEPARTMENTS(DEPARTMENTID,DEPARTMENTNAME,ACTIONDATE) values(?,?,sysdate)");
				ps1.setInt(1, id+1);
				ps1.setString(2, serviceName);	
				ps1.executeQuery();
			}else
			if("designation".equals(serviceType)){
				ps = conn.prepareStatement("select max(DESIGNATIONID) from TDESIGNATION");
				rs = ps.executeQuery();
				if(rs.next()){
					id = rs.getInt(1);
				}
				
				ps1 = conn.prepareStatement("insert into TDESIGNATION(DESIGNATIONID,DESIGNATIONNAME,ACTIONDATE) values(?,?,sysdate)");
				ps1.setInt(1, id+1);
				ps1.setString(2, serviceName);	
				ps1.executeQuery();
			}else
			if("travelMode".equals(serviceType)){
				ps = conn.prepareStatement("select max(TRAVELMODEID) from TTRAVELMODES");
				rs = ps.executeQuery();
				if(rs.next()){
					id = rs.getInt(1);
				}
				
				ps1 = conn.prepareStatement("insert into TTRAVELMODES(TRAVELMODEID,TRAVELMODENAME,ACTIONDATE) values(?,?,sysdate)");
				ps1.setInt(1, id+1);
				ps1.setString(2, serviceName);	
				ps1.executeQuery();
			}						
		}catch(Exception e){
			System.out.println("exception in adding new services to DB:::"+e.getMessage());
			status = false;
		}
		finally{
			try{
				ps.close();
				rs.close();
				ps1.close();
				//rs1.close();
				conn.close();
			}catch(Exception e){}
		}
		return status;
	}
}
