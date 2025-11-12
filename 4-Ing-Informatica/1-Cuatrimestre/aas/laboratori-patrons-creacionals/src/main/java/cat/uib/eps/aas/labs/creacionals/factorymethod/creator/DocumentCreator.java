package cat.uib.eps.aas.labs.creacionals.factorymethod.creator;

import cat.uib.eps.aas.labs.creacionals.factorymethod.document.Document;

public abstract class DocumentCreator {

    public abstract Document createDocumentWithContent(String content);
}
