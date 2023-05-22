package main.window.panels.reproduccion;

import main.entidades.Notas;
import main.entidades.ReproductorSonidos;
import main.window.panels.PanelPrincipal;

import javax.swing.*;
import java.awt.*;

/**
 * Panel donde se mostrará la reproducción de las notas.
 * @author jcasben
 */
public class PanelReproduccion extends JPanel {
    private Notas [] usedNotes;
    private JButton [] notas;
    private final int limite;
    private int index = 0;

    /**
     * Configura el panel para poder representar las notas.
     * @param notas notas introducidas en la melodía creada.
     * @param limite longitud de la melodía.
     */
    public PanelReproduccion(Notas [] notas, int limite) {
        setLayout(new GridLayout(10,11,8,8));
        setBackground(Color.BLACK);
        this.usedNotes = notas;
        this.limite = limite;
        inicializarGrid();
    }

    /**
     * Inicializa los botones que represnetarán las notas. Se incian de color negro.
     */
    public void inicializarGrid() {
        notas = new JButton[110];
        for (int i = 0; i < notas.length; i++) {
            notas[i] = new JButton(" ");
            notas[i].setOpaque(true);
            notas[i].setFocusPainted(false);
            notas[i].setBorder(BorderFactory.createEmptyBorder());
            notas[i].setEnabled(false);
            notas[i].setBackground(Color.BLACK);
        }

        for (int i = 0; i < limite; i++) {
            notas[i].setBackground(usedNotes[i].getColor());
        }

        for (JButton nota : notas) {
            add(nota);
        }
    }

    /**
     * Muestra solo las notas que ya han sido reproducidas y reproduce su sonido.
     */
    public void reproducir() {
        for (int i = 0; i < notas.length; i++) {
            notas[i].setBackground(Color.BLACK);
        }

        for (int i = 0; i <= index; i++) {
            notas[i].setBackground(usedNotes[i].getColor());
        }

        new ReproductorSonidos(usedNotes[index].name().toLowerCase());
        index++;

        /*
         * Al reproducir todas las notas ya no deja reproducir más.
         */
        if(limite == index) PanelPrincipal.getInstance().changeEndReproduction();
    }
}
