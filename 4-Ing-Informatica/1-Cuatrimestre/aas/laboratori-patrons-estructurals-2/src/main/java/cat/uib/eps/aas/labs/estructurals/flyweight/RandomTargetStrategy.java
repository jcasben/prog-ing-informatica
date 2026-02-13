package cat.uib.eps.aas.labs.estructurals.flyweight;

import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Estrategia que selecciona un objetivo aleatorio.
 * Introduce variabilidad en el juego.
 */
public class RandomTargetStrategy implements TargetSelectionStrategy<PlayingCard> {

    private final Random random;

    public RandomTargetStrategy() {
        this.random = new Random();
    }

    public RandomTargetStrategy(long seed) {
        this.random = new Random(seed);
    }

    @Override
    public Optional<PlayingCard> select(PlayingCard attacker, List<PlayingCard> candidateTargets) {
        if (candidateTargets == null || candidateTargets.isEmpty()) {
            return Optional.empty();
        }

        int index = random.nextInt(candidateTargets.size());
        return Optional.of(candidateTargets.get(index));
    }
}
