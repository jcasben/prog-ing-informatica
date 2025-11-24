package cat.uib.eps.aas.labs.estructurals.chain.validator;

import cat.uib.eps.aas.labs.estructurals.chain.Document;

public class AuthorValidator extends AbstractValidator {

    @Override
    public boolean validate(Document document) {
        String author = document.author();

        String[] parts = author.trim().split("\\s+");
        if (parts.length < 2) {
            System.out.println("Invalid author: must contain at least two words");
            return false;
        }

        for (String part : parts) {
            if (!part.matches("[A-Za-z]+")) {
                System.out.println("Invalid author: contains non-alphabetic characters");
                return false;
            }
        }

        return validateNext(document);
    }
}
