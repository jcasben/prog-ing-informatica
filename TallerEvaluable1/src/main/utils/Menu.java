package main.utils;

import main.elements.Equipo;

import javax.swing.*;
import java.io.File;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Clase encargada de mostrar el menu principal del programa.
 * @author jcasben
 */
public class Menu {
    private final Scanner IN = new Scanner(System.in);
    //La función quen tiene el Logger es la de mostrar avisos al usuario por consola, clasificados según la
    //importancia que tengan.
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private String [] nombreFicheros;

    /**
     * Metodo que se encarga de mostrar al menu y pedirle al usuario la opción que desea.
     */
    public void menuPrincipal() {
        boolean menuP = true;
        String opt;

        while(menuP) {
            JOptionPane.showMessageDialog(null,"Hola");
            System.out.printf("""
                    \n\n********************** MENU PRINCIPAL *********************
                    *\t1 - Crear fichero de jugadores                        *
                    *\t2 - Mostrar fichero de jugadores                      *
                    *\t3 - Estadisticas del fichero                          *
                    *\t4 - Separar los jugadores por equipos                 *
                    *\t5 - Mostrar los jugadores separados por equipos       *
                    *\t6 - Estadísticas                                      *
                    *\ts - Salir                                             *
                    ***********************************************************
                    """);
            opt = pedirOpcion("Elige la opción deseada: ",1).toLowerCase();

            switch (opt) {
                //Crear fichero de jugadores
                case "1":
                    String fichero = pedirOpcion("\nIntroduce el nombre del fichero: ",1);
                    new CreadorArchivos("resources/" + fichero + "/" + fichero + ".dat").generarJugadores();
                    break;
                //Estadisticas del fichero
                case "2":
                    if(mostrarDirectorios()) {
                        String ficheroImprimir = pedirOpcion("\nIntroduce el nombre del fichero que quieres mostrar " +
                                "por pantalla: ", 0);
                        new ImpresorFicheros("resources/" + ficheroImprimir + "/" + ficheroImprimir + ".dat")
                                .imprimir();
                    }
                    break;
                //Estadisticas del fichero
                case "3":
                    if(mostrarDirectorios()) {
                        String ficheroEstadisticas = pedirOpcion("\nIntroduce el nombre del fichero del cual quieres" +
                                " mostrar las estadisticas: ", 0);
                        new Estadisticas("resources/" + ficheroEstadisticas + "/" + ficheroEstadisticas + ".dat")
                                .generarEstadisticas();
                    }
                    break;
                //Separar los jugadores por equipos
                case "4":
                    if(mostrarDirectorios()) {
                        String ficheroSeparar = pedirOpcion("\nIntroduce el nombre del fichero sobre el cual quieres" +
                                " realizar la operación: ", 0);
                        new CreadorArchivos("resources/" + ficheroSeparar + "/" + ficheroSeparar + ".dat")
                                .separarEquipos();
                    }
                    break;
                //Mostrar los jugadores separados por equipos
                case "5":
                    if(mostrarDirectorios()) {
                        String ficheroImprimir2 = pedirOpcion("\nIntroduce el nombre del fichero sobre el cual " +
                                "quieres realizar la operación: ", 0);

                        for (int i = 0; i < Equipo.values().length; i++) {
                            System.out.println("\n-------------------- IMPRIMIENDO " + Equipo.values()[i]
                                    + ".dat --------------------\n");
                            new ImpresorFicheros("resources/" + ficheroImprimir2 + "/" +
                                    Equipo.values()[i].name() + ".dat").imprimir();
                        }
                    }
                    break;
                //Estadísticas
                case "6":
                    if(mostrarDirectorios()) {
                        String ficheroEstadisticas2 = pedirOpcion("\nIntroduce el nombre del fichero del cual " +
                                "quieres ver las estadisticas por equipos: ", 0);

                        for (int i = 0; i < Equipo.values().length; i++) {
                            System.out.println("\n\n              --- IMPRIMIENDO ESTADISTICAS DE " + Equipo.values()[i].name() +
                                    ".dat ---\n");
                            new Estadisticas("resources/" + ficheroEstadisticas2 + "/" +
                                    Equipo.values()[i].name() + ".dat").generarEstadisticas();
                        }
                    }
                    break;

                //Salir
                case "s":
                    menuP = false;
                    break;
                default:
                    logger.log(Level.INFO,"Opción inválida");
                    break;
            }
        }
    }

    /**
     * Método que le pide al usuario la entrada por teclado del nombre de un fichero.
     * @param frase la frase que verá el usuario cuando se le pida la entrada.
     * @param tipo si es 0, nos pedirá la entrada de un String y validará si coincide con alguno de los ficheros.
     *        existentes. Si es otro valor entero, simplemente pedirá la entrada de un String.
     * @return el String introducido por el ususario.
     */
    private String pedirOpcion(String frase, int tipo) {
        String fichero = "";
        boolean nombreCorrecto = false;
        if(tipo == 0) {
            while (!nombreCorrecto) {
                System.out.print(frase);
                fichero = IN.nextLine();
                for (String nombreFichero : nombreFicheros) {
                    if (nombreFichero.equals(fichero)) {
                        nombreCorrecto = true;
                        break;
                    }
                }
                if (!nombreCorrecto) logger.log(Level.INFO, " El fichero indicado no existe.");
            }
        } else {
            System.out.print(frase);
            fichero = IN.nextLine();
        }
        return fichero;
    }

    /**
     * Recoge los nombres de las carpetas que se encuentran dentro de resources/ y las guarda en un array de tipo String.
     * Si el tamaño de la array es 0, significará que aun no se han creado archivos de jugadores.
     * @return si ya hay ficheros creados: true; si no hay ficheros: false.
     */
    private boolean mostrarDirectorios() {
        boolean hayDirectorios = true;
        try {
            File[] directorios = new File("resources/").listFiles(File::isDirectory);
            nombreFicheros = new String[directorios.length];
            for (int i = 0; i < directorios.length; i++) {
                nombreFicheros[i] = directorios[i].toString().split("\\\\")[1];
            }
            if (nombreFicheros.length != 0) {
                System.out.println("\n\nDe los siguientes ficheros...\n");
                for (String s :
                        nombreFicheros) {
                    System.out.println(" - " + s);
                }
            } else {
                System.out.println("Todavia no existen ficheros. Por favor, cree un nuevo fichero de jugadores usando " +
                        "la opción 1.");
                hayDirectorios = false;
            }
        } catch (NullPointerException npEx) {
            System.out.println("Todavia no existen ficheros. Por favor, cree un nuevo fichero de jugadores usando " +
                    "la opción 1.");
            hayDirectorios = false;
        }
        return hayDirectorios;
    }
}