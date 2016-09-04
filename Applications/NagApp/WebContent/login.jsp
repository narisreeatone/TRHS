<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<link  type="text/css" href="styles/styles.css" rel="stylesheet">
<script type="text/javascript" src="javascript/jquery-3.1.0.min.js"></script>
<script type="text/javascript" src="javascript/common.js"></script>
<style>
.pageContent{
	float:left;
	width:950px;
	margin:20px 0 20px 0;
	
}
.container{
	float:left;
	width:100%;	
}

#loginPage{
	margin-top:75px;
}
.container{
	float:none;
}
.userNameDiv{
	float:left;
	width:100%;
	margin-bottom:10px;
}
.pwdDiv{
	float:left;
	width:100%;
}
.label{
	float:left;
	width:50%;
	text-align:right;
	font-weight:bold;
}
.ipField{
	float:left;
	width:50%;
	text-align:left;
}
.submitBtnDiv{
	float:left;
	width:100%;
	text-align:center;
	margin-top:20px;
}
.container{
	background: #ffffff none repeat scroll 0 0;    
    box-shadow: 0 0 3px #016fe3;    
    max-width: 350px; 
    text-align:center;   
    margin:0 auto;
}
.box{
	padding:40px;
}
.error {
    border: 1px dotted red;
}
.username, .password {
    border: 1px solid #d9d9d9;
    box-sizing: border-box;
    display: block;
    margin: 0 0 20px;
    outline: medium none;
    padding: 10px 15px;
    transition: all 0.3s ease 0s;
    width: 100%;
}
.box button {
    background: #33b5e5 none repeat scroll 0 0;
    border: 0 none;
    color: #ffffff;
    cursor: pointer;
    padding: 10px 15px;
    transition: all 0.3s ease 0s;
    width: 100%;
}
.errorMsg{
	color:red;
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
		
			<div id="loginPage" class="pageContent">
				
				<div class="container">
					<div class="box">
						<h2>Login to your account</h2>
						<div class="errorMsg"><c:if test="${errorMsg ne '' || errorMsg ne 'null'}">${errorMsg}</c:if></div>
						<!-- <div class="userNameDiv">
							<div class="label">User Name:</div>
							<div class="ipField"><input type="text" name="username" id="username" /></div>
						</div>
						<div class="pwdDiv">
							<div class="label">Password:</div>
							<div class="ipField"><input type="password" name="password" id="password" /></div>
						</div>
						<div class="submitBtnDiv"><input type="submit" value="sign in" id="submitBtn"/></div>
						 -->
						<form action="LoginServlet" method="post" id="loginForm">
						<input type="text" placeholder="username" name="username" id="username" class="username">
						<input type="password" placeholder="password" name="password" id="password" class="password">
						<button id="submitBtn">Login</button>
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
</body>
<script>

$("#loginForm").submit(function(){	
	var errorMsg = "Please enter correct values.";
	$("#username").css("border","1px solid #d9d9d9");
	$("#password").css("border","1px solid #d9d9d9");
	var error = false;
	if( IsEmpty($("#username")) ){		
		error = true;
	}
	if( IsEmpty($("#password")) ){		
		error = true;
	}
	if( validatePassword($("#password")) ){		
		error = true;
	}	
	if(error){		
		$(".errorMsg").html(errorMsg);
		
	}
	return !error;		
});
</script>
</html>