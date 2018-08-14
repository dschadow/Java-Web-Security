<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="styles.css" />
	<title>Chapter 06 - XPath Injection</title>
</head>
<body>
	<h1>Chapter 06 - XPath Injection</h1>

    <p><strong>Valid customers are:</strong> Arthur Dent, Ford Prefect, Tricia Trillian McMillan, Zaphod Beeblebrox, Marvin, Slartibartfast<br/>
    Password is always their first name.</p>

	<h2>Without Escaping</h2>
	
	<form name="stmt" method="post" action="XPathServlet">
		<table>
			<tr>
				<td><label for="name" title="Name">Name</label></td>
				<td><input type="text" id="name" name="name" class="text-input" /></td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td><label for="password" title="Password">Password</label></td>
                <td><input type="password" id="password" name="password" class="text-input" /></td>
				<td><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form>

    <h2>With Escaping</h2>

    <form name="stmtEsc" method="post" action="XPathEscapingServlet">
        <table>
            <tr>
                <td><label for="name" title="Name">Name</label></td>
                <td><input type="text" id="name" name="name" class="text-input" /></td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td><label for="password" title="Password">Password</label></td>
                <td><input type="password" id="password" name="password" class="text-input" /></td>
                <td><input type="submit" value="Submit" /></td>
            </tr>
        </table>
    </form>
</body>
</html>
