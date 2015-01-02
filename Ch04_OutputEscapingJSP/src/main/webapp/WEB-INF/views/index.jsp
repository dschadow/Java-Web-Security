<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles.css" />" >
    <title>Ch04_OutputEscapingJSP</title>
</head>
<body>
	<h1>Ch04_OutputEscapingJSP</h1>

	<form method="post" action="contacts/addContact">
		<fieldset>
			<legend>Add new contact</legend>
			<label for="firstname" title="Firstname">Firstname</label>
			<input type="text" id="firstname" name="firstname" class="text-input" />
			<label for="lastname" title="Lastname">Lastname</label>
			<input type="text" id="lastname" name="lastname" class="text-input" />
			<input type="submit" value="Submit" />
		</fieldset>
	</form>
</body>
</html>