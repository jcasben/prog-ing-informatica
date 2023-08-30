package main.window.panels.adivinar;

import main.entidades.ReproductorSonidos;
import main.window.panels.PanelPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que contiene los botones asociados al apartado de adivinar de la aplicación.
 * @author jcasben
 */
public class PanelBotonesAdivinar extends JPanel implements ActionListener {
    private final PanelAdivinar adivinar;
    private final JButton[] buttons = new JButton[8];

    private final String[] names = {
            "DO",
            "RE",
            "MI",
            "FA",
            "SOL",
            "LA",
            "SI",
            "FIN"
    };

    private final Color[] colors = {
            Color.RED,
            Color.PINK,
            Color.CYAN,
            Color.YELLOW,
            Color.MAGENTA,
            Color.WHITE,
            Color.GREEN,
            Color.BLACK
    };

    /**
     * Contiene los botones que sirven para jugar a adivinar las notas de la melodía.
     * @param adivinar panel que muestra las notas adivinadas.
     */
    public PanelBotonesAdivinar(PanelAdivinar adivinar) {
        this.adivinar = adivinar;
        setLayout(new GridLayout(1,8,4,0));
        inicializarBotones();
        agregarBotones();
    }

    /**
     * Realiza la inicialización de los botones que representan las notas.
     */
    private void inicializarBotones() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(names[i]);
            buttons[i].setBackground(colors[i]);
            buttons[i].setFocusPainted(false);
            buttons[i].setFont(new Font("Calibri",Font.BOLD,18));
            buttons[i].addActionListener(this);
        }
        buttons[7].setForeground(Color.WHITE);
        buttons[7].addActionListener(e -> PanelPrincipal.getInstance().changeToHome());
    }

    private void agregarBotones() {
        for(JButton jb : buttons) {
            this.add(jb);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String note = e.getActionCommand();

        if(!note.equals("FIN")) {
            new ReproductorSonidos(note.toLowerCase());
            adivinar.adivinarNota(note);
        }
    }
}
