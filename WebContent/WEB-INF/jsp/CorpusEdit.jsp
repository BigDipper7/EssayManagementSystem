<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<jsp:include page="universal/Header.jsp"></jsp:include>
</head>
<body>


	<div id="wrapper">
  		<jsp:include page="universal/NavigationBar.jsp"></jsp:include>
  		<div id="page-wrapper">

  			<div class="row">
  				<div class="col-lg-12">
  					<h1 class="page-header">语料编辑</h1>
  				</div>
  				<!-- /.col-lg-12 -->
  			</div>
  			<!-- /.row -->

        <div class="row">
          <div class="col-lg-12">

            <div class="panel panel-default">
              <div class="panel-heading">
                语料编辑表单
              </div>
              <div class="panel-body">
                <form role="form">
                  <div class="form-group">
                    <label>Input Corpus Item ID:</label>
                    <input class="form-control" placeholder="Enter ID..."/>
                  </div>
                  <hr/>
                  <button type="reset" class="btn btn-default">Reset</button>
                  <button type="submit" class="btn btn-info">Edit Now !</button>
                </form>
              </div>
              <%-- <div class="panel-footer">
              Panel Footer
            </div> --%>
          </div>

        </div>
        <!-- /.col-lg-12 -->
      </div>
      <!-- /.row -->
		</div>
	</div>


  	<jsp:include page="universal/Footer.jsp"></jsp:include>
</body>
</html>
