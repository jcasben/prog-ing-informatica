
package cat.uib.eps.aas.facade;

public interface PaymentGateway {
    boolean charge(String cardToken, int cents);
}
