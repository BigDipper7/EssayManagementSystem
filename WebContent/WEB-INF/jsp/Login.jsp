<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:if test="">
	</c:if>
	<h1>Login!</h1>
	<form:form method="POST" action="${pageContext.request.contextPath}/login" commandName="User">
		<table style="width: 90%">
			<tr>
				<td><form:label path="username">Username:</form:label></td>
				<td><form:input path="username"/></td>
			</tr>
			<tr>
				<td><form:label path="password">Password:</form:label></td>
				<td><form:input path="password"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="Login!"/></td>
				<td></td>
			</tr>
		</table>
	</form:form>
</body>
</html>