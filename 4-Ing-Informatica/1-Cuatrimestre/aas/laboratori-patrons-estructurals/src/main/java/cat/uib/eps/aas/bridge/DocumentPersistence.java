package cat.uib.eps.aas.bridge;

/**
 * Implementació del patró Bridge - Interface de persistència
 */
public interface DocumentPersistence {
    void save(String name, byte[] content) throws Exception;
}
