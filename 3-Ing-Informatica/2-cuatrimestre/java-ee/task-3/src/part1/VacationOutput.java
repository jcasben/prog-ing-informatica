package src.part1;

import org.xml.sax.*;

import javax.xml.parsers.*;

public class VacationOutput {
    public static void main(String[] args) {
        if (args.length < 1) System.out.println("usage: VacationOutput holidays.xml\n");
        else try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setValidating(false);
            SAXParser p = spf.newSAXParser();
            XMLReader parser = p.getXMLReader();
            parser.setErrorHandler(new ErrorOutput());
            VacationParser vacationParser = new VacationParser();
            parser.setContentHandler(vacationParser);
            parser.parse(args[0]);
            vacationParser.printVacations();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
