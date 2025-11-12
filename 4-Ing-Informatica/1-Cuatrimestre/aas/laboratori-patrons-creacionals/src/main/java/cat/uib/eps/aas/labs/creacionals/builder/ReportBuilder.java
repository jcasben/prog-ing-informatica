package cat.uib.eps.aas.labs.creacionals.builder;

import cat.uib.eps.aas.labs.creacionals.builder.report.Alignment;
import cat.uib.eps.aas.labs.creacionals.builder.report.Report;
import cat.uib.eps.aas.labs.creacionals.builder.report.Section;

public interface ReportBuilder {
    ReportBuilder setTitle(String title);
    ReportBuilder setHeader(String author, String title);
    ReportBuilder addSection(Section section);
    ReportBuilder addFooter(int pageNumber, Alignment alignment);
    Report build();
}
