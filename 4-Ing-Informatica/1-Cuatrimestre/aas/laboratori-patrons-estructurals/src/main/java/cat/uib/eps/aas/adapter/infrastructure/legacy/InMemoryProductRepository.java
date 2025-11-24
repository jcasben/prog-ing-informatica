
package cat.uib.eps.aas.adapter.infrastructure.legacy;

import cat.uib.eps.aas.adapter.domain.Product;
import cat.uib.eps.aas.adapter.domain.ProductRepository;
import cat.uib.eps.aas.adapter.domain.Unit;

import java.util.*;

public class InMemoryProductRepository implements ProductRepository {
    private final Map<String, Product> data = new HashMap<>();

    public InMemoryProductRepository() {
        save(new Product("P-001", "USB-C Cable", Unit.PCS, 120));
        save(new Product("P-002", "SSD 1TB", Unit.PCS, 40));
    }

    @Override public Optional<Product> findById(String id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override public List<Product> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override public void save(Product product) {
        data.put(product.id(), product);
    }
}
