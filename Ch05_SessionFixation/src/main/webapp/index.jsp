<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="styles.css" />
	<title>Chapter 05 - Session Fixation</title>
</head>
<body>
	<h1>Chapter 05 - Session Fixation</h1>

    <script type="text/javascript">document.write("<p><strong>Session ID</strong> [" + document.cookie + "]</p>")</script>

	<form name="login" method="post" action="LoginServlet">
		<table>
			<tr>
				<td><label for="stmt" title="Name">Name</label></td>
				<td><input type="text" id="stmt" name="name" class="text-input" /></td>
				<td><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
