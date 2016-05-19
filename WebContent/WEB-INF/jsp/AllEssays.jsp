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
	<h1>All essays</h1>
	
	<table bordercolor="black" border="1" style="width:100%">
		<caption>Results:</caption>
		
		<tr>
			<td>Id</td>
			<td>Content</td>
			<td>Title</td>
			<td>Author</td>
			<td colspan="2">Operation</td>
		</tr>
		
		<c:if test="${ empty Essays }">
			<tr>
				<td>No data!</td>
			</tr>
		</c:if>
			
		<c:if test="${ !empty Essays }">
			<c:forEach items="${ Essays }" var="essay">
				<tr>
					<td>${ essay.id }</td>
					<td>${ essay.content }</td>
					<td>${ essay.title }</td>
					<td>${ essay.author }</td>
					<td><a href="${ pageContext.request.contextPath }/update/${ essay.id }">Edit</a></td>
					<td><a class="delete" href="${ pageContext.request.contextPath }/delete/${ essay.id }">Delete</a></td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
	
</body>
</html>