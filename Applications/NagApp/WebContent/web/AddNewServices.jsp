<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.nag.dao.*" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<link href="styles/styles.css" type="text/css" rel="stylesheet">
<style>
.subHead{
	padding-bottom:90px;
}
.addNewServiceDiv{
	float:left;
	width:100%;	
	margin-bottom: 30px;
}
.firstDiv{
	float: left;
    padding-right: 1%;
    padding-top: 10px;
    text-align: right;
    width: 30%;
}
.inputDiv{
	float:left;
	width:30%;
	padding-top: 6px;
}
.submitBtnDiv{
	float:left;
	width:30%;
}
.inputDiv input{    
    box-sizing: border-box;
    display: block;
    outline: medium none;
    padding: 2px;
    transition: all 0.3s ease 0s;
    width: 207px;
}
.errorMsg{
	float:left;
	width:100%;
	visibility:hidden;
	color:red;
	padding-left: 104px;
}
.displayServicesInDB{float:left;width:86%;margin-left:14%;}
.dataDiv{float:left;width:100%;padding:5px 0;}
.leftDiv{float:left;width:10%;}
.rightDiv{float:left;width:90%;}
.headerDiv{
	font-weight:bold;
}
</style>
</head>
<%
	String seviceType = request.getParameter("seviceType");
	request.setAttribute("seviceType", seviceType);
	DataBaseConnection dbHandler = new DataBaseConnection();
	Map<String, String> idMap = new HashMap<String, String>();
	if("dept".equals(seviceType)){
		idMap = dbHandler.getDepartment();
	}
	if("designation".equals(seviceType)){
		idMap = dbHandler.getDesignation();	
	}
	if("travelMode".equals(seviceType)){
		idMap = dbHandler.getTravelModes();
	}
	
	request.setAttribute("idMap", idMap);
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
							<div class="subHead" style="padding-bottom:90px;">Add 
							<c:choose>
								<c:when test="${seviceType eq 'dept'}">
									Department
								</c:when>
								<c:when test="${seviceType eq 'designation'}">
									Designation
								</c:when>
								<c:when test="${seviceType eq 'travelMode'}">
									Travel Mode
								</c:when>
								</c:choose>
							</div>	
							<form id="AddNewService" action="AddNewServices" method="POST">
							<div class="errorMsg"></div>
							<div class="addNewServiceDiv">
															
								<div class="firstDiv">
								<c:choose>
								<c:when test="${seviceType eq 'dept'}"><c:set var="serviceName" value="Department Name"></c:set>
									Department Name:
								</c:when>
								<c:when test="${seviceType eq 'designation'}"><c:set var="serviceName" value="Designation Name"></c:set>
									Designation Name:
								</c:when>
								<c:when test="${seviceType eq 'travelMode'}"><c:set var="serviceName" value="Travel Mode"></c:set>
									Travel Mode:
								</c:when>
								</c:choose>
								</div>
							
								<div class="inputDiv"><input type="text" name="newServiceName" id="newServiceName" style="width:200px;"/></div>
								<div class="submitBtnDiv">
								<!-- <input type="submit" id="sumbitBtn" value="Add" style="width:100px;"/> -->
								<button id="submitBtn">Add</button>
								</div>												
								<input type="hidden" name="seviceType" id="seviceType" value="${seviceType}"/>																		
							</div>
							</form>
							<c:if test="${idMap ne null}">
							<div class="displayServicesInDB" style="">
								<div class="" style="float:left;width:100%;paddin:0 0 20px 0;">Below are already added to database.</div>
								<div class="dataDiv" style="">
									<div class="leftDiv headerDiv" style="">ID</div>
									<div class="rightDiv headerDiv" style="">${serviceName}</div>
									<c:if test="${idMap ne null}">
									<c:forEach items="${idMap}" var="idMapItmes">
									<div class="dataDiv">
										<div class="leftDiv">${idMapItmes.key}</div>
										<div class="rightDiv">${idMapItmes.value}</div>
									</div>
									</c:forEach>
									</c:if>
								</div>
								

							</div>
							</c:if>
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
<script>
$("#AddNewService").submit(function(){		
	var errorMsg = "Please enter correct value.";
	$("#newServiceName").css("border","1px solid #d9d9d9");	
	var error = false;
	if( IsEmpty($("#newServiceName")) ){		
		error = true;
	}
	
	if(error){		
		
		$(".errorMsg").css("visibility", "visible");
		$(".errorMsg").html(errorMsg);
		return false;		
	}	
	return !error;		
});
</script>
</body>
</html>