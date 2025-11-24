
package cat.uib.eps.aas.bridge;

import java.nio.file.Path;

/**
 * Demostració del patró Bridge
 * Permet combinar qualsevol tipus de document amb qualsevol tipus de persistència
 */
public class MainBridgeDemo {
    public static void main(String[] args) throws Exception {
        // Crear les implementacions de persistència
        DocumentPersistence fsPersistence = new FileSystemPersistence(Path.of(System.getProperty("user.dir")));
        DocumentPersistence dbPersistence = new DatabasePersistence();

        System.out.println("=== Demostració del patró Bridge ===\n");

        // PDF amb sistema de fitxers
        System.out.println("1. PDF Document + File System:");
        Document pdfFs = new PdfDocument("Informe trimestral Q4", fsPersistence);
        pdfFs.save("informe_trimestral.pdf");

        System.out.println("\n2. PDF Document + Database:");
        Document pdfDb = new PdfDocument("Informe anual 2024", dbPersistence);
        pdfDb.save("informe_anual.pdf");

        // Word amb sistema de fitxers
        System.out.println("\n3. Word Document + File System:");
        Document wordFs = new WordDocument("Reunió d'equip - Notes", fsPersistence);
        wordFs.save("notes_reunio.docx");

        // Word amb base de dades
        System.out.println("\n4. Word Document + Database:");
        Document wordDb = new WordDocument("Política de la companyia", dbPersistence);
        wordDb.save("politica_companyia.docx");

        System.out.println("\n=== Fi de la demostració ===");
    }
}
