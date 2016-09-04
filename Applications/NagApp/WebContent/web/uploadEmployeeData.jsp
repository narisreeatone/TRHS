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
	padding-top:30px;
}
.label{
	float:left;
	padding-right:5px;
	padding-top:9px;
	
}
.fileUploadBtn{
	float:left;
	padding-right:5px;
}
.submitBtnDiv{
	float:left;
}


body {
	font-family: 'Lucida Grande', 'Helvetica Neue', sans-serif;
	font-size: 13px;
}

div.custom_file_upload {
	width: 230px;
	height: 20px;
	float:left;
}

input.file {
	width: 150px;
	height: 20px;
	border: 1px solid #BBB;
	border-right: 0;
	color: #888;
	padding: 5px;
	
	-webkit-border-top-left-radius: 5px;
	-webkit-border-bottom-left-radius: 5px;
	-moz-border-radius-topleft: 5px;
	-moz-border-radius-bottomleft: 5px;
	border-top-left-radius: 5px;
	border-bottom-left-radius: 5px;
	
	outline: none;
}

div.file_upload {
	width: 80px;
	height: 24px;
	background: #33b5e5;
	background: -moz-linear-gradient(top,  #33b5e5 0%, #33b5e5 44%, #33b5e5 100%);
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#33b5e5), color-stop(44%,#33b5e5), color-stop(100%,#33b5e5));
	background: -webkit-linear-gradient(top,  #33b5e5 0%,#33b5e5 44%,#33b5e5 100%);
	background: -o-linear-gradient(top,  #33b5e5 0%,#33b5e5 44%,#33b5e5 100%);
	background: -ms-linear-gradient(top,  #33b5e5 0%,#33b5e5 44%,#33b5e5 100%);
	background: linear-gradient(top,  #33b5e5 0%,#33b5e5 44%,#33b5e5 100%);
	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#33b5e5', endColorstr='#33b5e5',GradientType=0 );

	display: inline;
	position: absolute;
	overflow: hidden;
	cursor: pointer;
	
	-webkit-border-top-right-radius: 5px;
	-webkit-border-bottom-right-radius: 5px;
	-moz-border-radius-topright: 5px;
	-moz-border-radius-bottomright: 5px;
	border-top-right-radius: 5px;
	border-bottom-right-radius: 5px;
	
	font-weight: bold;
	color: #FFF;
	text-align: center;
	padding-top: 8px;
}
div.file_upload:before {
	content: 'select';
	position: absolute;
	left: 0; right: 0;
	text-align: center;	
	cursor: pointer;
	font-weight:bold;
	font-size:14px;
}

div.file_upload input {
	position: relative;
	height: 30px;
	width: 80px;
	display: inline;
	cursor: pointer;
	opacity: 0;
}
.sumitBtnDiv{
	float:left;
	padding-left: 25px;
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
						<div class="subHead">Upload Employee Data</div>	
						<div class="empDetailsUploading">							
							<div class="errorMessage"><c:if test="${errorMessage ne null || errorMessage ne ''}">${errorMessage}</c:if></div>							
							<form action="UploadEmployeeDetails" method="post" enctype="multipart/form-data">
								<div class="label">Select employee xl file to upload:</div>						
								<!-- <div class="fileUploadBtn">	<input type="file" name="file" size="50"  value="choose xl file" style="margin:0;padding:0;"/></div>					
								<div class="submitBtnDiv"><button id="submitBtn">upload</button></div>	 -->
								
								
								<div class="custom_file_upload">
									<input type="text" class="file" name="file_info" id="uploadFile">
									<div class="file_upload">
										<input type="file" id="file_upload" name="file_upload">
									</div>									
								</div>
								<div class="sumitBtnDiv"><button id="submitBtn">upload</button></div>
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
<script>
document.getElementById("file_upload").onchange = function () {
    document.getElementById("uploadFile").value = this.value;
};
</script>
</body>
</html>