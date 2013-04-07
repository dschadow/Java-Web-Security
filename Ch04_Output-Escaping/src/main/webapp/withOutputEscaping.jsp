<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>With Output-Escaping</title>
</head>
<body>
	<h1>ESAPI</h1>
	<%@ page import="org.owasp.esapi.ESAPI" %>
	<strong>Hello</strong> <%= ESAPI.encoder().encodeForHTML(request.getParameter("name")) %>
	
	<h1>Apache Commons</h1>
	<%@ page import="org.apache.commons.lang3.StringEscapeUtils" %>
	<strong>Hello</strong> <%= StringEscapeUtils.escapeHtml4(request.getParameter("name")) %>
</body>
</html>