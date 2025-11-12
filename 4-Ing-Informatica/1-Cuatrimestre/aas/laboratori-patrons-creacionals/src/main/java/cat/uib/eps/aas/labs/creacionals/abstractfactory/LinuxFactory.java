package cat.uib.eps.aas.labs.creacionals.abstractfactory;

import cat.uib.eps.aas.labs.creacionals.abstractfactory.products.IButton;
import cat.uib.eps.aas.labs.creacionals.abstractfactory.products.ITextField;
import cat.uib.eps.aas.labs.creacionals.abstractfactory.products.IWindow;
import cat.uib.eps.aas.labs.creacionals.abstractfactory.linux.LinuxButton;
import cat.uib.eps.aas.labs.creacionals.abstractfactory.linux.LinuxTextField;
import cat.uib.eps.aas.labs.creacionals.abstractfactory.linux.LinuxWindow;

public class LinuxFactory implements GUIFactory {

    @Override
    public IButton createButton(String text) {
        return new LinuxButton(text);
    }

    @Override
    public IWindow createWindow(String title) {
        return new LinuxWindow(title);
    }

    @Override
    public ITextField createTextField(String label) {
        return new LinuxTextField(label);
    }
}
