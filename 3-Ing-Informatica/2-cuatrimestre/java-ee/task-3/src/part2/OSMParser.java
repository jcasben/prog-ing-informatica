package src.part2;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import src.part2.models.OSMNode;
import src.part2.models.OSMWay;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Parser for a OSM XML file.
 *
 * @author jcasben
 */
public class OSMParser extends DefaultHandler {
    private Map<Long, OSMNode> nodes;
    private List<OSMWay> ways;
    private List<Long> currentWayRefs;
    private long currentWayId;
    private String currentWayType;

    public List<OSMWay> getWays() {
        return ways;
    }

    public Map<Long, OSMNode> getNodes() {
        return nodes;
    }

    /**
     * Creates a new {@link SAXParser} and parses the file on the given path.
     *
     * @param path file to parse.
     */
    public void parse(String path) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(new File(path), this);
    }

    @Override
    public void startDocument() {
        nodes = new HashMap<>();
        ways = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals("node")) {
            long id = Long.parseLong(attributes.getValue("id"));
            double lat = Double.parseDouble(attributes.getValue("lat"));
            double lon = Double.parseDouble(attributes.getValue("lon"));
            nodes.put(id, new OSMNode(id, lat, lon));
        } else if (qName.equals("way")) {
            currentWayId = Long.parseLong(attributes.getValue("id"));
            currentWayRefs = new ArrayList<>();
            currentWayType = "unknown";
        } else if (qName.equals("nd")) {
            long ref = Long.parseLong(attributes.getValue("ref"));
            if (currentWayRefs != null)
                currentWayRefs.add(ref);
        } else if (qName.equals("tag") && currentWayRefs != null) {
            String key = attributes.getValue("k");
            String value = attributes.getValue("v");
            if (key.equals("highway") && value.equals("footway"))
                currentWayType = value;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("way") && currentWayType.equals("footway")) {
            ways.add(new OSMWay(currentWayId, currentWayType, currentWayRefs));
        }
    }
}
