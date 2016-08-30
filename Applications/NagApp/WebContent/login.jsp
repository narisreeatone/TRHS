<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<link href="styles/styles.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="javascript/jquery-3.1.0.min.js"></script>
<link href="styles/styles.css" type="text/css" rel="stylesheet">
<style>
.container{
	float:left;
	width:100%;
	text-align:center;
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
.box{
	width:100%;
	
	float:left;
}
.errorMsg{
	display:none;
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
				<form action="LoginServlet" method="post" id="loginForm">
				<div class="container">
					<div class="box">
						<div class="errorMsg">Please enter correct values</div>
						<div class="userNameDiv">
							<div class="label">User Name:</div>
							<div class="ipField"><input type="text" name="username" id="username" /></div>
						</div>
						<div class="pwdDiv">
							<div class="label">Password:</div>
							<div class="ipField"><input type="password" name="password" id="password" /></div>
						</div>
						<div class="submitBtnDiv"><input type="submit" value="sign in" id="submitBtn"/></div>
						<div class=""></div>
					</div>
				</div>	
				</form>			
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
$("#submitBtn").click(function(){
	username = $("#username").val();
	password = $("#password").val();	
	if(username != " " && password != " "){
		$("#loginForm").submit();
	}else{
		$(".errorMsg").show();
	}
});
</script>
</html>