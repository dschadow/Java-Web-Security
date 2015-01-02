<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles.css" />" >
        <title>Ch04_OutputEscapingJSP: New Contact</title>
    </head>
    <body>
        <h1>Ch04_OutputEscapingJSP: New Contact</h1>

        <h2>Input with direct value expression</h2>
        <p><strong>Hello</strong> ${contact.firstname} ${contact.lastname}</p>

        <h2>Input with out element</h2>
        <p><strong>Hello</strong> <c:out value="${contact.firstname}" /> <c:out value="${contact.lastname}" /></p>
    </body>
</html>