package cat.uib.eps.aas.labs.estructurals.flyweight;

import java.util.ArrayList;
import java.util.List;

public class CardGame {

    private final List<?> playingCards;

    private final TargetSelectionStrategy<?> targetSelectionStrategy;

    public CardGame(List<?> playingCards, TargetSelectionStrategy<?> targetSelectionStrategy) {
        this.playingCards = new ArrayList<>(playingCards);
        this.targetSelectionStrategy = targetSelectionStrategy;
    }

    public void playTurn() {
        // In each turn:
        // 1 - Select attacker (all live cards must attack, in the order defined by the list)
        // 2 - For each attacker
        //  2.1 - Select target (use a target selection strategy)
        //  2.2 - attacker attacks target
        // 3 - Remove dead cards
    }

    public boolean hasEnded() {
        return false;
    }

}
