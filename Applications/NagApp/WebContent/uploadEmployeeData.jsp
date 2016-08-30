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
<title>Upload Employee Data</title>
<link href="styles/styles.css" type="text/css" rel="stylesheet">
<style>
.welcomeMssg{
	color: blue;
    float: left;
    font-weight: bold;
    text-align: center;
    width: 100%;
}
.erroMsg{	
	padding-bottom:10px;
	
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
.empDetailsUploading{
	float:left;
	width:100%;
}
.label{
	float:left;
	padding-right:5px;
}
.fileUploadBtn{
	float:left;
	padding-right:5px;
}
.submitBtn{
	float:left;
}
</style>
</head>
<body>
<div id="mainDiv">
	<div class="header">
		<jsp:include page="header.jsp" />
	</div>
	<div id="innerMainDiv">	
		<div id="contentDiv">
		
			<div id="adminHome" class="pageContent">			
					
				<div class="container">
					<jsp:include page="adminMenu.jsp" />
					
					<div class="contentSection">
						<div class="welcomeMssg">Welcome ${loginUserDetails.employeeName}</div>						
							<div class="subHead">Upload Employee Data</div>	
							<div class="empDetailsUploading">
							<c:if test="${errorMessage != null}">
								<div class="error">${errorMessage}</div>
							</c:if>
							<form action="UploadEmployeeDetails" method="post" enctype="multipart/form-data">
								<div class="label">Select a file to upload:</div>						
								<div class="fileUploadBtn">	<input type="file" name="file" size="50"  value="choose xl file" style="margin:0;padding:0;"/></div>					
								<div class="submitBtn">	<input type="submit" value="Upload" /></div>	
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
</body>
</html>