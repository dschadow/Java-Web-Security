<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="resources/css/styles.css" />
	<title>Ch08_CSRF</title>
</head>
<body>
	<h1>Ch08_CSRF - Unprotected Requests</h1>

	<h2>GET Requests</h2>

    <ul>
        <li><a href="CSRFServlet?name=BrowserLink">Link</a></li>
        <li><a href="image.html">Image</a></li>
    </ul>
	
	<h2>POST Requests</h2>
	
	<h3>Form</h3>

	<form name="greeting" method="post" action="CSRFServlet">
		<table>
			<tr>
				<td>Name</td>
				<td><input type="text" name="name" class="text-input"></td>
				<td><input type="submit" value="Submit" class="send-button"></td>
			</tr>
		</table>
	</form>

    <ul>
        <li><a href="xmlhttprequest.html">XMLHttpRequest</a></li>
    </ul>

    <p><a href="index.jsp">Back</a></p>
</body>
</html>
