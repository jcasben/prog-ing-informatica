
package cat.uib.eps.aas.adapter.infrastructure.external;

/** DTO que simula la resposta JSON d'una API externa. */
public record ExternalApiProduct (
    String sku,
    String title,
    String uom, // Unit of measure, pot ser: "pieces" o "kilogram"
    double qty
) {}
