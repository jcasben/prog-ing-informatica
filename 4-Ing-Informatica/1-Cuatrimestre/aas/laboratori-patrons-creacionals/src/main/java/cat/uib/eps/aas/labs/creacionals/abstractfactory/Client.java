package cat.uib.eps.aas.labs.creacionals.abstractfactory;

import cat.uib.eps.aas.labs.creacionals.abstractfactory.products.IWindow;

public class Client {

    public static void main(String[] args) {
        System.out.println("=== Creating Windows GUI ===");
        createGUI(new WindowsFactory());
        
        System.out.println("\n=== Creating Linux GUI ===");
        createGUI(new LinuxFactory());
    }

    private static void createGUI(GUIFactory factory) {
        IWindow window = factory.createWindow("Login window");
        window.addTextField(factory.createTextField("User").setContent("rmassanet"));
        window.addTextField(factory.createTextField("Password").setContent("my-secret-password"));
        window.addButton(factory.createButton("Login"));
        window.addButton(factory.createButton("Cancel"));
        window.render(null);
    }

}
