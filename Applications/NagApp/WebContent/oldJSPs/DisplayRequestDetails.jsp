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
	//EmployeeDetails empDetails = (EmployeeDetails)request.getAttribute("loginUserDetails");
	RequestStatus requestStatus = new RequestStatus();	
	request.setAttribute("requestStatus", requestStatus);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Details</title>
<link href="styles/styles.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="javascript/jquery-3.1.0.min.js"></script>
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
.HeaderTd{
	font-weight:bold;
	padding:5px 5px 5px 0;
	width:15%;
}
.requestDetailsDiv{
	float:left;
	width:100%;
}
.requestDetails{
	float:left;
	text-align:center;
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
.zebraPattern{
	background-color:grey;
	color:white;
}
.dataRowDiv{}
.btnDiv{
	float:left;
	width:80%;
	text-align:center;
	padding:15px 0;
}
.approverDetailsTable{
	float:left;
	width:100%;
}
.approverDetailsTable .labelTD{
	font-weight:bold;
	width:13%;
	padding:5px 5px 10px 0;
}
.approverDetailsTable .dataTD{
	padding:5px 5px 10px 0;
}
.approveBtnDiv{
	float:left;
	padding-right:10px;
	padding-left:155px;
}
.rejectBtnDiv{
	float:left;
}
#approveBtn{
	background: #33b5e5 none repeat scroll 0 0;
    border: 0 none;
    color: #ffffff;
    cursor: pointer;
    padding: 7px 0;
    transition: all 0.3s ease 0s;
    width: 150px;
    font-weight:bold;
    
}
#rejectBtn{
	background: #33b5e5 none repeat scroll 0 0;
    border: 0 none;
    color: #ffffff;
    cursor: pointer;
    padding: 7px 0;
    transition: all 0.3s ease 0s;
    width: 150px;
    font-weight:bold;
}
</style>
</head>
<body>
<div id="mainDiv">
	<jsp:include page="header.jsp" />
	<div id="innerMainDiv">	
		<div id="contentDiv">
		
		<div id="displayRequestDetails" class="pageContent">			
				
			<div class="container">
				<c:choose>
				<c:when test="${isAdmin eq true}">
					<jsp:include page="adminMenu.jsp" />
				</c:when>
				<c:otherwise>
				<jsp:include page="employeeMenu.jsp" />
				</c:otherwise>
				</c:choose>
				<div class="contentSection">
					<div class="heading">Request Details</div>
					<div class="subHead">Details</div>
					<div class="requestDetailsDiv">	
						<div class="requestDetails">					
							<div class="dataRowDiv">
								<div class="leftDiv">Requested By:</div>
								<div class="rightDiv">${requestDetails.requestedEmpDetails.employeeName}</div>
							</div>
							<div class="dataRowDiv zebraPattern">
								<div class="leftDiv">Designation:</div>
								<div class="rightDiv">${requestDetails.requestedEmpDetails.designationName}</div>
							</div>
							<div class="dataRowDiv">
								<div class="leftDiv">Department:</div>
								<div class="rightDiv">${requestDetails.requestedEmpDetails.departmentName}</div>
							</div>
							<div class="dataRowDiv zebraPattern">
								<div class="leftDiv">Source:</div>
								<div class="rightDiv">${requestDetails.source}</div>
							</div>
							<div class="dataRowDiv">
								<div class="leftDiv">Destination:</div>
								<div class="rightDiv">${requestDetails.destination}</div>
							</div>
							<div class="dataRowDiv zebraPattern">
								<div class="leftDiv">Travel Mode:</div>
								<div class="rightDiv">
									<c:forEach items="${travelModesMap}" var="travelModes" varStatus="status">													
										<c:if test="${travelModes.key == requestDetails.travelModeId}">
											${travelModes.value}
										</c:if>
									</c:forEach>
								</div>
							</div>
							<div class="dataRowDiv">
								<div class="leftDiv">Travel Date:</div>
								<div class="rightDiv">
								<fmt:formatDate value="${requestDetails.travelDate}" type="date" pattern="dd-MM-yyyy" />
								</div>
							</div>
							<div class="dataRowDiv zebraPattern">
								<div class="leftDiv">Expenses (approximate):</div>
								<div class="rightDiv">${requestDetails.expenses}</div>
							</div>
							<div class="dataRowDiv">
								<div class="leftDiv">Purpose:</div>
								<div class="rightDiv">${requestDetails.purpose}</div>
							</div>
							<div class="dataRowDiv zebraPattern">
								<div class="leftDiv">Requested Date</div>
								<div class="rightDiv">
								<fmt:formatDate value="${requestDetails.createdDate}" type="date" pattern="dd-MM-yyyy" />
								</div>
							</div>
						</div> 
						<div class="btnDiv">
						<c:choose>
							<c:when test="${hideBtns eq 'hideBtns'}">
							</c:when>
							<c:otherwise>
							<div class="approveBtnDiv">
							<button id="approveBtn">Approve Request</button>
							<!-- <input type="button" value="Approve Request" id="approveBtn"/> -->
							</div>
							<div class="rejectBtnDiv">
							<button id="rejectBtn">Reject Request</button>
							<!--<input type="button" value="Reject Request" id="rejectBtn"/>-->
							</div>
							</c:otherwise>
						</c:choose>						
						</div>
						<div class="subHead">Approvers Details</div>
						<div class="approverDetails">
							<table cellpadding="0" cellspacing="0" class="approverDetailsTable">		
								<tr>
									<td class="labelTD">Name</td>
									<td class="labelTD">Designation</td>
									<td class="labelTD">Department</td>
									<td class="labelTD" style="width:10%;">Status</td>
									<td class="labelTD" style="width:10%;">Action Date</td>
									<td class="labelTD" style="width:20%;">Comments</td>									
								</tr>								
								<c:forEach items="${requestDetails.reqVersionList}" var="reqVersion" varStatus="status">
								<tr>
									<td class="dataTD">${reqVersion.approverEmpDetails.employeeName}</td>
									<td class="dataTD">${reqVersion.approverEmpDetails.designationName}</td>
									<td class="dataTD">${reqVersion.approverEmpDetails.departmentName}</td>
									<td class="dataTD">${requestStatus.STATUSSTRING[reqVersion.statusId]}</td>
									<td class="dataTD">
									<fmt:formatDate value="${reqVersion.actionDate}" type="date" pattern="dd-MM-yyyy" />
									</td>
									<td class="dataTD"></td>
								</tr>								
								<c:if test="${(requestDetails.travelRequestMasterId eq reqVersion.travelRequestMasterId) and (reqVersion.travelApproverId eq loginUserDetails.employeeDetailsId)}">
									<input type="hidden" id="reqVersionId" value="${reqVersion.travelRequestVersionId}"/>
									<input type="hidden" id="reqMasterId" value="${reqVersion.travelRequestMasterId}"/>									
								</c:if>
								</c:forEach>							
							</table>
							<form id="requestProcess" action="UpdateTravelRequest" method="POST"></form>		
						</div>
					</div>
				</div>
			</div>				
		</div>
		
		<div class="footerPush"></div>
	</div>
	</div>
	<jsp:include page="footer.jsp" />
</div>
</body>
</html>
<script>
$("#approveBtn").click(function(){	
	var action = $("#requestProcess").attr("action") + "?action=approve&travelRequestVersionId="+$("#reqVersionId").val()+"&reqMasterId="+$("#reqMasterId").val();	
	$("#requestProcess").attr("action", action); 
	$("#requestProcess").submit();	
});
$("#rejectBtn").click(function(){
	var action = $("#requestProcess").attr("action") + "?action=reject&travelRequestVersionId="+$("#reqVersionId").val()+"&reqMasterId="+$("#reqMasterId").val();	
	$("#requestProcess").attr("action", action);
	$("#requestProcess").submit();
	
});
</script>
</body>
</html>