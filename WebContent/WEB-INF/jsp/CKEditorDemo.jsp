<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>语料录入界面</title>
	<script src="//cdn.ckeditor.com/4.5.8/full/ckeditor.js"></script>

  <jsp:include page="universal/Header.jsp"></jsp:include>

</head>
<body>

	<div id="wrapper">
		<jsp:include page="universal/NavigationBar.jsp"></jsp:include>
		<div id="page-wrapper">

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">语料录入</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
							语料录入表单
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-lg-12">
									<form:form id="essay" role="form" commandName="essay" method="POST" action="" accept-charset="ISO-8859-1">
										<div class="form-group">
											<input id="hidtitle" type="hidden" value="${ essay.title }"/>
											<input id="hidauthor" type="hidden" value="${ essay.author }"/>

											<div class="form-group">
												<form:label path="title">Title:</form:label>
												<form:input class="form-control" id="title" type="text" path="title" placeholder="Enter title here."/>
											</div>
											<div class="form-group">
												<form:label path="author">Author:</form:label>
												<form:input class="form-control" id="author" path="author" placeholder="${auth_user.username}" value="${auth_user.username}"/>
											</div>
											<div class="form-group">
												<form:label path="content">Content:</form:label>
												<form:textarea path="content" rows="23" cols="34" id="editor" />
											</div>
											<div class="form-group">
												<label>Choose Corpus Type:</label>
												<form:select path="category" class="form-control">
													<option>无</option>
													<option>电子报税</option>
													<option>证书</option>
													<option>软件错误</option>
													<option>金税三期</option>
												</form:select>
											</div>
											<div class="form-group">
												<label>Appendix(Files):</label>
												<input type="file"/>
											</div>

											<hr/>

											<div class="form-group">
												<input type="reset" class="btn btn-default" value="Reset"/>
												<input type="button" class="btn btn-success" value="Submit" onClick="submitForm()"/>
											</div>

										</div>
									</form:form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>



		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->

	<script>
		CKEDITOR.config.htmlEncodeOutput = false;
		CKEDITOR.config.entities = false;
		CKEDITOR.config.basicEntities = false;
		CKEDITOR.config.entities_greek = false;
		CKEDITOR.config.entities_latin = false;
		
		// Replace the <textarea id="editor1"> with a CKEditor instance
		var editor = CKEDITOR.replace('editor');
		
	</script>
	
	<script type="text/javascript">
		function submitForm() {
			var content = CKEDITOR.instances.editor.getData();
			//console.log('contents:'+content);
			content = content.replace(/\—/g,'-');
			content = content.replace(/\——/g,'--');
			content = content.replace(/\“/g,'"');
			content = content.replace(/\”/g,'"');
			content = content.replace(/\‘/g,"'");
			content = content.replace(/\’/g,"'");
			content = content.replace(/\……/g,"......");
			//console.log('new contents:'+content);
			CKEDITOR.instances.editor.setData(content);
			$('#essay').submit();
		}
	</script>

	<script type="text/javascript">
		// reset value for input tag
		document.getElementById('title').value = document.getElementById('hidtitle').value;
		document.getElementById('author').value = document.getElementById('hidauthor').value;

	</script>

	<jsp:include page="universal/Footer.jsp"></jsp:include>

</body>
</html>
