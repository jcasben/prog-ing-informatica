package main.window.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Panel que cotiene el título del ejercicio.
 * @author jcasben
 */
public class TitlePanel extends JPanel {
    private JLabel jlabel;

    /**
     * Configura el panel para contener el título.
     */
    public TitlePanel() {
        setLayout(new BorderLayout());
        inicializarTitulo();
        add(jlabel);
        setVisible(true);
    }

    /**
     * Configura el {@link JLabel} que contiene el título del ejercicio.
     */
    private void inicializarTitulo() {
        jlabel = new JLabel("TALLER II - PROGRAMACIÓN II - CURSO 2022 - 2023");
        jlabel.setFont(new Font("calibri", Font.BOLD, 25));
        jlabel.setForeground(Color.WHITE);
        jlabel.setBackground(Color.BLACK);
        jlabel.setOpaque(true);
        jlabel.setHorizontalAlignment(0);
    }
}
