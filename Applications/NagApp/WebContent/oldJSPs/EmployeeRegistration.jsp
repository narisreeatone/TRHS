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
<title>Employee Registration</title>
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
		$("#dob" ).datepicker(
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
.empProfile{
	float:left;
	width:100%;
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
.zebraPattern1{
	background-color:grey;
	color:white;
}
.empProfile input{
	width:200px;
}
.submitBtnDiv{
	text-align:center;
	float:left;
	width:94%;
}
.reqFeildsMsgDiv{
	float:left;
	font-size:12px;
	width:52%;
}
.reqFeildsMsg{
	float:right;	
}
.ui-datepicker-trigger{
	position: absolute;
    right: 0;
    top:-1px;
    height:34px;
}
.rightDiv input {
    box-sizing: border-box;
    display: block;
    outline: medium none;
    padding: 2px;
    transition: all 0.3s ease 0s;
    width: 207px;
  }
</style>
</head>
<body>
<div id="mainDiv">
	<jsp:include page="header.jsp" />
	<div id="innerMainDiv">	
		<div id="contentDiv">
		
			<div id="empregistration" class="pageContent">			
					
				<div class="container">
					<jsp:include page="adminMenu.jsp" />
					
					<div class="contentSection">							
						<c:choose>
						<c:when test="{displayMessage != null}">
							<div class="displayMssg">${displayMessage}</div>
						</c:when>
						<c:otherwise>	
							<div class="subHead">Employee Registration Form</div>	
							<div class="empProfile">
							<form id="empRegistration" action="RegisterEmployee" method="POST" >
								<div class="errorMsgDiv"><span class="errorMsg">${errorMessage}</span></div>
								<div class="reqFeildsMsgDiv"><span class="reqFeildsMsg">*required fields</span></div>
								<div class="dataRowDiv zebraPattern">
									<div class="leftDiv">Employee Id*:</div>
									<div class="rightDiv"><input type="text" name="employeeId" id="employeeId" /></div>
								</div>
								<div class="dataRowDiv">
									<div class="leftDiv">Employee Name*:</div>
									<div class="rightDiv"><input type="text" name="employeeName" id="employeeName" /></div>
								</div>
								<div class="dataRowDiv">
									<div class="leftDiv">Date of Birth*:</div>
									<div class="rightDiv" style="position:relative;width:231px;"><input type="text" name="dob" id="dob" value="" disabled/></div>
								</div>					
								<div class="dataRowDiv">
									<div class="leftDiv">Designation*:</div>
									<div class="rightDiv">
										<select id="designationId" name="designationId" style="width: 206px;">
											<option value="select">Select</option>
										<c:forEach items="${designationMap}" var="designation" varStatus="status">													
											<option value="${designation.key}">${designation.value}</option>
										</c:forEach>
										</select>
									</div>
								</div>
								<div class="dataRowDiv zebraPattern">
									<div class="leftDiv">Department*:</div>
									<div class="rightDiv">
										<select id="departmentId" name="departmentId" style="width: 206px;">
											<option value="select">Select</option>
										<c:forEach items="${departmentMap}" var="department" varStatus="status">													
											<option value="${department.key}">${department.value}</option>
										</c:forEach>
										</select>
									</div>
								</div>
								<div class="dataRowDiv">
									<div class="leftDiv">Email*:</div>
									<div class="rightDiv"><input type="text" name="email" id="email" /></div>
								</div>
								<div class="dataRowDiv zebraPattern">
									<div class="leftDiv">Mobile*:</div>
									<div class="rightDiv"><input type="text" name="mobile" id="mobile" /></div>
								</div>
								<div class="dataRowDiv">
									<div class="leftDiv">Land line Number:</div>
									<div class="rightDiv"><input type="text" name="landline" id="landline" /></div>
								</div>
								<div class="dataRowDiv">
									<div class="leftDiv">Extension:</div>
									<div class="rightDiv"><input type="text" name="extension" id="extension" /></div>
								</div>
								<div class="dataRowDiv">									
									<div class="submitBtnDiv"><button id="submitBtn">register</button></div>
								</div>	
								</form>						
							</div>
						</c:otherwise>
						</c:choose>
					</div>
				</div>				
			</div>
			<div class="footerPush"></div>				
		</div>		
	</div>
	<jsp:include page="footer.jsp" />
</div>
<script type="text/javascript">
$("#submitBtn").click(function(){
	var errorMsg = "Please correct fields in red.";
	if(IsEmpty($("#employeeId")))
		error = true;
	if(IsEmpty($("#employeeName")))
		error = true;
	if(IsEmpty($("#dob")))
		error = true;
	else
		$("#travelDate").prop("disabled",false);	
	if(validListBox($("#designationId")))
		error = true;
	if(validListBox($("#departmentId")))
		error = true;	
	if(checkEmailFormat($("#email")))
		error = true;	
	if(checkMobileNumber($("#mobile")))
		error = true;	
	if(!IsEmptyNotRequired($("#landline"))){
		if(numberOnly($("#landline")))
			error = true;
	}
	if(!IsEmptyNotRequired($("#extension"))){
		if(numberOnly($("#extension")))
			error = true;
	}
	if(error)
		$(".errorMsg").html(errorMsg);
	else
		$(".errorMsg").html("");
	//return !error;
	return false;
});
</script>
</body>
</html>