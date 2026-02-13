package cat.uib.eps.aas.labs.estructurals.chain.validator;

import cat.uib.eps.aas.labs.estructurals.chain.Document;

public class FormValidator extends AbstractValidator {

    @Override
    public boolean validate(Document document) {
        if (!ValidFormats.set().contains(document.format())) {
            System.out.println("Invalid format");
            return false;
        }

        return validateNext(document);
    }
}
