package cat.uib.eps.aas.labs.estructurals.chain;

public record Document (
    String title,
    String author,
    int publicationYear,
    String content,
    String format
) {}
