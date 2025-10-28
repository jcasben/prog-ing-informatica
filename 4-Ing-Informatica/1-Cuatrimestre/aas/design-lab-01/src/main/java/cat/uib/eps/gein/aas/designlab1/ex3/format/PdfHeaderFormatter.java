package cat.uib.eps.gein.aas.designlab1.ex3.format;

public class PdfHeaderFormatter extends PdfFormatter {

    @Override
    public String format(String data) {
        return "H|" + super.format(data);
    }
}
