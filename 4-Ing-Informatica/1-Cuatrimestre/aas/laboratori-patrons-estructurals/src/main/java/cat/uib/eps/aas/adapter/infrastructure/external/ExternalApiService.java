
package cat.uib.eps.aas.adapter.infrastructure.external;

import cat.uib.eps.aas.adapter.domain.Product;
import cat.uib.eps.aas.adapter.domain.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** Simulació d'una API remota asíncrona. */
public class ExternalApiService {

    private static final ExecutorService EXEC = Executors.newFixedThreadPool(2);

    private static final List<ExternalApiProduct> PRODUCTS = new ArrayList<>(
            List.of(
                    new ExternalApiProduct("P-001", "USB-C Cable", "pieces", 100),
                    new ExternalApiProduct("P-002", "SSD 1TB", "pieces", 40),
                    new ExternalApiProduct("EXT-777", "External HDD 2TB", "pieces", 25)
            )
    );

    private static final Random RND = new Random(System.currentTimeMillis());

    public CompletableFuture<List<ExternalApiProduct>> fetchProductsAsync() {
        return CompletableFuture.supplyAsync(() -> {
            latency();
            return PRODUCTS.stream().toList();
        }, EXEC);
    }

    public CompletableFuture<ExternalApiProduct> fetchByIdAsync(String sku) {
        return CompletableFuture.supplyAsync(() -> {
            latency();
            return PRODUCTS.stream()
                    .filter(product -> sku.equals(product.sku()))
                    .findAny()
                    .orElse(null);
        }, EXEC);
    }

    public CompletableFuture<Boolean> save(ExternalApiProduct externalApiProduct) {
        return CompletableFuture.supplyAsync(() -> {
            latency();
            if(PRODUCTS.contains(externalApiProduct)) {
                return false;
            }
            PRODUCTS.add(externalApiProduct);
            return true;
        });
    }

    private static void latency() {
        sleep(RND.nextInt(100, 1000));
    }

    private static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }

}
