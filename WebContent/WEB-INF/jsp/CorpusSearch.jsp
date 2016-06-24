<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search Corpus</title>

<jsp:include page="universal/Header.jsp"></jsp:include>


<!-- DataTables CSS -->
<link href="<c:url value="/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"/>" rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="<c:url value="/bower_components/datatables-responsive/css/dataTables.responsive.css"/>" rel="stylesheet">

<%-- <link href="//cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" rel="stylesheet">
<script src="//cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script> --%>

</head>
<body>


	<div id="wrapper">
  		<jsp:include page="universal/NavigationBar.jsp"></jsp:include>
  		<div id="page-wrapper">

  			<div class="row">
  				<div class="col-lg-12">
  					<h1 class="page-header">语料搜索</h1>
  				</div>
  				<!-- /.col-lg-12 -->
  			</div>
  			<!-- /.row -->

        <div class="row">
          <div class="col-lg-12">

            <div class="panel panel-default">
              <div class="panel-heading">
                语料表单
              </div>
              <div class="panel-body">
                <form role="form" method="POST">
                  <div class="form-group">
                    <label>Input Your Question:</label>
                    <input name="question" class="form-control" placeholder="Enter Your Question..."/>
                  </div>
                  <hr/>
                  <button type="reset" class="btn btn-default">Reset</button>
                  <button type="submit" class="btn btn-info">Search</button>
                </form>
                
              </div>
              <%-- <div class="panel-footer">
              Panel Footer
            </div> --%>
          </div>


        <div class="row">
          <div class="col-lg-12">

            <div class="panel panel-default">
              <div class="panel-heading">
                语料搜索结果
              </div>
              <div class="panel-body">
                
            <div class="dataTable_wrapper">
              <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                <thead>
                  <tr>
                    <td>#</td>
                    <td>Question</td>
                    <td>Answer</td>
                    <td>Score</td>
                  </tr>
                </thead>
                <tbody>

                  <c:if test="${ empty result }">
                    <tr>
                      <td>No data!</td>
                    </tr>
                  </c:if>

                  <c:if test="${ !empty result }">
                    <c:forEach items="${ result }" var="user" varStatus="status" begin="1" step="1">
                      <c:if test="${ status.count%2==1}">
                      </c:if>

                      <tr
                        <c:if test="${ status.count%2==1}">
                          class = "odd"
                        </c:if>

                        <c:if test="${ status.count%2==0}">
                          class = "even"
                        </c:if>

                        >
                        <td>${ status.count }</td>
                        <td>${ user.qus }</td>
                        <td>${ user.ans }</td>
                        <td>${ user.sco }</td>
                      </tr>
                    </c:forEach>
                  </c:if>

              </tbody>
            </table>
          </div>



            <!-- /.table-responsive -->
              </div>
              <%-- <div class="panel-footer">
              Panel Footer
            </div> --%>
          </div>

		</div>
	</div>


  	<jsp:include page="universal/Footer.jsp"></jsp:include>
  	
  	
    <!-- DataTables JavaScript -->
    <script src="<c:url value="/bower_components/datatables/media/js/jquery.dataTables.min.js"/>"></script>
    <script src="<c:url value="/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"/>"></script>

    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
    $(document).ready(function() {
        $('#dataTables-example').DataTable({
                responsive: true
        });
    });
    </script>
</body>
</html>
