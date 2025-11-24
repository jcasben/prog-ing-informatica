
package cat.uib.eps.aas.decorator;

public class EmailNotification implements NotificationService {

    @Override
    public void send(Message message) {
        // Fake e-mail sending
        System.out.println("--- [EMAIL] ---");
        System.out.println("To: " + message.to());
        System.out.println("Subject: " + message.subject());
        System.out.println("Body: " + message.body());
        System.out.println("---------------");
    }
}
