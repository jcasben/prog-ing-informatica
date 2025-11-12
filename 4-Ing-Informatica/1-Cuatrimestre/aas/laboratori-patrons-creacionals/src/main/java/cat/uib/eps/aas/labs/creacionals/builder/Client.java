package cat.uib.eps.aas.labs.creacionals.builder;

import cat.uib.eps.aas.labs.creacionals.builder.report.Alignment;
import cat.uib.eps.aas.labs.creacionals.builder.report.Report;

public class Client {

    public static void main(String[] args) {
        Report report = new ReportBuilderImpl()
                .setTitle("My new report builder")
                .setHeader("Jesus Castillo", "My new report builder")
                .addSection(
                        new SectionBuilderImpl()
                            .setTitle("First section with builder")
                            .setBody("This is the body of the first section")
                            .addTable(3, 3)
                            .build()
                )
                .addSection(
                        new SectionBuilderImpl()
                            .setTitle("Second section with builder")
                            .setBody("This is the body of the second section")
                            .addFigure("An important figure in the second section")
                            .addTable(4, 2)
                            .addTable(1, 2)
                            .build()
                )
                .addFooter(1, Alignment.CENTER)
                .build();

        System.out.println("Built a report with title: " + report.getTitle());
    }
}
