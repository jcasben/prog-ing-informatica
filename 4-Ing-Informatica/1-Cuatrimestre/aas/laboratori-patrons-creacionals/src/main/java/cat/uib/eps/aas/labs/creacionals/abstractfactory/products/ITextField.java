package cat.uib.eps.aas.labs.creacionals.abstractfactory.products;

import cat.uib.eps.aas.labs.creacionals.abstractfactory.Renderable;

public interface ITextField extends Renderable {
    String getLabel();
    ITextField setContent(String content);
    String getContent();
}
