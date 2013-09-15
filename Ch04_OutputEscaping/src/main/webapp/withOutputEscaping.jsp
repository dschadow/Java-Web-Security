<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.owasp.esapi.ESAPI" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="styles.css" />
	<title>Ch04_OutputEscaping: With Output-Escaping (ESAPI)</title>
</head>
<body>
	<h1>Ch04_OutputEscaping: With Output-Escaping (ESAPI)</h1>

    <h2>HTML</h2>
	<p><strong>Hello</strong> <%= ESAPI.encoder().encodeForHTML(request.getParameter("protected")) %></p>

    <h2>CSS</h2>
    <p><strong>Hello</strong> <%= ESAPI.encoder().encodeForCSS(request.getParameter("protected")) %></p>

    <h2>XML</h2>
    <p><strong>Hello</strong> <%= ESAPI.encoder().encodeForXML(request.getParameter("protected")) %></p>
</body>
</html>