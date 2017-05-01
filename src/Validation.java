import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

/**
 * Created by cs.ucu.edu.ua on 30.04.2017.
 */
public class Validation {
    public static void main(String args[]) {
        boolean flag = true;
        try {
            validate("my_xml.xml", "my_xml.xsd");

        } catch(SAXException e) {
           flag = false;
        } catch(IOException i) {
            flag=false;
        }
        System.out.print("xml is valid: "+flag);
    }
    public static void validate(String XMLFile, String XSDFile) throws SAXException, IOException
    {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        ((schemaFactory.newSchema(new File(XSDFile))).newValidator()).validate(new StreamSource(new File(XMLFile)));

    }
}
