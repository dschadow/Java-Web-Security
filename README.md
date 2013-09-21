Java-Web-Security
==============

## Repository Content
All Java projects are created as Apache Maven projects (required are Java 7 and Maven 3). In Eclipse you therefore need to install the Maven integration and the git m2e connector via the update manager before you can import them as new projects.

Mozilla Firefox is the recommended browser for all web applications. Keep in mind that browsers or some addons may block or filter certain attacks already. Try a different browser if a sample application is not working.

Use the Maven-Tomcat7-Plug-in in each project directory to start each web application: **mvn tomcat7:run-war** (or simply **mvn** in the console, since this is the default goal). Open your browser and point it to **http://localhost:8080/PROJECT_NAME**, e.g. **http://localhost:8080/Ch04_OutputEscaping**. The project name is always the final part of the URL.

See the following paragraphs for a short description and the requirements to execute the sample code and launch the (web) application.

###Ch04_OutputEscaping
Web application using Java Server Pages (JSP) to show the difference between doing output escaping via Enterprise Security API (ESAPII and not doing output escaping at all. Use an input like *&lt;script&gt;alert(&#x27;hello&#x27;)&lt;/script&gt;* to see the difference.

**Requirements:** Apache Tomcat, Webbrowser

###Ch04_OutputEscapingJSP
Spring based web application using Java Server Pages (JSP) to show the two different possibilities to show user input in a web page with *${contact.firstname}* and *&lt;c:out value="${contact.firstname}" /&gt;*. Use an input like *&lt;script&gt;alert(&#x27;Hello&#x27;)&lt;/script&gt;* to see the difference.

**Requirements:** Apache Tomcat, Webbrowser

###Ch04_OutputEscapingJSF
Web application using Java Server Faces (JSF) to show the two different possibilities to show user input in a web page with *#{contact.firstname}* and *&lt;h:outputText value="#{contact.firstname}" /&gt;*. Use an input like *&lt;script&gt;alert(&#x27;Hello&#x27;)&lt;/script&gt;* to see the difference.

**Requirements:** Apache Tomcat, Webbrowser

###Ch05_AccessReferenceMaps
Web application using Java Server Faces (JSF) to show the difference between using unprotected and protected Maps (with *IntegerAccessReferenceMaps* and *RandomAccessReferenceMaps*) with user data.

**Requirements:** Apache Tomcat, Webbrowser

###Ch05_SessionHandling

###Ch06_SQLInjection

###Ch06_XPathInjection

###Ch07_XSS

###Ch07_XSSJSF
Web application showing Cross-Site Scripting (XSS) with Java Server Faces.

**Requirements:** Apache Tomcat, Webbrowser

###Ch07_XSSFilter

###Ch07_XSSJSF

###Ch08_CSRF
Web application showing Cross-Site Request Forgery (CSRF) with GET and POST requests and how to protect forms with an anti CSRF token.

**Requirements:** Apache Tomcat, Webbrowser
