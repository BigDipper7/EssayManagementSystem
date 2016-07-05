<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<jsp:include page="universal/Header.jsp"></jsp:include>

<!-- DataTables CSS -->
<!-- <link href="<c:url value="/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"/>" rel="stylesheet">-->

<!-- DataTables Responsive CSS -->
<!-- <link href="<c:url value="/bower_components/datatables-responsive/css/dataTables.responsive.css"/>" rel="stylesheet">-->
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css"/>
 

</head>
<body>

  	<div id="wrapper">
  		<jsp:include page="universal/NavigationBar.jsp"></jsp:include>
  		<div id="page-wrapper">

  			<div class="row">
  				<div class="col-lg-12">
  					<h1 class="page-header">所有语料列表</h1>
  				</div>
  				<!-- /.col-lg-12 -->
  			</div>
  			<!-- /.row -->

        <div class="row">
          <div class="col-lg-12">

            <div class="dataTable_wrapper">
              <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                <thead>
                  <tr>
                    <td>#</td>
                    <td>Corpus ID</td>
                    <td>Title</td>
                    <td>Content</td>
                    <td>Author</td>
                    <td></td>
                    <td></td>
                  </tr>
                </thead>
                <tbody>

				<!-- 
                  <c:if test="${ empty Essays }">
                    <tr>
                      <td>No data!</td>
                    </tr>
                  </c:if>

                  <c:if test="${ !empty Essays }">
                    <c:forEach items="${ Essays }" var="essay" varStatus="status" begin="1" step="1">
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
                        <td>${ essay.id }</td>
                        <td>${ essay.title }</td>
                        <td>${ essay.content }</td>
                        <td>${ essay.author }</td>
                        <td><a href="${ pageContext.request.contextPath }/update/${ essay.id }">Edit</a></td>
                        <td><a class="delete" href="${ pageContext.request.contextPath }/delete/${ essay.id }">Delete</a></td>
                      </tr>
                    </c:forEach>
                  </c:if>
 -->
              </tbody>
            </table>
          </div>



            <!-- /.table-responsive -->

          </div>
          <!-- /.col-lg-12 -->

        </div>
        <!-- /.row -->

      </div>
    </div>

  	<jsp:include page="universal/Footer.jsp"></jsp:include>

    <!-- DataTables JavaScript -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.3.min.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>
    <!--<script src="<c:url value="/bower_components/datatables/media/js/jquery.dataTables.min.js"/>"></script>
    <script src="<c:url value="/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"/>"></script>-->

    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
    $(document).ready(function() {
    	$('#dataTables-example').DataTable( {

	        "processing": true,
	        "serverSide": true,
	        "bSort": false,
	        "ajax": "${pageContext.request.contextPath}/json/allCorpus",
	        "columns": [
   		        { "data": "id" },
   		        { "data": "id" },
		        { "data": "title" },
		        { "data": "content" },
		        { "data": "author" },
	            {
					"targets": 0,
					"data": "id",
					"render": function ( data, type, full, meta ) {
					  return '<a  href="${ pageContext.request.contextPath }/update/'+data+'">更新</a>';
					}
	            },
	            {
					"targets":0,
					"data": "id",
					"render": function(data, type, full, meta) {
					    return '<a  href="${ pageContext.request.contextPath }/delete/'+data+'">删除</a>';    
	            }
	           }
	    	]
	        
    	});
    });
    </script>
</body>
</html>
