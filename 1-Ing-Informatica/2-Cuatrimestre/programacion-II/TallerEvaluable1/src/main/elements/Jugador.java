package main.elements;

import java.io.Serializable;

/**
 * @author jcasben
 */
public class Jugador implements Serializable {
    private String nombre;
    private Equipo equipo;
    private int trofeos;

    public Jugador(String nombre, Equipo equipo, int trofeos) {
        this.nombre = nombre;
        this.equipo = equipo;
        this.trofeos = trofeos;
    }

    public static final Jugador CENTINELA = new Jugador("XXX",Equipo.COPAS,-1);

    public Jugador() {

    }

    public String getNombre() {
        return nombre;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public int getTrofeos() {
        return trofeos;
    }

    public boolean esCentinela() {
        return this.nombre.equals(CENTINELA.nombre);
    }

    @Override
    public String toString() {
        return "Jugador: (" +
                "Nombre= " + nombre +
                ", Equipo= " + equipo +
                ", Trofeos= " + trofeos +
                ')';
    }
}
