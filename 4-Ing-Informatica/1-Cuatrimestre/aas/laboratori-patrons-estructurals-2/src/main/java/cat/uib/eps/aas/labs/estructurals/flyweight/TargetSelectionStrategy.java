package cat.uib.eps.aas.labs.estructurals.flyweight;

import java.util.List;
import java.util.Optional;

public interface TargetSelectionStrategy<T> {

    Optional<T> select(T attacker, List<T> candidateTargets);

}
