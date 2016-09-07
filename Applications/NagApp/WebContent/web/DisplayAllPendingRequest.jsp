<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.*" %>
<%@ page import="com.nag.bean.*" %>
<%@ page import="com.nag.dao.*" %>
<%@ page import="com.nag.sql.*" %>

<%
DataBaseConnection dbHandler = new DataBaseConnection();
RequestStatus reqStatus = new RequestStatus();
EmployeeDetails empDetails = (EmployeeDetails)session.getAttribute("loginUserDetails");
String empDetailsId = empDetails.getEmployeeDetailsId();
Map <String, TravelRequestMaster> allPendingRequestMap = dbHandler.getAllTravelRequestByStatus(reqStatus.PENDING);		
		
request.setAttribute("allPendingRequestMap", allPendingRequestMap);	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Details</title>
<link href="../styles/styles.css" type="text/css" rel="stylesheet">
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
	<div class="header">
		<jsp:include page="header.jsp" />
	</div>	
	
	<div id="innerMainDiv">	
		<div id="contentDiv">
		
		<div id="displayAllPendingReq" class="pageContent">			
				
			<div class="container">
				<jsp:include page="adminMenu.jsp" />
				
				<div class="contentSection">
					<div class="heading">All Pending Requests</div>
					<div class="">
					<%
						if(allPendingRequestMap!= null){							
						
					%>
						<table class="pedningReqTable display" id="resultTable" cellspacing="0" width="100%">
							<thead>
								<tr>
									<td class="HeaderTd" style="width:8%;">No</td>
									<td class="HeaderTd">Requested By</td>
									<td class="HeaderTd">Source</td>
									<td class="HeaderTd">Destination</td>
									<td class="HeaderTd dateTd">Travel Date</td>									
									<td class="HeaderTd">Expenses</td>
									<td class="HeaderTd dateTd">Requested Date</td>
									<td class="HeaderTd"></td>																		
								</tr>
							</thead>							
							<tbody>
								<%
								int count=0;
								for (Map.Entry<String, TravelRequestMaster> entry : allPendingRequestMap.entrySet())
								{
									TravelRequestMaster reqMaster= entry.getValue();
									
									count=count+1;							
								%>
							
								<tr>
									<td><%= count %></td>	
									<td><%=reqMaster.getRequestedEmpDetails().getEmployeeName() %></td>
									<td><%= reqMaster.getSource()%></td>
									<td><%= reqMaster.getDestination()%></td>
									<td><fmt:formatDate value="<%= reqMaster.getTravelDate()%>" type="date" pattern="dd-MM-yyyy" /></td>
									
									<td><%= reqMaster.getExpenses()%></td>
									<td>
									<fmt:formatDate value="<%= reqMaster.getCreatedDate()%>" type="date" pattern="dd-MM-yyyy" />
									</td>
									<td><a href="TravelRequestDetails?requestFrom=owner&travelRequestMasterId=<%= reqMaster.getTravelRequestMasterId()%>">Details</a></td>														
								</tr>
								<%
								}
								
								%>
							</tbody>
						</table>
						
						<%}else{ %>
							No Approved travel requests.
						<% } %>
					</div>
				</div>
			</div>				
		</div>
		
		<div class="footerPush"></div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</div>
<jsp:include page="DataTableImpl.jsp" />
</body>
</html>