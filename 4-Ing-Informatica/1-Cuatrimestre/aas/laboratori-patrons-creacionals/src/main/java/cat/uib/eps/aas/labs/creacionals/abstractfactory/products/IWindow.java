package cat.uib.eps.aas.labs.creacionals.abstractfactory.products;

import cat.uib.eps.aas.labs.creacionals.abstractfactory.Renderable;

public interface IWindow extends Renderable {
    void addButton(IButton button);
    void addTextField(ITextField textField);
    String getTitle();
}
