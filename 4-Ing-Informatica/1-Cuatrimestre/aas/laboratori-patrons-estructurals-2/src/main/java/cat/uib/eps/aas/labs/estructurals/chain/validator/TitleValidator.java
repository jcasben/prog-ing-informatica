package cat.uib.eps.aas.labs.estructurals.chain.validator;

import cat.uib.eps.aas.labs.estructurals.chain.Document;

public class TitleValidator extends AbstractValidator {

    @Override
    public boolean validate(Document document) {
        String title = document.title();

        if (title == null || !title.matches("[A-Za-z0-9]+")) {
            System.out.println("Invalid title");
            return false;
        }

        return validateNext(document);
    }
}
