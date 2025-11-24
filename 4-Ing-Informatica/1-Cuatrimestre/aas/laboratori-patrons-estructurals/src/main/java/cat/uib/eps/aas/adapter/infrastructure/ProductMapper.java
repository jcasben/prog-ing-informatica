package cat.uib.eps.aas.adapter.infrastructure;

import cat.uib.eps.aas.adapter.domain.Product;
import cat.uib.eps.aas.adapter.domain.Unit;
import cat.uib.eps.aas.adapter.infrastructure.external.ExternalApiProduct;

public class ProductMapper {

    static Product toDomain(ExternalApiProduct product) {
        return new Product(
                product.sku(),
                product.title(),
                switch (product.uom()) {
                    case "pieces" -> Unit.PCS;
                    case "kilogram" -> Unit.KG;
                    default -> throw new IllegalStateException("Unexpected value: " + product.uom());
                },
                product.qty()
        );
    }

    static ExternalApiProduct toExternal(Product product) {
        return new ExternalApiProduct(
                product.id(),
                product.name(),
                switch (product.unit()) {
                    case PCS -> "pieces";
                    case KG -> "kilogram";
                },
                product.quantity()
        );
    }
}
