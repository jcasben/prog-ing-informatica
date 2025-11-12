package cat.uib.eps.aas.labs.creacionals.builder.report;

import java.util.List;

public class Section {

    private final String title;

    private final String body;

    private final List<Figure> figures;

    private final List<Table> tables;

    public Section(String title, String body, List<Figure> figures, List<Table> tables) {
        this.title = title;
        this.body = body;
        this.figures = figures;
        this.tables = tables;
    }

}
