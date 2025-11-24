
package cat.uib.eps.aas.adapter;

import cat.uib.eps.aas.adapter.domain.ProductRepository;
import cat.uib.eps.aas.adapter.infrastructure.RepositoryAdapter;
import cat.uib.eps.aas.adapter.infrastructure.external.ExternalApiService;
import cat.uib.eps.aas.adapter.infrastructure.legacy.InMemoryProductRepository;

public class MainAdapterDemo {
    public static void main(String[] args) {
        ProductRepository legacy = new RepositoryAdapter(new ExternalApiService());
        System.out.println("[Legacy] " + legacy.findAll());
        System.out.println("[Legacy] " + legacy.findById("P-001"));
    }
}
