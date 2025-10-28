package cat.uib.eps.gein.aas.designlab1.ex3;

import cat.uib.eps.gein.aas.designlab1.ex3.format.PdfHeaderFooterFormatter;

public class Runner {

    public static void main(String[] args) {
        VolatileDataStore dataStore = new VolatileDataStore();

        ReportService reportService = new ReportService(
                new PdfHeaderFooterFormatter(),
                dataStore
        );

        dataStore.save("data", "My test data");
        String report = reportService.generateReport("data", "lastReport");
        System.out.println(report);
    }

}
