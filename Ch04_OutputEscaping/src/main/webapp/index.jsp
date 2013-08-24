<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="styles.css" />
	<title>Output-Escaping</title>
</head>
<body>
	<h1>Output-Escaping</h1>

	<h2>Without Output-Escaping</h2>
	
	<form name="noOutputEscaping" method="post" action="noOutputEscaping.jsp">
		<table>
			<tr>
				<td><label for="unprotected" title="Name">Name</label></td>
				<td><input type="text" id="unprotected" name="unprotected" class="text-input" /></td>
				<td><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form>

	<h2>With Output-Escaping (ESAPI)</h2>
	
	<form name="withOutputEscaping" method="post" action="withOutputEscaping.jsp">
		<table>
			<tr>
				<td><label for="protected" title="Name">Name</label></td>
				<td><input type="text" id="protected" name="protected" class="text-input" /></td>
				<td><input type="submit" name="submit" value="Submit" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
