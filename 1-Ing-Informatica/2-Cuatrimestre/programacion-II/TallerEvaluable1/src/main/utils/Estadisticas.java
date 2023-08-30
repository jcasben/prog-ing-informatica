package main.utils;

import main.elements.Jugador;
import main.io.LecturaObjetos;

/**
 * Clase encargada de calcular y mostrar por consola las estadisticas del fichero indicado.
 * @author jcasben
 */
public class Estadisticas {
    private final String RUTAFICHERO;
    private final String NOMBREFICHERO;
    private final int NJUGADORES;
    private final Jugador [] JUGADORES;
    private int trofeosTotales;
    private double mediaTrofeos;

    /**
     * Inicializa las variables de clase.
     * @param rutaFichero ruta del fichero.
     */
    public Estadisticas(String rutaFichero) {
        this.RUTAFICHERO = rutaFichero;
        String [] partes = rutaFichero.split("/");
        NOMBREFICHERO = partes[partes.length - 1];
        NJUGADORES = contarElementos();
        JUGADORES = getElementos();
    }

    /**
     * Muestra por pantalla las estadisticas del fichero seleccionado.
     */
    public void generarEstadisticas() {
        sumatorioTrofeos();
        mediaTrofeos();
        System.out.printf("""
                            \n
                            *======================================================================*
                            *\tNOMBRE DEL FICHERO: %s
                            *\tCANTIDAD DE JUGADORES: %s
                            *\tSUMATORIO DE LOS TROFEOS: %s
                            *\tMEDIA DE TROFEOS POR JUGADOR: %.3f
                            *\tDESVIACIÓN ESTÁNDAR: %.3f
                            """, NOMBREFICHERO, NJUGADORES, trofeosTotales, mediaTrofeos, desviacionStand());
        top3();
        System.out.printf("""
                            *-------------------------------- TOP 3 -------------------------------*
                            *\tTOP 1: %s
                            *\tTOP 2: %s
                            *\tTOP 3: %s
                            *======================================================================*
                            """, JUGADORES[0], JUGADORES[1], JUGADORES[2]);
    }


    /**
     * Cuenta los elementos del fichero indicado.
     * @return número de elementos del fichero.
     */
    private int contarElementos() {
        int i = 0;
        try(LecturaObjetos lector = new LecturaObjetos(RUTAFICHERO)) {
            Jugador tmp = lector.leer();
            while(!tmp.esCentinela()) {
                i++;
                tmp = lector.leer();
            }
        }
        return i;
    }

    /**
     * Guarda los elementos de un fichero en un array de tipo {@link Jugador}.
     * @return la array de tipo {@link Jugador}.
     */
    private Jugador[] getElementos() {
        Jugador [] array = new Jugador[NJUGADORES - 1];
        try(LecturaObjetos lector = new LecturaObjetos(RUTAFICHERO)) {
            Jugador tmp = lector.leer();
            for (int i = 0; i < NJUGADORES - 1; i++) {
                array[i] = tmp;
                tmp = lector.leer();
            }
        }
        return array;
    }

    /**
     * Realiza el sumatorio de los trofeos de los jugadores del fichero.
     */
    private void sumatorioTrofeos() {
        for(Jugador fe : JUGADORES) {
            trofeosTotales += fe.getTrofeos();
        }
    }

    /**
     * Calcula la media de trofeos por jugador.
     */
    private void mediaTrofeos() {
        mediaTrofeos = trofeosTotales/(double) NJUGADORES;
    }

    /**
     * Calcula la desviacion estandar.
     * @return el calculo realizado.
     */
    private double desviacionStand() {
        double sumatorio = 0.0;
        for (Jugador fe:
             JUGADORES) {
            sumatorio += Math.pow(fe.getTrofeos()-mediaTrofeos,2.0);
            sumatorio = Math.sqrt(sumatorio/ NJUGADORES);
        }
        return sumatorio;
    }

    /**
     * A través de una implementación del método de la burbuja para ordenar arrays, ordena de mayor a menor por sus
     * trofeos los elementos del array de jugadores.
     */
    private void top3() {
        Jugador tmp;
        for(int i = JUGADORES.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if(JUGADORES [j].getTrofeos() < JUGADORES [j + 1].getTrofeos()) {
                    tmp = JUGADORES[j];
                    JUGADORES[j] = JUGADORES[j + 1];
                    JUGADORES[j + 1] = tmp;
                }
            }
        }
    }
}
