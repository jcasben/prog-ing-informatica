package cat.uib.eps.aas.decorator.wrapper;

import cat.uib.eps.aas.decorator.Message;
import cat.uib.eps.aas.decorator.NotificationService;
import cat.uib.eps.aas.decorator.utils.StringCompressor;

public class CompressionDecorator extends NotificationDecorator {

    public CompressionDecorator(NotificationService wrappee) {
        super(wrappee);
    }

    @Override
    public void send(Message message) {
        String compressed = new StringCompressor().getCompressed(message.body());
        message = message.withBody(compressed);
        super.send(message);
    }
}
