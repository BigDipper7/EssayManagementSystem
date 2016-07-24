<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<jsp:include page="universal/Header.jsp"></jsp:include>
</head>
<body>

	<div id="wrapper">
		<jsp:include page="universal/NavigationBar.jsp"></jsp:include>
		<div id="page-wrapper">

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">导入、导出</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->

			<div class="row">
				<div class="col-lg-12">

					<div class="panel panel-default">
						<div class="panel-heading">导出到 excel</div>
						<div class="panel-body">
							<form role="form" method="POST">
								<div class="form-group">
									<label>将所有结果导出到excel</label>
								</div>
								<button type="submit" class="btn btn-info">Submit</button>
							</form>

						</div>
						<%-- <div class="panel-footer">
			              Panel Footer
			            </div> --%>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">句子分词结果</div>
						<div class="panel-body">

							<h1>${ result }</h1>
						</div>
						<%-- <div class="panel-footer">
					              Panel Footer
					            </div> --%>
					</div>
				</div>
			</div>


		</div>
	</div>


	<jsp:include page="universal/Footer.jsp"></jsp:include>
</body>
</html>
