package cat.uib.eps.aas.labs.creacionals.abstractfactory.windows;

import cat.uib.eps.aas.labs.creacionals.abstractfactory.RenderContext;
import cat.uib.eps.aas.labs.creacionals.abstractfactory.products.IButton;

public class WindowsButton implements IButton {

    private final String text;

    public WindowsButton(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void render(RenderContext context) {
        System.out.println("Specific code for rendering the button with text '" + text + "' in Windows");
    }

}
