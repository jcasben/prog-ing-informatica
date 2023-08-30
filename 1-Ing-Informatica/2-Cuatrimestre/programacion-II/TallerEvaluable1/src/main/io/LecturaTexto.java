package main.io;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase encargada de leer de un fichero de texto implemetando un {@link BufferedReader}.
 * Implementa la interfaz {@link Closeable} para poder cerrar la lectura automaticamente.
 * @author jcasben
 */
public class LecturaTexto implements Closeable {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private BufferedReader reader;

    /**
     * Inicializa el {@link BufferedReader} con un {@link FileReader} en su interior. Este se inicializa con la ruta
     * del fichero.
     * @param nombreFichero ruta del fichero a leer.
     */
    public LecturaTexto(String nombreFichero) {
        try {
            reader = new BufferedReader(new FileReader(nombreFichero));
        } catch(IOException ioEx) {
            logger.log(Level.WARNING, "--> Error al abrir el fichero para la lectura de texto.");
        }
    }

    /**
     * Metodo para leer una linea del fichero de texto
     * @return la linea leída del fichero.
     */
    public String leerLinea() {
        String tmp = "";
        try{
            tmp=  reader.readLine();
        } catch (IOException ioEx) {
            logger.log(Level.WARNING,"--> Error al leer del fichero de texto.");
        }
        return tmp;
    }

    /**
     * {@link Override} del método close() aplicado a nuestra clase.
     */
    @Override
    public void close() {
        try {
            reader.close();
        } catch(IOException ioEx1) {
            logger.log(Level.WARNING, "--> Error al cerrar el fichero de lectura de texto.");
        }
    }
}
