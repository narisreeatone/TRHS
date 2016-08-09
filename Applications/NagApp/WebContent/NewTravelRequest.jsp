<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*" %>
<%@ page import="com.nag.bean.*" %>
<%
	EmployeeDetails empDetails = (EmployeeDetails)request.getAttribute("empDetails");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Details</title>
<link href="styles/styles.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="javascript/jquery-3.1.0.min.js"></script>
<style>
.travelReqform{
	float:left;
	width:100%;	
}
.rowSection{
	float:left;
	width:100%;
	padding-bottom:5px;	
}
.leftSection{
	float:left;
	width:28%;
	text-align:right;
	padding-right:2%;
}
.rightSection{
	float:left;
	width:60%;
}
.rightSection input{
	width:200px;
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
.submitBtn{
	float:left;
	text-align:center;
	padding:10px 0;
	display:none;
	width:100%
}
</style>

</head>
<body>
<div id="mainDiv">
	<div id="innerMainDiv">
		
		<div class="header">Menu</div>
		
		<div id="travelRequestForm" class="pageContent">			
				
				<div class="container">
					<div class="menuSection">						
						<ul class="menuItems">
							<li><a href="">Employee Profile</a></li>
							<li><span class="currentMenuItem">New Travel Request</span></li>
							<li><a href="">Travel Request Approved</a></li>
							<li><a href="">Travel Request Pending</a></li>
							<li><a href="">Travel Request Rejected</a></li>
							<li><a href="">Approve Travel Request</a></li>
						</ul>						
					</div>
					
					<div class="contentSection">
						<div class="travelReqform">
							<form action="" method="POST" id="reqForm">
							<div class="rowSection">
								<div class="leftSection">Destination</div>
								<div class="rightSection"><input type="text" name="destination" id="destination" value="" /></div>
							</div>
							<div class="rowSection">
								<div class="leftSection">Source</div>
								<div class="rightSection"><input type="text" name="source" id="source" value="" /></div>
							</div>
							<div class="rowSection">
								<div class="leftSection">Travel Mode</div>
								<div class="rightSection"><input type="text" name="travelType" id="travelType" value="" /></div>
							</div>
							<div class="rowSection">
								<div class="leftSection">Travel Date</div>
								<div class="rightSection"><input type="text" name="travelDate" id="travelDate" value="" /></div>
							</div>
							<div class="rowSection">
								<div class="leftSection">Purpose</div>
								<div class="rightSection"><textarea name="purpose" id="purpose"></textarea></div>
							</div>
							<div class="rowSection">
								<div class="leftSection">&nbsp;</div>
								<div class="rightSection"><a href="javascript:void(0)" class="addApproverLink">Add Approvers</a></div>
							</div>
						</div>
						
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
										<td class="values"><input type="text" value="" id="approverName" name="approverName" class="approverName"/></td>
										<td class="values"><input type="text" value="" id="approverMailId" name="approverMailId" /></td>
										<td class="values"><input type="text" value="" id="approverDept" name="approverDept" /></td>
										<td class="values"><input type="text" value="" id="approverDesignation" name="approverDesignation" /></td>
										<td class="deleteApproverTd"><a onclick="javascript:deleteApproverRow.call(this);" class="deleteApprover">Delete</a></td>
									</tr>
									</tbody>
								</table> 
							</div>
						</div>
						<div class="submitBtn"><input type="submit" value="submit" /></div>
						</form>
					</div>
				</div>
				
				
		</div>
		
		<div class="footer">Footer</div>
	</div>
</div>
<script>

$approverRowHtml = $(".approverRowTable").html();
$(".approverList").html("");
var approverCount = 1;
$(".addApproverLink").click(function(){	
	$(".submitBtn").show();
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
	$(deleteApproverRef).parent().parent().parent().parent().html("").remove();
	approverCount = approverCount - 1;
	if(approverCount == 0)
		$(".submitBtn").hide();
}


</script>
</body>
</html>