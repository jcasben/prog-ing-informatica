package cat.uib.eps.aas.labs.creacionals.builder;

import cat.uib.eps.aas.labs.creacionals.builder.report.Section;

public interface SectionBuilder {
    SectionBuilder setTitle(String title);
    SectionBuilder setBody(String body);
    SectionBuilder addFigure(String caption);
    SectionBuilder addTable(int numRows, int numCols);
    Section build();
}
