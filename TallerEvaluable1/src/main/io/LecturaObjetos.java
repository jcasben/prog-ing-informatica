package main.io;

import main.elements.Jugador;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase nos permite leer de ficheros que almacenan objetos implementando la clase ObjectOutputStream.
 * Implementa la interfaz Closeable para que se pueda cerrar automaticamente al usar un try with resources.
 * @author jcasben
 */
public class LecturaObjetos implements Closeable {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private ObjectInputStream ois;

    /**
     * Inicializa un {@link java.io.ObjectOutputStream} con un {@link java.io.FileOutputStream}.
     * El {@link java.io.FileOutputStream} está inicializado con el fichero del cual tiene que leer.
     * @param nombreFichero ruta hacia el fichero del cual tiene que leer.
     */
    public LecturaObjetos(String nombreFichero) {
        try {
            ois = new ObjectInputStream(new FileInputStream(nombreFichero));
        } catch (IOException ioEx) {
            logger.log(Level.WARNING, "Error al abrir la lectura del fichero.");
        }
    }

    /**
     * Método para leer objetos del fichero abierto por el constructor.
     * @return el objeto de tipo jugador leido del fichero.
     */
    public Jugador leer() {
        Jugador tmp = new Jugador();
        try {
            tmp = (Jugador) ois.readObject();
        } catch (ClassNotFoundException | IOException ioEx) {
            logger.log(Level.WARNING,"Error al leer el objeto.");
        }
        return tmp;
    }

    /**
     * {@link Override} del método close() aplicado a nuestra clase.
     */
    @Override
    public void close() {
        try {
            ois.close();
        } catch (IOException ioEx) {
            logger.log(Level.WARNING, "Error al cerrar la lectura del fichero.");
        }
    }
}
