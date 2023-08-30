package main.window.panels.creacion;

import main.entidades.Notas;

import javax.swing.*;
import java.awt.*;

/**
 * Se mostrarán las notas que contenga la melodia creada por el usuario.
 * @author jcasben
 */
public class PanelCreacion extends JPanel {
    private JButton[] notas;
    private Notas [] usedNotes;
    private int index = 0;

    /**
     * Configura el panel para mostrarlo vacío, sin ninguna nota introducida.
     */
    public PanelCreacion() {
        setLayout(new GridLayout(10,11,8,8));
        setBackground(Color.BLACK);
        initializeGrid();
        agregarBotones();
        setVisible(true);
    }

    /**
     * Inicializa los botones que representarán las notas dentro del panel.
     */
    public void initializeGrid() {
        notas = new JButton[110];
        usedNotes = new Notas[110];
        for (int i = 0; i < notas.length; i++) {
            notas[i] = new JButton(" ");
            notas[i].setOpaque(true);
            notas[i].setFocusPainted(false);
            notas[i].setBorder(BorderFactory.createEmptyBorder());
            notas[i].setEnabled(false);
            notas[i].setBackground(Color.BLACK);
        }
    }
    
    private void agregarBotones() {
        for (JButton jlbl : notas) {
            add(jlbl);
        }
    }

    /**
     * Agrega una nota a la melodia. Si llega al máximo (110) le saldrá un panel de dialogo al usuario avisándolo.
     * @param note la nueva nota a ser introducida.
     */
    public void agregarNota(String note) {
        if(index == usedNotes.length) {
            JOptionPane.showMessageDialog(null, "HAS ALCANZADO EL LÍMITE DE NOTAS. PULSA FIN");
        } else {
            notas[index].setBackground(Notas.valueOf(note).getColor());
            usedNotes[index] = Notas.valueOf(note);
            index++;
        }
    }

    public Notas [] getUsedNotes() {
        return usedNotes;
    }

    public int getIndex() {
        return index;
    }
}
