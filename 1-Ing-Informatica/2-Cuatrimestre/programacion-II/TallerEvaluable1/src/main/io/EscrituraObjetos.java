package main.io;

import main.elements.Jugador;

import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase destinada a la escritura de objetos en ficheros secuenciales implementando un {@link ObjectOutputStream}.
 * Implementa la interfaz {@link Closeable} para permitir que se pueda cerrar el fichero automaticamente usando
 * unn try with resources.
 * @author jcasben
 */
public class EscrituraObjetos implements Closeable {
    private ObjectOutputStream oos;
    //La función quen tiene el Logger es la de mostrar avisos al usuario por consola, clasificados según la
    //importancia que tengan.
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Inicializa un {@link ObjectOutputStream} con un {@link FileOutputStream}. El {@link FileOutputStream} recibe como
     * parametro la ruta del fichero sobre el cual se escribirá.
     * @param nombreFichero ruta del fichero a escribir.
     */
    public EscrituraObjetos(String nombreFichero) {
        try {
            oos = new ObjectOutputStream(new FileOutputStream(nombreFichero, false));
        } catch (IOException ioEx) {
            logger.log(Level.WARNING,"--> Error al abrir el fichero de objetos " + nombreFichero);
        }
    }

    /**
     * Método que sirve para escribir objetos de tipo jugador en el fichero especificado en el constructor.
     * @param jugador el objeto de tipo jugador a escribir en el fichero.
     */
    public void escribir(Jugador jugador) {
        try{
            oos.writeObject(jugador);
        } catch(IOException ioEx) {
            ioEx.printStackTrace();
            logger.log(Level.WARNING,"--> Error al escribir en el fichero de objetos.");
        }
    }

    /**
     * {@link Override} del método close() aplicado a nuestra clase.
     */
    @Override
    public void close() {
        try{
            oos.close();
        } catch(IOException ioEx) {
            logger.log(Level.WARNING, "--> Error al cerrar el fichero de objetos.");
        }
    }
}
