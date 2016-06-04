<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>登录界面</title>
	
    <jsp:include page="universal/Header.jsp"></jsp:include>
    
<body>
	<!--<c:if test="${ Errors!=null }">
		<c:forEach items="${ Errors }" var="error">
			<font color="red"><c:out value="${ error }"></c:out></font>
		</c:forEach>
	</c:if>	-->
	
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                	
                	<c:if test="${ Errors!=null }">
						<c:forEach items="${ Errors }" var="error">
							<font color="red"></font>
							<div class="alert alert-danger alert-dismissable">
						       <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
						       <c:out value="${ error }"></c:out>
							</div>
						</c:forEach>
					</c:if>	
                    
                    <div class="panel-heading">
                        <h3 class="panel-title">Please Sign In</h3>
                    </div>
                    <div class="panel-body">
						<form:form role="form" method="POST" action="${pageContext.request.contextPath}/login" commandName="User">
							<!-- <table style="width: 90%">
								<tr>
									<td><form:label path="username">Username:</form:label></td>
									<td><form:input  path="username" placeholder="User Name"/></td>
								</tr>
								<tr>
									<td><form:label path="password">Password:</form:label></td>
									<td><form:password path="password"/></td>
								</tr>
								<tr>
									<td><input type="submit" value="Login!"/></td>
									<td></td>
								</tr>
							</table> -->
							
							<!-- <table>
								<tr>
									<td><form:label path="username">Username:</form:label></td>
									<td><form:input  path="username" placeholder="User Name"/></td>
								</tr>
								<tr>
									<td><form:label path="password">Password:</form:label></td>
									<td><form:password path="password"/></td>
								</tr>
								<tr>
									<td><input type="submit" value="Login!"/></td>
									<td></td>
								</tr>
							</table> -->
							
							
                            <fieldset>
                                <div class="form-group">
                                    <form:input path="username" class="form-control" placeholder="Username" name="email"/>
                                </div>
                                <div class="form-group">
                                    <form:password path="password" class="form-control" placeholder="Password" name="password"/>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input name="remember" type="checkbox" value="Remember Me">Remember Me
                                    </label>
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                <input type="submit" class="btn btn-lg btn-success btn-block" value="Login" />
                            </fieldset>
                             
						</form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
	
	<jsp:include page="universal/Footer.jsp"></jsp:include>
	
</body>
</html>