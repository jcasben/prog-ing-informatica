package cat.uib.eps.aas.labs.estructurals.chain;

import cat.uib.eps.aas.labs.estructurals.chain.validator.*;

public class Main {
    public static void main(String[] args) {
        Document validDocument = new Document("Report", "Some Author", 1997, "Some content", "pdf");
        Document invalidDocument = new Document("Report", "Some Author", -1, "Some content", "pdf");

        Validator title = new TitleValidator();
        Validator author = new AuthorValidator();
        Validator year = new YearValidator();
        Validator content = new ContentValidator();
        Validator format = new FormValidator();

        title.setNext(author);
        author.setNext(year);
        year.setNext(content);
        content.setNext(format);

        Validator validator = title;

        boolean result = validator.validate(validDocument);
        System.out.println("Document validation result: " + result);

        result = validator.validate(invalidDocument);
        System.out.println("Document validation result: " + result);
    }
}
