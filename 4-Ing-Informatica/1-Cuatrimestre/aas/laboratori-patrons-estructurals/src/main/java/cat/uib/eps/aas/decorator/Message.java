
package cat.uib.eps.aas.decorator;

public record Message (
    String to,
    String subject,
    String body
) {
    public Message withBody(String newBody) {
        return new Message(to, subject, newBody);
    }
}
