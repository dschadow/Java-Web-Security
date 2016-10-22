<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="de.dominikschadow.webappsecurity.token.CSRFTokenHandler" %>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Chapter 08 - CSRF</title>
    <link rel="stylesheet" type="text/css" href="resources/css/styles.css" />
</head>
<body>
    <h1>Chapter 08 - CSRF</h1>

    <p>The generated CSRF token for this session is: <%=CSRFTokenHandler.getToken(request.getSession(false))%></p>

    <ul>
        <li><a href="requests-unprotected.html">Unprotected Requests</a></li>
        <li><a href="requests-protected.html">Protected Requests</a></li>
    </ul>
</body>
</html>
