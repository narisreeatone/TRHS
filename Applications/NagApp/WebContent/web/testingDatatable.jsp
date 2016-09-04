
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.*" %>
<%@ page import="com.nag.sql.*" %>
<%@ page import="com.nag.bean.*" %>
<%@ page import="com.nag.dao.*" %>

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

<title>Employee Details</title>
<link href="styles/styles.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="http://code.jquery.com/jquery-1.12.3.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
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
		<div id="contentDiv">
		
		<div id="displayAllPendingReq" class="pageContent">			
				
			<div class="container">
				
				
				<div class="contentSection">
					<div class="heading">All Pending Requests</div>
					<div class="">
					
					<%
						if(allPendingRequestMap!= null){							
						
					%>
						<table class="display" id="resultTable">
							<thead>
								<tr>
									<th>S No</th>
									<th>Requested</th>
									<th>Source</th>
									<th>Destination</th>
									<th>Travel</th>
									<th></th>
									<th></th>
									<th></th>							
																							
								</tr>
								</thead>
								<%
								for (Map.Entry<String, TravelRequestMaster> entry : allPendingRequestMap.entrySet())
								{
									TravelRequestMaster reqMaster= entry.getValue();
								
								%>
								<tbody>
								<tr>
									<td class="dataTd"></td>	
									<td class="dataTd"><%= reqMaster.getRequestedEmpDetails().getEmployeeName() %></td>
									<td class="dataTd"><%= reqMaster.getSource() %></td>
									<td class="dataTd"></td>
									<td class="dataTd"><fmt:formatDate value="<%= reqMaster.getTravelDate() %>" type="date" pattern="dd-MM-yyyy" /></td>
									
									<td class="dataTd"></td>
									<td class="dataTd">
								
									</td>
									<td class="dataTd"><a href="TravelRequestDetails?requestFrom=owner&travelRequestMasterId=">Details</a></td>														
								</tr>
								<%
								}
								}
								%>
								
							</tbody>
						</table>
						
					
					</div>
				</div>
			</div>				
		</div>
		
		<div class="footerPush"></div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</div>


<script>

$("document").ready(function() {	
	$('#resultTable').DataTable();
});
</script>
</body>
</html>