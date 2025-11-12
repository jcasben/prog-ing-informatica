package cat.uib.eps.aas.labs.creacionals.builder.report;

import java.util.List;

public class Report {

    private final String title;

    private final Header header;

    private final List<Section> sections;

    private final Footer footer;


    public Report(String title, Header header, List<Section> sections, Footer footer) {
        this.title = title;
        this.header = header;
        this.sections = sections;
        this.footer = footer;
    }

    public String getTitle() {
        return title;
    }

}
