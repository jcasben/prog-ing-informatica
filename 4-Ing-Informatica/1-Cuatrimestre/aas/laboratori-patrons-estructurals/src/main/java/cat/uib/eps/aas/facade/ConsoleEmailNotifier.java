package cat.uib.eps.aas.facade;

public class ConsoleEmailNotifier implements Notifier {
    @Override public void sendEmail(String to, String subject, String body) {
        System.out.println("[Email] To=" + to + " | " + subject + " " + body);
    }
}
