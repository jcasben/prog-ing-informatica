package cat.uib.eps.aas.decorator.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.GZIPOutputStream;

public class StringCompressor {

    public String getCompressed(String string) {
        try (var bout = new ByteArrayOutputStream(); var gzip = new GZIPOutputStream(bout)) {
            gzip.write(string.getBytes());
            gzip.finish();
            return Base64.getEncoder().encodeToString(bout.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}