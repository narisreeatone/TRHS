<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*" %>
<%@ page import="com.nag.bean.*" %>
<%@ page import="com.nag.dao.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	EmployeeDetails empDetails = (EmployeeDetails)request.getAttribute("empDetails");
	DataBaseConnection db = new DataBaseConnection();
	Map<String, Object> empDetailsMap = db.getEmployeesDetails();
	request.setAttribute("empDetailsMap", empDetailsMap);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Details</title>
<link href="styles/styles.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="javascript/jquery-3.1.0.min.js"></script>
<link type="text/css" href="styles/datepicker/jquery.ui.datepicker.css" rel="stylesheet" />
<script src="javascript/datepicker/jquery-ui-1.8b1.custom.min.js" type="text/javascript" charset="utf-8"></script>
<script type='text/javascript'>
$("document").ready(function(){
	$(function()
			{
				// Datepicker (if available)
			  if ($.fn.datepicker) {			 
				$('input#travelDate').datepicker({
					buttonImage :    'images/datepicker/btn_date_from.png',
					dateFormat :     'mm/dd/yy',
				 	showOn:          "both",
				  	showButtonPanel : false,
				 	buttonImageOnly: true,
				 	minDate:		1,
				 	maxDate:		30			
				});		
			}
			});
});
</script> 
<style>
.travelReqform{
	float:left;
	width:100%;	
}
.rowSection{
	float:left;
	width:100%;
	padding-bottom:5px;	
}
.leftSection{
	float:left;
	width:28%;
	text-align:right;
	padding-right:2%;
}
.rightSection{
	float:left;
	width:60%;
}
.rightSection input{
	width:200px;
}
#purpose{
	width:200px;
	height:50px;
}
.addApproversList{
	float:left;
	width:100%;
}
.addApproverLinkDiv{
	float:left;
	width:100%;
}
.approverList{
	float:left;
	width:100%;
}
.approverList .labels{
	padding: 5px;
    width: 15%;
}
.approverList.values{
	padding: 5px;
    width: 15%;
}
.deleteApproverTd{
	padding-left:5px;
}
.submitBtn{
	float:left;
	text-align:center;
	padding:10px 0;
	display:none;
	width:100%
}
.popupDiv{
	left: 0;
    min-width: 590px;
    position: absolute;
    text-align: left;
    top: 39px;
    display:none;
}
.popupBox{
	border:1px solid black;
} 
.popup{
	background: #fff none repeat scroll 0 0;
}

.popupList {
    list-style-type: none;
    margin: 0;
    padding: 0;
}
</style>

</head>
<body>
<div id="mainDiv">
	<div id="innerMainDiv">
		
		<div class="header">Menu</div>
		
		<div id="travelRequestForm" class="pageContent">			
				
				<div class="container">
					<div class="menuSection">						
						<ul class="menuItems">
							<li><a href="">Employee Profile</a></li>
							<li><span class="currentMenuItem">New Travel Request</span></li>
							<li><a href="">Travel Request Approved</a></li>
							<li><a href="GetPendingRequest">Travel Request Pending</a></li>
							<li><a href="">Travel Request Rejected</a></li>
							<li><a href="">Approve Travel Request</a></li>
							<li><a href="">Approved Travel Requests by you</a></li>
							<li><a href="LogOut">Log out</a></li>
						</ul>						
					</div>
					
					<div class="contentSection">
						<div class="travelReqform">
							<form action="SaveTravelRequestDetails" method="POST" id="reqForm">
							<div class="rowSection">
								<div class="leftSection">Source</div>
								<div class="rightSection"><input type="text" name="source" id="source" value="" /></div>
							</div>
							<div class="rowSection">
								<div class="leftSection">Destination</div>
								<div class="rightSection"><input type="text" name="destination" id="destination" value="" /></div>
							</div>							
							<div class="rowSection">
								<div class="leftSection">Travel Mode</div>
								<div class="rightSection"><input type="text" name="travelType" id="travelType" value="" /></div>
							</div>
							<div class="rowSection">
								<div class="leftSection">Expenses (approximate)</div>
								<div class="rightSection"><input type="text" name="expenses" id="expenses" value="" /></div>
							</div>
							<div class="rowSection">
								<div class="leftSection">Travel Date</div>
								<div class="rightSection"><input type="text" name="travelDate" id="travelDate" value="" /></div>
							</div>
							<div class="rowSection">
								<div class="leftSection">Purpose</div>
								<div class="rightSection"><textarea name="purpose" id="purpose"></textarea></div>
							</div>
							<div class="rowSection">
								<div class="leftSection">&nbsp;</div>
								<div class="rightSection"><a href="javascript:void(0)" class="addApproverLink">Add Approvers</a></div>
							</div>
						
						
							<div class="addApproversList">						
								<div class="approverList">
									<table class="approverRowTable" cellspacing="0" cellpadding="0">
										<tbody>
										<tr>
											<td class="labels">Approver Name</td> 
											<td class="labels">Mail Id</td>
											<td class="labels">Department</td>
											<td class="labels">Designation</td>	
											<td class="labels"></td>									
										</tr>
										<tr>
											<td class="values">
												<select class="empIdList" id=empIdList name="approveEmpId" onchange="javascript:populateEmpDetails.call(this);">
													<option value="Select">Select</option>
													<c:if test="${empDetailsMap != null}">
														<c:forEach items="${empDetailsMap}" var="empDetails" varStatus="status">													
															<option value="${empDetails.key}">${empDetails.value.employeeName}</option>
														</c:forEach>
													</c:if>
												</select>
												<!-- <input type="text" value="" id="approverName" name="approverName" class="approverName" onkeyup="javascript:getEmployeeName.call(this);">
												<div class="popupDiv">
													<div class="popupBox"> 
														<div class="popup" style="">
															<ul class="popupList">
																<li id="1"></li>
																<li id="2"></li>
																<li id="3"></li>
															</ul>
														</div>
													</div>
												</div> -->
											</td>
											<td class="values"><input type="text" value="" id="approverMailId" name="approverMailId" class="approverMailId"/></td>
											<td class="values"><input type="text" value="" id="approverDept" name="approverDept" class="approverDept"/></td>
											<td class="values"><input type="text" value="" id="approverDesignation" name="approverDesignation" class="approverDesignation"/></td>
											<td class="deleteApproverTd"><a onclick="javascript:deleteApproverRow.call(this);" class="deleteApprover">Delete</a></td>
										</tr>
										</tbody>
									</table> 
								</div>
							</div>
							<div class="submitBtn"><input type="submit" value="submit" /></div>
						</form>
					</div>
				</div>				
		</div>
		
		<div class="footer">Footer</div>
	</div>
</div>
<script>

$approverRowHtml = $(".approverRowTable").html();
$(".approverList").html("");
var approverCount = 1;
$(".addApproverLink").click(function(){	
	$(".submitBtn").show();
	if(approverCount <= 6){		
		$(".approverList").append("<div class='approverListDiv"+approverCount+"'></div>");		
		$(".approverListDiv"+approverCount).html($approverRowHtml);
		/*$(".approverListDiv"+approverCount+" .approverName").attr("id", "approverName"+approverCount);
		$(".approverListDiv"+approverCount+" .approverName").attr("name", "approverName"+approverCount);*/
	}
	approverCount = approverCount + 1;	
});
function deleteApproverRow(){	
	deleteApproverRef = this;
	$(deleteApproverRef).parent().parent().parent().parent().html("").remove();
	approverCount = approverCount - 1;
	if(approverCount == 0)
		$(".submitBtn").hide();
}
function getEmployeeName(){
	getEmpName = this;
	if($(getEmpName).val().length > 2){
		getEmployeeNamesByAjax($(getEmpName).val());
	}
}
function populateEmpDetails(){
	
	getEmpId = this;
	
	<c:if test="${empDetailsMap != null}">
	<c:forEach items="${empDetailsMap}" var="empDetails" varStatus="status">
		var empid = "${empDetails.key}";
		if ($(getEmpId).val() == empid){
			if($(getEmpId).parent().parent().hasClass(".approverMailId"));{
				$(getEmpId).parent().parent().children().find(".approverMailId").val("${empDetails.value.emailId}");
				$(getEmpId).parent().parent().children().find(".approverDept").val("${empDetails.value.departmentName}");
				$(getEmpId).parent().parent().children().find(".approverDesignation").val("${empDetails.value.designationName}");
				//$("#reqForm").append("<input type='hidden' name='approveEmpId' value='"+ empid +"' />")
			}
		}
		
	</c:forEach>
	</c:if>
	
}
function getEmployeeNamesByAjax(empName){
	$.ajax({
		type: "POST",	
		url: "AjaxServlet?action=getAccountDetails&empName" + empName,
		dataType: "json",
		success:function(data){
			
		}
	});
}
</script>


</body>
</html>