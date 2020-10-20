import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxWF8H4F {

   public static void main(String argv[]) {

    try {

	SAXParserFactory factory = SAXParserFactory.newInstance();
	SAXParser saxParser = factory.newSAXParser();

	DefaultHandler handler = new DefaultHandler() {

	boolean person = false;
	boolean name = false;
	boolean age = false;
	boolean city = false;

	public void startElement(String uri, String localName,String qName,
                Attributes attributes) throws SAXException {

		String id = attributes.getValue("id") != null ? " {id:"+attributes.getValue("id")+"}" : "";
		
		System.out.println(qName + id + " start");

		if (qName.equalsIgnoreCase("nev")) {
			name = true;
		}

		if (qName.equalsIgnoreCase("kor")) {
			age = true;
		}

		if (qName.equalsIgnoreCase("varos")) {
			city = true;
		}

	}

	public void endElement(String uri, String localName,
		String qName) throws SAXException {

		System.out.println(qName+ " end");

	}

	public void characters(char ch[], int start, int length) throws SAXException {

		if (name) {
			System.out.println(new String(ch, start, length));
			name = false;
		}

		if (age) {
			System.out.println(new String(ch, start, length));
			age = false;
		}

		if (city) {
			System.out.println(new String(ch, start, length));
			city = false;
		}
		
		if (person) {
			System.out.println(new String(ch, start, length));
			person = false;
		}


	}

     };

       saxParser.parse("./src/szemelyek.xml", handler);

     } catch (Exception e) {
       e.printStackTrace();
     }

   }

}