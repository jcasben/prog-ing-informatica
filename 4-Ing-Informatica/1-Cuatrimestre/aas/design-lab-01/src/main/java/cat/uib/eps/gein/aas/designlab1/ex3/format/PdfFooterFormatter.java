package cat.uib.eps.gein.aas.designlab1.ex3.format;

public class PdfFooterFormatter extends PdfFormatter {

    @Override
    public String format(String data) {
        return super.format(data) + "|F";
    }
}
