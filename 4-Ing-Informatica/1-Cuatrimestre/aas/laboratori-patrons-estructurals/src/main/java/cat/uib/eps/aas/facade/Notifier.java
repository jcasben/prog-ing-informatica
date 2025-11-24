
package cat.uib.eps.aas.facade;

public interface Notifier {
    void sendEmail(String to, String subject, String body);
}
