package WF8H4F_1110;

import java.io.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import org.xml.sax.*;

public class XpathParseWF8H4F {
    public static void main(String[] args)
            throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("WF8H4F_1110\\studentWF8H4F.xml"));
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();

        String expression = "/class/student";
        NodeList nodes = (NodeList) xpath.compile(expression).evaluate(doc, XPathConstants.NODESET);

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            Element element = (Element) node;

            System.out.println("\nCurrent element: " + node.getNodeName());
            System.out.println("Student roll no: " + element.getAttribute("rollno"));

            NodeList childNodes = element.getChildNodes();

            for (int j = 0; j < childNodes.getLength(); j++) {
                Node childNode = childNodes.item(j);

                if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                    System.out.println(childNode.getNodeName() + ": " + childNode.getTextContent());
                }
            }
        }
    }
}