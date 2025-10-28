package cat.uib.eps.gein.aas.designlab1.ex3.format;

public class HtmlFormatter implements FormatStrategy {

    @Override
    public String format(String data) {
        return "<html>" + data + "</html>";
    }
}
