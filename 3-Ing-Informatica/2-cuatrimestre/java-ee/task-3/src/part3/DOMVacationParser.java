package src.part3;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Parses a XML file that stores data about vacations using the DOM Parser.
 *
 * @author jcasben
 */
public class DOMVacationParser {
    public static void main(String[] args) {
        if (args.length < 1) System.out.println("Usage: DOMVacationParser holidays.xml");
        else try {
            int totalDays = 0;
            File xmlFile = new File(args[0]);

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document xmlDocument = documentBuilder.parse(xmlFile);

            NodeList nodeList = xmlDocument.getElementsByTagName("vacation");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String year = element.getElementsByTagName("year").item(0).getTextContent();
                    String place = element.getElementsByTagName("place").item(0).getTextContent();
                    Element placeElement = (Element) element.getElementsByTagName("place").item(0);
                    String country = placeElement.getAttribute("country");
                    String duration = element.getElementsByTagName("duration").item(0).getTextContent();
                    totalDays += Integer.parseInt(duration);

                    System.out.println(year + ": " + place + " (" + country + ") - Duration: " + duration);
                }
            }
            System.out.println("\nTotal vacation days: " + totalDays);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
