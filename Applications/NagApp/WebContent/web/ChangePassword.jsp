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
<script type="text/javascript" src="javascript/jquery-3.1.0.min.js"></script>
<script type="text/javascript" src="javascript/common.js"></script>
<style>
.welcomeMssg{
	color: blue;
    float: left;
    font-weight: bold;
    text-align: center;
    width: 100%;
}
.errorMsg{
	float:left;
	width:100%;	
	padding-top:30px;
	color:red;
}
.empProfile{
	float:left;
	width:100%;
}
.dataRowDiv{
	float:left;
	min-width:100%;
	padding:5px 0;
	min-height:20px;
}
.dataRowDiv .leftDiv{
	float:left;
	width:41%;
	text-align:right;
	padding-right:2%;
	font-weight:bold;
}
.dataRowDiv .rightDiv{
	float:left;
	text-align:left;
	width:50%;
}
.zebraPattern1{
	background-color:grey;
	color:white;
}
.changePwd{
	width:50%;
	margin: 0 auto;
}
.changePwdHead{
	color:#f59000;
	font-weight:bold;
	font-size:14px;
}
#newPwd1, #newPwd2{
    border: 1px solid #d9d9d9;
    box-sizing: border-box;
    display: block;
    margin: 0 0 20px;
    outline: medium none;
    padding: 2px;
    transition: all 0.3s ease 0s;
    width: 100%;
}
.submitBtnDiv {
	width:100%;
	text-align:center;
}
#submitBtn {
    background: #33b5e5 none repeat scroll 0 0;
    border: 0 none;
    color: #ffffff;
    cursor: pointer;
    padding: 10px 15px;
    transition: all 0.3s ease 0s;
    width: 165px;
}
</style>
</head>
<body>
<div id="mainDiv">
	<div class="header">
		<div class="headerContentMainDiv">
			<div class="logo"><img src="images/logo.jpg" /></img></div>
		</div>
	</div>
	<div id="innerMainDiv">	
		<div id="contentDiv">
		
			<div id="changePassword" class="pageContent">
				
				<div class="container">			
					<div class="" style="margin:0 auto;">
							
						<div class="changePwd">
						<div class="changePwdHead">Change Password</div>
						<div class="errorMsg"></div>
						<form id="changePwdForm" action="web/ChangePassword" method="POST" >
							<!-- <div class="dataRowDiv">
								<div class="leftDiv">Please enter current password :</div>
								<div class="rightDiv"><input type="text" id="currentPwd" name="currentPwd" value="" /></div>
							</div> -->
							<div class="dataRowDiv zebraPattern">
								<div class="leftDiv">Please enter new password :</div>
								<div class="rightDiv"><input type="password" id="newPwd1" name="newPwd1" value="" /></div>
							</div>							
							<div class="dataRowDiv">
								<div class="leftDiv">Re-enter new password:</div>
								<div class="rightDiv"><input type="password" id="newPwd2" name="newPwd2" value="" /></div>
							</div>
							<div class="dataRowDiv">								
								<div class="submitBtnDiv">
								<button id="submitBtn">change password</button>
								</div>
							</div>	<input type="hidden" name="isRandomPwd" id="isRandomPwd" value="${empDetails.isRandomPwd}" />
							</form>											
						</div>	
					</div>				
				</div>
		
			<div class="footerPush"></div>
		</div>
	</div>
	<div class="footer">
		<div class="footerContentMainDiv">
			<div class=></div>
		</div>
	</div>	
</div>
<script>
$("#changePwdForm").submit(function(){		
	var errorMsg = "Please enter correct values.";
	$("#newPwd1").css("border","1px solid #d9d9d9");
	$("#newPwd1").css("border","1px solid #d9d9d9");
	var error = false;
	if( IsEmpty($("#newPwd1")) ){		
		error = true;
	}
	if( IsEmpty($("#newPwd2")) ){		
		error = true;
	}
	if( validatePassword($("#newPwd1")) ){		
		error = true;
	}
	if( validatePassword($("#newPwd2")) ){		
		error = true;
	}
	if(error){		
		$(".errorMsg").html(errorMsg);
		$(".errorMsg").css("visibility", "visibile");
		return false;		
	}
	if($("#newPwd1").val() != $("#newPwd2").val()){
		$(".errorMsg").html("Entered passwords are doesn't match. Please enter same password.");
		error = true;
		//return false;
	}
	
	return !error;		
});
</script>
</body>
</html>