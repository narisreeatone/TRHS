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
	request.setAttribute("requestFrom", request.getParameter("requestFrom"));
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Travel Request Details</title>
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
}
.dataRowDiv{
	float:left;
	width:100%;
	padding:5px 0;
	min-height:20px;
}
.dataRowDiv .leftDiv{
	float:left;
	width:48%;
	text-align:right;
	padding-right:2%;
	font-weight:bold;
}
.dataRowDiv .rightDiv{
	float:left;
	text-align:left;
	width:49%;
}
.zebraPattern{
	background-color:#f9f9f9;	
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
	padding:5px 5px 5px 0;
	color:#530594;
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
	padding-right:10px;
}
.clarificationBtnDiv{
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
#clarifyBtn{
	background: #33b5e5 none repeat scroll 0 0;
    border: 0 none;
    color: #ffffff;
    cursor: pointer;
    padding: 7px 0;
    transition: all 0.3s ease 0s;
    width: 80px;
    font-weight:bold;
}
#submitCommentBtn{
	background: #33b5e5 none repeat scroll 0 0;
    border: 0 none;
    color: #ffffff;
    cursor: pointer;
    padding: 3px 0;
    transition: all 0.3s ease 0s;
    width: 50px;
    font-weight:bold;
}
.sectionDiv{
	float:left;
	width:155px;
	padding-right:10px;
}
#multipleReqTable{
	float:left;
	width:100%;
}
#multipleReqTable th{
	text-align:left;
}
#multipleReqTable td{
	padding:5px 5px 5px 3px;
}
.headTd{
	width:15%;
	font-weight:bold;
	color:#530594;
}
.clarificationTextDiv{
	float:left;
	width:100%;
	padding-top:20px;
	display:none;
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
					<div class="heading">Travel Request Details</div>					
					<div class="requestDetailsDiv">	
						<div class="requestDetails">
						<c:choose>
						<c:when test="${requestFrom ne 'owner'}">			
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
								<div class="leftDiv">Expenses (approximate):</div>
								<div class="rightDiv">${requestDetails.expenses}</div>
							</div>
							<div class="dataRowDiv">
								<div class="leftDiv">Purpose:</div>
								<div class="rightDiv">${requestDetails.purpose}</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="dataRowDiv">
								<div class="leftDiv">Purpose:</div>
								<div class="rightDiv">${requestDetails.purpose}</div>
							</div>
							<div class="dataRowDiv">
								<div class="leftDiv">Expenses (approximate):</div>
								<div class="rightDiv">${requestDetails.expenses}</div>
							</div>
						</c:otherwise>
						</c:choose>
						<c:choose>
						<c:when test="${requestDetails.isMultipleRequest eq 'true'}">
						<div class="contentSubHead">Tour details</div>	
						<table class="" id="multipleReqTable" border="0">
						<thead>
						<tr>
							<th class="headTd">Source</th>
							<th class="" style="width:3%;"></th>
							<th class="headTd">Destination</th>
							<th class="" style="width:3%;"></th>
							<th class="headTd" style="width:7%;">Travel Date</th>
							<th class="" style="width:3%;"></th>
							<th class="headTd" style="width:17%;">Travel Mode</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${requestDetails.multipleRequestMap}" var="multipleRequestItem" varStatus="status">
						<tr>
							<td class="dataTd">${multipleRequestItem.value.source}</td>
							<td class="dataTd">to</td>
							<td class="dataTd">${multipleRequestItem.value.destination}</td>
							<td class="dataTd">on</td>
							<td class="dataTd"><fmt:formatDate value="${multipleRequestItem.value.travelDate}" type="date" pattern="dd-MM-yyyy" /></td>
							<td class="dataTd">by</td>
							<td class="dataTd">
								<c:forEach items="${travelModesMap}" var="travelModes" varStatus="status">													
									<c:if test="${travelModes.key == multipleRequestItem.value.travelType}">
										${travelModes.value}
									</c:if>
								</c:forEach>
							</td>
							
						</tr>
						</c:forEach>
						</tbody>
						</table>
						</c:when>
						<c:otherwise>							
							<div class="contentSubHead">Tour details</div>	
							<table class="" id="multipleReqTable" border="0">
							<thead>
							<tr>
								<th class="headTd">Source</th>
								<th class="" style="width:3%;"></th>
								<th class="headTd">Destination</th>
								<th class="" style="width:3%;"></th>
								<th class="headTd" style="width:7%;">Travel Date</th>
								<th class="" style="width:3%;"></th>
								<th class="headTd" style="width:17%;">Travel Mode</th>
							</tr>
							</thead>
							<tbody>
								<tr>
									<td class="dataTd">${requestDetails.source}</td>
									<td class="dataTd">to</td>
									<td class="dataTd">${requestDetails.destination}</td>
									<td class="dataTd">on</td>
									<td class="dataTd"><fmt:formatDate value="${requestDetails.travelDate}" type="date" pattern="dd-MM-yyyy" /></td>
									<td class="dataTd">by</td>
									<td class="dataTd">
										<c:forEach items="${travelModesMap}" var="travelModes" varStatus="status">													
											<c:if test="${travelModes.key == requestDetails.travelModeId}">
												${travelModes.value}
											</c:if>
										</c:forEach>
									</td>
								</tr>
							</tbody>
							</table>
							
						</c:otherwise>
						</c:choose>
							
						</div> 
						<div class="btnDiv">
						<c:choose>
							<c:when test="${hideBtns eq 'hideBtns'}">
							</c:when>
							<c:otherwise>
							<div class="approveBtnDiv">
								<button id="approveBtn">approve request</button>							
							</div>
							<div class="rejectBtnDiv">
								<button id="rejectBtn">reject request</button>							
							</div>
							<div class="clarificationBtnDiv">
								<button id="clarifyBtn">clarify</button>
							</div>
							
							<div class="clarificationTextDiv">	
								<form id="saveComment" action="SaveTRComments" method="POST">
								<input type="hidden" name="recieverId" id="recieverId" value="${requestDetails.requestedEmpDetails.employeeDetailsId}" />
								<input type="hidden" name="recieverName" id="recieverName" value="${requestDetails.requestedEmpDetails.employeeName}" />
								<input type="hidden" name="senderId" id="senderId" value="${loginUserDetails.employeeDetailsId}" />
								<input type="hidden" name="reqVersionId1" id="reqVersionId1" value="" />								
								<input type="hidden" name="reqMasterId1" id="reqMasterId1" value="" />
														
								<div class="" style="float:left;width:31%;padding-top:18px;">Please enter your comments</div>
								<div class="" style="float:left;width:65%;">
									<textarea name="comment" id="comment" style="margin-right:10px;width:300px;height:50px;float:left;"></textarea>
									<button id="submitCommentBtn" style="float:left;margin-top:15px;">submit</button>
								</div>
								</form>	
							</div>
							</c:otherwise>
						</c:choose>						
						</div>
						<div class="contentSubHead">Approvers Details</div>
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
									<c:choose>
									<c:when test="${reqVersion.statusId eq 3}">-</c:when>
									<c:otherwise>
									<fmt:formatDate value="${reqVersion.actionDate}" type="date" pattern="dd-MM-yyyy" />
									</c:otherwise>
									</c:choose>
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
$("#clarifyBtn").click(function(){
	$(".clarificationTextDiv").show();	
});
$("#submitCommentBtn").click(function(){
	$("#reqVersionId1").val($("#reqVersionId").val());
	$("#reqMasterId1").val($("#reqMasterId").val());
	if(IsEmpty($("#comment")))
		return false;
	else
		return true;
});
</script>
</body>
</html>