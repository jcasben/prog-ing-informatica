
package cat.uib.eps.aas.facade;

/**
 * Demostració del patró Facade
 * Abans: Codi complex amb múltiples sub-sistemes
 * Després: Interfície simplificada amb OrderFacade
 */
public class MainFacadeDemo {

    public static void main(String[] args) {
        // Inicialitzar sub-sistemes
        Logger log = Logger.std();
        InventoryService inventory = new InventoryService();
        PaymentGateway payments = new DummyPaymentGateway();
        Notifier notifier = new ConsoleEmailNotifier();

        // Crear la Facade
        OrderFacade orderFacade = new OrderFacade(log, inventory, payments, notifier);

        System.out.println("=== Demostració del patró Facade ===\n");

        // EXEMPLE 1: Comanda exitosa
        System.out.println("--- Comanda 1: Exitosa ---");
        OrderRequest req1 = new OrderRequest("P-001", 5, "raimon.massanet@uib.cat", "123-456789-1011");
        OrderFacade.OrderResult result1 = orderFacade.createOrder(req1);
        System.out.println("Resultat: " + result1 + "\n");

        // EXEMPLE 2: Comanda amb stock insuficient
        System.out.println("--- Comanda 2: Stock insuficient ---");
        OrderRequest req2 = new OrderRequest("P-002", 100, "client2@uib.cat", "123-456789-2022");
        OrderFacade.OrderResult result2 = orderFacade.createOrder(req2);
        System.out.println("Resultat: " + result2 + "\n");

        // EXEMPLE 3: Comanda amb pagament rebutjat (import > 1000€)
        System.out.println("--- Comanda 3: Pagament rebutjat ---");
        OrderRequest req3 = new OrderRequest("P-002", 5, "client3@uib.cat", "123-456789-3033");
        OrderFacade.OrderResult result3 = orderFacade.createOrder(req3);
        System.out.println("Resultat: " + result3 + "\n");

        // EXEMPLE 4: Una altra comanda exitosa
        System.out.println("--- Comanda 4: Exitosa ---");
        OrderRequest req4 = new OrderRequest("P-001", 3, "client4@uib.cat", "123-456789-4044");
        OrderFacade.OrderResult result4 = orderFacade.createOrder(req4);
        System.out.println("Resultat: " + result4 + "\n");

        System.out.println("=== Fi de la demostració ===");
    }

}
