package cat.uib.eps.gein.aas.designlab1.ex3.format;

public class PdfFormatter implements FormatStrategy {

    @Override
    public String format(String data) {
        return "PDF:" + data;
    }
}
