package cat.uib.eps.aas.labs.creacionals.builder;

import cat.uib.eps.aas.labs.creacionals.builder.report.*;

import java.util.ArrayList;
import java.util.List;

public class ReportBuilderImpl implements ReportBuilder {

    private String title;
    private Header header;
    private List<Section> sections = new ArrayList<>();
    private Footer footer;


    @Override
    public ReportBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public ReportBuilder setHeader(String author, String title) {
        this.header = new Header(author, title);
        return this;
    }

    @Override
    public ReportBuilder addSection(Section section) {
        this.sections.add(section);
        return this;
    }

    @Override
    public ReportBuilder addFooter(int pageNumber, Alignment alignment) {
        this.footer = new Footer(pageNumber, alignment);
        return this;
    }

    @Override
    public Report build() {
        return new Report(title, header, sections, footer);
    }
}
