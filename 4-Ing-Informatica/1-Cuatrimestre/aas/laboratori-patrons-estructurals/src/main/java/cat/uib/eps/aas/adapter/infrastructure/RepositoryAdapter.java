package cat.uib.eps.aas.adapter.infrastructure;

import cat.uib.eps.aas.adapter.domain.Product;
import cat.uib.eps.aas.adapter.domain.ProductRepository;
import cat.uib.eps.aas.adapter.infrastructure.external.ExternalApiProduct;
import cat.uib.eps.aas.adapter.infrastructure.external.ExternalApiService;

import java.util.List;
import java.util.Optional;

public class RepositoryAdapter implements ProductRepository {

    private final ExternalApiService externalApiService;

    public RepositoryAdapter(ExternalApiService externalApiService) {
        this.externalApiService = externalApiService;
    }

    @Override
    public Optional<Product> findById(String id) {
        ExternalApiProduct product = externalApiService.fetchByIdAsync(id).join();
        if (product == null) {
            return Optional.empty();
        }

        return Optional.of(ProductMapper.toDomain(product));
    }

    @Override
    public List<Product> findAll() {
        return this.externalApiService
                .fetchProductsAsync()
                .join()
                .stream()
                .map(ProductMapper::toDomain)
                .toList();
    }

    @Override
    public void save(Product product) {
        this.externalApiService.save(ProductMapper.toExternal(product)).join();
    }
}
