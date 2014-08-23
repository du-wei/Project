<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="upload.action!uploadWithStruts" method="post" enctype="multipart/form-data">
	<label for="upload">struts upload</label> <input type="file" id="upload" name="upload">
	<br>
	<input type="submit" value="submit">
</form>

<form action="upload.action!uploadWithJavaIO" method="post" enctype="multipart/form-data">
	<label for="javaio">java io upload</label> <input type="file" id="javaio" name="javaio">
	<br>
	<input type="submit" value="submit">
</form>

<form action="../filter" method="post" enctype="multipart/form-data">
	<label for="jspsmart">jspsmart upload</label> <input type="file" id="jspsmart" name="jspsmart">
	<br>
	<input type="submit" value="submit">
</form>


</body>
</html>