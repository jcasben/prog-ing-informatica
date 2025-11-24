package cat.uib.eps.aas.bridge;

/**
 * Implementació concreta de persistència en base de dades
 */
public class DatabasePersistence implements DocumentPersistence {
    
    @Override
    public void save(String name, byte[] content) {
        // Simulació de guardar en BBDD
        System.out.println("[DB] Guardat document '" + name + "' a la base de dades (" + content.length + " bytes)");
    }
}
