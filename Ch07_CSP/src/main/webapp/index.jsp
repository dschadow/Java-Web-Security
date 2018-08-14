<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="styles.css" />
	<title>Chapter 07 - Content Security Policy (CSP)</title>
</head>
<body>
	<h1>Chapter 07 - Content Security Policy (CSP)</h1>

	<h2>Without Content Security Policy</h2>
	
	<form name="withoutCSP" method="post" action="WithoutCSPServlet">
		<table>
			<tr>
				<td><label for="unprotected" title="Name">Name</label></td>
				<td><input type="text" id="unprotected" name="unprotected" class="text-input" /></td>
				<td><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form>

	<h2>With Content Security Policy</h2>

	<form name="withCSP" method="post" action="WithCSPServlet">
		<table>
			<tr>
				<td><label for="protected" title="Name">Name</label></td>
				<td><input type="text" id="protected" name="protected" class="text-input" /></td>
				<td><input type="submit" name="submit" value="Submit" /></td>
			</tr>
		</table>
	</form>

    <h2>With Content Security Policy Reporting</h2>

    <form name="withCSPReporting" method="post" action="WithCSPReportingServlet">
        <table>
            <tr>
                <td><label for="reporting" title="Name">Name</label></td>
                <td><input type="text" id="reporting" name="reporting" class="text-input" /></td>
                <td><input type="submit" name="submit" value="Submit" /></td>
            </tr>
        </table>
    </form>
</body>
</html>
