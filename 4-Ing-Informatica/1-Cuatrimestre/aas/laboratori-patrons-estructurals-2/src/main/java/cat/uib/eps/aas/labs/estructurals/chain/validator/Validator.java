package cat.uib.eps.aas.labs.estructurals.chain.validator;

import cat.uib.eps.aas.labs.estructurals.chain.Document;

public interface Validator {
    void setNext(Validator next);
    boolean validate(Document document);
}
