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
						<li><span class="currentMenuItem">Travel Request Pending</span></li>
						<li><a href="">Travel Request Rejected</a></li>
						<li><a href="">Approve Travel Request</a></li>
						<li><a href="">Approved Travel Requests by you</a></li>
						<li><a href="LogOut">Log out</a></li>
					</ul>						
				</div>
				
				<div class="contentSection">
					<div class=""></div>					
					<div class="">${pendingRequestList}</div>
				</div>
			</div>				
		</div>
		
		<div class="footer">Footer</div>
	</div>
</div>
</body>
</html>