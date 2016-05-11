<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>This is Main Page!</h1>
	
	<table bordercolor="black" border="1" style="width:100%">
		<caption>Results:</caption>
		<tr>
			<td>Id</td>
			<td>${id}</td>
		</tr>
		<tr>
			<td>Username</td>
			<td>${username}</td>
		</tr>
		<tr>
			<td>Password</td>
			<td>${password}</td>
		</tr>
		<tr>
			<td>Email</td>
			<td>${email}</td>
		</tr>
	</table>
</body>
</html>