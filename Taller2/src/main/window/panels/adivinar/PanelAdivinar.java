package main.window.panels.adivinar;

import main.entidades.Notas;
import main.entidades.ReproductorSonidos;
import main.window.panels.PanelPrincipal;

import javax.swing.*;
import java.awt.*;

/**
 * Panel encargado de mostrar la interfaz de la sección adivinar.
 * @author jcasben
 */
public class PanelAdivinar extends JPanel {
    private JButton[] notas;
    private final Notas[] notasUsadas;
    private int index = 0;
    private final int limite;

    /**
     * Configura el panel
     * @param notas array de tipo {@link Notas} que contiene las notas usadas en la melodia original.
     * @param limite longitud de notas de la melodia.
     */
    public PanelAdivinar(Notas[] notas, int limite) {
        setLayout(new GridLayout(10,11,8,8));
        this.notasUsadas = notas;
        this.limite = limite;
        setBackground(Color.BLACK);
        inicializarGrid();
        agregarBotones();
        setVisible(true);
    }

    /**
     * Inicia el panel con botones de tal manera que se vea el panel vacío.
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
    }

    private void agregarBotones() {
        for (JButton jlbl : notas) {
            add(jlbl);
        }
    }

    /**
     * Compara la nota introducida por el usuario con la nota que debería estar en esa posición.
     * @param nota nota presionada por el usuario.
     */
    public void adivinarNota(String nota) {
        if(nota.equals(notasUsadas[index].name())) {
            notas[index++].setBackground(Notas.valueOf(nota).getColor());
            if(index == limite) {
                new ReproductorSonidos("campeones");
                JOptionPane.showMessageDialog(null, "¡¡LO HAS CONSEGUIDO!!");
                PanelPrincipal.getInstance().changeToHome();
            }
        } else {
            new ReproductorSonidos("error");
        }
    }
}
