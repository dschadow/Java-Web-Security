<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="resources/css/styles.css" />
	<title>Chapter 04 - Output Escaping</title>
</head>
<body>
	<h1>Chapter 04 - Output Escaping</h1>

	<p>This demo application shows how JavaServer Pages (JSP) can be extended with safe output escaping provided by the
        OWASP Enterprise Security API (ESAPI). Feel free to enter any attack data like
        <strong>&lt;script&gt;alert(&apos;XSS&apos;)&lt;/script&gt;</strong>.</p>

	<form name="withoutOutputEscaping" method="post" action="withoutOutputEscaping.jsp">
		<fieldset>
			<legend>Without Output Escaping</legend>
			<label for="unprotected" title="Name">Name</label>
			<input type="text" id="unprotected" name="unprotected" class="text-input" />
			<input type="submit" value="Submit" />
		</fieldset>
	</form>

	<form name="withOutputEscaping" method="post" action="withOutputEscaping.jsp">
		<fieldset>
			<legend>With Output Escaping</legend>
			<label for="protected" title="Name">Name</label>
			<input type="text" id="protected" name="protected" class="text-input" />
			<input type="submit" value="Submit" />
		</fieldset>
	</form>
</body>
</html>
