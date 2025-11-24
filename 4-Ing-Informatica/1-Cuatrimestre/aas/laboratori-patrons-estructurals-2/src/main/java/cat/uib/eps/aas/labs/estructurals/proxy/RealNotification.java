package cat.uib.eps.aas.labs.estructurals.proxy;

public class RealNotification implements Notification {

    @Override
    public void send(String message) {
        System.out.println("Sending notification: " + message);
    }

}
