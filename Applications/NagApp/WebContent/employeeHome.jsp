<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*" %>
<%@ page import="com.nag.bean.*" %>
<%
	EmployeeDetails empDetails = (EmployeeDetails)request.getAttribute("empDetails");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Details</title>
<link href="styles/styles.css" type="text/css" rel="stylesheet">
<style>
.welcomeMssg{
	float:left;
	width:100%;
	text-align:center;
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
						<li><a href="">Employee Profile</a></li>
						<li><a href="NewTravelRequest.jsp">New Travel Request</a></li>
						<li><a href="">Travel Request Approved</a></li>
						<li><a href="">Travel Request Pending</a></li>
						<li><a href="">Travel Request Rejected</a></li>
						<li><a href="">Approve Travel Request</a></li>
					</ul>						
				</div>
				
				<div class="contentSection">
					<div class="welcomeMssg">Welcome ${empDetails.employeeName}</div>
				</div>
			</div>				
		</div>
		
		<div class="footer">Footer</div>
	</div>
</div>
</body>
</html>