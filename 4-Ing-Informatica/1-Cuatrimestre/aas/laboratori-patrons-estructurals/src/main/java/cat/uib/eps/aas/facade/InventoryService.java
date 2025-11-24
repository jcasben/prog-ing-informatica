
package cat.uib.eps.aas.facade;

import java.util.HashMap;
import java.util.Map;

public class InventoryService {
    private final Map<String, Integer> stock = new HashMap<>();
    private final Map<String, Integer> pricesCents = new HashMap<>();

    public InventoryService() {
        stock.put("P-001", 50);
        stock.put("P-002", 5);
        pricesCents.put("P-001", 2999);
        pricesCents.put("P-002", 12999);
    }

    public boolean hasStock(String sku, int qty) {
        return stock.getOrDefault(sku, 0) >= qty;
    }

    public void reserve(String sku, int qty) {
        stock.computeIfPresent(sku, (k, v) -> v - qty);
        System.out.println("[Inventory] Reservat " + qty + " de " + sku);
    }

    public void release(String sku, int qty) {
        stock.compute(sku, (k, v) -> (v == null ? 0 : v) + qty);
        System.out.println("[Inventory] Reversió reserva " + qty + " de " + sku);
    }

    public int priceCents(String sku) { return pricesCents.getOrDefault(sku, 0); }
}
