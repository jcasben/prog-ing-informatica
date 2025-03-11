package src.part1;

import java.util.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class VacationParser extends DefaultHandler {
    private StringBuffer year = new StringBuffer(10);
    private StringBuffer place = new StringBuffer(50);
    private StringBuffer duration = new StringBuffer(4);
    private String country;
    private int totalDays = 0;
    private boolean processingYear, processingPlace, processingDuration;
    private TreeMap<Integer, String> vacations;

    public void printVacations() {
        for (Iterator<Map.Entry<Integer, String>> i = vacations.entrySet().iterator(); i.hasNext(); ) {
            Map.Entry<Integer, String> entry = i.next();
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("\nTotal days on vacation: " + totalDays);
    }

    public void startDocument() {
        vacations = new TreeMap<>();
    }

    public void startElement(String uri, String localName, String qName, Attributes atts) {
        if (qName.equals("year")) {
            processingYear = true;
            year.setLength(0);
        } else if (qName.equals("place")) {
            processingPlace = true;
            country = atts.getValue("country");
            place.setLength(0);
        } else if (qName.equals("duration")) {
            processingDuration = true;
            duration.setLength(0);
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("year")) {
            processingYear = false;
        } else if (qName.equals("place")) {
            processingPlace = false;
        } else if (qName.equals("duration")) {
            processingDuration = false;
        } else if (qName.equals("vacation")) {
            place.append(" (");
            place.append(country);
            place.append(")");
            place.append(" - Duration: ");
            place.append(duration);

            totalDays += Integer.parseInt(duration.toString());

            vacations.put(Integer.parseInt(year.toString()), place.toString());
        }
    }

    public void characters(char[] ch, int start, int length) {
        if (processingYear) {
            year.append(ch, start, length);
        } else if (processingPlace) {
            place.append(ch, start, length);
        } else if (processingDuration) {
            duration.append(ch, start, length);
        }
    }
}
