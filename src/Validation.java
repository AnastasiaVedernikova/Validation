import org.xml.sax.SAXException;

//import javax.swing.text.Document;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;


import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;


import org.w3c.dom.Document;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Created by cs.ucu.edu.ua on 30.04.2017.
 */
public class Validation {
    public static void main(String args[]) {
        getInformation("my_xml.xml");

        boolean flag = true;
        try {
            validate("my_xml.xml", "my_xml.xsd");
        } catch(SAXException e) {
           flag = false;
           // e.printStackTrace();
        } catch(IOException i) {
           // i.printStackTrace();
            flag=false;
        }
        System.out.print("xml is valid: "+flag);
    }
    public static void validate(String XMLFile, String XSDFile) throws SAXException, IOException
    {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        ((schemaFactory.newSchema(new File(XSDFile))).newValidator()).validate(new StreamSource(new File(XMLFile)));

    }
    private static void getInformation (String filename){
        try{
            ArrayList<String> my_names = new ArrayList<String>();
            ArrayList<String> my_prices = new ArrayList<String>();
            ArrayList<String> my_calories = new ArrayList<String>();
            ArrayList<String> my_descriptions = new ArrayList<String>();

            File file = new File(filename);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);

            document.getDocumentElement ().normalize ();
//            System.out.println ("Root element of the doc is " +
//                    document.getDocumentElement().getNodeName());

            NodeList listOfFood = document.getElementsByTagName("FOOD");

            for(int s=0; s<listOfFood.getLength() ; s++) {
                Node firstFoodNode = listOfFood.item(s);
                if (firstFoodNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element firstFoodElement = (Element) firstFoodNode;
                    //names
                    NodeList NameList = firstFoodElement.getElementsByTagName("name");
                    Element NameElement = (Element)NameList.item(0);
                    NodeList textFNList = NameElement.getChildNodes();
//                    System.out.println("First Name : " +
//                            ((Node)textFNList.item(0)).getNodeValue().trim());
                            my_names.add(((Node)textFNList.item(0)).getNodeValue().trim());
                    //prices
                    NodeList PriceList = firstFoodElement.getElementsByTagName("price");
                    Element PriceElement = (Element)PriceList.item(0);
                    NodeList text1FNList = PriceElement.getChildNodes();
                    my_prices.add(((Node)text1FNList.item(0)).getNodeValue().trim());
                    //descriptions
                    NodeList DescriptionList = firstFoodElement.getElementsByTagName("description");
                    Element DescElement = (Element)DescriptionList.item(0);
                    NodeList text2FNList = DescElement.getChildNodes();
                    my_descriptions.add(((Node)text2FNList.item(0)).getNodeValue().trim());
                    //calories
                    NodeList CaloriesList = firstFoodElement.getElementsByTagName("calories");
                    Element CalElement = (Element)CaloriesList.item(0);
                    NodeList text3FNList = CalElement.getChildNodes();
                    my_calories.add(((Node)text3FNList.item(0)).getNodeValue().trim());

                }
            }

//            for (String i: my_calories) {
//                System.out.print(i);
//            }

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error reading configuration file:");
        }



    }
}
