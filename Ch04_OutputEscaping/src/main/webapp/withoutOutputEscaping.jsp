<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="resources/css/styles.css" />
	<title>Ch04_OutputEscaping: Without Output-Escaping</title>
</head>
<body>
    <h1>Ch04_OutputEscaping: Without Output-Escaping</h1>
	<p><strong>Hello</strong> [<%= request.getParameter("unprotected") %>]</p>

	<p><a href="index.jsp">Home</a></p>
</body>
</html>