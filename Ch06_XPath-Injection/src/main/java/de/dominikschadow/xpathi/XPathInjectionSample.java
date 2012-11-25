package de.dominikschadow.xpathi;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XPathInjectionSample {
    private Document doc;

    public static void main(String[] args) {
        XPathInjectionSample xis = new XPathInjectionSample();
        xis.parseXML();
        // valid input
        xis.evaluateXPath("/customers/customer[@name='Maier' and @password='MaierPassword']/orderLimit");
        // invalid input
        xis.evaluateXPath("/customers/customer[@name='dummy' and @password='' or '1' = '1']/orderLimit");
        // Blind XPath Injection
        xis.evaluateXPath("/customers/customer[@name='dummy' and @password=''] | /* | /foo[bar='']/orderLimit");
    }

    private void evaluateXPath(String xpath) {
        System.out.println("XPath " + xpath);
        System.out.println("-----------------------");

        try {
            XPathExpression expression = XPathFactory.newInstance().newXPath().compile(xpath);
            Object result = expression.evaluate(doc, XPathConstants.NODESET);

            NodeList nodes = (NodeList) result;
            for (int i = 0; i < nodes.getLength(); i++) {
                System.out.println(nodes.item(i).getTextContent());
            }
        } catch (XPathExpressionException ex) {
            ex.printStackTrace();
        }
    }

    private void parseXML() {
        try {
            File xmlFile = new File("src/main/resources/customer.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList customer = doc.getElementsByTagName("customer");
            System.out.println("-----------------------");

            for (int id = 0; id < customer.getLength(); id++) {
                Element element = (Element) customer.item(id);
                System.out.println("ID: " + getAttributeValue("id", element));
                System.out.println("name: " + getAttributeValue("name", element));
                System.out.println("status: " + getTagValue("status", element));
                System.out.println("order limit: " + getTagValue("orderLimit", element));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String getTagValue(String tag, Element element) {
        Node elementNode = element.getElementsByTagName(tag).item(0).getChildNodes().item(0);

        return elementNode.getNodeValue();
    }

    private String getAttributeValue(String attribute, Element element) {
        Node attributeNode = element.getAttributes().getNamedItem(attribute);

        return attributeNode.getNodeValue();
    }
}
