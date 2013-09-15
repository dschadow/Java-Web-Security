<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles.css" />
        <title>Ch04_OutputEscapingJSP: New Contact</title>
    </head>
    <body>
        <h1>Ch04_OutputEscapingJSP: New Contact</h1>

        <h2>Without Output-Escaping</h2>
        <p><strong>Hello</strong> ${contact.firstname} ${contact.lastname}</p>

        <h2>With Output-Escaping</h2>
        <p><strong>Hello</strong> <c:out value="${contact.firstname}" /> <c:out value="${contact.lastname}" /></p>
    </body>
</html>