package cat.uib.eps.aas.labs.estructurals.flyweight;

import java.util.Map;

public class CardFactory {

    private final Map<String, Card> cardPool = Map.of(
            "Dragon", new Card("Dragon", 6, 10, 2),
            "Dwarf", new Card("Dwarf", 3, 4, 1),
            "Hobbit", new Card("Hobbit", 1, 2, 0)
    );

    public Card getCard(String name) {
        if(!cardPool.containsKey(name)) {
            throw new InvalidCardException(name);
        }
        return cardPool.get(name);
    }

}
