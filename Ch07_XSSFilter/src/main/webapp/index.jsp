<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Chapter 07 - XSS Filter</title>
    <link rel="stylesheet" type="text/css" href="resources/css/styles.css" />
</head>
<body>
	<h1>Chapter 07 - XSS Filter</h1>

    <p>This demo application shows how the Enterprise Security API (ESAPI) can escape potentially dangerous input. The
        second form uses a blacklist to remove dangerous tags and attributes from the input before displaying it.
        Feel free to enter any attack data like <strong>&lt;script&gt;alert(&apos;XSS&apos;)&lt;/script&gt;</strong>
        and try to outsmart the filter.</p>

    <form method="post" name="esapiForm" action="outputEsapi.jsp">
        <fieldset>
            <legend>ESAPI Filter</legend>
            <label for="esapi" title="Name">Name</label>
            <input type="text" id="esapi" name="esapi" class="text-input" />
            <input type="submit" value="Submit" />
        </fieldset>
    </form>

    <form method="post" name="blacklistForm" action="outputBlacklist.jsp">
        <fieldset>
            <legend>Blacklist Filter</legend>
            <label for="blacklist" title="Name">Name</label>
            <input type="text" id="blacklist" name="blacklist" class="text-input" />
            <input type="submit" value="Submit" />
        </fieldset>
    </form>
</body>
</html>
