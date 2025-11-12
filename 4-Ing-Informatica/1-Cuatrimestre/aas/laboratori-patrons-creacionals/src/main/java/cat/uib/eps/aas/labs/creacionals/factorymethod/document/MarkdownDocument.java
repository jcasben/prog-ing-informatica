package cat.uib.eps.aas.labs.creacionals.factorymethod.document;

public class MarkdownDocument extends Document {

    public MarkdownDocument(String content) {
        super(content);
    }

    public String getContent() {
        return content;
    }
}
