<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Cross-Site Request Forgery</title>
</head>
<body>
	<h1>Cross-Site Request Forgery</h1>

	<form name="greeting" method="post" action="CSRFPostServlet">
		<table>
			<tr>
				<td>Name</td>
				<td><input type="text" name="name"></td>
				<td><input type="submit" value="Senden"></td>
			</tr>
		</table>
	</form>
</body>
</html>
