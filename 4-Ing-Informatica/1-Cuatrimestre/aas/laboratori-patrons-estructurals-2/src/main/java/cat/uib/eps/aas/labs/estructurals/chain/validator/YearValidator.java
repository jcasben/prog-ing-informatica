package cat.uib.eps.aas.labs.estructurals.chain.validator;

import cat.uib.eps.aas.labs.estructurals.chain.Document;

public class YearValidator extends AbstractValidator {

    @Override
    public boolean validate(Document document) {
        if (document.publicationYear() <= 0) {
            System.out.println("Invalid publication year");
            return false;
        }

        return validateNext(document);
    }
}
