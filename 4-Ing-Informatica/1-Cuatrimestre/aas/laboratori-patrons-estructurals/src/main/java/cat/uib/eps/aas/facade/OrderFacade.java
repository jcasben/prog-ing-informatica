package cat.uib.eps.aas.facade;

import java.util.UUID;

/**
 * Patró Facade - Simplifica la interfície per crear comandes
 * Encapsula tota la lògica complexa dels sub-sistemes
 */
public class OrderFacade {

    private final Logger log;
    private final InventoryService inventory;
    private final PaymentGateway payments;
    private final Notifier notifier;

    public OrderFacade(Logger log, InventoryService inventory, PaymentGateway payments, Notifier notifier) {
        this.log = log;
        this.inventory = inventory;
        this.payments = payments;
        this.notifier = notifier;
    }

    /**
     * Crea una comanda de forma simplificada
     * Gestiona automàticament: validació stock, reserva, pagament i notificació
     * 
     * @param request Dades de la comanda
     * @return OrderResult amb l'estat de la comanda
     */
    public OrderResult createOrder(OrderRequest request) {
        String orderId = UUID.randomUUID().toString();
        log.info("Nova ordre " + orderId + " per SKU=" + request.sku() + ", qty=" + request.quantity());

        // 1. Validar stock
        if (!inventory.hasStock(request.sku(), request.quantity())) {
            String msg = "Stock insuficient per " + request.sku();
            log.info(msg);
            return new OrderResult(orderId, false, msg);
        }

        // 2. Reservar stock (evitar over-selling)
        inventory.reserve(request.sku(), request.quantity());

        // 3. Processar pagament
        int totalCents = inventory.priceCents(request.sku()) * request.quantity();
        boolean charged = payments.charge(request.cardToken(), totalCents);
        
        if (!charged) {
            // Pagament fallit - revertir reserva
            inventory.release(request.sku(), request.quantity());
            String msg = "Pagament rebutjat";
            log.info(msg);
            return new OrderResult(orderId, false, msg);
        }

        // 4. Enviar notificació
        notifier.sendEmail(
            request.email(), 
            "Confirmació comanda " + orderId,
            "Hem rebut la teva comanda de " + request.quantity() + " unitats de " + request.sku() +
                    ". Import: " + (totalCents / 100.0) + "€"
        );

        log.info("Ordre " + orderId + " confirmada");
        return new OrderResult(orderId, true, "Comanda processada correctament");
    }

    /**
     * Resultat de la creació d'una comanda
     */
    public record OrderResult(String orderId, boolean success, String message) {}
}
