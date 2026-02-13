package cat.uib.eps.aas.labs.estructurals.flyweight;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * CardGame gestiona el flujo del juego de cartas.
 * Utiliza el patrón Strategy para la selección de objetivos,
 * cumpliendo con el principio Open/Closed (abierto a extensión, cerrado a modificación).
 */
public class CardGame {

    private final List<PlayingCard> playingCards;
    private final TargetSelectionStrategy<PlayingCard> targetSelectionStrategy;
    private int turnNumber = 0;

    public CardGame(List<PlayingCard> playingCards, TargetSelectionStrategy<PlayingCard> targetSelectionStrategy) {
        if (playingCards == null || targetSelectionStrategy == null) {
            throw new IllegalArgumentException("PlayingCards and strategy cannot be null");
        }
        this.playingCards = new ArrayList<>(playingCards);
        this.targetSelectionStrategy = targetSelectionStrategy;
    }

    /**
     * Ejecuta un turno completo del juego.
     * Todas las cartas vivas atacan en orden, seleccionando objetivos según la estrategia.
     * Al final del turno se eliminan las cartas muertas.
     */
    public void playTurn() {
        turnNumber++;
        System.out.println("\n=== Turn " + turnNumber + " ===");
        
        // 1. Obtener todas las cartas vivas que pueden atacar
        List<PlayingCard> liveCards = getLiveCards();
        
        // 2. Cada carta ataca en orden
        for (PlayingCard attacker : liveCards) {
            if (!attacker.isAlive()) {
                continue; // Puede haber muerto en este mismo turno
            }
            
            // 2.1 Seleccionar objetivo (cartas vivas del oponente)
            List<PlayingCard> possibleTargets = getPossibleTargets(attacker);
            
            if (possibleTargets.isEmpty()) {
                continue; // No hay objetivos válidos
            }
            
            Optional<PlayingCard> targetOpt = targetSelectionStrategy.select(attacker, possibleTargets);
              // 2.2 Atacar si se encontró un objetivo
            if (targetOpt.isPresent()) {
                PlayingCard target = targetOpt.get();
                System.out.printf("%s ataca a %s%n", attacker, target);
                attacker.getCard().attack(attacker, target);
                
                if (!target.isAlive()) {
                    System.out.printf("  -> ¡%s ha sido derrotado!%n", target);
                }
            }
        }
        
        // 3. Eliminar cartas muertas
        removeDeadCards();
        
        System.out.println("Cartas restantes: " + playingCards.size());
    }

    /**
     * Verifica si el juego ha terminado.
     * El juego termina cuando todas las cartas vivas pertenecen al mismo jugador.
     * 
     * @return true si el juego ha terminado, false en caso contrario
     */
    public boolean hasEnded() {
        List<PlayingCard> liveCards = getLiveCards();
        
        if (liveCards.isEmpty()) {
            return true; // No quedan cartas vivas
        }
        
        // Verificar si todas las cartas vivas pertenecen al mismo jugador
        Player firstPlayer = liveCards.get(0).getOwner();
        return liveCards.stream()
                .allMatch(card -> card.getOwner() == firstPlayer);
    }

    /**
     * Obtiene el jugador ganador si el juego ha terminado.
     * 
     * @return El jugador ganador, o null si no hay ganador
     */
    public Player getWinner() {
        if (!hasEnded()) {
            return null;
        }
        
        List<PlayingCard> liveCards = getLiveCards();
        if (liveCards.isEmpty()) {
            return null; // Empate (no deberían quedar 0 cartas)
        }
        
        return liveCards.get(0).getOwner();
    }

    // Métodos auxiliares privados (respetan el principio SRP)
    
    private List<PlayingCard> getLiveCards() {
        return playingCards.stream()
                .filter(PlayingCard::isAlive)
                .collect(Collectors.toList());
    }

    private List<PlayingCard> getPossibleTargets(PlayingCard attacker) {
        return playingCards.stream()
                .filter(PlayingCard::isAlive)
                .filter(card -> card.getOwner() != attacker.getOwner())
                .collect(Collectors.toList());
    }

    private void removeDeadCards() {
        playingCards.removeIf(card -> !card.isAlive());
    }

    public List<PlayingCard> getPlayingCards() {
        return new ArrayList<>(playingCards);
    }

}
