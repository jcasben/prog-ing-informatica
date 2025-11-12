package cat.uib.eps.aas.labs.creacionals.abstractfactory.windows;

import cat.uib.eps.aas.labs.creacionals.abstractfactory.RenderContext;
import cat.uib.eps.aas.labs.creacionals.abstractfactory.products.IButton;
import cat.uib.eps.aas.labs.creacionals.abstractfactory.products.ITextField;
import cat.uib.eps.aas.labs.creacionals.abstractfactory.products.IWindow;

import java.util.ArrayList;
import java.util.List;

public class WindowsWindow implements IWindow {

    private final String title;

    private final List<IButton> buttons = new ArrayList<>();

    private final List<ITextField> textFields = new ArrayList<>();

    public WindowsWindow(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void addButton(IButton button) {
        buttons.add(button);
    }

    @Override
    public void addTextField(ITextField textField) {
        textFields.add(textField);
    }

    @Override
    public void render(RenderContext context) {
        renderSelf(context);
        for(ITextField textField : textFields) {
            textField.render(context);
        }
        for(IButton button : buttons) {
            button.render(context);
        }
    }

    private void renderSelf(RenderContext context) {
        System.out.println("Specific code for rendering the window '" + title + "' in Windows");
    }

}
