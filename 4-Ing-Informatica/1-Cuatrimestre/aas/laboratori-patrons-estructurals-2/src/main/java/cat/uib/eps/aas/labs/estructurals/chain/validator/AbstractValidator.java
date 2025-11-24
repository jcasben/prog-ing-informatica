package cat.uib.eps.aas.labs.estructurals.chain.validator;

import cat.uib.eps.aas.labs.estructurals.chain.Document;

public abstract class AbstractValidator implements Validator {

    protected Validator next;

    @Override
    public void setNext(Validator next) {
        this.next = next;
    }

    public boolean validateNext(Document document) {
        if (next == null) return true;
        return next.validate(document);
    }
}
