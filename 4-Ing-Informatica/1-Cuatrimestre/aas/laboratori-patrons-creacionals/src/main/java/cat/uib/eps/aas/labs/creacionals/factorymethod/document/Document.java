package cat.uib.eps.aas.labs.creacionals.factorymethod.document;

public abstract class Document {

    protected String content;

    public Document(String content) {
        this.content = content;
    }

    public abstract String getContent();
}
