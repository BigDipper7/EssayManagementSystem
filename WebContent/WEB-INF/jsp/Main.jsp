<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>语料库维护系统</title>

    <jsp:include page="universal/Header.jsp"></jsp:include>

</head>
<body>

	<div id="wrapper">
		<jsp:include page="universal/NavigationBar.jsp"></jsp:include>
	    <div id="page-wrapper">
		
		<c:if test="${ Infos!=null }">
			<c:forEach items="${ Infos }" var="info">
				<font color="red"></font>
				<div class="alert alert-info alert-dismissable">
			       <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
			       <c:out value="${ info }"></c:out>
				</div>
			</c:forEach>
		</c:if>
		
        <div class="row">
          <div class="col-lg-12">
            <h1 class="page-header">下午好，${auth_user.username}</h1>
          </div>
          <!-- /.col-lg-12 -->
        </div>

        <div class="row">
          <div class="col-lg-6">
            <div class="panel panel-default">
              <div class="panel-heading">
                User Info
              </div>
              <div class="panel-body">
                <div class="table-responsive">
                  <table class="table table-striped">
                    <thead>
                      <tr>
                        <th>#</th>
                        <th>Key</th>
                        <th>Value</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td>1</td>
                        <td>User ID</td>
                        <td>${auth_user.id}</td>
                      </tr>
                      <tr>
                        <td>2</td>
                        <td>Username</td>
                        <td>${auth_user.username}</td>
                      </tr>
                      <tr>
                        <td>3</td>
                        <td>Email</td>
                        <td>${auth_user.email}</td>
                      </tr>
                      <tr>
                        <td>4</td>
                        <td>Last Login Time</td>
                        <td>2016-06-06 11:16:52 +08:00</td>
                      </tr>
                      <tr>
                        <td>5</td>
                        <td>My IP</td>
                        <td>180.160.19.166</td>
                      </tr>
                    </tbody>
                  </table>
                </div>

            </div>

        </div>


	    </div>
	    <!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->


	<jsp:include page="universal/Footer.jsp"></jsp:include>

</body>
</html>
