package cat.uib.eps.aas.adapter.domain;

public record Product (
    String id,
    String name,
    Unit unit,
    double quantity
){}
