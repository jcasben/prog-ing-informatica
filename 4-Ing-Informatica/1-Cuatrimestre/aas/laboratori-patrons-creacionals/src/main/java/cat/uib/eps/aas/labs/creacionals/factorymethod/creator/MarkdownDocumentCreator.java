package cat.uib.eps.aas.labs.creacionals.factorymethod.creator;

import cat.uib.eps.aas.labs.creacionals.factorymethod.document.Document;
import cat.uib.eps.aas.labs.creacionals.factorymethod.document.MarkdownDocument;

public class MarkdownDocumentCreator extends DocumentCreator {

    @Override
    public Document createDocumentWithContent(String content) {
        return new MarkdownDocument(content);
    }
}
