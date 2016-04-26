<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test Editor</title>
<script src="//cdn.ckeditor.com/4.5.8/full/ckeditor.js"></script>
</head>
<body>
	<form:form>
		<form:textarea path="editor" rows="23" cols="34" id="editor" />
		<input type="submit" value="Submit"/>
	</form:form>

	<script>
		// Replace the <textarea id="editor1"> with a CKEditor
		// instance, using default configuration.
		CKEDITOR.replace('editor');
	</script>
</body>
</html>