<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Output-Escaping mit Spring</title>
</head>
<body>
	<h1>Spring MVC Output-Escaping</h1>

	<form:form method="post" action="addContact.html">
		<table>
			<tr>
				<td><form:label path="firstname">Vorname</form:label></td>
				<td><form:input path="firstname" /></td>
			</tr>
			<tr>
				<td><form:label path="lastname">Nachname</form:label></td>
				<td><form:input path="lastname" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Weiter" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>