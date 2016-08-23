<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ page import="com.nag.bean.*" %>
<%@ page import="com.nag.dao.*" %>

<%
	//EmployeeDetails empDetails = (EmployeeDetails)request.getAttribute("loginUserDetails");	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Details</title>
<link href="styles/styles.css" type="text/css" rel="stylesheet">
<style>
.welcomeMssg{
	color: blue;
    float: left;
    font-weight: bold;
    text-align: center;
    width: 100%;
}
.displayMssg{
	float:left;
	width:100%;
	text-align:center;
	padding-top:30px;
}
.empProfile{
	float:left;
	width:100%;
}
.dataRowDiv{
	float:left;
	min-width:80%;
	padding:5px 0;
	min-height:20px;
}
.dataRowDiv .leftDiv{
	float:left;
	width:29%;
	text-align:right;
	padding-right:2%;
	font-weight:bold;
}
.dataRowDiv .rightDiv{
	float:left;
	text-align:left;
	width:69%;
}
.zebraPattern1{
	background-color:grey;
	color:white;
}
.empProfile input{
	width:200px;
}
.submitBtnDiv{
	text-align:center;
	float:left;
	width:100%;
}
.reqFeildsMsgDiv{
	float:left;
	font-size:12px;
	width:52%;
}
.reqFeildsMsg{
	float:right;	
}
</style>
</head>
<body>
<div id="mainDiv">
	<div id="innerMainDiv">
		
		<div class="header">Menu</div>
		
		<div id="empHome" class="pageContent">			
				
			<div class="container">
				<div class="menuSection">						
					<ul class="menuItems">
						<li><a href="AdminHome.jsp">Admin Home</a></li>
						<li><a href="EmployeeRegistration.jsp">Employee Registration</a></li>
						<li><a href="UploadEmpData.jsp">Upload Employee's Data</a></li>
						<li></li>
						<li></li>
						
						<li><a href="GetAllApprovedRequest">Approved Travel Requests</a></li>
						<li><a href="GetAllPendingRequest">Pending Travel Requests</a></li>
						<li><a href="GetAllRejectedRequest">Rejected Travel Requests</a></li>
						<li></li>
						<li></li>						
						<li><a href="LogOut">Log out</a></li>
					</ul>						
				</div>
				
				<div class="contentSection">
					<div class="welcomeMssg">Welcome Admin</div>	
					<c:choose>
					<c:when test="{displayMessage != null}">
						<div class="displayMssg">${displayMessage}</div>
					</c:when>
					<c:otherwise>	
						<div class="subHead">Employee Registration Form</div>	
						<div class="empProfile">
						<form id="empRegistration" action="RegisterEmployee" method="POST" >
							<div class="errorMsgDiv"><span class="errorMsg">${errorMessage}</span></div>
							<div class="reqFeildsMsgDiv"><span class="reqFeildsMsg">*required fields</span></div>
							<div class="dataRowDiv zebraPattern">
								<div class="leftDiv">Employee Id*:</div>
								<div class="rightDiv"><input type="text" name="employeeId" id="employeeId" /></div>
							</div>
							<div class="dataRowDiv">
								<div class="leftDiv">Employee Name*:</div>
								<div class="rightDiv"><input type="text" name="employeeName" id="employeeName" /></div>
							</div>
							<div class="dataRowDiv">
								<div class="leftDiv">Date of Birth*:</div>
								<div class="rightDiv"><input type="text" name="dob" id="dob" /></div>
							</div>					
							<div class="dataRowDiv">
								<div class="leftDiv">Designation*:</div>
								<div class="rightDiv">
									<select id="designationId" name="designationId" style="width: 206px;">
										<option value="select">Select</option>
									<c:forEach items="${designationMap}" var="designation" varStatus="status">													
										<option value="${designation.key}">${designation.value}</option>
									</c:forEach>
									</select>
								</div>
							</div>
							<div class="dataRowDiv zebraPattern">
								<div class="leftDiv">Department*:</div>
								<div class="rightDiv">
									<select id="departmentId" name="departmentId" style="width: 206px;">
										<option value="select">Select</option>
									<c:forEach items="${departmentMap}" var="department" varStatus="status">													
										<option value="${department.key}">${department.value}</option>
									</c:forEach>
									</select>
								</div>
							</div>
							<div class="dataRowDiv">
								<div class="leftDiv">Email*:</div>
								<div class="rightDiv"><input type="text" name="email" id="email" /></div>
							</div>
							<div class="dataRowDiv zebraPattern">
								<div class="leftDiv">Mobile*:</div>
								<div class="rightDiv"><input type="text" name="mobile" id="mobile" /></div>
							</div>
							<div class="dataRowDiv">
								<div class="leftDiv">Land line Number:</div>
								<div class="rightDiv"><input type="text" name="landline" id="landline" /></div>
							</div>
							<div class="dataRowDiv">
								<div class="leftDiv">Extension:</div>
								<div class="rightDiv"><input type="text" name="extension" id="extension" /></div>
							</div>
							<div class="dataRowDiv">								
								<div class="submitBtnDiv"><input type="submit" id="sumbitBtn" value="register" style="width:100px;"/></div>
							</div>	
							</form>						
						</div>
					</c:otherwise>
					</c:choose>
				</div>
			</div>				
		</div>
		
		<div class="footer">Footer</div>
	</div>
</div>
</body>
</html>