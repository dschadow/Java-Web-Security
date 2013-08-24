<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="styles.css" />
	<title>With ESAPI Output-Escaping</title>
</head>
<body>
	<h1>With ESAPI Output-Escaping</h1>
	<%@ page import="org.owasp.esapi.ESAPI" %>
	<p>(html) <strong>Hello</strong> <%= ESAPI.encoder().encodeForHTML(request.getParameter("protected")) %></p>
    <p>(html attribute) <strong>Hello</strong> <%= ESAPI.encoder().encodeForHTMLAttribute(request.getParameter("protected")) %></p>
    <p>(css) <strong>Hello</strong> <%= ESAPI.encoder().encodeForCSS(request.getParameter("protected")) %></p>
    <p>(xml) <strong>Hello</strong> <%= ESAPI.encoder().encodeForXML(request.getParameter("protected")) %></p>
</body>
</html>