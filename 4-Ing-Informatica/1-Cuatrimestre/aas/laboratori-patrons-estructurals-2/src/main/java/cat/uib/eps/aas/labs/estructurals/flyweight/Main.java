package cat.uib.eps.aas.labs.estructurals.flyweight;

import java.util.ArrayList;
import java.util.List;

/**
 * Main demuestra el patrón Flyweight en acción.
 * 
 * Las instancias de Card son compartidas (Flyweight) a través de CardFactory,
 * mientras que cada PlayingCard tiene su propio estado extrínseco (propietario, daño).
 * 
 * Esto minimiza el uso de memoria al compartir el estado intrínseco pesado
 * (nombre, ataque, hitPoints, armor, data[1024]).
 */
public class Main {
    public static void main(String[] args) {
        CardFactory cardFactory = new CardFactory();

        // Crear cartas en juego - Notemos que las instancias de Card son compartidas
        // pero cada PlayingCard tiene su propio estado (owner, damageTaken)
        List<PlayingCard> playingCards = new ArrayList<>();
        
        // Jugador 1 tiene: Dragon, Dwarf, Hobbit
        playingCards.add(new PlayingCard(cardFactory.getCard("Dragon"), Player.PLAYER1));
        playingCards.add(new PlayingCard(cardFactory.getCard("Dwarf"), Player.PLAYER1));
        playingCards.add(new PlayingCard(cardFactory.getCard("Hobbit"), Player.PLAYER1));
        
        // Jugador 2 tiene: Dragon, Dwarf, Dwarf
        playingCards.add(new PlayingCard(cardFactory.getCard("Dragon"), Player.PLAYER2));
        playingCards.add(new PlayingCard(cardFactory.getCard("Dwarf"), Player.PLAYER2));
        playingCards.add(new PlayingCard(cardFactory.getCard("Dwarf"), Player.PLAYER2));
        
        // Demostrar que las instancias de Card son compartidas (Flyweight)
        System.out.println("=== Demostración del Patrón Flyweight ===");
        Card dragon1 = cardFactory.getCard("Dragon");
        Card dragon2 = cardFactory.getCard("Dragon");
        System.out.println("¿Misma instancia de Dragon? " + (dragon1 == dragon2)); // true
        System.out.println("Total de instancias Card en el pool: 3 (Dragon, Dwarf, Hobbit)");
        System.out.println("Total de instancias PlayingCard: " + playingCards.size());
        System.out.println();
        
        // Crear el juego con estrategia de selección (atacar al más débil)
        TargetSelectionStrategy<PlayingCard> strategy = new WeakestTargetStrategy();
        CardGame game = new CardGame(playingCards, strategy);
        
        System.out.println("=== Estado Inicial ===");
        printGameState(game);
        
        // Ejecutar el juego
        while (!game.hasEnded()) {
            game.playTurn();
            
            // Evitar bucles infinitos (por si acaso)
            if (game.getPlayingCards().isEmpty()) {
                break;
            }
        }
        
        // Mostrar resultado final
        System.out.println("\n=== Fin del Juego ===");
        Player winner = game.getWinner();
        if (winner != null) {
            System.out.println("Ganador: " + winner);
        } else {
            System.out.println("¡Es un empate!");
        }
        
        printGameState(game);
    }
    
    private static void printGameState(CardGame game) {
        List<PlayingCard> cards = game.getPlayingCards();
        System.out.println("Cartas en juego:");
        for (PlayingCard card : cards) {
            System.out.println("  " + card);
        }
    }
}
