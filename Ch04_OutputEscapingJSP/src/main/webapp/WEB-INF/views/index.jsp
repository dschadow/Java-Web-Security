<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles.css" />" >
    <title>Chapter 04 - JSP Output Escaping</title>
</head>
<body>
	<h1>Chapter 04 - JSP Output Escaping</h1>

	<p>This demo application shows how JavaServer Pages (JSP) handle output escaping with direct value expressions and
        normal elements/ attributes. Feel free to enter any attack data like <strong>&lt;script&gt;alert(&apos;XSS&apos;)&lt;/script&gt;</strong>.</p>

	<form method="post" action="addContact">
		<fieldset>
			<legend>Add new contact</legend>
			<label for="firstname" title="First name">First name</label>
			<input type="text" id="firstname" name="firstname" class="text-input" />
			<label for="lastname" title="Last name">Last name</label>
			<input type="text" id="lastname" name="lastname" class="text-input" />
			<input type="submit" value="Submit" />
		</fieldset>
	</form>
</body>
</html>