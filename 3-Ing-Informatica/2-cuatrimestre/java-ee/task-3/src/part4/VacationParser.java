package src.part4;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Parses an XML file which stores data about vacations.
 *
 * @author jcasben
 */
public class VacationParser extends DefaultHandler {
    private final StringBuffer year = new StringBuffer(10);
    private final StringBuffer place = new StringBuffer(50);
    private final StringBuffer duration = new StringBuffer(4);
    private String country;
    private int totalDays = 0;
    private boolean processingYear, processingPlace, processingDuration;

    private final Transformer transformer;
    private final StreamResult result;
    private final Document document;
    private final Element rootElement;

    public VacationParser() {
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
            result = new StreamResult("assets/outputs/vacations_output.xml");
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            rootElement = document.createElement("vacationsList");
            document.appendChild(rootElement);
        } catch (TransformerConfigurationException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) {
        switch (qName) {
            case "year" -> {
                processingYear = true;
                year.setLength(0);
            }
            case "place" -> {
                processingPlace = true;
                country = atts.getValue("country");
                place.setLength(0);
            }
            case "duration" -> {
                processingDuration = true;
                duration.setLength(0);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case "year" -> processingYear = false;
            case "place" -> processingPlace = false;
            case "duration" -> processingDuration = false;
            case "vacation" -> {
                place.append(" (");
                place.append(country);
                place.append(")");
                place.append(" - Duration: ");
                place.append(duration);

                totalDays += Integer.parseInt(duration.toString());

                Element vacationElement = document.createElement("vacation");
                Element yearElement = document.createElement("year");
                yearElement.appendChild(document.createTextNode(year.toString()));
                Element placeElement = document.createElement("place");
                placeElement.appendChild(document.createTextNode(place.toString()));
                vacationElement.appendChild(yearElement);
                vacationElement.appendChild(placeElement);
                rootElement.appendChild(vacationElement);
            }
        }
    }

    @Override
    public void endDocument() {
        Element totalDurationElement = document.createElement("totalDuration");
        totalDurationElement.appendChild(document.createTextNode(String.valueOf(totalDays)));
        rootElement.appendChild(totalDurationElement);
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (processingYear) {
            year.append(ch, start, length);
        } else if (processingPlace) {
            place.append(ch, start, length);
        } else if (processingDuration) {
            duration.append(ch, start, length);
        }
    }

    /**
     * Writes the generated XML document into the specified path.
     */
    public void writeXML() {
        try {
            transformer.transform(new DOMSource(document), result);
            System.out.println("XML written in assets/outputs/output_vacations.xml");
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}
