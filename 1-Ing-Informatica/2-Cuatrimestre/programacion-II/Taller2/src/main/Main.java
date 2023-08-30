package main;

import main.window.MainWindow;

import java.awt.*;

/**
 * @author jcasben
 */
public class Main {
    public static void main(String[] args) {
        /*
         * No invoca a la ventana hasta que no está lista para ser ejecutada.
         */
        EventQueue.invokeLater(MainWindow::new);
    }
}