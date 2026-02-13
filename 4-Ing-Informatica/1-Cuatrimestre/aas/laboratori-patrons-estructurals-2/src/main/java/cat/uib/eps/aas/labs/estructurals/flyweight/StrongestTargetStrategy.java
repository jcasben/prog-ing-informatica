package cat.uib.eps.aas.labs.estructurals.flyweight;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Estrategia que selecciona el objetivo con más vida restante.
 * Útil para eliminar las amenazas más fuertes primero.
 */
public class StrongestTargetStrategy implements TargetSelectionStrategy<PlayingCard> {

    @Override
    public Optional<PlayingCard> select(PlayingCard attacker, List<PlayingCard> candidateTargets) {
        if (candidateTargets == null || candidateTargets.isEmpty()) {
            return Optional.empty();
        }

        return candidateTargets.stream()
                .max(Comparator.comparingInt(card -> card.getCard().getRemainingHitPoints(card)));
    }
}
