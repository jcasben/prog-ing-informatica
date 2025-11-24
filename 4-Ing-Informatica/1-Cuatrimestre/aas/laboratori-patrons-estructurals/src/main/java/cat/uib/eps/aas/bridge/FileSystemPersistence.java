package cat.uib.eps.aas.bridge;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Implementació concreta de persistència en sistema de fitxers
 */
@RequiredArgsConstructor
public class FileSystemPersistence implements DocumentPersistence {
    private final Path basePath;

    @Override
    public void save(String name, byte[] content) throws IOException {
        Files.createDirectories(basePath);
        Files.write(basePath.resolve(name), content);
        System.out.println("[FS] Guardat " + basePath.resolve(name));
    }
}
