package cat.uib.eps.aas.labs.estructurals.chain.validator;

import cat.uib.eps.aas.labs.estructurals.chain.Document;

public class ContentValidator extends AbstractValidator {

    @Override
    public boolean validate(Document document) {
        String content = document.content();

        if (content == null || content.isEmpty()) {
            System.out.println("Invalid content");
            return false;
        }

        return validateNext(document);
    }
}
