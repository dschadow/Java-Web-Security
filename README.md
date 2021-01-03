Java-Web-Security
==============

This repository contains the complete code samples from my book **Java-Web-Security - Sichere Webanwendungen mit Java entwickeln** (dpunkt.verlag, ISBN 978-3-86490-146-1), available as [Print](http://www.dpunkt.de/buecher/4198/java-web-security.html), as [PDF/EPub](http://www.dpunkt.de/buecher/4825/java-web-security.html) and for [Kindle](http://www.amazon.de/gp/product/B00IUJM3J4/ref=as_li_qf_sp_asin_tl?ie=UTF8&camp=1638&creative=6742&creativeASIN=B00IUJM3J4&linkCode=as2&tag=dominikswelt).

All Java projects are created as **Maven** projects and require [Java 8](http://www.oracle.com/technetwork/java) and [Apache Maven 3](http://maven.apache.org) or newer. In **Eclipse** you therefore need to install the Maven integration via the Eclipse update manager. After that, you can either use the **git m2e connector (m2e-egit)** to import the new projects directly from the repository. Alternatively, you can clone the repository and use **Import Maven Projects** instead (no connector required here). **IntelliJ IDEA** supports this out of the box.

**Mozilla Firefox** is the recommended and up until today working browser for all vulnerable web applications in this repository. Keep in mind that browsers or some add-ons may block or filter certain attacks already. Deactivate all blocking or intercepting add-ons or try a different browser if a sample application is not working.

The easiest way to start a web application is to use the **Maven-Tomcat7-Plug-in** in each project: **mvn tomcat7:run-war** (or simply **mvn** in the console, since this is the default goal). Open your browser and point it to **http://localhost:8080/PROJECT_NAME**, e.g. **http://localhost:8080/Ch04_OutputEscaping**. The project name is always the final part of the URL.

See the following subsections for a short description and the requirements to execute the sample code and launch the web application.

## Ch04_OutputEscaping
Web application using JavaServer Pages (JSP) to show the difference between output escaping via Enterprise Security API (ESAPI) and no output escaping at all. Use an input like *&lt;script&gt;alert(&#x27;XSS&#x27;)&lt;/script&gt;* to examine the difference.

**Requirements:** Apache Tomcat, Webbrowser

## Ch04_OutputEscapingJSF
Web application using JavaServer Faces (JSF) to demonstrate the two different possibilities to show user input in a web page with *#{contact.firstname}* and *&lt;h:outputText value="#{contact.firstname}" /&gt;*. Use an input like *&lt;script&gt;alert(&#x27;XSS&#x27;)&lt;/script&gt;* to examine the difference.

**Requirements:** Apache Tomcat, Webbrowser

## Ch04_OutputEscapingJSP
Spring based web application using JavaServer Pages (JSP) to demonstrate the two different possibilities to show user input in a web page with *${contact.firstname}* and *&lt;c:out value="${contact.firstname}" /&gt;*. Use an input like *&lt;script&gt;alert(&#x27;XSS&#x27;)&lt;/script&gt;* to examine the difference.

**Requirements:** Apache Tomcat, Webbrowser

## Ch05_AccessReferenceMaps
Web application using JavaServer Faces (JSF) to show the difference between using unprotected and protected Maps (with *IntegerAccessReferenceMaps* and *RandomAccessReferenceMaps*) with user data.

**Requirements:** Apache Tomcat, Webbrowser

## Ch05_HSTS
Web application using a Servlet filter to add the *Strict-Transport-Security* header to each response.

**Requirements:** Apache Tomcat, Webbrowser

## Ch05_SessionFixation
Web application invalidating an existing session and its session id before continuing the login process. This web application requires the included special *context.xml* configuration for Tomcat in order to display the current session id via JavaScript.

**Requirements:** Apache Tomcat, Webbrowser

## Ch05_SessionHandling
Web application containing a complete *web.xml* configuration showing how to protect cookies and other session data. Contains only a start page which fails trying to show the session cookie in a JavaScript popup.

**Requirements:** Apache Tomcat, Webbrowser

## Ch06_SQLInjection
Web application using user input to query a in-memory-database. The entered data is used as part of a normal *Statement*, an *escaped Statement*, a *Prepared Statement* and as input for a *Hibernate Query Language*.

**Requirements:** Apache Tomcat, Webbrowser

## Ch06_XPathInjection
Web application using user input to query a simple XML document. The entered data (name and password) is used as part of a normal *XPath expression* without any escaping and escaped as part of another *XPath expression*. The unescaped version is prune to XPath Injection, which makes it possible to retrieve more data of the XML document as the intended order limit.

**Requirements:** Apache Tomcat, Webbrowser

## Ch07_CSP
Web application with three input processing servlets. One is unprotected and processes any input without input validation or output escaping and is prone to Cross-Site Scripting. The second servlet adds a minimal *Content-Security-Policy* header to the response and allows to use any source from the same page (URL). This already protects the response page from Cross-Site Scripting in supported browsers. The third form adds a *Content-Security-Policy-Report-Only* header and shows how easy the reported data can be processed.

**Requirements:** Apache Tomcat, Webbrowser

## Ch07_XSS
Web application to test the three XSS types *stored*, *reflected* and *DOM based*. The input textfield is vulnerable to XSS and can be easily protected by enabling output escaping. Cookie could be protected by removing the special *context.xml* and by setting the corresponding *web.xml* parameter.

This web application requires the included special *context.xml* configuration for Tomcat in order to display the current session id via JavaScript.

**Requirements:** Apache Tomcat, Webbrowser

## Ch07_XSSFilter
Web application showing the differences between a blacklist and an ESAPI based request filtering. Use an input like *&lt;script&gt;alert(&#x27;XSS&#x27;)&lt;/script&gt;* to see the different output on the output pages.

**Requirements:** Apache Tomcat, Webbrowser

## Ch07_XSSJSF
JavaServer Faces (JSF) based web application accepting user input in two forms. The first form results into an output page showing the user input in drop down boxes and output text fields with all default attributes active. The second form results into an output page using the same output fields with any additional security related attribute set to the maximum. Use an input like *&lt;script&gt;alert(&#x27;XSS&#x27;)&lt;/script&gt;* to challenge the JSF XSS protection.

**Requirements:** Apache Tomcat, Webbrowser

## Ch08_CSRF
Web application showing Cross-Site Request Forgery (CSRF) with GET and POST requests and how to protect forms with an anti CSRF token. All requests on the **Unprotected Requests** page are successful and reach the backend as a normal request. The **Protected Requests** page contains successful (with token) and unsuccessful (without token) requests; only the valid requests are processed in the backend.

**Requirements:** Apache Tomcat, Webbrowser

## Meta
![Build](https://github.com/dschadow/Java-Web-Security/workflows/Build/badge.svg) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
