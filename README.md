Java-Web-Security
==============

## Repository Content
All Java projects are created as **Apache Maven** projects (required are Java 7 and Maven 3). In Eclipse you therefore need to install the Maven integration and the **git m2e connector** via the Eclipse update manager before you can import them as new projects.

**Mozilla Firefox** is the recommended and up until today working browser for all web applications in this repository. Keep in mind that browsers or some addons may block or filter certain attacks already. Deactivate all blocking or intercepting addons or try a different browser if a sample application is not working.

The easiest way to start a web application is to use the **Maven-Tomcat7-Plug-in** in each project directory to start a web application: **mvn tomcat7:run-war** (or simply **mvn** in the console, since this is the default goal). Open your browser and point it to **http://localhost:8080/PROJECT_NAME**, e.g. **http://localhost:8080/Ch04_OutputEscaping**. The project name is always the final part of the URL.

See the following paragraphs for a short description and the requirements to execute the sample code and launch the web application.

###Ch04_OutputEscaping
Web application using Java Server Pages (JSP) to show the difference between doing output escaping via Enterprise Security API (ESAPI) and not doing output escaping at all. Use an input like *&lt;script&gt;alert(&#x27;hello&#x27;)&lt;/script&gt;* to see the difference.

**Requirements:** Apache Tomcat, Webbrowser

###Ch04_OutputEscapingJSF
Web application using Java Server Faces (JSF) to show the two different possibilities to show user input in a web page with *#{contact.firstname}* and *&lt;h:outputText value="#{contact.firstname}" /&gt;*. Use an input like *&lt;script&gt;alert(&#x27;Hello&#x27;)&lt;/script&gt;* to see the difference.

**Requirements:** Apache Tomcat, Webbrowser

###Ch04_OutputEscapingJSP
Spring based web application using Java Server Pages (JSP) to show the two different possibilities to show user input in a web page with *${contact.firstname}* and *&lt;c:out value="${contact.firstname}" /&gt;*. Use an input like *&lt;script&gt;alert(&#x27;Hello&#x27;)&lt;/script&gt;* to see the difference.

**Requirements:** Apache Tomcat, Webbrowser

###Ch05_AccessReferenceMaps
Web application using Java Server Faces (JSF) to show the difference between using unprotected and protected Maps (with *IntegerAccessReferenceMaps* and *RandomAccessReferenceMaps*) with user data.

**Requirements:** Apache Tomcat, Webbrowser

###Ch05_HSTS
Web application using a Servlet filter to add the *Strict-Transport-Security* header to each response.

**Requirements:** Apache Tomcat, Webbrowser

###Ch05_SessionFixation
Web application invalidating an existing session and its session id before continuing in the login process. This web application requires the included special *context.xml* configuration for Tomcat in order to display the current session id via JavaScript.

**Requirements:** Apache Tomcat, Webbrowser

###Ch05_SessionHandling
Web application containing a complete *web.xml* configuration showing how to protect cookies. Contains only a start page which tries to show the session cookie in a JavaScript popup.

**Requirements:** Apache Tomcat, Webbrowser

###Ch06_SQLInjection
Web application using user input to query a in-memory-database. The entered data is used as part of a normal *Statement*, an *escaped Statement*, a *Prepared Statement* and as input for a *Hibernate Query Language*.

**Requirements:** Apache Tomcat, Webbrowser

###Ch06_XPathInjection
Web application using user input to query a simple XML document. The entered data (name and password) is used as part of a normal *XPath expression* without any escaping and escaped as part of another *XPath expression*. The unescaped version is prune to XPath Injection, which makes it possible to retrieve more data of the XML document as the intended order limit.

**Requirements:** Apache Tomcat, Webbrowser

###Ch07_CSP
Web application with three input processing servlets. One is unprotected and processes any input without input validation or output escaping and is prone to Cross-Site Scripting. The second servlet adds a minimal *Content-Security-Policy* header to the response and allows to use any source from the same page (URL). This already protects the response page from Cross-Site Scripting in supported browsers. The third form adds a *Content-Security-Policy-Report-Only* header and shows how easy the reported data can be processed.

**Requirements:** Apache Tomcat, Webbrowser

###Ch07_XSS
Web application to test the three XSS types *stored*, *reflected* and *DOM based*. The comment input field is vulnerable to XSS and can be easily protected by enabling output escaping. Cookie could be protected by removing the special *context.xml* and by setting the corresponding *web.xml* parameter.

This web application requires the included special *context.xml* configuration for Tomcat in order to display the current session id via JavaScript.

**Requirements:** Apache Tomcat, Webbrowser

###Ch07_XSSFilter
Web application showing the differences between a blacklist and an ESAPI based request filtering. Use an input like *&lt;script&gt;alert(&#x27;XSS&#x27;)&lt;/script&gt;* to see the different output on the output pages.

**Requirements:** Apache Tomcat, Webbrowser

###Ch07_XSSJSF
Java Server Faces (JSF) based web application accepting user input in two forms. The first form results into an output page showing the user input in drop down boxes and output text fields with all default attributes active. The second form results into an output page using the same output fields with any additional security related attribute set to the maximum. Use an input like *&lt;script&gt;alert(&#x27;Hello&#x27;)&lt;/script&gt;* to challenge the JSF XSS protection.

**Requirements:** Apache Tomcat, Webbrowser

###Ch08_CSRF
Web application showing Cross-Site Request Forgery (CSRF) with GET and POST requests and how to protect forms with an anti CSRF token.

**Requirements:** Apache Tomcat, Webbrowser
