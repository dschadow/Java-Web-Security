WebAppSecurity
==============

##Contents and Requirements
###Ch04_OutputEscaping (4.3)
Simple web application using JSPs to show the difference between doing output escaping via ESAPI and Apache Commons and not doing output escaping at all. Use an input like *&lt;script&gt;alert(&#x27;hello&#x27;)&lt;/script&gt;* to see the difference. Keep in mind that some browsers (like Google Chrome) provide some XSS protection and may filter your input (Firefox works fine at the moment).

**Requirements:** Apache Tomcat

###Ch04_OutputEscapingSpring (4.3)
Simple Spring based web application using JSPs to show the two different possibilities to show user input in a web page with *${contact.firstname}* and *&lt;c:out value="${contact.firstname}" /&gt;*. Use an input like *&lt;script&gt;alert(&#x27;Hello&#x27;)&lt;/script&gt;* to see the difference. Keep in mind that some browsers (like Google Chrome) provide some XSS protection and may filter your input (Firefox works fine at the moment).

**Requirements:** Apache Tomcat

###Ch05_AccessReferenceMaps (5.3)
Command line sample project. Both sample classes, *IntegerAccessReferenceMapSample* and *RandomAccessReferenceMapSample*, contain main() methods to start the sample.

###Ch06_SQL-Injection

###Ch06_XPath-Injection

###Ch07_XSS

###Ch07_XSS_JSF (7.4)
A simple web project to show XSS with  Java Server Faces.

**Requirements:** Apache Tomcat

###Ch07_XSS_Filter

###Ch08_CSRF
A simple web project to show CSRF with GET and POST requests and how to protect forms with an anti CSRF token.

**Requirements:** Apache Tomcat

##Setup
All Java projects are created as Apache Maven projects (required are Java 7 and Maven 3). In Eclipse you therefore need to install the Maven integration and the git m2e connector via the update manager before you can import them as new projects.