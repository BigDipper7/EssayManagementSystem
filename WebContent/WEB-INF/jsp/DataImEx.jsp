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
					
					<c:if test="${ Infos!=null }">
						<c:forEach items="${ Infos }" var="info">
							<font color="red"></font>
							<div class="alert alert-info alert-dismissable">
						       <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
						       <c:out value="${ info }"></c:out>
							</div>
						</c:forEach>
					</c:if>

					<div class="panel panel-default">
						<div class="panel-heading">Excel 导出</div>
						<div class="panel-body">

							<label>将所有结果导出到 Excel</label>

							<form role="form" method="POST" action="${pageContext.request.contextPath}/dataExportExcel">
								<button type="submit" class="btn btn-info">一键导出</button>
							</form>
							<hr/>
							<label>将所有结果导出到 Excel并下载该文件</label>
							
							<form role="form" method="POST" action="${pageContext.request.contextPath}/dataExportExcelDownload">
								<button type="submit" class="btn btn-info">一键导出并下载</button>
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
						<div class="panel-heading">Excel 导入</div>
						<div class="panel-body">

							<label>将 Excel 中数据导入数据库，注意，并不会进行覆盖，增量导入，如有重复，手动删除</label>

							<hr />

							<form role="form" method="POST" action="${pageContext.request.contextPath}/singleSaveExcel"
								enctype="multipart/form-data">

								<div class="form-group">
									<label>Input Excel Description:</label>
									<input name="desc" class="form-control" placeholder="Enter description..." />
								</div>

								<div class="form-group">
									<label>文件上传：（仅支持excel）</label>
									<input id="file" type="file" name="file" />
								</div>

								<br /> <input type="submit" class="btn btn-info" value="Upload" />

							</form>

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
