<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="de.dominikschadow.webappsecurity.token.CSRFTokenHandler" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="styles.css" />
	<title>Ch08_CSRF</title>
</head>
<body>
	<h1>Ch08_CSRF - Protected Requests</h1>
	
	<h2>GET Requests</h2>

    <ul>
        <li>Successful <a href="ProtectedServlet?name=BrowserLink&<%=CSRFTokenHandler.CSRF_TOKEN%>=<%=CSRFTokenHandler.getToken(request.getSession(false))%>">Link</a></li>
        <li>Unsuccessful <a href="image-protected.html">Image</a></li>
    </ul>

	<h2>POST Requests</h2>
	
	<h3>Successful Form</h3>

	<form name="greetingProtected" method="post" action="ProtectedServlet">
		<input type="hidden" name="<%=CSRFTokenHandler.CSRF_TOKEN%>"
		  value="<%=CSRFTokenHandler.getToken(request.getSession(false))%>">
		<table>
			<tr>
				<td>Name</td>
				<td><input type="text" name="name" class="text-input"></td>
				<td><input type="submit" value="Submit" class="send-button"></td>
			</tr>
		</table>
	</form>

    <ul>
        <li>Unsuccessful <a href="xmlhttprequest-protected.html">XMLHttpRequest</a></li>
    </ul>

    <p><a href="index.jsp">Back</a></p>
</body>
</html>
