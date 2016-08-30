<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<link href="styles/styles.css" type="text/css" rel="stylesheet">
<style>
.addNewServiceDiv{
	float:left;
	width:100%;
	padding-top:100px;
}
.firstDiv{
	float: left;
    padding-right: 1%;
    padding-top: 3px;
    text-align: right;
    width: 30%;
}
.inputDiv{
	float:left;
	width:30%;
}
.submitBtnDiv{
	float:left;
	width:30%;
}
</style>
</head>
<%
	String seviceType = request.getParameter("seviceType");
	request.setAttribute("seviceType", seviceType);
%>
<body>
<div id="mainDiv">
	<jsp:include page="header.jsp" />
	<div id="innerMainDiv">	
		<div id="contentDiv">
		
			<div id="addNewService" class="pageContent">			
					
				<div class="container">
					<jsp:include page="adminMenu.jsp" />
					
					<div class="contentSection">						
						<c:choose>
						<c:when test="${displayMessage != null}">
							<div class="displayMssg">${displayMessage}</div>
						</c:when>
						<c:otherwise>	
							<div class="subHead">Add 
							<c:choose>
								<c:when test="${seviceType eq 'dept'}">
									Department:
								</c:when>
								<c:when test="${seviceType eq 'designation'}">
									Designation:
								</c:when>
								<c:when test="${seviceType eq 'travelMode'}">
									Travel Mode:
								</c:when>
								</c:choose>
							</div>	
							<form id="" action="AddNewServices" method="POST">
							<div class="addNewServiceDiv">
															
								<div class="firstDiv">
								<c:choose>
								<c:when test="${seviceType eq 'dept'}">
									Department Name:
								</c:when>
								<c:when test="${seviceType eq 'designation'}">
									Designation Name:
								</c:when>
								<c:when test="${seviceType eq 'travelMode'}">
									Travel Mode:
								</c:when>
								</c:choose>
								</div>
							
								<div class="inputDiv"><input type="text" name="newServiceName" id="newServiceName" style="width:200px;"/></div>
								<div class="submitBtnDiv"><input type="submit" id="sumbitBtn" value="Add" style="width:100px;"/></div>												
								<input type="hidden" name="seviceType" id="seviceType" value="${seviceType}"/>			
									
																		
							</div>
							</form>
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
</body>
</html>