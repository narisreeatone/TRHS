<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<link href="styles/styles.css" type="text/css" rel="stylesheet">
<style>
.container{
	float:left;
	width:100%;
}

.userNameContainer{
	float:left;
	width:100%;
}
.pwdContainer{
	float:left;
	width:100%;
}
.label{
	float:left;
	width:50%;
	text-align:right;
}
.ipField{
	float:left;
	width:50%;
	text-align:left;
}
</style>
</head>
<body>
<div id="mainDiv">
	<div id="innerMainDiv">
		
		<div class="header">Menu</div>
		
		<div id="" class="pageContent">
			<form action="LoginServlet" method="post">
				<div class="container">
					<div class="userNameContainer">
						<div class="label">User Name:</div>
						<div class="ipField"><input type="text" name="username" id="username" /></div>
					</div>
					<div class="pwdContainer">
						<div class="label">Password:</div>
						<div class="ipField"><input type="password" name="password" id="password" /></div>
					</div>
					<div class=""><input type="submit" value="Enter" id=""/></div>
					<div class=""></div>
				</div>
			</form>
		</div>
		
		<div class="footer">Footer</div>
	</div>
</div>
</body>
</html>