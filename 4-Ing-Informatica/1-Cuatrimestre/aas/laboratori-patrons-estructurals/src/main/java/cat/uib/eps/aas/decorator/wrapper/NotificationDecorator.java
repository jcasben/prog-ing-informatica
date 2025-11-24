package cat.uib.eps.aas.decorator.wrapper;

import cat.uib.eps.aas.decorator.Message;
import cat.uib.eps.aas.decorator.NotificationService;

public abstract class NotificationDecorator implements NotificationService {

    protected final NotificationService wrappee;

    public NotificationDecorator(NotificationService wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public void send(Message message) {
        this.wrappee.send(message);
    }
}
