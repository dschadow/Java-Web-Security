<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Spring based Output Escaping</title>
    </head>
    <body>
        <h2>Insecure (not escaped): Hello ${contact.firstname} ${contact.lastname}</h2>
        <h2>Secure (automatically escaped): Hello <c:out value="${contact.firstname}" /> <c:out value="${contact.lastname}" /></h2>
    </body>
</html>