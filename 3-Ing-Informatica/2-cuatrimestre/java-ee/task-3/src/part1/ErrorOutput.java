package src.part1;

import org.xml.sax.*;

public class ErrorOutput implements ErrorHandler {

    private String errorMessage(SAXParseException e) {
        return e.getSystemId() + "\nLine: " + e.getLineNumber() + " Column: " + e.getColumnNumber() + "\n" + e.getMessage();
    }

    public void warning(SAXParseException e) {
        System.out.println("Warning: " + errorMessage(e));
    }

    public void error(SAXParseException e) throws SAXException {
        throw new SAXException("Error: " + errorMessage(e));
    }

    public void fatalError(SAXParseException e) throws SAXException {
        throw new SAXException("Critical error: " + errorMessage(e));
    }
}
