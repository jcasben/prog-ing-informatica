package main.utils;

import main.elements.Jugador;
import main.io.LecturaObjetos;

/**
 * Clase destinada a mostrar por consola el contenido de los ficheros de objetos.
 * @author jcasben
 */
public class ImpresorFicheros {
    private final String nombreFichero;

    /**
     * Constructor que inicializa la variable de clase nombreFichero con el contenido del parametro.
     * @param nombreFichero ruta que indica el fichero deseado.
     */
    public ImpresorFicheros(String nombreFichero) {
        this.nombreFichero = nombreFichero;
    }

    /**
     * Metodo que se sirve de {@link LecturaObjetos} para leer el contenido del fichero indicado en el constructor
     * y mostrarlo por consola al mismo tiempo.
     */
    public void imprimir() {
        int i = 0;
        try(LecturaObjetos lector = new LecturaObjetos(nombreFichero)) {
            Jugador tmp = lector.leer();
            while(!tmp.esCentinela()) {
                i++;
                System.out.println(i+ " - " + tmp);
                tmp = lector.leer();
            }
        }
    }
}
