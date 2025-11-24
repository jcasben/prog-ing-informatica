package cat.uib.eps.aas.labs.estructurals.proxy;

import java.util.LinkedList;
import java.util.Queue;

public class ThrottlingNotificationProxy implements Notification {

    private final Notification realNotification;
    private final Queue<String> notifications = new LinkedList<>();
    private final int threshold;

    public ThrottlingNotificationProxy(Notification realNotification, int threshold) {
        this.realNotification = realNotification;
        this.threshold = threshold;
    }

    @Override
    public void send(String message) {
        notifications.add(message);
        System.out.println("Queued message: " + message + " (queue size: " + notifications.size() + ")");

        if (notifications.size() >= threshold) {
            flushQueue();
        }
    }

    private void flushQueue() {
        System.out.println("\nThreshold reached. Flushing notifications.");

        while (!notifications.isEmpty()) {
            String message = notifications.poll();
            realNotification.send(message);
        }

        System.out.println();
    }
}
