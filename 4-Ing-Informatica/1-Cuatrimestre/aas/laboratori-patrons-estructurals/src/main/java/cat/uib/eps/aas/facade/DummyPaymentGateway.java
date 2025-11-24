package cat.uib.eps.aas.facade;

public class DummyPaymentGateway implements PaymentGateway {
    @Override public boolean charge(String cardToken, int cents) {
        // Simulació: rebutja si import > 1000€
        boolean ok = cents <= 100_000;
        System.out.println("[Payment] token=" + cardToken + " amount=" + (cents/100.0) + "€ -> " + ok);
        return ok;
    }
}
