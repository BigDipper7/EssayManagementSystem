<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>登录界面</title>
	
    <!-- Bootstrap Core CSS -->
    <link href="<c:url value="/bower_components/bootstrap/dist/css/bootstrap.min.css" />" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="<c:url value="/bower_components/metisMenu/dist/metisMenu.min.css" />" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<c:url value="/css/sb-admin-2.css" />" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<c:url value="/bower_components/font-awesome/css/font-awesome.min.css" />" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	
	<!-- no use <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery.1.10.2.min.js" />"></script>
    <script src="<c:url value="/resources/js/main.js" />"></script> -->
    
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
                                    <form:input path="username" class="form-control" placeholder="E-mail" name="email"/>
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

    <!-- jQuery -->
    <script src="<c:url value="/bower_components/jquery/dist/jquery.min.js"/>"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<c:url value="/bower_components/bootstrap/dist/js/bootstrap.min.js"/>"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="<c:url value="/bower_components/metisMenu/dist/metisMenu.min.js"/>"></script>

    <!-- Custom Theme JavaScript -->
    <script src="<c:url value="/js/sb-admin-2.js"/>"></script>
</body>
</html>