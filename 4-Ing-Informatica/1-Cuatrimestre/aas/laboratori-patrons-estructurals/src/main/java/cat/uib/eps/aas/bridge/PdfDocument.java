package cat.uib.eps.aas.bridge;

/**
 * Abstracció refinada - Document PDF
 */
public class PdfDocument extends Document {

    public PdfDocument(String text, DocumentPersistence persistence) {
        super(text, persistence);
    }

    @Override
    protected byte[] toBinary() {
        // Simulació de conversió a format PDF
        String pdfContent = "%PDF-1.4\n" +
                "1 0 obj<</Type/Catalog/Pages 2 0 R>>endobj\n" +
                "2 0 obj<</Type/Pages/Count 1/Kids[3 0 R]>>endobj\n" +
                "3 0 obj<</Type/Page/Contents 4 0 R>>endobj\n" +
                "4 0 obj<</Length " + text.length() + ">>stream\n" +
                text + "\nendstream\nendobj\n" +
                "xref\n0 5\ntrailer<</Size 5/Root 1 0 R>>\nstartxref\n%%EOF";
        return pdfContent.getBytes();
    }
}
