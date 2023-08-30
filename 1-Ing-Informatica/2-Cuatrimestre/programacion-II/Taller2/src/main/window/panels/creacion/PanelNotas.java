package main.window.panels.creacion;

import main.entidades.ReproductorSonidos;
import main.window.panels.PanelPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Contiene los botones que representan las notas para formar la melodía.
 * @author jcasben
 */
public class PanelNotas extends JPanel implements ActionListener {

    private PanelCreacion visualizacion;
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
     * Configura el panel donde estarán los botones que representan las notas.
     * @param visualizacion panel asociado donde se representarán las notas de la melodía.
     */
    public PanelNotas(PanelCreacion visualizacion) {
        this.visualizacion = visualizacion;
        setLayout(new GridLayout(1,8,4,0));
        inicializarBotones();
        agregarBotones();
    }

    /**
     * Inicializa los botones que representan las notas.
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

    /**
     * Captura el evento producido al hacer click en alguno de los botones y realiza la acción necesaria.
     * @param e evento a ser procesado
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String note = e.getActionCommand();

        if(!note.equals("FIN")) {
            new ReproductorSonidos(note.toLowerCase());
            visualizacion.agregarNota(note);
        }
    }
}
