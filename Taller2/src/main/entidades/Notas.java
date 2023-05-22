package main.entidades;

import java.awt.*;

/**
 * Enum que contiene todas las posibles notas
 * @author jcasben
 */
public enum Notas {
    DO(Color.RED),
    RE(Color.PINK),
    MI(Color.CYAN),
    FA(Color.YELLOW),
    SOL(Color.MAGENTA),
    LA(Color.WHITE),
    SI(Color.GREEN),
    FIN(Color.BLACK);

    private Color color;

    /**
     * Enum con parametros. Cada nota tiene un color asociado
     * @param color color asociado a la nota musical
     */
    Notas(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
