<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Chapter 07 - XSS Filter</title>
    <link rel="stylesheet" type="text/css" href="resources/css/styles.css" />
</head>
<body>
    <h1>Chapter 07 - XSS Filter</h1>

    <p>This output is filtered by an ESAPI filter. This filter escapes potentially dangerous input. [] are used to
        indicate the position of the given input.</p>

    <p>[<%= request.getParameter("esapi") %>]</p>

    <a href="<c:url value="/" />">Back</a>
</body>
</html>