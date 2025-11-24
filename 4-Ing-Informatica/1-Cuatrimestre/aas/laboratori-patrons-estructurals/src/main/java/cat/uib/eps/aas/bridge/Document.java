
package cat.uib.eps.aas.bridge;

import lombok.RequiredArgsConstructor;

/**
 * Patró Bridge - Abstracció base de Document
 * Delega la persistència a DocumentPersistence
 */
@RequiredArgsConstructor
public abstract class Document {

    protected final String text;
    protected final DocumentPersistence persistence;

    /**
     * Converteix el document a format binari
     */
    protected abstract byte[] toBinary();

    /**
     * Guarda el document utilitzant la implementació de persistència
     */
    public void save(String name) throws Exception {
        byte[] content = toBinary();
        persistence.save(name, content);
    }

}
