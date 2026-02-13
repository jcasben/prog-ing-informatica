package cat.uib.eps.aas.labs.estructurals.flyweight;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Estrategia que selecciona el objetivo con menos vida restante.
 * Esta estrategia implementa el patrón Strategy, permitiendo intercambiar
 * algoritmos de selección fácilmente (Open/Closed Principle).
 */
public class WeakestTargetStrategy implements TargetSelectionStrategy<PlayingCard> {

    @Override
    public Optional<PlayingCard> select(PlayingCard attacker, List<PlayingCard> candidateTargets) {
        if (candidateTargets == null || candidateTargets.isEmpty()) {
            return Optional.empty();
        }

        return candidateTargets.stream()
                .min(Comparator.comparingInt(card -> card.getCard().getRemainingHitPoints(card)));
    }
}
