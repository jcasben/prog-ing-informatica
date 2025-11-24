package cat.uib.eps.aas.labs.estructurals.proxy;

public class Main {
    public static void main(String[] args) {
        Notification notification = new ThrottlingNotificationProxy(new RealNotification(), 5);
        notification.send("Hello 1");
        notification.send("Hello 2");
        notification.send("Hello 3");
        notification.send("Hello 4");
        notification.send("Hello 5");
        notification.send("Hello 6");
        notification.send("Hello 7");
        notification.send("Hello 8");
    }
}
