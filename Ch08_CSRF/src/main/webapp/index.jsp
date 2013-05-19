<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="de.dominikschadow.webappsecurity.csrf.CSRFTokenHandler" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Cross-Site Request Forgery</title>
</head>
<body>
	<h1>Cross-Site Request Forgery</h1>
	
	<h2>Unprotected</h2>
	
	<h3>GET</h3>
	
	<h4>Normal browser link</h4>
	
	<a href="CSRFServlet?name=BrowserLink">Send</a>
	
	<h4>Image</h4>
	
	<a href="image.html">Image</a>
	
	<h3>POST</h3>
	
	<h4>Normal browser form</h4>

	<form name="greeting" method="post" action="CSRFServlet">
		<table>
			<tr>
				<td>Name</td>
				<td><input type="text" name="name"></td>
				<td><input type="submit" value="Senden"></td>
			</tr>
		</table>
	</form>
	
	<h4>XMLHttpRequest</h4>
	
	<a href="xmlhttprequest.html">XMLHttpRequest</a>
	
	<h2>Protected</h2>
	
	<h3>POST</h3>
	
	<h4>Normal browser form</h4>

	<form name="greetingProtected" method="post" action="ProtectedServlet">
		<input type="hidden" name="<%=CSRFTokenHandler.CSRF_TOKEN%>"
		  value="<%=CSRFTokenHandler.getToken(request.getSession(false))%>">
		<table>
			<tr>
				<td>Name</td>
				<td><input type="text" name="name"></td>
				<td><input type="submit" value="Senden"></td>
			</tr>
		</table>
	</form>
	
	<h4>XMLHttpRequest</h4>
	
	<a href="xmlhttprequest-protected.html">XMLHttpRequest</a>
</body>
</html>
