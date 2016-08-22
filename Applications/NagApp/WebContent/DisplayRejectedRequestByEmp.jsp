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
.pedningReqTable{
	float:left;
	width:100%;
	
}
.HeaderTd{
	font-weight:bold;
	padding:5px 5px 5px 0;
	width:15%;
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
						<li><a href="employeeHome.jsp">Employee Profile</a></li>
						<li><a href="NewTravelRequest.jsp">New Travel Request</a></li>
						<li><a href="GetApprovedRequest">Approved Travel Requests</a></li>
						<li><a href="GetrejectedRequest">Pending Travel Requests</a></li>
						<li><a href="GetRejectedRequest">Rejected Travel Requests</a></li>
						<li></li>
						<li></li>
						<li><a href="GetApproveRequest">Approve Travel Request</a></li>
						<li><a href="GetApprovedReqByEmp">Approved Travel Requests by you</a></li>
						<li><a href="GetRejectedReqByEmp">Rejected Travel Requests by you</a></li>
						<li><a href="LogOut">Log out</a></li>
					</ul>						
				</div>
				
				<div class="contentSection">
					<div class="heading">Rejected Requests by you</div>
					<div class="">
					<c:choose>
					<c:when test="${not empty rejectedRequestByEmpMap}">
						<table class="pedningReqTable">
							<tbody>
								<tr>
									<td class="HeaderTd" style="width:8%;">S No</td>
									<td class="HeaderTd">Source</td>
									<td class="HeaderTd">Destination</td>
									<td class="HeaderTd">Travel Date</td>
									<td class="HeaderTd">Travel Mode</td>
									<td class="HeaderTd">Expenses</td>
									<td class="HeaderTd">Date</td>																		
								</tr>
								<c:set var="count" value="0"></c:set>
								<c:forEach items="${rejectedRequestByEmpMap}" var="rejectedRequest" varStatus="status">
								<tr>
									<td class="dataTd">${count + 1}</td>	
									<td class="dataTd">${rejectedRequest.value.source}</td>
									<td class="dataTd">${rejectedRequest.value.destination}</td>
									<td class="dataTd">${rejectedRequest.value.travelDate}</td>
									<td class="dataTd">
										<c:forEach items="${travelModesMap}" var="travelModes" varStatus="status">													
										<c:if test="${travelModes.key == rejectedRequest.value.travelModeId}">
											${travelModes.value}
										</c:if>
										</c:forEach>
									</td>
									<td class="dataTd">${rejectedRequest.value.expenses}</td>
									<td class="dataTd">${rejectedRequest.value.createdDate}</td>	
									<td class="dataTd"><a href="TravelRequestDetails?requestFrom=completed&travelRequestMasterId=${rejectedRequest.value.travelRequestMasterId}">Details</a></td>															
								</tr>
								</c:forEach>
							</tbody>
						</table>
						</c:when>
						<c:otherwise>
							No rejected travel requests.
						</c:otherwise>
					</c:choose>
					</div>
				</div>
			</div>				
		</div>
		
		<div class="footer">Footer</div>
	</div>
</div>
</body>
</html>