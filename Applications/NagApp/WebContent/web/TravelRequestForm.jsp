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
<title>New travel request</title>



<style>
.travelReqform{
	float:left;
	width:100%;	
}
.rowSection{
	float:left;
	width:100%;
	padding-bottom:10px;
	padding-left:30px;
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
	padding-left:30px;
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
	width:100%;
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
	height: 26px;
    position: absolute;
    right: 28px;
    top: -3px;
}
.sameEmpErrorMsg{
	visibility:hidden;
	color:red;
	float:left;
	width:100%;
	padding-left:30px;
}
.sectionDiv{
	float:left;
	width:155px;
	padding-right:10px;
}
.addBtn{
	cursor:pointer;
}
.deleteBtn{
	cursor:pointer;
	display:none;
}
.addApproverBtn{
	cursor:pointer;
}
.deleteApproverBtn{
	cursor:pointer;
	display:none;
}
#approverRowTable{
	padding-bottom:10px;
}
.errorMessage{
	float:left;
	width:100%;
	color:red;
	padding-left:30px;	
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
							<div class="errorMessage">Please correct fields in red.</div>
							<div id="TRInputsRow">
								<div class="rowSection travelRequest" id="travelRequest1">
									<div class="sectionDiv">
										<div class="">Source*</div>
										<div class=""><input type="text" name="source1" id="source" class="source" value="" /></div>
									</div>
									<div class="sectionDiv" style="">
										<div class="">Destination*</div>
										<div class=""><input type="text" name="destination1" id="destination" class="destination" value="" /></div>
									</div>
									<div class="sectionDiv" style="width:170px;">
										<div class="">Travel Date*</div>
										<div class="" style="position:relative;width:174px;"><input type="text" name="travelDate" id="travelDateId1" class="travelDateClass" value="" disabled/></div>
									</div>
									<div class="sectionDiv" style="">
										<div class="">Travel Mode*</div>
										<div class="">
											<select id="travelMode" class="travelMode" name="travelMode1" style="width:150px;">
												<option value="select">Select</option>
											<c:forEach items="${travelModesMap}" var="travelModes" varStatus="status">													
												<option value="${travelModes.key}">${travelModes.value}</option>
											</c:forEach>
											</select>
										</div>								
									</div>
									
									<div class="deleteBtnDiv" style="float:left;width:51px;">
										<div class="" style="width:170px;height:14px;"></div>
										<div class="TRbuttonsDiv">
											<img class="addBtn" src="../images/addTR.png" style="width:20px;heigth:20px;padding-right:7px;" title="add multiple locations" alt="add" />
											<img onclick="javascript:deleteTravelReqRow.call(this);" class="deleteBtn" src="../images/delete.png" style="width:20px;heigth:20px" title="delete location" alt="delete" />
										</div>
									</div>
								</div>
							</div>
							<div class="rowSection" style="padding-top:10px;">
								<div class="rowSection" style="">								
									<div class="leftSection">Expenses (approximate)*</div>
									<div class="rightSection"><input type="text" name="expenses" id="expenses" value="" /></div>							
								</div>				
								
								<div class="rowSection">
									<div class="leftSection">Purpose*</div>
									<div class="rightSection"><textarea name="purpose" id="purpose"></textarea></div>
								</div>
							</div>
							<!--<div class="rowSection">
								<div class="leftSection">&nbsp;</div>
								<div class="rightSection" style="padding-left:80px;width:58%;"><a href="javascript:void(0)" class="addApproverLink">Add Approvers</a></div>
							</div>-->
						
							<div class="sameEmpErrorMsg">Please select different employees.</div>
							<div class="addApproversList">						
								<div class="approverList">
									<table id="approverRowTable"  class="approverRowTable0 approverRowTable" cellspacing="0" cellpadding="0">
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
												<select class="empIdList" id=empIdList name="approveEmpId" onchange="javascript:populateEmpDetails.call(this);" style="width:150px;">
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
											<td class="values"><input type="text" value="" id="approverMailId" name="approverMailId" class="approverMailId" disabled/></td>
											<td class="values"><input type="text" value="" id="approverDept" name="approverDept" class="approverDept" disabled/></td>
											<td class="values"><input type="text" value="" id="approverDesignation" name="approverDesignation" class="approverDesignation" disabled/></td>
											<!--<td class="deleteApproverTd"><a onclick="javascript:deleteApproverRow.call(this);" class="deleteApprover">Delete</a>-->
											<td class="deleteApproverTd">
												<div class="deleteBtnDiv" style="float:left;width:51px;">
													<div class="" style="width:40px;"></div>
														<div class="TRbuttonsDiv">
															<img onclick="javascript:addApproverRow()" class="addApproverBtn" src="../images/addTR.png" style="width:20px;heigth:20px;padding-right:7px;" title="add approver" alt="add" />
															<img onclick="javascript:deleteApproverRow.call(this)" class="deleteApproverBtn" src="../images/delete.png" style="width:20px;heigth:20px" title="delete approver" alt="delete" />
														</div>
													</div>
													<input type="hidden" name="approveOrder" id="approveOrder" value="" />
												</div>
											</td>
										</tr>
										</tbody>
									</table> 
								</div>
							</div>
							<input type="hidden" name="travelRequestCount" id="travelRequestCount" value="0" />
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

<script type='text/javascript'>
var TRInputsRowHtml;
$("document").ready(function(){
	$( function() {		
		$("#travelDateId1" ).datepicker(
			{
				buttonImage :    '../images/datepicker/btn_date_from.png',
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
	TRInputsRowHtml = $("#TRInputsRow").html();
});
</script> 


<script>
var travelRequest = 1;
$(".addBtn").click(function(){
	travelRequest = travelRequest + 1;	
	modifiedTRInputsRowHtml = TRInputsRowHtml.replace("travelRequest1","travelRequest"+travelRequest);	
	modifiedTRInputsRowHtml = modifiedTRInputsRowHtml.replace("travelDateId1","travelDateId"+travelRequest);	
	var appendedTRInputs = $(this).parent().parent().parent().parent().after().append(modifiedTRInputsRowHtml);		
	$("#travelDateId"+travelRequest ).datepicker(
		{
			buttonImage :    '../images/datepicker/btn_date_from.png',
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
	$("#travelRequest"+travelRequest+" .addBtn").hide();
	$("#travelRequest"+travelRequest+" .deleteBtn").show();
	
});

function deleteTravelReqRow(){	
	deleteTravelReqRowRef = this;
	$(deleteTravelReqRowRef).parent().parent().parent().remove();
	//travelRequest = travelRequest - 1;	
	
}

$approverRowHtml = $(".approverList").html();
var approverCount = 0;
function addApproverRow(){	
	if(approverCount < 6){		
		approverCount = approverCount + 1;
		modifiedApproverRowHtml = $approverRowHtml.replace("approverRowTable0","approverRowTable"+approverCount);
		$(".approverList").after().append(modifiedApproverRowHtml);
		$(".approverRowTable"+approverCount+" .addApproverBtn").hide();
		$(".approverRowTable"+approverCount+" .deleteApproverBtn").show();		
	}		
}
function deleteApproverRow(){	
	deleteApproverRef = this;
	$(deleteApproverRef).parent().parent().parent().parent().parent().remove();
	approverCount = approverCount - 1;	
	/*if(approverCount == 1)
		$(".submitBtnDiv").hide();*/	
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
				//$("#travelRequestForm").append("<input type='hidden' name='approveOrder' id='approveOrder' value='' />")
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
	approveOrder = 1;
	$(".sameEmpErrorMsg").css("visibility","hidden");	
	//validations		
	var error = false;	
	$(".source").each(function(){
		if(alphabetsOnly($(this)))
		error = true;
	});
	$(".destination").each(function(){
		if(alphabetsOnly($(this)))
		error = true;
	});
	$(".travelMode").each(function(){
		if(validListBox($(this)))
		error = true;
	});	
	$(".travelDateClass").each(function(){
		if(IsEmpty($(this))){
			error = true;		
		}else{		
			$(this).prop("disabled",false);		
		}
	});
	if(currencyOnly($("#expenses"))){
		error = true;
		
	}	
	if(IsEmpty($("#purpose")))
		error = true;	
		
	if(error)
		$(".errorMessage").css("visibility","visible");
		
	var travelReqCount= 1;
	$(".travelRequest").each(function(){	
		$(this).children().find("#source").attr("name","source"+travelReqCount);
		$(this).children().find("#destination").attr("name","destination"+travelReqCount);
		$(this).children().find(".travelDateClass").attr("name","travelDate"+travelReqCount);
		//$(this).children().find(".travelDateClass").prop("disabled",false);	
		$(this).children().find("#travelMode").attr("name","travelMode"+travelReqCount);
		travelReqCount++;
	});
	$("#travelRequestCount").val(travelReqCount-1);
	$(".empIdList").each(function(){
		$(this).css("border","1px solid #7A7A7A");
		//$(this).css("border","1px dotted red"); 
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
				}else{
					//$(this).css("border","1px solid #7A7A7A");
				}
			}
		});
	});	
	$(".approverRowTable").each(function(){
		empId = $(this).children().find(".values .empIdList").val();
		//alert("empId::"+empId);
		$(this).children().find(".deleteApproverTd #approveOrder").val(empId + "-" + approveOrder);				
		approveOrder++;
	});
	//return false;
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