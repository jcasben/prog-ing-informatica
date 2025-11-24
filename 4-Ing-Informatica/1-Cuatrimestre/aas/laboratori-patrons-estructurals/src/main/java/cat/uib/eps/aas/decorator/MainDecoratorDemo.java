
package cat.uib.eps.aas.decorator;

import cat.uib.eps.aas.decorator.utils.StringCompressor;
import cat.uib.eps.aas.decorator.utils.StringEncryptor;
import cat.uib.eps.aas.decorator.wrapper.CompressionDecorator;
import cat.uib.eps.aas.decorator.wrapper.EncryptionDecorator;

public class MainDecoratorDemo {
    public static void main(String[] args) {
        NotificationService base = new EmailNotification();

        System.out.println("--- Compressed Email ---");
        NotificationService compressor = new CompressionDecorator(base);
        compressor.send(new Message("raimon.massanet@uib.cat", "Greetings", "Hello, world!"));

        System.out.println("--- Encrypted Email ---");
        NotificationService encryptor = new EncryptionDecorator(base);
        encryptor.send(new Message("raimon.massanet@uib.cat", "Greetings", "Hello, world!"));

        System.out.println("--- Encrypted and Compressed Email ---");
        NotificationService encryptedAndCompressed = new CompressionDecorator(new EncryptionDecorator(base));
        encryptedAndCompressed.send(new Message("raimon.massanet@uib.cat", "Greetings", "Hello, world!"));

        System.out.println("--- Compressed and Encrypted Email ---");
        NotificationService compressedAndEncrypted = new CompressionDecorator(new EncryptionDecorator(base));
        encryptedAndCompressed.send(new Message("raimon.massanet@uib.cat", "Greetings", "Hello, world!"));
    }
}
