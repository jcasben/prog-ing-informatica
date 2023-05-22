package main.window.panels.reproduccion;

import javax.swing.*;
import java.awt.*;

/**
 * Panel que contiene el boton que avanza a la siguiente nota en la reproducción.
 * @author jcasben
 */
public class BotonReproduccion extends JPanel {
    /**
     * Crea y configura el botón que avanza a la siguiente nota.
     * @param panelReproduccion panel asociado donde se representará la reproducción.
     */
    public BotonReproduccion(PanelReproduccion panelReproduccion) {
        setLayout(new GridLayout(1,1));
        JButton siguiente = new JButton(">");
        siguiente.setFocusPainted(false);
        siguiente.setBackground(Color.WHITE);
        siguiente.addActionListener(e -> panelReproduccion.reproducir());
        siguiente.setFont(new Font("Calibri",Font.BOLD,18));
        add(siguiente);
    }
}
