package cat.uib.eps.aas.labs.creacionals.abstractfactory.linux;

import cat.uib.eps.aas.labs.creacionals.abstractfactory.RenderContext;
import cat.uib.eps.aas.labs.creacionals.abstractfactory.products.ITextField;

public class LinuxTextField implements ITextField {

    private final String label;
    private String content;

    public LinuxTextField(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public ITextField setContent(String content) {
        this.content = content;
        return this;
    }

    @Override
    public void render(RenderContext context) {
        System.out.println("Specific code for rendering the text field with label '" + label + "' and content '" + content + "' in Linux");
    }

}
