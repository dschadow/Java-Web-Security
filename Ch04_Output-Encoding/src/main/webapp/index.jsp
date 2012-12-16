<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Output-Escaping</title>
</head>
<body>
	<h1>Output-Escaping</h1>

	<h2>Ohne Output-Escaping</h2>
	
	<form name="noOutputEscaping" method="post" action="noOutputEscaping.jsp">
		<table>
			<tr>
				<td>Name</td>
				<td><input type="text" name="name"></td>
				<td><input type="submit" value="Senden"></td>
			</tr>
		</table>
	</form>

	<h2>Mit Output-Escaping</h2>
	
	<form name="withOutputEscaping" method="post" action="withOutputEscaping.jsp">
		<table>
			<tr>
				<td>Name</td>
				<td><input type="text" name="name"></td>
				<td><input type="submit" name="submit" value="Senden"></td>
			</tr>
		</table>
	</form>
</body>
</html>
