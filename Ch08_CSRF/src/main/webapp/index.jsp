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
	
	<h2>Unprotected GET</h2>
	
	<p><a href="CSRFServlet?name=BrowserLink">Link</a></p>
	
	<p><a href="image.html">Image</a></p>
	
	<h2>Unprotected POST</h2>
	
	<h3>Form</h3>

	<form name="greeting" method="post" action="CSRFServlet">
		<table>
			<tr>
				<td>Name</td>
				<td><input type="text" name="name"></td>
				<td><input type="submit" value="Senden"></td>
			</tr>
		</table>
	</form>
	
	<p><a href="xmlhttprequest.html">XMLHttpRequest</a></p>
	
	<h2>Protected GET</h2>
	
	<p><a href="ProtectedServlet?name=BrowserLink&<%=CSRFTokenHandler.CSRF_TOKEN%>=<%=CSRFTokenHandler.getToken(request.getSession(false))%>">Link</a></p>
	
	<p><a href="image-protected.html">Image</a></p>
	
	<h2>Protected POST</h2>
	
	<h3>Form with token</h3>

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
	
	<p><a href="xmlhttprequest-protected.html">XMLHttpRequest</a></p>
</body>
</html>
