package main.utils;

import main.elements.Equipo;
import main.elements.Jugador;
import main.io.LecturaObjetos;
import main.io.LecturaTexto;
import main.io.EscrituraObjetos;

import java.io.File;
import java.util.Random;

/**
 * Clase encargada de crear ficheros e intanciar objetos de la clase {@link Jugador} con datos aleatorios dentro de ellos.
 * @author jcasben
 */
public class CreadorArchivos {
    private final Random RNDM = new Random();
    private final int MAXTROFEOS = 250;
    private final int MAXJUGADORES = 200;
    private final int MAXNOMBRES = 510;
    private final String NOMBREFICHERO;
    private String MKDIRS = "";

    /**
     * Inicializa las variables de clase y crea una carpeta para cada archivo de jugadores nuevo con el nombre del
     * archivo.
     * @param nombreFichero ruta del fichero.
     */
    public CreadorArchivos(String nombreFichero) {
        this.NOMBREFICHERO = nombreFichero;
        String [] partes = nombreFichero.split("/");
        for (int i = 0; i<partes.length-1;i++) {
            MKDIRS = MKDIRS + partes[i] + "/";
        }
        new File(MKDIRS).mkdirs();
    }

    /**
     * Genera un numero aleatorio de objetos de la clase {@link Jugador} con datos aleatorios.
     */
    public void generarJugadores() {
        try (EscrituraObjetos escritor = new EscrituraObjetos(NOMBREFICHERO)) {
            for(int i = 0; i< RNDM.nextInt(MAXJUGADORES) + 1; i++) {
                escritor.escribir(new Jugador(generarNombre(),generarEquipo(),generarTrofeos()));
            }
            escritor.escribir(Jugador.CENTINELA);
        }
    }

    /**
     * Crea un fichero individual con el nombre de cada uno de los valores de {@link Equipo} y guarda en estos ficheros
     * los objetos del fichero de jugadores escogido que tengan ese valor.
     * Estos ficheros se guardan en la misma carpeta que el fichero de jugadores correspondiente.
     */
    public void separarEquipos() {
        EscrituraObjetos [] escritores = new EscrituraObjetos[Equipo.values().length];
        for(int i = 0; i<Equipo.values().length;i++) {
            escritores[i] = new EscrituraObjetos(MKDIRS + Equipo.values()[i].name() + ".dat");
        }

        try(LecturaObjetos lector = new LecturaObjetos(NOMBREFICHERO)) {
            Jugador tmp = lector.leer();
            while(!tmp.esCentinela()) {
                escritores[encontrarValor(tmp.getEquipo())].escribir(tmp);
                tmp = lector.leer();
            }
        }

        for (EscrituraObjetos eos : escritores) {
            eos.escribir(Jugador.CENTINELA);
            eos.close();
        }
    }

    /**
     * Genera un nombre aleatorio escogido del fichero nombres.txt
     * @return el nombre generado.
     */
    private String generarNombre() {
        String nombre = "";
        int limite = RNDM.nextInt(MAXNOMBRES) + 1;
        try(LecturaTexto lector = new LecturaTexto("resources/nombres.txt")) {
            for (int i = 0; i<= limite; i++){
                nombre = lector.leerLinea();
            }
        }
        return nombre;
    }

    /**
     * Genera un valor aleatorio del enum {@link Equipo}.
     * @return el valor generado.
     */
    private Equipo generarEquipo() {
        Equipo [] valores = Equipo.values();
        return valores[RNDM.nextInt(valores.length)];
    }

    /**
     * Genera un numero aleatorio de trofeos.
     * @return numero aleatorio entre 0 y 250.
     */
    private int generarTrofeos() {
        return RNDM.nextInt(MAXTROFEOS + 1);
    }

    /**
     * Dado un valor del enum {@link Equipo}, devuelve su indice dentro del enum.
     * @param equipo
     * @return
     * @author brouse13
     */
    private int encontrarValor(final Equipo equipo) {
        int index = 0;
        for (int i = 0; i < Equipo.values().length; i++) {
            if (Equipo.values()[i].equals(equipo)) {
                return index;
            }
            index++;
        }
        return -1;
    }
}
