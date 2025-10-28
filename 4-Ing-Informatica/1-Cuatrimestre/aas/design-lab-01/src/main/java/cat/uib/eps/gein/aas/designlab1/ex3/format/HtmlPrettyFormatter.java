package cat.uib.eps.gein.aas.designlab1.ex3.format;

public class HtmlPrettyFormatter implements FormatStrategy {

    @Override
    public String format(String data) {
        return "<html>\n" + data + "\n</html>";
    }
}
