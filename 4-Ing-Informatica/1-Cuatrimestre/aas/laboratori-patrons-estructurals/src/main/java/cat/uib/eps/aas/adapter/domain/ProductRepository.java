package cat.uib.eps.aas.adapter.domain;

import java.util.List;
import java.util.Optional;

/** Contracte establert pels clients interns. No es pot canviar. */
public interface ProductRepository {
    Optional<Product> findById(String id);
    List<Product> findAll();
    void save(Product product);
}
