package main.window;

import main.window.panels.PanelPrincipal;

import javax.swing.*;

/**
 * @author jcasben
 */
public class MainWindow extends JFrame {
    public MainWindow() {
        initFrame("TALLER II - PROGRAMACIÓN II",800,750);
    }

    /**
     * Configura los parámetros del {@link JFrame}.
     * @param title título.
     * @param width ancho.
     * @param height alto.
     */
    private void initFrame(String title, int width, int height) {
        setSize(width,height);
        setTitle(title);
        setResizable(false);
        setContentPane(PanelPrincipal.getInstance());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
