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
	padding-left:235px;
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
    width: 140px;
    font-weight:bold;
    
}
#rejectBtn{
	background: #33b5e5 none repeat scroll 0 0;
    border: 0 none;
    color: #ffffff;
    cursor: pointer;
    padding: 7px 0;
    transition: all 0.3s ease 0s;
    width: 125px;
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
.replyBtn{
	background: #33b5e5 none repeat scroll 0 0;
    border: 0 none;
    color: #ffffff;
    cursor: pointer;
    padding: 1px 3px;
    transition: all 0.3s ease 0s;    
}
#submitCommentBtn{
	background: #33b5e5 none repeat scroll 0 0;
    border: 0 none;
    color: #ffffff;
    cursor: pointer;
    padding: 1px 0 2px 0;
    transition: all 0.3s ease 0s;
    width: 60px;
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
								</tr>								
								<c:if test="${(requestDetails.travelRequestMasterId eq reqVersion.travelRequestMasterId) and (reqVersion.travelApproverId eq loginUserDetails.employeeDetailsId)}">
									<input type="hidden" id="reqVersionId" value="${reqVersion.travelRequestVersionId}"/>
									<input type="hidden" id="reqMasterId" value="${reqVersion.travelRequestMasterId}"/>	
									<c:set var="reqVersionId" value="${reqVersion.travelRequestVersionId}"/>
									<c:set var="reqMasterId" value="${reqVersion.travelRequestMasterId}"/>
									<c:set var="requestApproverId" value="${reqVersion.travelApproverId}" />
								</c:if>
								<div class="replyToApproverEmail${reqVersion.travelRequestVersionId}">
									<input type="hidden" id="recieverMailId${reqVersion.travelRequestVersionId}" value="${reqVersion.approverEmpDetails.emailId}"/>
								</div>
								</c:forEach>							
							</table>
							<form id="requestProcess" action="UpdateTravelRequest" method="POST"></form>													
						</div>
						<div class="commentsMainDiv">
							<div class="contentSubHead">Comments</div>
							<c:choose>
							<c:when test="${not empty requestDetails.reqCommentList && requestDetails.reqCommentList ne null}">
							<c:set var="commentListSize" value="${requestDetails.reqCommentList.size()}"/>
								<div class="" style="float:left;width:100%;padding-bottom:10px;padding-top:5px;">
									<div class="" style="float:left;width:20%;padding-right:2%;font-weight:bold;color:#530594;">Comments By</div>
									<div class="" style="float:left;width:78%;font-weight:bold;color:#530594;">Details</div>
								</div>
								<c:forEach items="${requestDetails.reqCommentList}" var="reqComment" varStatus="status">
								<div class="commentDiv replyToApprover${reqComment.requestVersionId}" style="float:left;width:100%;padding-bottom:15px;">
									<div class="senderName" style="float:left;width:20%;padding-right:2%;">${reqComment.senderName}</div>
									<div class="commentDiv" style="float:left;width:78%;">
										<div class="comment" style="float:left;width:100%;">${reqComment.comment}</div>
										<div class="date" style="float:left;width:100%;padding-top:5px;">
											<span>date: 
											<fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${reqComment.createdDate}" />
											</span>											
											<c:if test="${commentListSize-1 eq status.index}">
											&nbsp;&nbsp;<span id="${reqComment.requestVersionId}" class="replyBtn">reply</span>
											</c:if>
										</div>
										<input type="hidden" id="reqVersionId0" value="${reqComment.requestVersionId}"/>
										<input type="hidden" id="reqMasterId0" value="${reqComment.requestMasterId}"/>
										<input type="hidden" id="recieverId0" value="${reqComment.senderId}"/>
										<input type="hidden" id="recieverName0" value="${reqComment.senderName}"/>
									</div>
								</div>						
								</c:forEach>
							</c:when>
							<c:otherwise>							
								<div class="" style="float:left;width:100%;">
									No Comments.&nbsp;&nbsp;
									<c:if test="${requestApproverId eq loginUserDetails.employeeDetailsId}"><span style="pointer:cursor;" class="replyBtn">Write a comment</a></c:if>
								</div>
							</c:otherwise>							
							</c:choose>
							<div class="clarificationTextDiv">																						
								<div class="" style="float:left;width:26%;padding-top:18px;">Please enter your comments</div>
								<div class="" style="float:left;width:70%;">
									<textarea name="commentTextArea" id="commentTextArea" style="margin-right:10px;width:300px;height:50px;float:left;"></textarea>
									<button id="submitCommentBtn" style="float:left;margin-top:15px;">submit</button>
								</div>
								
							</div>
							<form id="saveComment" action="SaveTRComments" >
								<c:choose>
								<c:when test="${requestDetails.requestedEmpDetails.employeeDetailsId eq loginUserDetails.employeeDetailsId}">
									<input type="hidden" name="commentsBy" id="commentsBy" value="requestOwner" />
									<input type="hidden" name="senderName" id="senderName" value="${loginUserDetails.employeeName}" />
									<input type="hidden" name="senderId" id="senderId" value="${loginUserDetails.employeeDetailsId}" />
									
									<input type="hidden" name="reqVersionId1" id="reqVersionId1" value="" />								
									<input type="hidden" name="reqMasterId1" id="reqMasterId1" value="" />									
									<input type="hidden" name="recieverId" id="recieverId" value="" />																
									<input type="hidden" name="recieverName1" id="recieverName1" value="" />
									<input type="hidden" name="recieverMailId1" id="recieverMailId1" value="" />
									<input type="hidden" name="comment" id="comment" value="" />
								</c:when>							
								<c:otherwise>
									<input type="hidden" name="commentsBy" id="commentsBy" value="requestApprover" />
									
									<input type="hidden" name="reqVersionId1" id="reqVersionId1" value="${reqVersionId}" />								
									<input type="hidden" name="reqMasterId1" id="reqMasterId1" value="${reqMasterId}" />								
									<input type="hidden" name="senderName" id="senderName" value="${loginUserDetails.employeeName}" />
									
									<input type="hidden" name="recieverId" id="recieverId" value="${requestDetails.requestedEmpDetails.employeeDetailsId}" />
									<input type="hidden" name="senderId" id="senderId" value="${loginUserDetails.employeeDetailsId}" />
																								
									<input type="hidden" name="recieverMailId1" id="recieverMailId1" value="${requestDetails.requestedEmpDetails.emailId}" />								
									<input type="hidden" name="recieverName1" id="recieverName1" value="${requestDetails.requestedEmpDetails.employeeName}" />
									<input type="hidden" name="comment" id="comment" value="" />
								</c:otherwise>
								</c:choose>
							</form>	
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
$(".replyBtn").click(function(){
	$(".clarificationTextDiv").show();
	<c:if test="${requestDetails.requestedEmpDetails.employeeDetailsId eq loginUserDetails.employeeDetailsId}">
		reqVersionId = $(this).attr("id");		
		$("#recieverMailId1").val($("#recieverMailId"+reqVersionId).val());
		$("#reqVersionId1").val($(".replyToApprover"+reqVersionId).children().find("#reqVersionId0").val());
		$("#reqMasterId1").val($(".replyToApprover"+reqVersionId).children().find("#reqMasterId0").val());
		$("#recieverId").val($(".replyToApprover"+reqVersionId).children().find("#recieverId0").val());
		$("#recieverName1").val($(".replyToApprover"+reqVersionId).children().find("#recieverName0").val());	
	</c:if>
});
$("#submitCommentBtn").click(function(){
<c:if test="${requestDetails.requestedEmpDetails.employeeDetailsId ne loginUserDetails.employeeDetailsId}">
	$("#reqVersionId1").val($("#reqVersionId").val());
	$("#reqMasterId1").val($("#reqMasterId").val());
</c:if>
	
	<!--<c:if test="${requestDetails.requestedEmpDetails.employeeDetailsId eq loginUserDetails.employeeDetailsId}">
		//$("#recieverMailId1").val($("#recieverMailId").val());
		//$("#recieverName1").val($("#recieverName").val());
	</c:if>-->
	//return false;
	if(IsEmpty($("#commentTextArea")))
		return false;
	else{
		$("#comment").val($("#commentTextArea").val());
		$("#saveComment").submit();
	}
});
</script>
</body>
</html>