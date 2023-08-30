package main.window.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Panel que contiene los botones de acción del menu inferior.
 * @author jcasben
 */
public class PanelActividades extends JPanel {
    private final JButton [] buttons = new JButton[4];
    private final String [] buttonsName = {"CREAR","REPRODUCIR","ADIVINAR","SALIR"};
    private boolean created = false;

    /**
     * Configura el panel para contener de manera adecuada los botones.
     */
    public PanelActividades() {
        setLayout(new GridLayout(1,4));
        inicializarBotones();
        agregarActionListers();
        agregarBotones();

        setVisible(true);
    }

    /**
     * Inicializa los botones de acción.
     */
    private void inicializarBotones() {
        for(int i = 0 ; i<buttons.length; i++) {
            buttons[i] = new JButton(buttonsName[i]);
            buttons[i].setFocusPainted(false);
            buttons[i].setBackground(Color.BLACK);
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setFont(new Font("Calibri",Font.BOLD,18));
        }
    }

    /**
     * Agrega {@link java.awt.event.ActionListener} a los botones de acción.
     */
    private void agregarActionListers() {
        buttons[0].addActionListener(e -> {
            PanelPrincipal.getInstance().changeToCreate();
            created = true;
        });
        buttons[1].addActionListener(e -> {
            if(created) PanelPrincipal.getInstance().changeToReproduce();
            else JOptionPane.showMessageDialog(null,"PRIMERO CREA UNA MELODIA POR FAVOR");
        });
        buttons[2].addActionListener(e -> {
            if(created) PanelPrincipal.getInstance().changeToGuess();
            else JOptionPane.showMessageDialog(null,"PRIMERO CREA UNA MELODIA POR FAVOR");
        });
        buttons[3].addActionListener(e -> System.exit(0));
    }

    private void agregarBotones() {
        for(JButton jb : buttons) {
            this.add(jb);
        }
    }
}
