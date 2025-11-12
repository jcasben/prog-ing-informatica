package cat.uib.eps.aas.labs.creacionals.abstractfactory;

import cat.uib.eps.aas.labs.creacionals.abstractfactory.products.IButton;
import cat.uib.eps.aas.labs.creacionals.abstractfactory.products.ITextField;
import cat.uib.eps.aas.labs.creacionals.abstractfactory.products.IWindow;
import cat.uib.eps.aas.labs.creacionals.abstractfactory.windows.WindowsButton;
import cat.uib.eps.aas.labs.creacionals.abstractfactory.windows.WindowsTextField;
import cat.uib.eps.aas.labs.creacionals.abstractfactory.windows.WindowsWindow;

public class WindowsFactory implements GUIFactory {

    @Override
    public IButton createButton(String text) {
        return new WindowsButton(text);
    }

    @Override
    public IWindow createWindow(String title) {
        return new WindowsWindow(title);
    }

    @Override
    public ITextField createTextField(String label) {
        return new WindowsTextField(label);
    }
}
