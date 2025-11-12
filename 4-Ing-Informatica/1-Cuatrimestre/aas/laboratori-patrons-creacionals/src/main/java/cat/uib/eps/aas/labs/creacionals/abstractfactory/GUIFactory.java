package cat.uib.eps.aas.labs.creacionals.abstractfactory;

import cat.uib.eps.aas.labs.creacionals.abstractfactory.products.IButton;
import cat.uib.eps.aas.labs.creacionals.abstractfactory.products.ITextField;
import cat.uib.eps.aas.labs.creacionals.abstractfactory.products.IWindow;

public interface GUIFactory {
    IButton createButton(String text);
    IWindow createWindow(String title);
    ITextField createTextField(String label);
}
