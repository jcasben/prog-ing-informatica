package cat.uib.eps.gein.aas.designlab1.ex3;

import cat.uib.eps.gein.aas.designlab1.ex3.format.FormatStrategy;

class ReportService {
    private DataStore dataStore;
    private FormatStrategy formatStrategy;

    public ReportService(
            FormatStrategy formatStrategy,
            DataStore dataStore
    ) {
        this.formatStrategy = formatStrategy;
        this.dataStore = dataStore;
    }

    public String generateReport(String dataKey, String reportKey) {
        String data = dataStore.read(dataKey);
        String result = formatStrategy.format(data);
        dataStore.save(reportKey, result);

        return result;
    }
}

