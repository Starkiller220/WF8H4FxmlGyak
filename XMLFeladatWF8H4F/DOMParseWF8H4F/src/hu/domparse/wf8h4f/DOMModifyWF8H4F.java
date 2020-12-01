package hu.domparse.wf8h4f;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class DOMModifyWF8H4F {

	public static void main(String[] args) {
		
		try {
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(new File("src/XMLWF8H4F.xml"));
	        doc.getDocumentElement().normalize();
	        
	        Node root = doc.getDocumentElement();
	        
	        search(root, "videogame", "name", "xd", "1");
	        
	        
	        modifyDocument(doc, new File("src/XMLWF8H4F.xml"));
	        
			
		}catch (Exception e) {
	         e.printStackTrace();
	      }	
	
	}
	
	public static void search(Node root, String parent, String child, String replace, String id) {
		
		
		NodeList list = root.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) node;
               if (parent.equals(eElement.getNodeName()) && eElement.getAttributes().getNamedItem(parent+"_id").getNodeValue().equals(id)) {
                 Element foundElement = (Element) eElement.getElementsByTagName(child).item(0);
                 foundElement.setTextContent(replace);
               }
            }
         }
		
	}
	
	public static void modifyDocument(Document document, File xmlFile) throws TransformerException
	{
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(xmlFile);

		transformer.transform(source, new StreamResult(System.out));
		transformer.transform(source, result);
	}

}
