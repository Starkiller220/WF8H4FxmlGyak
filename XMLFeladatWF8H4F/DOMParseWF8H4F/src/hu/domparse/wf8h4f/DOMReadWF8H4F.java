package hu.domparse.wf8h4f;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;

public class DOMReadWF8H4F {

	public static void main(String[] args) {

	      try {
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(new File("src/XMLWF8H4F.xml"));
	         doc.getDocumentElement().normalize();
	         
	         printNode(doc.getDocumentElement(), "+");

	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }

	
	public static void printNode(Node rootNode, String spacer) {
		if (rootNode.getNodeType() == Node.ELEMENT_NODE) {
	    System.out.print(spacer + rootNode.getNodeName() + " -> " + (rootNode.hasAttributes()? printAttributes(rootNode)+"\n" : rootNode.getFirstChild().getNodeValue().replace("\n", "")+"\n"));
		
		}
	    NodeList nl = rootNode.getChildNodes();
	    for (int i = 0; i < nl.getLength(); i++)
	        printNode(nl.item(i), spacer + "   ");
	}
	
	public static String printAttributes(Node mainNode)
    {
        NamedNodeMap attributeList = mainNode.getAttributes();
        String attributes = " " + attributeList.item(0).getNodeName() + ":" +attributeList.item(0).getNodeValue();
        for(int i = 1; i < attributeList.getLength(); i++)
        {
            Node attribute = attributeList.item(i);
            attributes += (", " + attribute.getNodeName() + ":" +attribute.getNodeValue());
        }
        return attributes;
    }
	
	
}
