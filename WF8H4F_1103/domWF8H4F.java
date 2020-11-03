import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class domWF8H4F {

   public static void main(String argv[]) {

    try {

    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser saxParser = factory.newSAXParser();

    DefaultHandler handler = new DefaultHandler() {

    boolean name = false;
    boolean kor = false;
    boolean varos = false;

    public void startElement(String uri, String localName,String qName,
                Attributes attributes) throws SAXException {


        if(qName=="szemelyek") System.out.println("Gyökér elem: "+ qName);

        if(attributes.getValue("id")!=null) {
            System.out.println("id: "+attributes.getValue("id"));
        }
        if (qName.equalsIgnoreCase("nev")) {
            name = true;
        }

        if (qName.equalsIgnoreCase("kor")) {
            kor = true;
        }

        if (qName.equalsIgnoreCase("varos")) {
            varos = true;
        }
    }

    public void characters(char ch[], int start, int length) throws SAXException {

        if (name) {
            System.out.println("név:"+new String(ch, start, length));
            name = false;
        }

        if (kor) {
            System.out.println("kor:"+new String(ch, start, length));
            kor = false;
        }

        if (varos) {
            System.out.println("város:"+new String(ch, start, length));
            varos = false;
        }



    }

     };

       saxParser.parse("./szemelyek.xml", handler);

     } catch (Exception e) {
       e.printStackTrace();
     }

   }

}