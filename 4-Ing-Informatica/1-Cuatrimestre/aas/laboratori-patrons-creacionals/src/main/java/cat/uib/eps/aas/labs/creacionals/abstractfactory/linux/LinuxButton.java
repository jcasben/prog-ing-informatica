package cat.uib.eps.aas.labs.creacionals.abstractfactory.linux;

import cat.uib.eps.aas.labs.creacionals.abstractfactory.RenderContext;
import cat.uib.eps.aas.labs.creacionals.abstractfactory.products.IButton;

public class LinuxButton implements IButton {

    private final String text;

    public LinuxButton(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void render(RenderContext context) {
        System.out.println("Specific code for rendering the button with text '" + text + "' in Linux");
    }

}
