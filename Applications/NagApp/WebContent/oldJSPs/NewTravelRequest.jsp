<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*" %>
<%@ page import="com.nag.bean.*" %>
<%@ page import="com.nag.dao.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	EmployeeDetails loginUserDetails = (EmployeeDetails)session.getAttribute("loginUserDetails");
	DataBaseConnection db = new DataBaseConnection();
	Map<String, Object> empDetailsMap = db.getAllEmployeesDetails();	
	for (Map.Entry<String, Object> entry : empDetailsMap.entrySet()) {		
		String empid = entry.getKey();		
		if( empid.equalsIgnoreCase(loginUserDetails.getEmployeeId())){
			empDetailsMap.remove(empid);
			break;
		}
	}
	request.setAttribute("empDetailsMap", empDetailsMap);	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Details</title>
<link href="styles/styles.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="javascript/jquery-3.1.0.min.js"></script>
<script type="text/javascript" src="javascript/common.js"></script>
<!-- Date picker -->
<script src="javascript/datepicker/jquery-1.12.4.js"></script>
<script src="javascript/datepicker/jquery-ui.js"></script>
<link rel="stylesheet" href="styles/datepicker/jquery-ui.css">
<!-- date picker end -->

<script type='text/javascript'>
$("document").ready(function(){
	$( function() {
		$("#travelDate" ).datepicker(
				{
					buttonImage :    'images/datepicker/btn_date_from.png',
					dateFormat :     'dd/mm/yy',
				 	showOn:          "both",
				  	showButtonPanel : true,
				 	buttonImageOnly: true,
				 	minDate: new Date(),
				 	maxDate:90,
				 	prevText:'Prev',
				 	nextMonth:'Next'
				}
		);
	} );	
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
	padding-bottom:10px;	
}
.leftSection{
	float:left;
	width:28%;
	text-align:right;
	padding-right:2%;
	padding-top: 4px;
}
.rightSection{
	float:left;
	width:60%;
}
.rightSection input{    
    box-sizing: border-box;
    display: block;
    outline: medium none;
    padding: 2px;
    transition: all 0.3s ease 0s;
    width: 207px;
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
.submitBtnDiv{
	float:left;
	text-align:center;
	padding: 20px 0 10px;
	display:none;
	width:85%;
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
.ui-datepicker-trigger{
	position: absolute;
    right: 0;
    top:-1px;
    height:34px;
}
.sameEmpErrorMsg{
	visibility:hidden;
	color:red;
	float:left;
	width:100%;
}
</style>

</head>
<body>
<div id="mainDiv">
	<jsp:include page="header.jsp" />
	<div id="innerMainDiv">	
		<div id="contentDiv">
		
		<div id="travelRequestForm" class="pageContent">			
				
				<div class="container">
					<jsp:include page="employeeMenu.jsp" />
					
					<div class="contentSection">
						<div class="heading">Travel Request Form</div>
						<div class="travelReqform">
							<form action="SaveTravelRequestDetails" method="POST" id="travelRequestForm">
							<div class="rowSection">
								<div class="leftSection">Source*</div>
								<div class="rightSection"><input type="text" name="source" id="source" value="" /></div>
							</div>
							<div class="rowSection">
								<div class="leftSection">Destination*</div>
								<div class="rightSection"><input type="text" name="destination" id="destination" value="" /></div>
							</div>							
							<div class="rowSection">
								<div class="leftSection">Travel Mode*</div>
								<div class="rightSection">
									<select id="travelMode" name="travelMode" style="width: 206px;">
										<option value="select">Select</option>
									<c:forEach items="${travelModesMap}" var="travelModes" varStatus="status">													
										<option value="${travelModes.key}">${travelModes.value}</option>
									</c:forEach>
									</select>
								</div>
							</div>
							<div class="rowSection">
								<div class="leftSection">Expenses (approximate)*</div>
								<div class="rightSection"><input type="text" name="expenses" id="expenses" value="" /></div>
							</div>
							<div class="rowSection">
								<div class="leftSection">Travel Date*</div>
								<div class="rightSection" style="position:relative;width:231px;"><input type="text" name="travelDate" id="travelDate" value="" disabled/></div>
							</div>
							<div class="rowSection">
								<div class="leftSection">Purpose*</div>
								<div class="rightSection"><textarea name="purpose" id="purpose"></textarea></div>
							</div>
							<div class="rowSection">
								<div class="leftSection">&nbsp;</div>
								<div class="rightSection"><a href="javascript:void(0)" class="addApproverLink">Add Approvers</a></div>
							</div>
						
							<div class="sameEmpErrorMsg">Please select different employees.</div>
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
											<td class="deleteApproverTd"><a onclick="javascript:deleteApproverRow.call(this);" class="deleteApprover">Delete</a>
												<input type="hidden" name="approveOrder" id="approveOrder" value="" />
											</td>
										</tr>
										</tbody>
									</table> 
								</div>
							</div>
							<div class="submitBtnDiv"><button id="submitBtn">submit</button></div>
						</form>
					</div>
				</div>				
			</div>
		
			
		</div>
		<div class="footerPush"></div>
	</div>
	</div>
	<jsp:include page="footer.jsp" />	
</div>

<c:set var="mapcount" value="1"></c:set>
<c:if test="${empDetailsMap != null}">
<c:forEach items="${empDetailsMap}" var="empDetails" varStatus="status">
	
<script>		
	var empMapKey = new Object();
	empMapKey['${empDetails.key}']="${empDetails.key}";	
	//alert(map['${empDetails.key}']);	
</script>		
</c:forEach>
</c:if>

<script>
$approverRowHtml = $(".approverList").html();
$(".approverList").html("");
var approverCount = 1;
$(".addApproverLink").click(function(){	
	$(".submitBtnDiv").show();	
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
	$(deleteApproverRef).parent().parent().parent().parent().parent().remove();
	approverCount = approverCount - 1;	
	if(approverCount == 1)
		$(".submitBtnDiv").hide();	
}
function populateEmpDetails(){	
	getEmpId = this;	
	$(this).addClass(""+$(getEmpId).val());
	<c:if test="${empDetailsMap != null}">
	<c:forEach items="${empDetailsMap}" var="empDetails" varStatus="status">		
	
		var empid = "${empDetails.key}";
		if ($(getEmpId).val() == empid){
			if($(getEmpId).parent().parent().hasClass(".approverMailId"));{
				$(getEmpId).parent().parent().children().find(".approverMailId").val("${empDetails.value.emailId}");
				$(getEmpId).parent().parent().children().find(".approverDept").val("${empDetails.value.departmentName}");
				$(getEmpId).parent().parent().children().find(".approverDesignation").val("${empDetails.value.designationName}");
				$("#travelRequestForm").append("<input type='hidden' name='approveOrder' id='approveOrder' value='' />")
			}
		}
		if($(getEmpId).val() == "Select"){
			$(getEmpId).parent().parent().children().find(".approverMailId").val("");
			$(getEmpId).parent().parent().children().find(".approverDept").val("");
			$(getEmpId).parent().parent().children().find(".approverDesignation").val("");
		}
		
	</c:forEach>
	</c:if>	
}

$("#submitBtn").click(function(){
	selectedApprovercount = 1;
	$(".sameEmpErrorMsg").css("visibility","hidden");
	
	//validations	
	error = false;
	if(alphabetsOnly($("#source")))
		error = true;
	if(alphabetsOnly($("#destination")))
		error = true;	 
	if(validListBox($("#travelMode")))
		error = true;
	if(currencyOnly($("#expenses")))
		error = true;
	if(IsEmpty($("#travelDate")))
		error = true;
	else
		$("#travelDate").prop("disabled",false);
	if(IsEmpty($("#purpose")))
		error = true;
	$(".empIdList").each(function(){
		$(this).css("border","1px solid #7A7A7A"); 
		if(validListBox($(this))){
			error = true;			
		}
	});
	$(".empIdList").each(function(){
		currentObj = $(this);
		currentListValue = $(this).val();
		$(".empIdList").each(function(){
			if($(this).is(currentObj)){				
				return false;
			}else{ 
				if(currentListValue == $(this).val()){
					//alert("else block:::currentListValue:::"+currentListValue+" this obj value:::"+$(this).val());
					error = true;
					$(this).css("border","1px dotted red"); 
					$(".sameEmpErrorMsg").css("visibility","visible");
				}
			}
		});
	});
	//alert(error);
	$(".approverRowTable").each(function(){
		empId = $(this).children().find(".values .empIdList").val();
		$(this).children().find(".deleteApproverTd #approveOrder").val(empId + "-" + selectedApprovercount);				
		selectedApprovercount++;
	});	
	return !error;	
});




function getEmployeeName(){
	getEmpName = this;
	if($(getEmpName).val().length > 2){
		getEmployeeNamesByAjax($(getEmpName).val());
	}
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