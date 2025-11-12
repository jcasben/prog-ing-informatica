package cat.uib.eps.aas.labs.creacionals.factorymethod;

import cat.uib.eps.aas.labs.creacionals.factorymethod.creator.MarkdownDocumentCreator;
import cat.uib.eps.aas.labs.creacionals.factorymethod.creator.TextDocumentCreator;
import cat.uib.eps.aas.labs.creacionals.factorymethod.document.Document;

public class Client {

    public static void main(String[] args) {
        Document textDocument = new TextDocumentCreator().createDocumentWithContent("Hello, world!");
        System.out.println("Created document with content: " + textDocument.getContent());

        Document markdownDocument = new MarkdownDocumentCreator().createDocumentWithContent("# Hello, world!");
        System.out.println("Created document with content: " + markdownDocument.getContent());
    }
}
