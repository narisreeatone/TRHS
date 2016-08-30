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
	<div class="header">
		<jsp:include page="header.jsp" />
	</div>
	<div id="innerMainDiv">	
		<div id="contentDiv">			
			<div id="empHome" class="pageContent">
								
				<div class="container">
					<jsp:include page="employeeMenu.jsp" />
					
					<div class="contentSection">
						<div class="welcomeMssg">Welcome ${loginUserDetails.employeeName}</div>	
						<c:choose>
						<c:when test="${displayMessage != null}">
							<div class="displayMssg">${displayMessage}</div>
						</c:when>
						<c:otherwise>	
							<div class="subHead">Employee Profile</div>	
							<div class="empProfile">
								<div class="dataRowDiv">
									<div class="leftDiv">Name:</div>
									<div class="rightDiv">${loginUserDetails.employeeName}</div>
								</div>
								<div class="dataRowDiv zebraPattern">
									<div class="leftDiv">Employee Id:</div>
									<div class="rightDiv">${loginUserDetails.employeeId}</div>
								</div>
								<div class="dataRowDiv">
									<div class="leftDiv">Designation:</div>
									<div class="rightDiv">
									<c:forEach items="${designationMap}" var="designation" varStatus="status">													
										<c:if test="${designation.key == loginUserDetails.designationId}">
											${designation.value}
										</c:if>
									</c:forEach>
									</div>
								</div>
								<div class="dataRowDiv zebraPattern">
									<div class="leftDiv">Department:</div>
									<div class="rightDiv">
									<c:forEach items="${departmentMap}" var="department" varStatus="status">													
										<c:if test="${department.key == loginUserDetails.deptId}">
											${department.value}
										</c:if>
									</c:forEach>								
									</div>
								</div>
								<div class="dataRowDiv">
									<div class="leftDiv">Email:</div>
									<div class="rightDiv">${loginUserDetails.emailId}</div>
								</div>
								<div class="dataRowDiv zebraPattern">
									<div class="leftDiv">Mobile:</div>
									<div class="rightDiv">${loginUserDetails.mobileNumber}</div>
								</div>
								<div class="dataRowDiv">
									<div class="leftDiv">Land line Number:</div>
									<div class="rightDiv">${loginUserDetails.landLineNumber}</div>
								</div>							
							</div>
						</c:otherwise>
						</c:choose>
					</div>
				</div>					
			</div>			
		</div>
		<div class="footerPush"></div>
	</div>
	<jsp:include page="footer.jsp" />
</div>
</body>
</html>