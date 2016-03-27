<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles.css" />" >
        <title>Chapter 04 - JSP Output Escaping</title>
    </head>
    <body>
        <h1>Chapter 04 - JSP Output Escaping</h1>

        <h2>Input displayed as direct value expression</h2>
        <p>${contact.firstname} ${contact.lastname}</p>

        <h2>Input displayed as out element</h2>
        <p><c:out value="${contact.firstname}" /> <c:out value="${contact.lastname}" /></p>

        <h2>Input displayed inside Spring escapeBody element as direct value expression</h2>
        <p><s:escapeBody htmlEscape="true">${contact.firstname} ${contact.lastname}</s:escapeBody></p>

        <a href="<c:url value="/" />">Back</a>
    </body>
</html>