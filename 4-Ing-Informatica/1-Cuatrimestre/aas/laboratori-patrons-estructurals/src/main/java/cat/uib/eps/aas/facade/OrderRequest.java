
package cat.uib.eps.aas.facade;

public record OrderRequest(
        String sku,
        int quantity,
        String email,
        String cardToken
) {}
