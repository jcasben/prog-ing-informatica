package cat.uib.eps.aas.labs.estructurals.flyweight;

import java.util.List;
import java.util.Optional;

/**
 * Estrategia para seleccionar el objetivo de un ataque.
 * Este patrón Strategy permite cambiar el algoritmo de selección de objetivos
 * sin modificar la lógica del juego (cumple OCP - Open/Closed Principle).
 * 
 * @param <T> El tipo de elemento que puede ser objetivo
 */
public interface TargetSelectionStrategy<T> {

    /**
     * Selecciona un objetivo de ataque de entre los candidatos disponibles.
     * 
     * @param attacker El atacante
     * @param candidateTargets Lista de posibles objetivos
     * @return El objetivo seleccionado, o Optional.empty() si no hay objetivos válidos
     */
    Optional<T> select(T attacker, List<T> candidateTargets);

}
