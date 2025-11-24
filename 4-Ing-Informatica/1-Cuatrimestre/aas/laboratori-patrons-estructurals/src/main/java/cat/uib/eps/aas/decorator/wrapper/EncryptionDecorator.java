package cat.uib.eps.aas.decorator.wrapper;

import cat.uib.eps.aas.decorator.Message;
import cat.uib.eps.aas.decorator.NotificationService;
import cat.uib.eps.aas.decorator.utils.StringEncryptor;

public class EncryptionDecorator extends NotificationDecorator {

    public EncryptionDecorator(NotificationService wrappee) {
        super(wrappee);
    }

    @Override
    public void send(Message message) {
        String encrypted = new StringEncryptor().getEncrypted(message.body());
        message = message.withBody(encrypted);
        super.send(message);
    }
}
