package cat.uib.eps.aas.labs.creacionals.builder;

import cat.uib.eps.aas.labs.creacionals.builder.report.Figure;
import cat.uib.eps.aas.labs.creacionals.builder.report.Section;
import cat.uib.eps.aas.labs.creacionals.builder.report.Table;

import java.util.ArrayList;
import java.util.List;

public class SectionBuilderImpl implements SectionBuilder {

    private String title;
    private String body;
    private List<Figure> figures = new ArrayList<>();
    private List<Table> tables = new ArrayList<>();


    @Override
    public SectionBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public SectionBuilder setBody(String body) {
        this.body = body;
        return this;
    }

    @Override
    public SectionBuilder addFigure(String caption) {
        this.figures.add(new Figure(caption));
        return this;
    }

    @Override
    public SectionBuilder addTable(int numRows, int numCols) {
        this.tables.add(new Table(numCols, numRows));
        return this;
    }

    @Override
    public Section build() {
        Section section = new Section(this.title, this.body, this.figures, this.tables);
        return section;
    }
}
