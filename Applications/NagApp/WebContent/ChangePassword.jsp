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
</style>
</head>
<body>
<div id="mainDiv">
	<div id="innerMainDiv">
		
		<jsp:include page="header.jsp" />
		
		<div id="changeRandomPwd" class="pageContent">				
			<div class="container">				
				<div class="contentSection">			
					
						<div class="subHead">Change Password</div>	
						<div class="empProfile">
						<div class="displayMssg">${displayMessage}</div>
						<form id="changePwd" action="ChangePassword" method="POST" >
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
								<div class="submitBtnDiv"><input type="submit" id="sumbitBtn" value="change password" style="width:130px;"/></div>
							</div>	<input type="hidden" name="isRandomPwd" id="isRandomPwd" value="${empDetails.isRandomPwd}" />
							</form>											
						</div>					
				</div>
			</div>
			<div class="footerPush"></div>				
		</div>
		
		<jsp:include page="footer.jsp" />
	</div>
</div>
</body>
</html>