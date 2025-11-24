package cat.uib.eps.aas.bridge;

/**
 * Abstracció refinada - Document Word
 */
public class WordDocument extends Document {

    public WordDocument(String text, DocumentPersistence persistence) {
        super(text, persistence);
    }

    @Override
    protected byte[] toBinary() {
        // Simulació de conversió a format Word (simplificat)
        String wordContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<w:document xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\">\n" +
                "  <w:body>\n" +
                "    <w:p><w:r><w:t>" + text + "</w:t></w:r></w:p>\n" +
                "  </w:body>\n" +
                "</w:document>";
        return wordContent.getBytes();
    }
}
