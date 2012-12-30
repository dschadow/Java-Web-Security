<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Output-Escaping mit Spring</title>
    </head>
    <body>
        <h2>Unsicher: Hallo ${contact.firstname} ${contact.lastname}</h2>
        <h2>Sicher: Hallo <c:out value="${contact.firstname}" /> <c:out value="${contact.lastname}" /></h2>
    </body>
</html>