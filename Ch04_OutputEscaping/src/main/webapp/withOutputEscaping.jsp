<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.owasp.esapi.ESAPI" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="resources/css/styles.css" />
	<title>Chapter 04 - Output Escaping</title>
</head>
<body>
    <h1>Chapter 04 - Output Escaping</h1>

    <p>The provided input is printed output escaped. Output escaping is done by the Enterprise Security API (ESAPI) for
        different contexts. Only HTML is the correct context here, the others are provided for reference only. The input
        is printed into [] to show its position.</p>

    <h2>CSS</h2>
    <p>[<%= ESAPI.encoder().encodeForCSS(request.getParameter("protected")) %>]</p>

    <h2>HTML</h2>
	<p>[<%= ESAPI.encoder().encodeForHTML(request.getParameter("protected")) %>]</p>

    <h2>HTML Attribute</h2>
    <p>[<%= ESAPI.encoder().encodeForHTMLAttribute(request.getParameter("protected")) %>]</p>

    <h2>JavaScript</h2>
    <p>[<%= ESAPI.encoder().encodeForJavaScript(request.getParameter("protected")) %>]</p>

    <h2>URL</h2>
    <p>[<%= ESAPI.encoder().encodeForURL(request.getParameter("protected")) %>]</p>

    <h2>XML</h2>
    <p>[<%= ESAPI.encoder().encodeForXML(request.getParameter("protected")) %>]</p>

    <h2>XML Attribute</h2>
    <p>[<%= ESAPI.encoder().encodeForXMLAttribute(request.getParameter("protected")) %>]</p>

    <h2>XPath</h2>
    <p>[<%= ESAPI.encoder().encodeForXPath(request.getParameter("protected")) %>]</p>

    <p><a href="index.jsp">Back</a></p>
</body>
</html>