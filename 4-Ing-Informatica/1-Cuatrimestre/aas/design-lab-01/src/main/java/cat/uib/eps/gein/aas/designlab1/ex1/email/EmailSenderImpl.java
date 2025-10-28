package cat.uib.eps.gein.aas.designlab1.ex1.email;

public class EmailSenderImpl implements EmailSender {

    @Override
    public void send(String to, String message) {
        System.out.println("Sending email to " + to + ": " + message);
    }
}
