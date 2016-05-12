<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<h1>This is Main Page!</h1>
	
	<table bordercolor="black" border="1" style="width:100%">
		<caption>Results:</caption>
		<tr>
			<td>Id</td>
			<td>${auth_user.id}</td>
		</tr>
		<tr>
			<td>Username</td>
			<td>${auth_user.username}</td>
		</tr>
		<tr>
			<td>Password</td>
			<td>${auth_user.password}</td>
		</tr>
		<tr>
			<td>Email</td>
			<td>${auth_user.email}</td>
		</tr>
	</table>
	
	<br>
	<br>
	<form:form method="POST" action="${pageContext.request.contextPath}/logout">
		<input type="submit" value="退出登陆"/>
	</form:form>
	
	<hr/>
	
	<h1>功能列表：</h1>
	
	<a href="${ pageContext.request.contextPath }/deamon">语料录入</a> <br/>
	<a href="${ pageContext.request.contextPath }/all">语料列表</a> <br/>
	<a href="${ pageContext.request.contextPath }/deamon">录入数据界面</a> <br/>
	
	
	<hr/>
</body>
</html>